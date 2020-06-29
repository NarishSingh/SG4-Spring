package com.sg.classroster.advice;

import com.sg.classroster.dao.ClassRosterAuditDAO;
import com.sg.classroster.dao.ClassRosterPersistenceException;
import org.aspectj.lang.JoinPoint;

/**
 *
 * @author naris
 */
public class LoggingAdvice {

    ClassRosterAuditDAO auditDAO;

    public LoggingAdvice(ClassRosterAuditDAO auditDAO) {
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
        } catch (ClassRosterPersistenceException e) {
            System.err.println("ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }

    public void createExceptionEntry(Exception ex) {
        String auditEntry = "Thrown: " + ex.getMessage();

        try {
            auditDAO.writeAuditEntry(auditEntry);
        } catch (ClassRosterPersistenceException e) {
            System.err.println("ERROR: could not write audit entry in LoggingAdvice.");
        }
    }
    
    public void createExceptionWithJPEntry(JoinPoint jp, Exception ex) {
        String auditEntry = "Exception Thrown in: " + jp.getSignature().getName() + ":: Message: " + ex.getMessage();

        try {
            auditDAO.writeAuditEntry(auditEntry);
        } catch (ClassRosterPersistenceException e) {
            System.err.println("ERROR: could not write audit entry in LoggingAdvice.");
        }
    }
}
