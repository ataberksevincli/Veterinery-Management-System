package dev.patika.veterinary.business.abstracts;

import dev.patika.veterinary.dto.request.appointment.AppointmentSaveRequest;
import dev.patika.veterinary.entity.Animal;
import dev.patika.veterinary.entity.Appointment;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IAppointmentService {
    Appointment save(Appointment appointment);

    Appointment createAppointment(AppointmentSaveRequest request);
    Appointment get(int id);
    Page<Appointment> cursor(int page, int pageSize);
    Appointment update(Appointment appointment);
    boolean delete(int id);

    List<Appointment> getAppointmentsByAnimalIdAndDateTimeRange(Integer animalId, LocalDateTime startDate, LocalDateTime endDate);

    List<Appointment> getAppointmentsByDoctorIdAndDateTimeRange(Integer doctorId, LocalDateTime startDate, LocalDateTime endDate);

}
