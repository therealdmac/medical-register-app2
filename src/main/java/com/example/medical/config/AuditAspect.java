package com.example.medical.config;

import com.example.medical.model.AuditLog;
import com.example.medical.repository.AuditLogRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import com.example.medical.common.UserSession;

@Aspect
@Component
public class AuditAspect {

	private final AuditLogRepository auditLogRepository;
	private final UserSession userSession;

	public AuditAspect(AuditLogRepository auditLogRepository, UserSession userSession) {
		this.auditLogRepository = auditLogRepository;
		this.userSession = userSession;
	}

	@AfterReturning("execution(* com.example.medical.controller.MedicalRecordController.save(..))")
	public void logSave(JoinPoint joinPoint) {
		logAction("Created or Updated a medical record");
	}

	@AfterReturning("execution(* com.example.medical.controller.MedicalRecordController.delete(..))")
	public void logDelete(JoinPoint joinPoint) {
		logAction("Deleted a medical record");
	}

	private void logAction(String action) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth != null ? auth.getName() : "anonymous";
		String role = userSession.getRole();
		action += " as " + (role != null ? role : "[unspecified role]");
		auditLogRepository.save(new AuditLog(username, action));
	}
}
