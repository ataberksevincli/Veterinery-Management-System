package dev.patika.veterinary.dto.request.availableDate;

import dev.patika.veterinary.entity.Animal;
import dev.patika.veterinary.entity.Doctor;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvailableDateUpdateRequest {
    @Positive
    private int id;
    private LocalDate availableDate;
    private Doctor doctor;
}
