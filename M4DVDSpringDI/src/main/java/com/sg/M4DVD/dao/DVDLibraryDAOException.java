/*
exception definition class
 */
package com.sg.M4DVD.dao;

public class DVDLibraryDAOException extends Exception {
    public DVDLibraryDAOException(String message){
        super(message);
    }
    
    public DVDLibraryDAOException(String message, Throwable cause){
        super(message, cause);
    }
}
