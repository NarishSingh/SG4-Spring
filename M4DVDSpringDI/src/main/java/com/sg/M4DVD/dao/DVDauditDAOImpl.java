package com.sg.M4DVD.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;


public class DVDauditDAOImpl implements DVDauditDAO {

    public static final String AUDIT_FILE = "audit.txt";

    @Override
    public void writeAuditEntry(String entry) throws DVDLibraryDAOException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        } catch (IOException e) {
            throw new DVDLibraryDAOException("Could not persist record", e);
        }

        LocalDateTime timestamp = LocalDateTime.now();

        out.println(timestamp.toString() + " : " + entry);
        out.flush();
    }
    
}
