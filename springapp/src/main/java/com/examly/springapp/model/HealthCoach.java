package com.examly.springapp.model;

import jakarta.persistence.*;

@Entity
public class HealthCoach {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String specialization;
    private String certification;
    private String phoneNumber;
    private int experience;

    public HealthCoach() {}

    public HealthCoach(Long id, String name, String specialization, String certification, String phoneNumber, int experience) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.certification = certification;
        this.phoneNumber = phoneNumber;
        this.experience = experience;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public String getCertification() { return certification; }
    public void setCertification(String certification) { this.certification = certification; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public int getExperience() { return experience; }
    public void setExperience(int experience) { this.experience = experience; }
}