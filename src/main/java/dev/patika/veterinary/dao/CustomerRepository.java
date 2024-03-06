package dev.patika.veterinary.dao;

import dev.patika.veterinary.entity.Customer;
import dev.patika.veterinary.entity.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    List<Customer> findByNameContainingIgnoreCase(String name);
}
