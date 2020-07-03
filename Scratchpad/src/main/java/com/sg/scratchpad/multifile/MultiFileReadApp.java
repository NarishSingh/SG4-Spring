/*
multiple file io
TreeMap inside of a TreeMap

use the File class
https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/io/File.html
 */
package com.sg.scratchpad.multifile;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.TreeMap;

public class MultiFileReadApp {

    public static void main(String[] args) throws IOException {
        Scanner input1 = new Scanner(System.in); //string
        Scanner input2 = new Scanner(System.in); //numerical
        PrintWriter out;
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

                Employee newEmpl = new Employee(userDate, emplID, emplName, emplJob, emplSalary);

                System.out.println("newEmpl = " + newEmpl); //test

                employeesByDate.put(newEmpl.getId(), newEmpl); //add to inner first
                hiredEmployees.put(userDate, employeesByDate); //add to outer second

                System.out.print("Hire another? (y/n): ");
                hiring = input1.nextLine().equals("y");
                System.out.println("-------");
            } while (hiring);

            System.out.print("Start a new day of interviews? (y/n): ");
            hiringDay = input1.nextLine().equals("y");
            System.out.println("*******");
        } while (hiringDay);

        //print the maps
        hiredEmployees.forEach((date, emplMap) -> {
            System.out.println(date.toString());
            
            emplMap.forEach((id, empl) -> {
                System.out.println(id + " " + empl.toString());
            });
        });

        /*
        String fileName = "record_" + userDate.toString() + ".txt";
        try {
            out = new PrintWriter(new FileWriter(fileName));
            //add logic here
            out.close();
        } catch (IOException e) {
            System.out.println("Could not write to file.");
        }
         */
    }

}
