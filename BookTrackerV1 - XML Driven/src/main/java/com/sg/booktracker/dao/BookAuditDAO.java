package com.sg.booktracker.dao;

public interface BookAuditDAO {
    public void writeAuditEntry(String entry) throws Exception;
}
