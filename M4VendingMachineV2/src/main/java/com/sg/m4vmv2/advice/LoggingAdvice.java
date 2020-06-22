package com.sg.m4vmv2.advice;

import com.sg.m4vmv2.dao.VMAuditDAO;
import com.sg.m4vmv2.dao.VendingPersistenceException;
import org.aspectj.lang.JoinPoint;

public class LoggingAdvice {

    VMAuditDAO auditDAO;

    public LoggingAdvice(VMAuditDAO auditDAO) {
        this.auditDAO = auditDAO;
    }

    public void createAuditEntry(JoinPoint jp) {
        Object[] args = jp.getArgs(); //gets the parameters
        String auditEntry = jp.getSignature().getName() + ": ";

        for (Object currentArg : args) {
            auditEntry += currentArg;
        }

        try {
            auditDAO.writeAuditEntry(auditEntry);
        } catch (VendingPersistenceException e) {
            System.err.println("ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }

    public void createExceptionEntry(Exception ex) {
        String auditEntry = "Thrown: " + ex.getMessage();

        try {
            auditDAO.writeAuditEntry(auditEntry);
        } catch (VendingPersistenceException e) {
            System.err.println("ERROR: could not write audit entry in LoggingAdvice.");
        }
    }
}
