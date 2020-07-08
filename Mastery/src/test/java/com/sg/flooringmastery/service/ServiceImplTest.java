package com.sg.flooringmastery.service;

import com.sg.flooringmastery.model.Order;
import com.sg.flooringmastery.model.Product;
import com.sg.flooringmastery.model.State;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServiceImplTest {
    
    private Service testServ;
    
    public ServiceImplTest() {
        ApplicationContext actx = new ClassPathXmlApplicationContext("applicationContext.xml");
        testServ = actx.getBean("testService", Service.class);
    }
    
    /**
     * Test of validateOrder method, of class ServiceImpl.
     */
    @Test
    public void testValidateOrder() throws Exception {
        System.out.println("validateOrder");
        Order orderRequest = null;
        ServiceImpl instance = null;
        Order expResult = null;
        Order result = instance.validateOrder(orderRequest);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addOrder method, of class ServiceImpl.
     */
    @Test
    public void testAddOrder() throws Exception {
        System.out.println("addOrder");
        Order newOrder = null;
        ServiceImpl instance = null;
        Order expResult = null;
        Order result = instance.addOrder(newOrder);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeOrder method, of class ServiceImpl.
     */
    @Test
    public void testRemoveOrder() throws Exception {
        System.out.println("removeOrder");
        LocalDate date = null;
        int orderNum = 0;
        ServiceImpl instance = null;
        Order expResult = null;
        Order result = instance.removeOrder(date, orderNum);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of editOrder method, of class ServiceImpl.
     */
    @Test
    public void testEditOrder() throws Exception {
        System.out.println("editOrder");
        Order editedOrder = null;
        ServiceImpl instance = null;
        Order expResult = null;
        Order result = instance.editOrder(editedOrder);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOrder method, of class ServiceImpl.
     */
    @Test
    public void testGetOrder() {
        System.out.println("getOrder");
        LocalDate date = null;
        int orderNum = 0;
        ServiceImpl instance = null;
        Order expResult = null;
        Order result = instance.getOrder(date, orderNum);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOrdersByDate method, of class ServiceImpl.
     */
    @Test
    public void testGetOrdersByDate() {
        System.out.println("getOrdersByDate");
        LocalDate date = null;
        ServiceImpl instance = null;
        List<Order> expResult = null;
        List<Order> result = instance.getOrdersByDate(date);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of exportOrder method, of class ServiceImpl.
     */
    @Test
    public void testExportOrder() {
        System.out.println("exportOrder");
        ServiceImpl instance = null;
        instance.exportOrder();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllOrders method, of class ServiceImpl.
     */
    @Test
    public void testGetAllOrders() {
        System.out.println("getAllOrders");
        ServiceImpl instance = null;
        List<Order> expResult = null;
        List<Order> result = instance.getAllOrders();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValidStateList method, of class ServiceImpl.
     */
    @Test
    public void testGetValidStateList() {
        System.out.println("getValidStateList");
        ServiceImpl instance = null;
        List<State> expResult = null;
        List<State> result = instance.getValidStateList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValidProductList method, of class ServiceImpl.
     */
    @Test
    public void testGetValidProductList() {
        System.out.println("getValidProductList");
        ServiceImpl instance = null;
        List<Product> expResult = null;
        List<Product> result = instance.getValidProductList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
