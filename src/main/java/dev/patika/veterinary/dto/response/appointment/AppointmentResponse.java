package dev.patika.veterinary.dto.response.appointment;

import dev.patika.veterinary.entity.Animal;
import dev.patika.veterinary.entity.Doctor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentResponse {
    private int id;
    private LocalDateTime appointmentDate;
    private Animal animal;
    private Doctor doctor;
}
