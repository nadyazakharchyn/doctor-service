package com.polyclinic.polyclinicapp.api;

import com.polyclinic.polyclinicapp.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/doctors")
@RestController
public class DoctorController {
    private final DoctorService doctorService;

    @GetMapping
    public ResponseEntity<List<com.polyclinic.polyclinicapp.repo.model.Doctor>> index(){
        final List<com.polyclinic.polyclinicapp.repo.model.Doctor> doctors = doctorService.fetchAllDoctors();
        return ResponseEntity.ok(doctors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<com.polyclinic.polyclinicapp.repo.model.Doctor> showById(@PathVariable long id){
        try {
            final com.polyclinic.polyclinicapp.repo.model.Doctor doctor = doctorService.fetchDoctorById(id);
            return ResponseEntity.ok(doctor);
        } catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody com.polyclinic.polyclinicapp.api.dto.Doctor doctor){
        final String name = doctor.getName();
        final String jobTitle = doctor.getJobTitle();
        final long doctorId = doctorService.createDoctor(name, jobTitle);
        final String doctorUri = String.format("/doctors/%d", doctorId);
        return ResponseEntity.created(URI.create(doctorUri)).build();

    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> change(@PathVariable long id, @RequestBody com.polyclinic.polyclinicapp.api.dto.Doctor doctor){
        final String name = doctor.getName();
        final String jobTitle = doctor.getJobTitle();

        try {
            doctorService.updateDoctor(id, name, jobTitle);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id){
        doctorService.deleteDoctor(id);
        return ResponseEntity.noContent().build();
    }
}
