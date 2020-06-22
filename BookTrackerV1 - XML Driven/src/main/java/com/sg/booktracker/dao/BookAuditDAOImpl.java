package com.sg.booktracker.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;


public class BookAuditDAOImpl implements BookAuditDAO {

    public static final String AUDIT_FILE = "audit.txt";
    
    @Override
    public void writeAuditEntry(String entry) throws Exception {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        } catch (IOException e) {
            throw new Exception("Could not persist audit info", e);
        }

        LocalDateTime timestamp = LocalDateTime.now();

        out.println(timestamp.toString() + " : " + entry);
        out.flush();
    }
    
}
