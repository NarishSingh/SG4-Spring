package com.sg.M4DVD.advice;

import com.sg.M4DVD.dao.DVDLibraryDAOException;
import com.sg.M4DVD.dao.DVDauditDAO;
import org.aspectj.lang.JoinPoint;

public class LoggingAdvice {
    DVDauditDAO auditDAO;

    public LoggingAdvice(DVDauditDAO auditDAO) {
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
        } catch (DVDLibraryDAOException e) {
            System.err.println(
                    "ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }
}
