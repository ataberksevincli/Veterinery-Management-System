package dev.patika.veterinary.business.abstracts;

import dev.patika.veterinary.entity.AvailableDate;
import dev.patika.veterinary.entity.Doctor;
import org.springframework.data.domain.Page;

public interface IDoctorService {
    Doctor save(Doctor doctor);
    Doctor get(int id);
    Page<Doctor> cursor(int page, int pageSize);
    Doctor update(Doctor doctor);
    boolean delete(int id);
}
