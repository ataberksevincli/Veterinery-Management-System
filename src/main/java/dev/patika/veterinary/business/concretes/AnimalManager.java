package dev.patika.veterinary.business.concretes;

import dev.patika.veterinary.business.abstracts.IAnimalService;
import dev.patika.veterinary.core.exception.AnimalNotFoundException;
import dev.patika.veterinary.core.exception.NotFoundException;
import dev.patika.veterinary.core.utilies.Msg;
import dev.patika.veterinary.dao.AnimalRepository;
import dev.patika.veterinary.dto.request.animal.AnimalSaveRequest;
import dev.patika.veterinary.dto.response.animal.AnimalResponse;
import dev.patika.veterinary.dto.response.customer.CustomerResponse;
import dev.patika.veterinary.entity.Animal;
import dev.patika.veterinary.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnimalManager implements IAnimalService {

    private final AnimalRepository animalRepository;

    @Autowired
    public AnimalManager(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @Override
    public Animal save(Animal animal) {
        return this.animalRepository.save(animal);
    }

    @Override
    public Animal get(int id) {
        return this.animalRepository.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Page<Animal> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        return this.animalRepository.findAll(pageable);
    }

    @Override
    public Animal update(Animal animal) {
        this.get(animal.getId());
        return this.animalRepository.save(animal);
    }

    @Override
    public boolean delete(int id) {
        Animal animal = this.get(id);
        this.animalRepository.delete(animal);
        return true;
    }

    // Finds and returns animals belonging to a customer with a specific name
    @Override
    public List<AnimalResponse> findAnimalsByCustomerName(String customerName) {
        List<Animal> animals = animalRepository.findByCustomerNameContainingIgnoreCase(customerName);
        // IF THERE IS NO CUSTOMER ANIMAL THROW EXCEPTION
        if (animals.isEmpty()) {
            throw new NotFoundException(Msg.NO_CUSTOMER_ANIMAL);
        }
        return animals.stream()
                .map(animal -> {
                    AnimalResponse response = new AnimalResponse();
                    response.setId(animal.getId());
                    response.setName(animal.getName());
                    response.setSpecies(animal.getSpecies());
                    response.setBreed(animal.getBreed());
                    response.setGender(animal.getGender());
                    response.setColour(animal.getColour());
                    response.setDateofBirth(animal.getDateofBirth());
                    response.setCustomer(animal.getCustomer());
                    return response;
                })
                .collect(Collectors.toList());
    }

    // Finds and returns animals belonging to a customer with a specific name
    @Override
    public List<AnimalResponse> findAnimalsByName(String name) {
        List<Animal> animals = animalRepository.findByNameContainingIgnoreCase(name);
        if (animals.isEmpty()) {
            throw new NotFoundException(Msg.NO_ANIMAL);
        }
        return animals.stream()
                .map(animal -> new AnimalResponse(
                        animal.getId(),
                        animal.getName(),
                        animal.getSpecies(),
                        animal.getBreed(),
                        animal.getGender(),
                        animal.getColour(),
                        animal.getDateofBirth(),
                        animal.getCustomer()
                ))
                .collect(Collectors.toList());
    }

    // Finds and returns all animals associated with a given customer ID
    public List<AnimalResponse> findAllAnimalsByCustomerId(int customerId) {
        List<Animal> animals = animalRepository.findByCustomerId(customerId);
        if (animals.isEmpty()) {
            throw new NotFoundException(Msg.NO_CUSTOMERID_ANIMAL);
        }
        return animals.stream()
                .map(animal -> new AnimalResponse(
                        animal.getId(),
                        animal.getName(),
                        animal.getSpecies(),
                        animal.getBreed(),
                        animal.getGender(),
                        animal.getColour(),
                        animal.getDateofBirth(),
                        animal.getCustomer()
                ))
                .collect(Collectors.toList());
    }
}
