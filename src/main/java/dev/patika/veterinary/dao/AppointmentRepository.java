package dev.patika.veterinary.dao;

import dev.patika.veterinary.entity.Appointment;
import dev.patika.veterinary.entity.Doctor;
import dev.patika.veterinary.entity.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {

    // Checks if an appointment exists for a given doctor ID and appointment date/time.
    boolean existsByDoctorIdAndAppointmentDate(int doctorId, LocalDateTime appointmentDate);

    // Fetches appointments for a specific doctor within a given date/time range.
    @Query("SELECT a FROM Appointment a WHERE a.doctor = :doctor AND a.appointmentDate BETWEEN :startDateTime AND :endDateTime")
    List<Appointment> findByDoctorAndAppointmentDateTimeBetween(@Param("doctor") Doctor doctor, @Param("startDateTime") LocalDateTime startDateTime, @Param("endDateTime") LocalDateTime endDateTime);


    // Checks if there's any appointment for a given doctor ID within a specified date/time range.
    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Appointment a WHERE a.doctor.id = :doctorId AND a.appointmentDate >= :startDateTime AND a.appointmentDate < :endDateTime")
    boolean existsAppointmentForDoctorBetween(@Param("doctorId") Integer doctorId, @Param("startDateTime") LocalDateTime startDateTime, @Param("endDateTime") LocalDateTime endDateTime);


    // Retrieves appointments for a specific animal within a given date/time range.
    @Query("SELECT a FROM Appointment a WHERE a.animal.id = :animalId AND a.appointmentDate >= :startDate AND a.appointmentDate <= :endDate")
    List<Appointment> findAppointmentsByAnimalIdAndDateTimeRange(
            @Param("animalId") Integer animalId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);

    // Fetches appointments for a specific doctor within a given date/time range.
    @Query("SELECT a FROM Appointment a WHERE a.doctor.id = :doctorId AND a.appointmentDate >= :startDate AND a.appointmentDate < :endDate")
    List<Appointment> findAppointmentsByDoctorIdAndDateTimeRange(
            @Param("doctorId") Integer doctorId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);
}

