package dev.patika.veterinary.business.abstracts;

import dev.patika.veterinary.dto.response.vaccine.VaccineDtoWithoutAnimal;
import dev.patika.veterinary.dto.response.vaccine.VaccineResponse;
import dev.patika.veterinary.entity.Vaccine;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface IVaccineService {
    Vaccine save(Vaccine vaccine);
    Vaccine get(int id);
    Page<Vaccine> cursor(int page, int pageSize);
    Vaccine update(Vaccine vaccine);
    boolean delete(int id);
    List<VaccineDtoWithoutAnimal> findByAnimalId(Integer animalId);

    List<VaccineResponse> findVaccinesByProtectionDateRange(LocalDate startDate, LocalDate endDate);

}
