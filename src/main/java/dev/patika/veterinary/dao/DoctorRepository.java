package dev.patika.veterinary.dao;

import dev.patika.veterinary.entity.Doctor;
import dev.patika.veterinary.entity.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Integer> {
}
