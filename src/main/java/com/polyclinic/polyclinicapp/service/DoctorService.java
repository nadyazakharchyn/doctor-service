package com.polyclinic.polyclinicapp.service;

import com.polyclinic.polyclinicapp.repo.model.Doctor;
import com.polyclinic.polyclinicapp.repo.DoctorRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DoctorService {
    private final DoctorRepo doctorRepo;

    public List<Doctor> fetchAllDoctors(){
        return doctorRepo.findAll();
    }

    public Doctor fetchDoctorById(long id) throws IllegalArgumentException{
        final Optional<Doctor> maybeDoctor = doctorRepo.findById(id);

        if (maybeDoctor.isPresent())
            return maybeDoctor.get();
        else
            throw new IllegalArgumentException("Invalid doctor ID");
    }

    public long createDoctor(String name, String jobTitle){
        final Doctor doctor = new Doctor(name, jobTitle);
        final Doctor savedDoctor = doctorRepo.save(doctor);
        return savedDoctor.getId();
    }

    public void updateDoctor(long id, String name, String jobTitle){
        final Optional<Doctor> maybeDoctor = doctorRepo.findById(id);

        if (maybeDoctor.isEmpty()) throw new IllegalArgumentException("Invalid doctor id");

        final Doctor doctor = maybeDoctor.get();
        if( name != null && !name.isBlank()) doctor.setName(name);
        if(jobTitle != null && jobTitle.isBlank()) doctor.setJobTitle(jobTitle);
        doctorRepo.save(doctor);

    }

    public void deleteDoctor(long id){
        doctorRepo.deleteById(id);
    }
}
