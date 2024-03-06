package dev.patika.veterinary.dao;

import dev.patika.veterinary.entity.Animal;
import dev.patika.veterinary.entity.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, Integer> {

    //Finds existing vaccines by name, code, and animal ID that overlap with a given date range for protection.
    @Query("SELECT v FROM Vaccine v WHERE v.name = :name AND v.code = :code AND v.animal.id = :animalId AND ((v.protectionStartDate <= :finalDate AND v.protectionFinalDate >= :startDate) OR (v.protectionStartDate = :startDate AND v.protectionFinalDate = :finalDate))")
    List<Vaccine> findExistingVaccines(@Param("name") String name, @Param("code") String code, @Param("animalId") int animalId, @Param("startDate") LocalDate startDate, @Param("finalDate") LocalDate finalDate);

    //Finds vaccines by the final date of protection falling between a specified start and end date.
    @Query("SELECT v FROM Vaccine v WHERE v.protectionFinalDate BETWEEN :startDate AND :endDate")
    List<Vaccine> findByProtectionFinalDateBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    //Finds vaccines associated with a specific animal by the animal's ID.

    List<Vaccine> findByAnimalId(Integer animalId);
}



