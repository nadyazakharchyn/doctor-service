package com.polyclinic.polyclinicapp.repo;

import com.polyclinic.polyclinicapp.repo.model.Doctor;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface DoctorRepo extends JpaRepository<Doctor, Long> {
}
