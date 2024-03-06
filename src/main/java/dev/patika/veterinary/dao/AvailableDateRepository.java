package dev.patika.veterinary.dao;

import dev.patika.veterinary.entity.AvailableDate;
import dev.patika.veterinary.entity.Doctor;
import dev.patika.veterinary.entity.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface AvailableDateRepository extends JpaRepository<AvailableDate,Integer> {


    // Checks if a doctor is available on a specific date.
    @Query("SELECT CASE WHEN COUNT(ad) > 0 THEN true ELSE false END FROM AvailableDate ad WHERE ad.doctor.id = :doctorId AND ad.availableDate = :date")
    boolean isDoctorAvailableOnDate(@Param("doctorId") Integer doctorId, @Param("date") LocalDate date);

}

