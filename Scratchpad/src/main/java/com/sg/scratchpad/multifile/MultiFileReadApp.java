/*
multiple file io
TreeMap inside of a TreeMap

use the File class
https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/io/File.html
 */
package com.sg.scratchpad.multifile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.TreeMap;

public class MultiFileReadApp {

    public static final String DELIMITER = "::";
    public static TreeMap<LocalDate, TreeMap<Integer, Employee>> hired = new TreeMap<>();

    /**
     * Marshall an employee to text
     *
     * @param empl {Employee} a hired worker
     * @return {String} a delimited String with the new hiree's information
     */
    static public String marshallEmpl(Employee empl) {
        String emplAsText = empl.getHireDate() + DELIMITER;
        emplAsText += empl.getId() + DELIMITER;
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

        LocalDate emplHireDate = LocalDate.parse(emplTokens[0]);
        int emplID = Integer.parseInt(emplTokens[1]);
        String emplName = emplTokens[2];
        String emplJob = emplTokens[3];
        BigDecimal emplSalary = new BigDecimal(emplTokens[4]);

        Employee emplFromFile = new Employee(emplHireDate, emplID, emplName, emplJob, emplSalary);

        return emplFromFile;
    }

    /**
     * Create new files per date recording new hires from that day
     *
     * @param fileName        {String} the name of the .txt file, formatted as
     *                        "record_" with date
     * @param employeesByDate {TreeMap} all new hires that day
     */
    public static void createHiredByDateFile(String fileName, TreeMap<Integer, Employee> employeesByDate) {
        PrintWriter out;
        try {
            //to create the files in a specific directory, nest a new file obj ctor in a new FileWriter
            out = new PrintWriter(new FileWriter(new File("test_docs", fileName)));

            //try to always encapsulate this in a function such that it feels as if the vars are all effectively final
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

    /**
     * Read in files from the directory and construct the main TreeMap with hire
     * record
     */
    public static void loadHireRecords() {
        File dir = new File("test_docs");

        //for every file in directory
        for (File file : dir.listFiles()) {
            TreeMap<Integer, Employee> emplHiredByDate = new TreeMap<>(); //temp inner Map
            String currentLine;
            Employee currentEmpl;

            //load file
            Scanner sc;
            try {
                sc = new Scanner(new BufferedReader(new FileReader(file)));
            } catch (FileNotFoundException e) {
                System.out.println("Can't load in file from directory");
                break;
            }

            emplHiredByDate.clear();

            LocalDate hiredMapDate = LocalDate.now(); //just for initialization purposes

            //unmarshall
            while (sc.hasNextLine()) {
                currentLine = sc.nextLine();
                currentEmpl = unmarshallEmpl(currentLine);

                emplHiredByDate.put(currentEmpl.getId(), currentEmpl);

                hiredMapDate = currentEmpl.getHireDate();
            }

            hired.put(hiredMapDate, emplHiredByDate);

            sc.close();
        }
    }

    public static void main(String[] args) {
        Scanner input1 = new Scanner(System.in); //string
        Scanner input2 = new Scanner(System.in); //numerical
        boolean hiringDay;
        DateTimeFormatter mmddyyyy = DateTimeFormatter.ofPattern("MM-dd-yyyy");

        System.out.println("---Hiring Log---");
        do {
            boolean hiring;
            loadHireRecords();

            System.out.print("Enter a date in MM-DD-YYYY format: ");
            LocalDate userDate = LocalDate.parse(input1.nextLine(), DateTimeFormatter.ofPattern("MM-dd-yyyy"));

            do {
                //we want a new inner map every hiring day, so we scope locally
                //since the outer map was loaded, we need to catch the old data or else we will re-write it
                TreeMap<Integer, Employee> employeesByDate = hired.get(userDate); //inner
                if (employeesByDate == null) {
                    employeesByDate = new TreeMap<>(); //to ensure not null for a new date
                }

                System.out.print("Enter ID: ");
                int emplID = input2.nextInt();
                System.out.print("Enter name: ");
                String emplName = input1.nextLine();
                System.out.print("Enter job: ");
                String emplJob = input1.nextLine();
                System.out.print("Enter salary: ");
                BigDecimal emplSalary = new BigDecimal(input1.nextLine());

                Employee newEmpl = new Employee(userDate, emplID, emplName, emplJob, emplSalary);

                System.out.println("newEmpl = " + newEmpl); //test

                employeesByDate.put(newEmpl.getId(), newEmpl); //put to inner first
                hired.put(userDate, employeesByDate); //put to outer second

                System.out.print("Hire another? (y/n): ");
                hiring = input1.nextLine().equals("y");
                System.out.println("-------");

                //data marshalling, write to file
                String userDateFormatted = userDate.format(mmddyyyy);
                String fileName = "record_" + userDateFormatted + ".txt";
                createHiredByDateFile(fileName, employeesByDate);
            } while (hiring);

            System.out.print("Start a new day of interviews? (y/n): ");
            hiringDay = input1.nextLine().equals("y");
            System.out.println("*******");
        } while (hiringDay);

        //print the maps, for each entry of outer, print all of inner
        hired.forEach((date, emplMap) -> {
            System.out.println(date.format(mmddyyyy));

            emplMap.forEach((id, empl) -> {
                System.out.println(id + " " + empl.toString());
            });
        });
    }

}
