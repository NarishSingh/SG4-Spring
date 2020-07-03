package com.sg.scratchpad.multifile;

import java.math.BigDecimal;

public class Employee {

    private int id;
    private String name;
    private String job;
    private BigDecimal salary;

    public Employee(int id, String name, String job, BigDecimal salary) {
        this.id = id;
        this.name = name;
        this.job = job;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", name=" + name + ", job=" + job + ", salary=" + salary + '}';
    }

}
