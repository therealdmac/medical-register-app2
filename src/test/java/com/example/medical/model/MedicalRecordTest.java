package com.example.medical.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

public class MedicalRecordTest {

    @Test
    void testMedicalRecordProperties() {
        MedicalRecord record = new MedicalRecord();
        record.setName("John Doe");
        record.setAge(30);
        record.setMedicalHistory("No significant history");
        record.setCreatedAt(LocalDate.now());

        assertEquals("John Doe", record.getName());
        assertEquals(30, record.getAge());
        assertEquals("No significant history", record.getMedicalHistory());
        assertNotNull(record.getCreatedAt());
    }
}
