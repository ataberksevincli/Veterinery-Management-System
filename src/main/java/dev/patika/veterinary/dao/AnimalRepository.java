package dev.patika.veterinary.dao;

import dev.patika.veterinary.entity.Animal;
import dev.patika.veterinary.entity.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal,Integer> {

        // Fetches a list of animals whose associated customer's name contains the specified string.
        @Query("SELECT a FROM Animal a JOIN FETCH a.customer c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))")
        List<Animal> findByCustomerNameContainingIgnoreCase(@Param("name") String name);

        // Finds animals by name that contains the specified string
        List<Animal> findByNameContainingIgnoreCase(String name);

        // Retrieves a list of animals associated with a given customer ID.
        List<Animal> findByCustomerId(int customerId);
}
