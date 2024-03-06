package dev.patika.veterinary.dto.request.availableDate;

import dev.patika.veterinary.entity.Animal;
import dev.patika.veterinary.entity.Doctor;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvailableDateSaveRequest {
    private LocalDate availableDate;
    private Doctor doctor;
}
