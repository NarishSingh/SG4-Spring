package com.sg.M4DVD.dao;

public interface DVDauditDAO {

    /**
     * Create a time stamped entry of DVD manipulation
     *
     * @param entry {String} record of stocking or sale of an item
     * @throws com.sg.M4DVD.dao.DVDLibraryDAOException if cannot persist to record
     */
    public void writeAuditEntry(String entry) throws DVDLibraryDAOException;
}
