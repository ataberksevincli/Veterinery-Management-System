package dev.patika.veterinary.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "vaccines")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vaccine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vaccine_id",columnDefinition = "serial")
    private int id;

    @Column(name = "vaccine_name",nullable = false)
    private String name;

    @Column(name = "vaccine_code",nullable = false)
    private String code;

    @Column(name = "vaccine_protectionStartDate",nullable = false)
    private LocalDate protectionStartDate;

    @Column(name = "vaccine_protectionFinalDate",nullable = false)
    private LocalDate protectionFinalDate;

    @ManyToOne
    @JoinColumn(name = "vaccine_animal_id",referencedColumnName = "animal_id")
    private Animal animal;

}
