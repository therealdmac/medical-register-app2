//package com.example.medical.model;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.time.LocalDateTime;
//
//@Entity
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class AuditLog {
//	@Id @GeneratedValue private Long id;
//	private String username;
//	private String action;
//	private LocalDateTime timestamp;
//
//	public AuditLog(String username, String action) {
//		this.username = username;
//		this.action = action;
//		this.timestamp = LocalDateTime.now();
//	}
//}
