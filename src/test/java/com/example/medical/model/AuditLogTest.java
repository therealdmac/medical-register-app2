package com.example.medical.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;

public class AuditLogTest {

    @Test
    void testAuditLogProperties() {
        AuditLog log = new AuditLog();
        log.setUsername("admin");
        log.setAction("created record");
        log.setTimestamp(LocalDateTime.now());

        assertEquals("admin", log.getUsername());
        assertEquals("created record", log.getAction());
        assertNotNull(log.getTimestamp());
    }
}
