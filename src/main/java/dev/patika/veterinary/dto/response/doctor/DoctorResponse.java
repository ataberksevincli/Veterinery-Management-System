package dev.patika.veterinary.dto.response.doctor;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorResponse {
    private int id;
    private String name;
    private String phone;
    private String mail;
    private String address;
    private String city;
}
