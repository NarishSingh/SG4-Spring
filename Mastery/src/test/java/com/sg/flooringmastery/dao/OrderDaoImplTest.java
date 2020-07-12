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
    }

    /**
     * Test of removeOrder method, of class OrderDaoImpl.
     */
    @Test
    public void testRemoveOrder() throws Exception {
        System.out.println("removeOrder");
    }

    /**
     * Test of getOrder method, of class OrderDaoImpl.
     */
    @Test
    public void testGetOrder() throws Exception {
        System.out.println("getOrder");
    }

    /**
     * Test of editOrder method, of class OrderDaoImpl.
     */
    @Test
    public void testEditOrder() throws Exception {
        System.out.println("editOrder");
    }

    /**
     * Test of getOrdersByDate method, of class OrderDaoImpl.
     */
    @Test
    public void testGetOrdersByDate() throws Exception {
        System.out.println("getOrdersByDate");
    }

    /**
     * Test of getAllOrders method, of class OrderDaoImpl.
     */
    @Test
    public void testGetAllOrders() throws Exception {
        System.out.println("getAllOrders");
    }

}
