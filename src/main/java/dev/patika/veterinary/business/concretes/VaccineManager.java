package dev.patika.veterinary.business.concretes;

import dev.patika.veterinary.business.abstracts.IVaccineService;
import dev.patika.veterinary.core.exception.ConflictException;
import dev.patika.veterinary.core.exception.NotFoundException;
import dev.patika.veterinary.core.utilies.Msg;
import dev.patika.veterinary.dao.AnimalRepository;
import dev.patika.veterinary.dao.VaccineRepository;
import dev.patika.veterinary.dto.response.vaccine.VaccineDtoWithoutAnimal;
import dev.patika.veterinary.dto.response.vaccine.VaccineResponse;
import dev.patika.veterinary.entity.Vaccine;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VaccineManager implements IVaccineService {

    private final VaccineRepository vaccineRepository;
    private final AnimalRepository animalRepository;
    public VaccineManager(VaccineRepository vaccineRepository, AnimalRepository animalRepository) {
        this.vaccineRepository = vaccineRepository;
        this.animalRepository = animalRepository;
    }

    @Override
    @Transactional
    public Vaccine save(Vaccine vaccine) {
        List<Vaccine> existingVaccines = vaccineRepository.findExistingVaccines(vaccine.getName(), vaccine.getCode(), vaccine.getAnimal().getId(), vaccine.getProtectionStartDate(), vaccine.getProtectionFinalDate());
        if (!existingVaccines.isEmpty()) {
            throw new ConflictException(Msg.EXIST_VACCINE);
        }
        return vaccineRepository.save(vaccine);
    }

    @Override
    public Vaccine get(int id) {
        return this.vaccineRepository.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Page<Vaccine> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        return this.vaccineRepository.findAll(pageable);
    }

    @Override
    public Vaccine update(Vaccine vaccine) {
        this.get(vaccine.getId());
        return this.vaccineRepository.save(vaccine);
    }

    // Retrieves a list of vaccines for a given animal ID

    public List<VaccineDtoWithoutAnimal> findByAnimalId(Integer animalId) {
        List<Vaccine> vaccines = vaccineRepository.findByAnimalId(animalId);
        return vaccines.stream().map(vaccine -> new VaccineDtoWithoutAnimal(vaccine.getId(), vaccine.getName(), vaccine.getCode(), vaccine.getProtectionStartDate(), vaccine.getProtectionFinalDate())).collect(Collectors.toList());
    }

    // Retrieves vaccines within a specified protection date range
    public List<VaccineResponse> findVaccinesByProtectionDateRange(LocalDate startDate, LocalDate endDate) {
        List<Vaccine> vaccines = vaccineRepository.findByProtectionFinalDateBetween(startDate, endDate);
        return vaccines.stream()
                .map(vaccine -> new VaccineResponse(
                        vaccine.getId(),
                        vaccine.getName(),
                        vaccine.getCode(),
                        vaccine.getProtectionStartDate(),
                        vaccine.getProtectionFinalDate(),
                        vaccine.getAnimal()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public boolean delete(int id) {
        Vaccine vaccine = this.get(id);
        this.vaccineRepository.delete(vaccine);
        return true;
    }
}
