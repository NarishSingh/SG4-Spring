/*
multiple file io
TreeMap inside of a TreeMap

use the File class
https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/io/File.html
 */
package com.sg.scratchpad.multifile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.TreeMap;

public class MultiFileReadApp {

    static public final String DELIMITER = "::";

    /**
     * Marshall an employee to text
     *
     * @param empl {Employee} a hired worker
     * @return {String} a delimited String with the new hiree's information
     */
    static public String marshallEmpl(Employee empl) {
        String emplAsText = empl.getId() + DELIMITER;
        emplAsText += empl.getName() + DELIMITER;
        emplAsText += empl.getJob() + DELIMITER;
        emplAsText += empl.getSalary().toString();

        return emplAsText;
    }

    /**
     * Unmarshall an Employee obj from text
     *
     * @param emplAsText {String} a delimited String with all fields of/info for
     *                   an Employee obj
     * @return {Employee} a new object constructed from file
     */
    static public Employee unmarshallEmpl(String emplAsText) {
        String[] emplTokens = emplAsText.split(DELIMITER);

        int emplID = Integer.parseInt(emplTokens[0]);
        String emplName = emplTokens[1];
        String emplJob = emplTokens[2];
        BigDecimal emplSalary = new BigDecimal(emplTokens[3]);

        Employee emplFromFile = new Employee(emplID, emplName, emplJob, emplSalary);

        return emplFromFile;
    }

    /**
     * Create new files per date recording new hires from that day
     *
     * @param fileName        {String} the name of the .txt file, formatted as
     *                        "record_" with the date in ISO format
     * @param employeesByDate {TreeMap} all new hires that day
     */
    public static void createHiredByDateFile(String fileName, TreeMap<Integer, Employee> employeesByDate) {
        PrintWriter out;
        try {
            //to create the files in a specific directory, next a new file creation in a new FileWriter
            out = new PrintWriter(new FileWriter(new File("test_docs", fileName)));

            employeesByDate.values().stream()
                    .forEach((empl) -> {
                        String emplAsText = marshallEmpl(empl);
                        out.println(emplAsText);
                        out.flush();
                    });

            out.close();
        } catch (IOException e) {
            System.out.println("Could not write to file.");
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner input1 = new Scanner(System.in); //string
        Scanner input2 = new Scanner(System.in); //numerical
        boolean hiringDay;

        //create outer and inner map one after another
        TreeMap<LocalDate, TreeMap<Integer, Employee>> hiredEmployees = new TreeMap<>(); //outer

        System.out.println("---Hiring Log---");
        do {
            boolean hiring;

            System.out.print("Enter a date in MM-DD-YYYY format: ");
            LocalDate userDate = LocalDate.parse(input1.nextLine(), DateTimeFormatter.ofPattern("MM-dd-yyyy"));

            //we want a new inner map every day, so we scope locally
            TreeMap<Integer, Employee> employeesByDate = new TreeMap<>(); //inner
            do {
                System.out.print("Enter ID: ");
                int emplID = input2.nextInt();
                System.out.print("Enter name: ");
                String emplName = input1.nextLine();
                System.out.print("Enter job: ");
                String emplJob = input1.nextLine();
                System.out.print("Enter salary: ");
                BigDecimal emplSalary = new BigDecimal(input1.nextLine());

                Employee newEmpl = new Employee(emplID, emplName, emplJob, emplSalary);

                System.out.println("newEmpl = " + newEmpl); //test

                employeesByDate.put(newEmpl.getId(), newEmpl); //add to inner first
                hiredEmployees.put(userDate, employeesByDate); //add to outer second

                System.out.print("Hire another? (y/n): ");
                hiring = input1.nextLine().equals("y");
                System.out.println("-------");

                //data marshalling, write to file
                String fileName = "record_" + userDate.toString() + ".txt";
                createHiredByDateFile(fileName, employeesByDate);
            } while (hiring);

            System.out.print("Start a new day of interviews? (y/n): ");
            hiringDay = input1.nextLine().equals("y");
            System.out.println("*******");
        } while (hiringDay);

        //print the maps, for each entry of outer, print all of inner
        hiredEmployees.forEach((date, emplMap) -> {
            System.out.println(date.toString());

            emplMap.forEach((id, empl) -> {
                System.out.println(id + " " + empl.toString());
            });
        });
    }

}
