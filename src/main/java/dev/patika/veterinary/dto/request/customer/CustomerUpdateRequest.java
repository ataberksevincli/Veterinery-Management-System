package dev.patika.veterinary.dto.request.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerUpdateRequest {
    @Positive
    private int id;
    private String name;
    private String phone;
    @Email
    private String mail;
    private String address;
    private String city;
}
