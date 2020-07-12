package com.sg.flooringmastery.service;

import com.sg.flooringmastery.model.Order;
import com.sg.flooringmastery.model.Product;
import com.sg.flooringmastery.model.State;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServiceImplTest {

    private Service testServ;

    public ServiceImplTest() {
        ApplicationContext actx = new ClassPathXmlApplicationContext("applicationContext.xml");
        testServ = actx.getBean("testServ", Service.class);

    }

    /**
     * Test of validateOrder method, of class ServiceImpl.
     */
    @Test
    public void testValidateOrder() throws Exception {
        System.out.println("validateOrder");
    }

    /**
     * Test of addOrder method, of class ServiceImpl.
     */
    @Test
    public void testAddOrder() throws Exception {
        System.out.println("addOrder");
    }

    /**
     * Test of removeOrder method, of class ServiceImpl.
     */
    @Test
    public void testRemoveOrder() throws Exception {
        System.out.println("removeOrder");
    }

    /**
     * Test of editOrder method, of class ServiceImpl.
     */
    @Test
    public void testEditOrder() throws Exception {
        System.out.println("editOrder");
    }

    /**
     * Test of getOrder method, of class ServiceImpl.
     */
    @Test
    public void testGetOrder() throws Exception {
        System.out.println("getOrder");
    }

    /**
     * Test of getOrdersByDate method, of class ServiceImpl.
     */
    @Test
    public void testGetOrdersByDate() throws Exception {
        System.out.println("getOrdersByDate");
    }

    /**
     * Test of exportOrder method, of class ServiceImpl.
     */
    @Test
    public void testExportOrder() throws Exception {
        System.out.println("exportOrder");
    }

    /**
     * Test of getAllOrders method, of class ServiceImpl.
     */
    @Test
    public void testGetAllOrders() throws Exception {
        System.out.println("getAllOrders");
    }

    /**
     * Test of getValidStateList method, of class ServiceImpl.
     */
    @Test
    public void testGetValidStateList() throws Exception {
        System.out.println("getValidStateList");
    }

    /**
     * Test of getValidProductList method, of class ServiceImpl.
     */
    @Test
    public void testGetValidProductList() throws Exception {
        System.out.println("getValidProductList");
    }

    /**
     * Test of validateState method, of class ServiceImpl.
     */
    @Test
    public void testValidateState() throws Exception {
        System.out.println("validateState");
    }

    /**
     * Test of validateProduct method, of class ServiceImpl.
     */
    @Test
    public void testValidateProduct() throws Exception {
        System.out.println("validateProduct");
    }

}
