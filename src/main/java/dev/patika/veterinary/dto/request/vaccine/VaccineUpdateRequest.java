package dev.patika.veterinary.dto.request.vaccine;

import dev.patika.veterinary.entity.Animal;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VaccineUpdateRequest {
    @Positive
    private int id;
    private String name;
    private String code;
    private LocalDate protectionStartDate;
    private LocalDate protectionFinalDate;
    //private Animal animal;
    //private int animalId;
}
