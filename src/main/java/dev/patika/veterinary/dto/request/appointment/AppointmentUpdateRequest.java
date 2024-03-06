package dev.patika.veterinary.dto.request.appointment;

import dev.patika.veterinary.entity.Animal;
import dev.patika.veterinary.entity.Doctor;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentUpdateRequest {
    @Positive
    private int id;
    private LocalDateTime appointmentDate;
    private Animal animal;
    private Doctor doctor;
}
