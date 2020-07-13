package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.model.Order;
import com.sg.flooringmastery.model.Product;
import com.sg.flooringmastery.model.State;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class OrderDaoImplTest {

    OrderDao testDao;

    public OrderDaoImplTest() {
    }
    
    //TODO try to make the 3 orders @BeforeAll

    @BeforeEach
    public void setUp() throws IOException {
        String testDir = ".\\TestingFileData\\Orders";
//        new FileWriter(new File(testDir, "testOrder.txt"));
        new FileWriter(new File(testDir));
        
        ApplicationContext actx = new ClassPathXmlApplicationContext("applicationContext.xml");
        testDao = actx.getBean("testOrderDao", OrderDaoImpl.class);
    }

    /**
     * Test of addOrder, getOrder, getAllOrders method, of class OrderDaoImpl.
     */
    @Test
    public void testAddGetOrder() throws Exception {
        System.out.println("addOrder");
        
        //arrange
        final LocalDate testDate = LocalDate.parse("1-1-2020", DateTimeFormatter.ofPattern("MM-dd-yyyy"));
        final int testOrderNum = 1;
        final String testCustomerName = "John Doe";
        final State testTexas = new State("TX", new BigDecimal("4.45"));
        final Product testCarpet = new Product("Carpet", new BigDecimal("2.25"), new BigDecimal("2.10"));
        final BigDecimal testArea100 = new BigDecimal("100");
        final BigDecimal testMatCost = testCarpet.getCostPerSqFt().multiply(testArea100);
        final BigDecimal testLaborCost = testArea100.multiply(testCarpet.getLaborCostPerSqFt());
        final BigDecimal testTax = (testMatCost.add(testLaborCost)).multiply((testTexas.getTaxRate().divide(new BigDecimal("100"))));
        final BigDecimal testTotal = testMatCost.add(testLaborCost).add(testTax);
        
        Order newOrder = new Order(testDate, testOrderNum, testCustomerName, testTexas, testCarpet, testArea100, testMatCost, testLaborCost, testTax, testTotal);
        
        //act
        testDao.addOrder(newOrder);
        Order added = testDao.getOrder(testDate, testOrderNum);
        List<Order> allOrders = testDao.getAllOrders();
                
        //assert
        assertEquals(added, newOrder, "Order should've been added");
        assertTrue(allOrders.contains(added), "Order list should contain added order");
    }

    /**
     * Test of removeOrder method, of class OrderDaoImpl.
     */
    @Test
    public void testRemoveOrder() throws Exception {
        System.out.println("removeOrder");
        //arrange
        final LocalDate testDate = LocalDate.parse("1-1-2020", DateTimeFormatter.ofPattern("MM-dd-yyyy"));
        final int testOrderNum = 1;
        final String testCustomerName = "John Doe";
        final State testTexas = new State("TX", new BigDecimal("4.45"));
        final Product testCarpet = new Product("Carpet", new BigDecimal("2.25"), new BigDecimal("2.10"));
        final BigDecimal testArea100 = new BigDecimal("100");
        final BigDecimal testMatCost = testCarpet.getCostPerSqFt().multiply(testArea100);
        final BigDecimal testLaborCost = testArea100.multiply(testCarpet.getLaborCostPerSqFt());
        final BigDecimal testTax = (testMatCost.add(testLaborCost)).multiply((testTexas.getTaxRate().divide(new BigDecimal("100"))));
        final BigDecimal testTotal = testMatCost.add(testLaborCost).add(testTax);
        
        Order newOrder = new Order(testDate, testOrderNum, testCustomerName, testTexas, testCarpet, testArea100, testMatCost, testLaborCost, testTax, testTotal);
        
        //act
        testDao.addOrder(newOrder);
        Order removed = testDao.removeOrder(testDate, testOrderNum);
        List<Order> allOrders = testDao.getAllOrders();
        
        //assert
        assertEquals(removed, newOrder, "Order should've been removed");
        assertFalse(allOrders.contains(removed), "Order list should not contain removed order");
    }

    /**
     * Test of editOrder method, of class OrderDaoImpl.
     */
    @Test
    public void testEditOrder() throws Exception {
        System.out.println("editOrder");
        //arrange
        final LocalDate testDate = LocalDate.parse("1-1-2020", DateTimeFormatter.ofPattern("MM-dd-yyyy"));
        final int testOrderNum = 1;
        final String testCustomerName = "John Doe";
        final State testTexas = new State("TX", new BigDecimal("4.45"));
        final Product testCarpet = new Product("Carpet", new BigDecimal("2.25"), new BigDecimal("2.10"));
        final BigDecimal testArea100 = new BigDecimal("100");
        final BigDecimal testMatCost = testCarpet.getCostPerSqFt().multiply(testArea100);
        final BigDecimal testLaborCost = testArea100.multiply(testCarpet.getLaborCostPerSqFt());
        final BigDecimal testTax = (testMatCost.add(testLaborCost)).multiply((testTexas.getTaxRate().divide(new BigDecimal("100"))));
        final BigDecimal testTotal = testMatCost.add(testLaborCost).add(testTax);
        
        Order originalOrder = new Order(testDate, testOrderNum, testCustomerName, testTexas, testCarpet, testArea100, testMatCost, testLaborCost, testTax, testTotal);
        
        //act
        final String editName = "Juan Dos";
        final State editCali = new State("CA", new BigDecimal("25.00"));
        final Product editLaminate = new Product("Laminate", new BigDecimal("1.75"), new BigDecimal("2.10"));
        final BigDecimal editArea200 = new BigDecimal("200");
        final BigDecimal editMatCost = editLaminate.getCostPerSqFt().multiply(editArea200);
        final BigDecimal editLaborCost = editArea200.multiply(editLaminate.getLaborCostPerSqFt());
        final BigDecimal editTax = (editMatCost.add(editLaborCost)).multiply((editCali.getTaxRate().divide(new BigDecimal("100"))));
        final BigDecimal editTotal = editMatCost.add(editLaborCost).add(editTax);
        
        Order editOrder = new Order(testDate, testOrderNum, editName, editCali, editLaminate, editArea200, editMatCost, editLaborCost, editTax, editTotal);
        
        testDao.editOrder(originalOrder, editOrder);
        Order edited = testDao.getOrder(testDate, testOrderNum);
        List<Order> allOrders = testDao.getAllOrders();
        
        //assert
        assertEquals(edited, editOrder, "Edited order should be editOrder");
        assertNotEquals(edited, originalOrder, "Edited order should not be originalOrder");
        assertTrue(allOrders.contains(editOrder), "List should contain editOrder");
        assertFalse(allOrders.contains(originalOrder), "List should not contain originalOrder");
    }

    /**
     * Test of getOrdersByDate method, of class OrderDaoImpl.
     */
    @Test
    public void testGetOrdersByDate() throws Exception {
        System.out.println("getOrdersByDate");
        //arrange
        final LocalDate testDate = LocalDate.parse("1-1-2020", DateTimeFormatter.ofPattern("MM-dd-yyyy"));
        
        final int testOrderNum = 1;
        final String testCustomerName = "John Doe";
        final State testTexas = new State("TX", new BigDecimal("4.45"));
        final Product testCarpet = new Product("Carpet", new BigDecimal("2.25"), new BigDecimal("2.10"));
        final BigDecimal testArea100 = new BigDecimal("100");
        final BigDecimal testMatCost = testCarpet.getCostPerSqFt().multiply(testArea100);
        final BigDecimal testLaborCost = testArea100.multiply(testCarpet.getLaborCostPerSqFt());
        final BigDecimal testTax = (testMatCost.add(testLaborCost)).multiply((testTexas.getTaxRate().divide(new BigDecimal("100"))));
        final BigDecimal testTotal = testMatCost.add(testLaborCost).add(testTax);
        
        Order firstOrder = new Order(testDate, testOrderNum, testCustomerName, testTexas, testCarpet, testArea100, testMatCost, testLaborCost, testTax, testTotal);
        
        final int nextOrderNum = 2;
        final String nextName = "Juan Dos";
        final State nextCali = new State("CA", new BigDecimal("25.00"));
        final Product nextLaminate = new Product("Laminate", new BigDecimal("1.75"), new BigDecimal("2.10"));
        final BigDecimal nextArea200 = new BigDecimal("200");
        final BigDecimal nextMatCost = nextLaminate.getCostPerSqFt().multiply(nextArea200);
        final BigDecimal nextLaborCost = nextArea200.multiply(nextLaminate.getLaborCostPerSqFt());
        final BigDecimal nextTax = (nextMatCost.add(nextLaborCost)).multiply((nextCali.getTaxRate().divide(new BigDecimal("100"))));
        final BigDecimal nextTotal = nextMatCost.add(nextLaborCost).add(nextTax);
        
        Order secondOrder = new Order(testDate, nextOrderNum, nextName, nextCali, nextLaminate, nextArea200, nextMatCost, nextLaborCost, nextTax, nextTotal);

        //not on same date
        final int thirdOrderNum = 3;
        final String thirdName = "Anthony Third";
        final State thirdCali = new State("CA", new BigDecimal("25.00"));
        final Product thirdLaminate = new Product("Laminate", new BigDecimal("1.75"), new BigDecimal("2.10"));
        final BigDecimal thirdArea200 = new BigDecimal("300");
        final BigDecimal thirdMatCost = thirdLaminate.getCostPerSqFt().multiply(thirdArea200);
        final BigDecimal thirdLaborCost = thirdArea200.multiply(thirdLaminate.getLaborCostPerSqFt());
        final BigDecimal thirdTax = (thirdMatCost.add(thirdLaborCost)).multiply((thirdCali.getTaxRate().divide(new BigDecimal("100"))));
        final BigDecimal thirdTotal = thirdMatCost.add(thirdLaborCost).add(thirdTax);
        
        Order thirdOrder = new Order(testDate, thirdOrderNum, thirdName, thirdCali, thirdLaminate, thirdArea200, thirdMatCost, thirdLaborCost, thirdTax, thirdTotal);
        
        //act
        testDao.addOrder(firstOrder);
        testDao.addOrder(secondOrder);
        testDao.addOrder(thirdOrder);
        List<Order> ordersOnDate = testDao.getOrdersByDate(testDate);

        //assert
        assertTrue(ordersOnDate.contains(firstOrder), "List should contain first order");
        assertTrue(ordersOnDate.contains(secondOrder), "List should contain second order");
        assertFalse(ordersOnDate.contains(thirdOrder), "List should not contain third order");
        
    }

}
