package com.sg.booktracker.advice;

import com.sg.booktracker.dao.BookAuditDAO;
import org.aspectj.lang.JoinPoint;

public class LoggingAdvice {

    BookAuditDAO auditDAO;

    public LoggingAdvice(BookAuditDAO auditDAO) {
        this.auditDAO = auditDAO;
    }

    public void createAuditEntry(JoinPoint jp) {
        Object[] args = jp.getArgs(); //gets the parameters
        String auditEntry = jp.getSignature().getName() + ": ";

        for (Object currentArg : args) {
            auditEntry += currentArg; //ONLY logs the parameters
        }

        try {
            auditDAO.writeAuditEntry(auditEntry);
        } catch (Exception e) {
            System.err.println(
                    "ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }
    
    public void createExceptionEntry(Exception ex) {
        String auditEntry = "Thrown: " + ex.getMessage();

        try {
            auditDAO.writeAuditEntry(auditEntry);
        } catch (Exception e) {
            System.err.println("ERROR: could not write audit entry in LoggingAdvice.");
        }
    }
}
