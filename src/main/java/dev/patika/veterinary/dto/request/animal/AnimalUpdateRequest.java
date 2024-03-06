package dev.patika.veterinary.dto.request.animal;

import dev.patika.veterinary.entity.Customer;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimalUpdateRequest {
    @Positive
    private int id;
    @NotNull(message = "Dog name cannot be null or an empty value.")
    private String name;
    private String species;
    private String breed;
    private String gender;
    private String colour;
    private LocalDate dateofBirth;
    private Customer customer;
}
