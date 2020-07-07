package com.sg.flooringmastery.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Order {

    /*fields*/
    private LocalDate orderDate;
    private int orderNum;
    private String customerName;
    private State state;
    private Product product;
    private BigDecimal area;
    private BigDecimal materialCost;
    private BigDecimal tax;
    private BigDecimal total;

    /*ctors*/
    //view to service
    public Order(LocalDate orderDate, String customerName, State state, Product productType, BigDecimal area) {
        this.orderDate = orderDate;
        this.customerName = customerName;
        this.state = state;
        this.product = productType;
        this.area = area;
    }

    //service down
    public Order(LocalDate orderDate, int orderNum, String customerName, State state, Product productType, BigDecimal area, BigDecimal materialCost, BigDecimal tax, BigDecimal total) {
        this.orderDate = orderDate;
        this.orderNum = orderNum;
        this.customerName = customerName;
        this.state = state;
        this.product = productType;
        this.area = area;
        this.materialCost = materialCost;
        this.tax = tax;
        this.total = total;
    }

    /*read/write*/
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getMaterialCost() {
        return materialCost;
    }

    public void setMaterialCost(BigDecimal materialCost) {
        this.materialCost = materialCost;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    /*read-only*/
    public LocalDate getOrderDate() {
        return orderDate;
    }

    public int getOrderNum() {
        return orderNum;
    }

}
