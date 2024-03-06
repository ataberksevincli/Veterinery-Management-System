package dev.patika.veterinary.business.abstracts;

import dev.patika.veterinary.dto.response.animal.AnimalResponse;
import dev.patika.veterinary.entity.Animal;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IAnimalService {
    Animal save(Animal animal);
    Animal get(int id);
    Page<Animal> cursor(int page, int pageSize);
    Animal update(Animal animal);
    boolean delete(int id);
    List<AnimalResponse> findAnimalsByCustomerName(String customerName);

    List<AnimalResponse> findAnimalsByName(String name);

    List<AnimalResponse> findAllAnimalsByCustomerId(int customerId);
}
