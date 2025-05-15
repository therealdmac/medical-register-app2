package com.example.medical.config;

import com.example.medical.model.MedicalRecord;
import com.example.medical.repository.MedicalRecordRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initDatabase(MedicalRecordRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                for (int i = 1; i <= 20; i++) {
                    MedicalRecord record = new MedicalRecord();
                    record.setName("Patient " + i);
                    record.setAge(20 + i);
                    record.setMedicalHistory("History for Patient " + i);
                    record.setCreatedAt(LocalDate.now().minusDays(i));
                    repository.save(record);
                }
            }
        };
    }
}
