package dev.patika.veterinary.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "animals")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "animal_id",columnDefinition = "serial")
    private int id;

    @Column(name = "animal_name",nullable = false)
    private String name;

    @Column(name = "animal_species",nullable = false)
    private String species;

    @Column(name = "animal_breed",nullable = false)
    private String breed;

    @Column(name = "animal_gender",nullable = false)
    private String gender;

    @Column(name = "animal_colour",nullable = false)
    private String colour;

    @Column(name = "animal_dateofBirth",nullable = false)
    private LocalDate dateofBirth;

    @ManyToOne
    @JoinColumn(name = "animal_customer_id",referencedColumnName = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "animal",cascade = CascadeType.ALL,fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore
    private List<Vaccine> vaccineList;

    @OneToMany(mappedBy = "animal",cascade = CascadeType.ALL,fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore
    private List<Appointment> appointmentList;
}
