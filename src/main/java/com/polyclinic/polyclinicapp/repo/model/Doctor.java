package com.polyclinic.polyclinicapp.repo.model;

import javax.persistence.*;

@Entity
@Table(name="doctor")
public final class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "job_title")
    private String jobTitle;

    public Doctor(){
    }

    public Doctor(String name, String jobTitle) {
        this.name = name;
        this.jobTitle = jobTitle;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
}
