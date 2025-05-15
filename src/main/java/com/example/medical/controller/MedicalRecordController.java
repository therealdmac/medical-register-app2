package com.example.medical.controller;

import com.example.medical.model.MedicalRecord;
import com.example.medical.repository.MedicalRecordRepository;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Named
@RequestScoped
public class MedicalRecordController {

    @Autowired
    private MedicalRecordRepository repository;

    private MedicalRecord record = new MedicalRecord();
    private List<MedicalRecord> records;

    @PostConstruct
    public void init() {
        records = repository.findAll();
    }

    public void save() {
        repository.save(record);
        record = new MedicalRecord(); // reset form
        records = repository.findAll(); // refresh
    }

    public void delete(Long id) {
        repository.deleteById(id);
        records = repository.findAll();
    }

    public MedicalRecord getRecord() {
        return record;
    }

    public void setRecord(MedicalRecord record) {
        this.record = record;
    }

    public List<MedicalRecord> getRecords() {
        return records;
    }
}
