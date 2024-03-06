package dev.patika.veterinary.business.concretes;

import dev.patika.veterinary.business.abstracts.IAppointmentService;
import dev.patika.veterinary.core.exception.NotFoundException;
import dev.patika.veterinary.core.utilies.Msg;
import dev.patika.veterinary.dao.AnimalRepository;
import dev.patika.veterinary.dao.AppointmentRepository;
import dev.patika.veterinary.dao.AvailableDateRepository;
import dev.patika.veterinary.dao.DoctorRepository;
import dev.patika.veterinary.dto.request.appointment.AppointmentSaveRequest;
import dev.patika.veterinary.entity.Animal;
import dev.patika.veterinary.entity.Appointment;
import dev.patika.veterinary.entity.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentManager implements IAppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final AvailableDateRepository availableDateRepository;
    private final DoctorRepository doctorRepository;
    private final AnimalRepository animalRepository;
    public AppointmentManager(AppointmentRepository appointmentRepository, AvailableDateRepository availableDateRepository, DoctorRepository doctorRepository, AnimalRepository animalRepository) {
        this.appointmentRepository = appointmentRepository;
        this.availableDateRepository = availableDateRepository;
        this.doctorRepository = doctorRepository;
        this.animalRepository = animalRepository;
    }


    // Method to create a new appointment with necessary checks for doctor's availability and existing appointments
    public Appointment createAppointment(AppointmentSaveRequest request) {
        // Fetch the doctor and animal from the database, throw exceptions if not found

        Doctor doctor = doctorRepository.findById(request.getDoctor().getId())
                .orElseThrow(() -> new RuntimeException("Doctor not found."));
        Animal animal = animalRepository.findById(request.getAnimal().getId())
                .orElseThrow(() -> new RuntimeException("Animal not found."));


        // Check if the doctor is available on the requested date
        boolean isAvailable = availableDateRepository.isDoctorAvailableOnDate(doctor.getId(), request.getAppointmentDate().toLocalDate());
        if (!isAvailable) {
            throw new RuntimeException("Doctor is not available.");
        }

        // Check if there is already an appointment for the doctor at the requested time
        LocalDateTime startDateTime = request.getAppointmentDate();
        LocalDateTime endDateTime = startDateTime.plusHours(1);
        boolean existsAppointment = appointmentRepository.existsAppointmentForDoctorBetween(doctor.getId(), startDateTime, endDateTime);
        if (existsAppointment) {
            throw new RuntimeException("There is already an appointment at this time.");
        }

        // Create and save the new appointment
        Appointment appointment = new Appointment();
        appointment.setAppointmentDate(request.getAppointmentDate());
        appointment.setDoctor(doctor);
        appointment.setAnimal(animal);

        return appointmentRepository.save(appointment);
    }


    // Method to retrieve appointments for a given animal within a specified date and time range
    @Override
    public List<Appointment> getAppointmentsByAnimalIdAndDateTimeRange(Integer animalId, LocalDateTime startDate, LocalDateTime endDate) {
        return appointmentRepository.findAppointmentsByAnimalIdAndDateTimeRange(animalId, startDate, endDate);
    }


    // Method to retrieve appointments for a given doctor within a specified date and time range
    @Override
    public List<Appointment> getAppointmentsByDoctorIdAndDateTimeRange(Integer doctorId, LocalDateTime startDate, LocalDateTime endDate) {
        return appointmentRepository.findAppointmentsByDoctorIdAndDateTimeRange(doctorId, startDate, endDate);
    }

    // Standard CRUD operations below

    @Override
    public Appointment save(Appointment appointment) {
        return this.appointmentRepository.save(appointment);
    }

    @Override
    public Appointment get(int id) {
        return this.appointmentRepository.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Page<Appointment> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        return this.appointmentRepository.findAll(pageable);
    }

    @Override
    public Appointment update(Appointment appointment) {
        this.get(appointment.getId());
        return this.appointmentRepository.save(appointment);
    }

    @Override
    public boolean delete(int id) {
        Appointment appointment = this.get(id);
        this.appointmentRepository.delete(appointment);
        return true;
    }
}
