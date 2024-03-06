package dev.patika.veterinary.dto.response.availableDate;

import dev.patika.veterinary.entity.Animal;
import dev.patika.veterinary.entity.Doctor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvailableDateResponse {
    private int id;
    private LocalDate availableDate;
    private Doctor doctor;
}
