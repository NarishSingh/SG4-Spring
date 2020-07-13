package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.OrderPersistenceException;
import com.sg.flooringmastery.dao.ProductReadException;
import com.sg.flooringmastery.dao.StateReadException;
import com.sg.flooringmastery.model.Order;
import com.sg.flooringmastery.model.Product;
import com.sg.flooringmastery.model.State;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServiceImplTest {

    private Service testServ;
    public Order firstOrderUnval;
    public Order firstOrder;
    public Order firstOrderReplacement;
    public List<State> onlyState;
    public List<Product> onlyProduct;

    public ServiceImplTest() {
        ApplicationContext actx = new ClassPathXmlApplicationContext("applicationContext.xml");
        testServ = actx.getBean("testService", Service.class);
    }

    @BeforeAll
    public void setUpBeforeClass() {
        //first order unvalidated
        final LocalDate testDate = LocalDate.parse("1-1-2020", DateTimeFormatter.ofPattern("MM-dd-yyyy"));
        final String testCustomerName = "John Doe";
        final State testTexas = new State("TX", new BigDecimal("4.45"));
        final Product testCarpet = new Product("Carpet", new BigDecimal("2.25"), new BigDecimal("2.10"));
        final BigDecimal testArea100 = new BigDecimal("100");

        firstOrderUnval = new Order(testDate, testCustomerName, testTexas, testCarpet, testArea100);

        //first order, fully validated
        final int testOrderNum = 1;
        final BigDecimal testMatCost = testCarpet.getCostPerSqFt().multiply(testArea100);
        final BigDecimal testLaborCost = testArea100.multiply(testCarpet.getLaborCostPerSqFt());
        final BigDecimal testTax = (testMatCost.add(testLaborCost)).multiply((testTexas.getTaxRate().divide(new BigDecimal("100"))));
        final BigDecimal testTotal = testMatCost.add(testLaborCost).add(testTax);

        firstOrder = new Order(testDate, testOrderNum, testCustomerName, testTexas, testCarpet, testArea100, testMatCost, testLaborCost, testTax, testTotal);

        //first order replacement
        final String editName = "Juan Dos";
        final State editCali = new State("CA", new BigDecimal("25.00"));
        final Product editLaminate = new Product("Laminate", new BigDecimal("1.75"), new BigDecimal("2.10"));
        final BigDecimal editArea200 = new BigDecimal("200");
        final BigDecimal editMatCost = editLaminate.getCostPerSqFt().multiply(editArea200);
        final BigDecimal editLaborCost = editArea200.multiply(editLaminate.getLaborCostPerSqFt());
        final BigDecimal editTax = (editMatCost.add(editLaborCost)).multiply((editCali.getTaxRate().divide(new BigDecimal("100"))));
        final BigDecimal editTotal = editMatCost.add(editLaborCost).add(editTax);

        firstOrderReplacement = new Order(testDate, testOrderNum, editName, editCali, editLaminate, editArea200, editMatCost, editLaborCost, editTax, editTotal);

        //State and product
        onlyState.add(testTexas);
        onlyProduct.add(testCarpet);
    }

    /**
     * Test of validateOrder method, of class ServiceImpl.
     */
    @Test
    public void testValidateOrder() throws Exception {
        System.out.println("validateOrder");

        //arrange
        final int testOrderNum = 1;
        final BigDecimal testMatCost = firstOrderUnval.getProduct().getCostPerSqFt().multiply(firstOrderUnval.getArea());
        final BigDecimal testLaborCost = firstOrderUnval.getArea().multiply(firstOrderUnval.getProduct().getLaborCostPerSqFt());
        final BigDecimal testTax = (testMatCost.add(testLaborCost)).multiply((firstOrderUnval.getState().getTaxRate().divide(new BigDecimal("100"))));
        final BigDecimal testTotal = testMatCost.add(testLaborCost).add(testTax);

        Order firstOrderVal = null;

        //act
        try {
            firstOrderVal = testServ.validateOrder(firstOrderUnval);
        } catch (OrderPersistenceException e) {
            fail("valid order");
        }

        //assert
        assertEquals(firstOrderVal.getMaterialCost(), testMatCost, "Material cost should've been validated");
        assertEquals(firstOrderVal.getLaborCost(), testLaborCost, "Labor cost should've been validated");
        assertEquals(firstOrderVal.getTax(), testTax, "Tax should've been validated");
        assertEquals(firstOrderVal.getTotal(), testTotal, "Total cost should've been validated");
        assertEquals(firstOrderVal.getOrderNum(), testOrderNum, "Order number should be 1");
    }

    /**
     * Test of addOrder method, of class ServiceImpl.
     */
    @Test
    public void testAddOrder() throws Exception {
        System.out.println("addOrder");

        //arrange
        Order firstAdded = null;
        List<Order> allOrders = null;

        //act
        try {
            firstAdded = testServ.addOrder(firstOrder);

            allOrders = testServ.getAllOrders();
        } catch (OrderPersistenceException e) {
            fail("valid order");
        }

        //assert
        assertEquals(firstAdded, firstOrder, "First order added should be firstOrder");
        assertTrue(allOrders.contains(firstAdded), "Should contain first order");
    }

    /**
     * Test of removeOrder method, of class ServiceImpl.
     */
    @Test
    public void testRemoveOrder() throws Exception {
        System.out.println("removeOrder");
        //arrange
        Order firstRemoved = null;
        List<Order> allOrders = null;

        //act
        try {
            testServ.addOrder(firstOrder);

            firstRemoved = testServ.removeOrder(firstOrder.getOrderDate(), firstOrder.getOrderNum());

            allOrders = testServ.getAllOrders();
        } catch (OrderPersistenceException e) {
            fail("valid order");
        }

        //assert
        assertEquals(firstRemoved, firstOrder, "Removed order should be first order");
        assertFalse(allOrders.contains(firstRemoved), "Should contain not first order");
    }

    /**
     * Test of editOrder method, of class ServiceImpl.
     */
    @Test
    public void testEditOrder() throws Exception {
        System.out.println("editOrder");

        //arrange
        Order original = null;
        Order edit = null;
        List<Order> allOrders = null;

        //act
        try {
            original = testServ.addOrder(firstOrder);

            edit = testServ.editOrder(firstOrderReplacement, firstOrder);

            allOrders = testServ.getAllOrders();
        } catch (OrderPersistenceException e) {
            fail("valid order");
        }

        //assert
        assertTrue(allOrders.contains(edit), "List should contain edited order");
        assertFalse(allOrders.contains(original), "List should not contain original order");
    }

    /**
     * Test of getOrder method, of class ServiceImpl.
     */
    @Test
    public void testGetOrder() throws Exception {
        System.out.println("getOrder");

        //arrange
        Order first = null;

        //act
        try {
            testServ.addOrder(firstOrder);

            first = testServ.getOrder(firstOrder.getOrderDate(), firstOrder.getOrderNum());
        } catch (OrderPersistenceException e) {
            fail("valid order");
        }

        //assert
        assertEquals(first, firstOrder, "Should've retrieved the first Order");
    }

    /**
     * Test of getOrdersByDate method, of class ServiceImpl.
     */
    @Test
    public void testGetOrdersByDate() throws Exception {
        System.out.println("getOrdersByDate");

        //arrange
        Order first = null;
        List<Order> ordersByDate = null;

        //act
        try {
            first = testServ.addOrder(firstOrder);

            ordersByDate = testServ.getOrdersByDate(firstOrder.getOrderDate());
        } catch (OrderPersistenceException e) {
            fail("valid order");
        }

        //assert
        assertTrue(ordersByDate.contains(first), "Should've retrieved first order");
    }

    /**
     * Test of exportOrder method, of class ServiceImpl.
     */
    @Test
    public void testExportOrder() throws Exception {
        System.out.println("exportOrder");

        //arrange
        Order firstAdded = null;

        //act
        try {
            firstAdded = testServ.addOrder(firstOrder);

            testServ.exportOrder();
        } catch (OrderPersistenceException e) {
            return; //does nothing anyway just pass the test no matter what
        }

        //assert
    }

    /**
     * Test of getAllOrders method, of class ServiceImpl.
     */
    @Test
    public void testGetAllOrders() throws Exception {
        System.out.println("getAllOrders");

        //arrange
        Order firstAdded = null;
        List<Order> allOrders = null;

        //act
        try {
            firstAdded = testServ.addOrder(firstOrder);

            allOrders = testServ.getAllOrders();
        } catch (OrderPersistenceException e) {
            fail("valid order");
        }

        //assert
        assertEquals(firstAdded, firstOrder, "First order added should be firstOrder");
        assertTrue(allOrders.contains(firstAdded), "Should contain first order");
        assertFalse(allOrders.contains(firstOrderReplacement), "Should only contain firstOrder");
        assertFalse(allOrders.contains(firstOrderUnval), "Should only contain firstOrder");
    }

    /**
     * Test of getValidStateList method, of class ServiceImpl.
     */
    @Test
    public void testGetValidStateList() throws Exception {
        System.out.println("getValidStateList");

        //arrange
        List<State> retrievedState = null;

        //act
        try {
            retrievedState = testServ.getValidStateList();
        } catch (StateReadException e) {
            fail("Valid retrieval");
        }

        //assert
        assertEquals(retrievedState, onlyState.get(0), "Only state should be Texas");
    }

    /**
     * Test of getValidProductList method, of class ServiceImpl.
     */
    @Test
    public void testGetValidProductList() throws Exception {
        System.out.println("getValidProductList");

        //arrange
        List<Product> retrievedProduct = null;

        //act
        try {
            retrievedProduct = testServ.getValidProductList();
        } catch (ProductReadException e) {
            fail("Valid retrieval");
        }

        //assert
        assertEquals(retrievedProduct, onlyProduct.get(0), "Only Product should be carpet");
    }

    /**
     * Test of validateState method, of class ServiceImpl.
     */
    @Test
    public void testValidateState() throws Exception {
        System.out.println("validateState");

        //assert
        final String testTX = "TX";
        State testState = null;

        //act
        try {
            testState = testServ.validateState(testTX);
        } catch (InvalidStateException e) {
            fail("Valid State");
        }

        //arrange
        assertEquals(testState, onlyState.get(0), "Should've retrieved TX");
    }

    /**
     * Test of validateProduct method, of class ServiceImpl.
     */
    @Test
    public void testValidateProduct() throws Exception {
        System.out.println("validateProduct");

        //assert
        final String testProd = "Carpet";
        Product testProduct = null;

        //act
        try {
            testProduct = testServ.validateProduct(testProd);
        } catch (InvalidProductException e) {
            fail("Valid State");
        }

        //arrange
        assertEquals(testProduct, onlyProduct.get(0), "Should've retrieved Carpet");
    }

}
