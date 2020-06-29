/*
UserIO interface
will be used for the menu system
 */
package com.sg.flooringmastery.view;

import java.math.BigDecimal;

/**
 * Handles I/O, taking prompts as arguments with overloads to take within a
 * range
 *
 * @author Narish Singh
 */
public interface UserIO {

    void print(String message);

    String readString(String prompt);

    int readInt(String prompt);

    int readInt(String prompt, int min, int max);

    double readDouble(String prompt);

    double readDouble(String prompt, double min, double max);

    float readFloat(String prompt);

    float readFloat(String prompt, float min, float max);

    long readLong(String prompt);

    long readLong(String prompt, long min, long max);
    
    BigDecimal readBigDecimal(String prompt);

    BigDecimal readBigDecimal(String prompt, BigDecimal min, BigDecimal max);
}
