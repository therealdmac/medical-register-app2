package com.example.medical.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class MedicalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public MedicalRecord(String name, int age, String medicalHistory){
        this.name = name;
        this.age = age;
        this.medicalHistory = medicalHistory;
    }

    private String name;
    private int age;
    private String medicalHistory;
    private LocalDate createdAt = LocalDate.now();
}
