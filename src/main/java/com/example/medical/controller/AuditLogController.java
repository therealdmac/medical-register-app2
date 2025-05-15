package com.example.medical.controller;

import com.example.medical.repository.AuditLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuditLogController {

	@Autowired
	private AuditLogRepository auditLogRepository;

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/audit-log")
	public String viewAuditLog(Model model) {
		model.addAttribute("logs", auditLogRepository.findAll());
		return "audit-log.xhtml";
	}
}
