package dev.patika.veterinary.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "availableDates")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailableDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "availableDate_id",columnDefinition = "serial")
    private int id;

    @Column(name = "availableDate_availableDate",nullable = false)
    private LocalDate availableDate;

    @ManyToOne
    @JoinColumn(name = "availableDate_doctor_id",referencedColumnName = "doctor_id")
    private Doctor doctor;
}
