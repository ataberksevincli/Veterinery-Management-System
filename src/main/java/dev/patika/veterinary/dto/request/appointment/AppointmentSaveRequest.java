package dev.patika.veterinary.dto.request.appointment;

import dev.patika.veterinary.entity.Animal;
import dev.patika.veterinary.entity.Doctor;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentSaveRequest {
    private LocalDateTime appointmentDate;
    private Animal animal;
    private Doctor doctor;
}
