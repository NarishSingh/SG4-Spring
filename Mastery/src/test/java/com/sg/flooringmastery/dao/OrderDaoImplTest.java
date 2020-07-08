package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.model.Order;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class OrderDaoImplTest {

    OrderDao testDao;

    public OrderDaoImplTest() {
    }

    @BeforeEach
    public void setUp() throws IOException {
        String testFile = ".//TestingFileData//Orders";
        new FileWriter(testFile);

        ApplicationContext actx = new ClassPathXmlApplicationContext("applicationContext.xml");
        testDao = actx.getBean("testOrderDao", OrderDaoImpl.class);
    }

    /**
     * Test of addOrder method, of class OrderDaoImpl.
     */
    @Test
    public void testAddOrder() throws Exception {
        System.out.println("addOrder");
        Order newOrder = null;
        OrderDaoImpl instance = new OrderDaoImpl();
        Order expResult = null;
        Order result = instance.addOrder(newOrder);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeOrder method, of class OrderDaoImpl.
     */
    @Test
    public void testRemoveOrder() throws Exception {
        System.out.println("removeOrder");
        LocalDate removalDate = null;
        int removalID = 0;
        OrderDaoImpl instance = new OrderDaoImpl();
        Order expResult = null;
        Order result = instance.removeOrder(removalDate, removalID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOrder method, of class OrderDaoImpl.
     */
    @Test
    public void testGetOrder() throws Exception {
        System.out.println("getOrder");
        LocalDate Date = null;
        int id = 0;
        OrderDaoImpl instance = new OrderDaoImpl();
        Order expResult = null;
        Order result = instance.getOrder(Date, id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of editOrder method, of class OrderDaoImpl.
     */
    @Test
    public void testEditOrder() throws Exception {
        System.out.println("editOrder");
        Order orderToReplace = null;
        Order orderEdit = null;
        OrderDaoImpl instance = new OrderDaoImpl();
        Order expResult = null;
        Order result = instance.editOrder(orderToReplace, orderEdit);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOrdersByDate method, of class OrderDaoImpl.
     */
    @Test
    public void testGetOrdersByDate() throws Exception {
        System.out.println("getOrdersByDate");
        LocalDate date = null;
        OrderDaoImpl instance = new OrderDaoImpl();
        List<Order> expResult = null;
        List<Order> result = instance.getOrdersByDate(date);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllOrders method, of class OrderDaoImpl.
     */
    @Test
    public void testGetAllOrders() throws Exception {
        System.out.println("getAllOrders");
        OrderDaoImpl instance = new OrderDaoImpl();
        List<Order> expResult = null;
        List<Order> result = instance.getAllOrders();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
