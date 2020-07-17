package com.sg.flooringmastery.view;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Implementation of UserIO interface
 *
 * @author Narish
 */
public class UserIOImpl implements UserIO {

    final private Scanner input = new Scanner(System.in);

    /**
     * Print a message
     *
     * @param message {String} message to be printed to console
     */
    @Override
    public void print(String message) {
        System.out.println(message);
    }

    /**
     * Print a prompt and read line
     *
     * @param prompt {String} prompt to be printed to console
     * @return {String} user's String
     */
    @Override
    public String readString(String prompt) {
        System.out.print(prompt);
        return input.nextLine();
    }

    /**
     * Print a prompt and read in an integer
     *
     * @param prompt {String} prompt to be printed to console
     * @return {int} user's integer
     */
    @Override
    public int readInt(String prompt) {
        System.out.print(prompt);
        return Integer.parseInt(input.nextLine());
    }

    /**
     * Print a prompt and read in an integer, accepting only if it is within
     * specified range
     *
     * @param prompt {String} prompt to be printed to console
     * @param min    {int} minimum integer for acceptable inputs
     * @param max    {int} maximum integer for acceptable inputs
     * @return {int} user's integer in range
     */
    @Override
    public int readInt(String prompt, int min, int max) {
        int userInput;

        System.out.println(prompt);
        System.out.println("min = " + min);
        System.out.println("max = " + max);
        do {
            System.out.print("Enter integer: ");
            userInput = Integer.parseInt(input.nextLine());
        } while (userInput < min || userInput > max);

        return userInput;
    }

    /**
     * Print a prompt and read in a double
     *
     * @param prompt {String} prompt to be printed to console
     * @return {double} user's double
     */
    @Override
    public double readDouble(String prompt) {
        System.out.print(prompt);
        return Double.parseDouble(input.nextLine());
    }

    /**
     * Print a prompt and read in a double, accepting only if it is within
     * specified range
     *
     * @param prompt {String} prompt to be printed to console
     * @param min    {double} minimum double for acceptable inputs
     * @param max    {double} maximum double for acceptable inputs
     * @return {double} user's double in range
     */
    @Override
    public double readDouble(String prompt, double min, double max) {
        double userInput;

        System.out.println(prompt);
        System.out.println("min = " + min);
        System.out.println("max = " + max);
        do {
            System.out.print("Enter double: ");
            userInput = Double.parseDouble(input.nextLine());
        } while (userInput < min || userInput > max);

        return userInput;
    }

    /**
     * Print a prompt and read in a float
     *
     * @param prompt {String} prompt to be printed to console
     * @return {float} user's float
     */
    @Override
    public float readFloat(String prompt) {
        System.out.print(prompt);
        return Float.parseFloat(input.nextLine());
    }

    /**
     * Print a prompt and read in a float, accepting only if it is within
     * specified range
     *
     * @param prompt {String} prompt to be printed to console
     * @param min    {float} minimum float for acceptable inputs
     * @param max    {float} maximum float for acceptable inputs
     * @return {float} user's float in range
     */
    @Override
    public float readFloat(String prompt, float min, float max) {
        float userInput;

        System.out.println(prompt);
        System.out.println("min = " + min);
        System.out.println("max = " + max);
        do {
            System.out.print("Enter float: ");
            userInput = Float.parseFloat(input.nextLine());
        } while (userInput < min || userInput > max);

        return userInput;
    }

    /**
     * Print a prompt and read in a long
     *
     * @param prompt {String} prompt to be printed to console
     * @return {long} user's long
     */
    @Override
    public long readLong(String prompt) {
        System.out.print(prompt);
        return Long.parseLong(input.nextLine());
    }

    /**
     * Print a prompt and read in a long, accepting only if it is within
     * specified range
     *
     * @param prompt {String} prompt to be printed to console
     * @param min    {long} minimum long for acceptable inputs
     * @param max    {long} maximum long for acceptable inputs
     * @return {long} user's long in range
     */
    @Override
    public long readLong(String prompt, long min, long max) {
        long userInput;

        System.out.println(prompt);
        System.out.println("min = " + min);
        System.out.println("max = " + max);
        do {
            System.out.print("Enter long: ");
            userInput = Long.parseLong(input.nextLine());
        } while (userInput < min || userInput > max);

        return userInput;
    }

    /**
     * Get a BigDecimal value from user input
     *
     * @param prompt {String} the prompt to print to the user
     * @return {BigDecimal} the user's BigDecimal value
     */
    @Override
    public BigDecimal readBigDecimal(String prompt) {
        boolean hasErrors;
        BigDecimal userInput = null;

        System.out.println(prompt);

        do {
            try {
                userInput = new BigDecimal(input.nextLine());
                userInput.setScale(2, RoundingMode.HALF_UP);
                hasErrors = false;
            } catch (NumberFormatException e) {
                hasErrors = true;
            }
        } while (hasErrors);

        return userInput;
    }

    /**
     * Get a BigDecimal value from user input between a specified range
     *
     * @param prompt {String} the prompt to print to the user
     * @param min    {BigDecimal} the minimum input
     * @param max    {BigDecimal} the maximum input
     * @return {BigDecimal} the user's BigDecimal value in range
     */
    @Override
    public BigDecimal readBigDecimal(String prompt, BigDecimal min, BigDecimal max) {
        boolean hasErrors;
        BigDecimal userInput = null;

        System.out.println(prompt);
        System.out.println("min = " + min);
        System.out.println("max = " + max);
        do {
            System.out.print("Enter number: ");
            try {
                userInput = new BigDecimal(input.nextLine());
                userInput.setScale(2, RoundingMode.HALF_UP);
                hasErrors = false;
            } catch (NumberFormatException e) {
                hasErrors = true;
            }
        } while (hasErrors || userInput.compareTo(min) < 0 || userInput.compareTo(max) > 0);

        return userInput;
    }

    /**
     * Get a LocalDate value from the user
     *
     * @param prompt {String} prompt to print to the user
     * @return {LocalDate} the user's inputted date as a LocalDate obj
     */
    @Override
    public LocalDate readLocalDate(String prompt) {
        boolean hasErrors;
        LocalDate date = LocalDate.now(); //just for initialization

        System.out.println(prompt);
        do {
            try {
                date = LocalDate.parse(readString("Enter date in MM-dd-yyyy format: "), DateTimeFormatter.ofPattern("MM-dd-yyyy"));
                hasErrors = false;
            } catch (DateTimeParseException e) {
                hasErrors = true;
            }
        } while (hasErrors);

        return date;
    }

    /**
     * Get a LocalDate value from the user between a set period of time
     *
     * @param prompt   {String} prompt to print to the user
     * @param earliest {LocalDate} the earliest date the user may enter
     * @param latest   {LocalDate} the latest date the user may enter
     * @return the user's inputted date in range, as a LocalDate obj
     */
    @Override
    public LocalDate readLocalDate(String prompt, LocalDate earliest, LocalDate latest) {
        boolean hasErrors;
        DateTimeFormatter mmddyyyy = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        LocalDate date = LocalDate.now(); //just for initialization

        System.out.println(prompt);
        do {
            try {
                date = LocalDate.parse(readString("Enter date in MM-dd-yyyy format between " + earliest.format(mmddyyyy)
                        + " and " + latest.format(mmddyyyy) + ": "), mmddyyyy);

                hasErrors = date.isBefore(earliest) || date.isAfter(latest);
            } catch (DateTimeParseException e) {
                hasErrors = true;
            }
        } while (hasErrors);

        return date;
    }

}
