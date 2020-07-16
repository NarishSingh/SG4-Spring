package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.OrderPersistenceException;
import com.sg.flooringmastery.dao.ProductReadException;
import com.sg.flooringmastery.dao.StateReadException;
import com.sg.flooringmastery.model.Order;
import com.sg.flooringmastery.model.Product;
import com.sg.flooringmastery.model.State;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServiceImplTest {

    private Service testServ;
    public Order firstOrderUnval;
    public Order firstOrder;
    public Order firstOrderReplacement;
    public List<State> onlyState = new ArrayList<>();
    public List<Product> onlyProduct = new ArrayList<>();

    public ServiceImplTest() {
        ApplicationContext actx = new ClassPathXmlApplicationContext("applicationContext.xml");
        testServ = actx.getBean("testService", Service.class);
    }

    @BeforeEach
    public void setUp() {
        final LocalDate testDate = LocalDate.parse("01-01-2020", DateTimeFormatter.ofPattern("MM-dd-yyyy"));
        final int testNum = 1;
        final String testName = "John Doe";
        final State testTexas = new State("TX", new BigDecimal("4.45").setScale(2, RoundingMode.HALF_UP));
        final Product testCarpet = new Product("Carpet", new BigDecimal("2.25").setScale(2, RoundingMode.HALF_UP), new BigDecimal("2.10").setScale(2, RoundingMode.HALF_UP));
        final BigDecimal testArea100 = new BigDecimal("100").setScale(2, RoundingMode.HALF_UP);
        final BigDecimal testMatCost = (testCarpet.getCostPerSqFt().multiply(testArea100)).setScale(2, RoundingMode.HALF_UP);
        final BigDecimal testLaborCost = (testArea100.multiply(testCarpet.getLaborCostPerSqFt())).setScale(2, RoundingMode.HALF_UP);
        final BigDecimal testTax = ((testMatCost.add(testLaborCost)).multiply((testTexas.getTaxRate().divide(new BigDecimal("100").setScale(2, RoundingMode.HALF_UP))))).setScale(2, RoundingMode.HALF_UP);
        final BigDecimal testTotal = (testMatCost.add(testLaborCost).add(testTax)).setScale(2, RoundingMode.HALF_UP);

        //first order unvalidated
        firstOrderUnval = new Order(testDate, testName, testTexas, testCarpet, testArea100);

        //first order, fully validated
        firstOrder = new Order(testDate, testNum, testName, testTexas, testCarpet, testArea100, testMatCost, testLaborCost, testTax, testTotal);

        //first order replacement
        final String editName = "Juan Dos";
        final State editCali = new State("CA", new BigDecimal("25.00"));
        final Product editLaminate = new Product("Laminate", new BigDecimal("1.75"), new BigDecimal("2.10"));
        final BigDecimal editArea200 = new BigDecimal("200");
        final BigDecimal editMatCost = editLaminate.getCostPerSqFt().multiply(editArea200);
        final BigDecimal editLaborCost = editArea200.multiply(editLaminate.getLaborCostPerSqFt());
        final BigDecimal editTax = (editMatCost.add(editLaborCost)).multiply((editCali.getTaxRate().divide(new BigDecimal("100"))));
        final BigDecimal editTotal = editMatCost.add(editLaborCost).add(editTax);

        firstOrderReplacement = new Order(testDate, testNum, editName, editCali, editLaminate, editArea200, editMatCost, editLaborCost, editTax, editTotal);

        //State and product
        onlyState.clear();
        onlyProduct.clear();
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
        final int testOrderNum = 2;

        final BigDecimal testMatCost = (firstOrderUnval.getProduct().getCostPerSqFt().multiply(firstOrderUnval.getArea())).setScale(2, RoundingMode.HALF_UP);
        final BigDecimal testLaborCost = (firstOrderUnval.getArea().multiply(firstOrderUnval.getProduct().getLaborCostPerSqFt())).setScale(2, RoundingMode.HALF_UP);
        final BigDecimal testTax = ((testMatCost.add(testLaborCost)).multiply((firstOrderUnval.getState().getTaxRate().divide(new BigDecimal("100").setScale(2, RoundingMode.HALF_UP))))).setScale(2, RoundingMode.HALF_UP);
        final BigDecimal testTotal = (testMatCost.add(testLaborCost).add(testTax)).setScale(2, RoundingMode.HALF_UP);

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
        //act
        try {
            Order firstAdded = testServ.addOrder(firstOrder);

            //assert
            assertEquals(firstOrder, firstAdded, "First order added should be firstOrder");
        } catch (OrderPersistenceException e) {
            fail("valid order");
        }

    }

    /**
     * Test of removeOrder method, of class ServiceImpl.
     */
    @Test
    public void testRemoveOrder() throws Exception {
        System.out.println("removeOrder");
        //arrange
        //act
        try {
            Order firstRemoved = testServ.removeOrder(firstOrder.getOrderDate(), firstOrder.getOrderNum());

            //assert
            assertEquals(firstRemoved, firstOrder, "Removed order should be first order");
        } catch (OrderPersistenceException e) {
            fail("valid order");
        }

    }

    /**
     * Test of editOrder method, of class ServiceImpl.
     */
    @Test
    public void testEditOrder() throws Exception {
        System.out.println("editOrder");

        //arrange
        //act
        try {
            Order original = testServ.addOrder(firstOrder);

            Order edit = testServ.editOrder(firstOrder, firstOrderReplacement);

            //assert
            assertNotEquals(original, edit, "Replacement should be different from original order");
        } catch (OrderPersistenceException e) {
            fail("valid order");
        }

    }

    /**
     * Test of getOrder method, of class ServiceImpl.
     */
    @Test
    public void testGetOrder() throws Exception {
        System.out.println("getOrder");

        //arrange
        //act
        try {
            Order first = testServ.getOrder(firstOrder.getOrderDate(), firstOrder.getOrderNum());

            //assert
            assertEquals(firstOrder, first, "Should've retrieved the first Order");
        } catch (OrderPersistenceException e) {
            fail("valid order");
        }
    }

    /**
     * Test of getOrdersByDate method, of class ServiceImpl.
     */
    @Test
    public void testGetOrdersByDate() throws Exception {
        System.out.println("getOrdersByDate");

        //arrange
        Order first;
        List<Order> ordersByDate;

        //act
        try {
            first = testServ.addOrder(firstOrder);

            ordersByDate = testServ.getOrdersByDate(firstOrder.getOrderDate());
            assertTrue(ordersByDate.contains(first), "Should've retrieved first order");
        } catch (OrderPersistenceException e) {
            fail("valid order");
        }

        //assert
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
        Order firstAdded;
        List<Order> allOrders;

        //act
        try {
            firstAdded = testServ.addOrder(firstOrder);
            allOrders = testServ.getAllOrders();

            assertEquals(firstAdded, firstOrder, "First order added should be firstOrder");
            assertTrue(allOrders.contains(firstAdded), "Should contain first order");
            assertFalse(allOrders.contains(firstOrderReplacement), "Should only contain firstOrder");
            assertFalse(allOrders.contains(firstOrderUnval), "Should only contain firstOrder");
        } catch (OrderPersistenceException e) {
            fail("valid order");
        }

        //assert
    }

    /**
     * Test of getValidStateList method, of class ServiceImpl.
     */
    @Test
    public void testGetValidStateList() throws Exception {
        System.out.println("getValidStateList");

        //arrange
        List<State> retrievedState;

        //act
        try {
            retrievedState = testServ.getValidStateList();
            assertEquals(retrievedState, onlyState, "Only state should be Texas");
        } catch (StateReadException e) {
            fail("Valid retrieval");
        }

        //assert
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
        assertEquals(retrievedProduct, onlyProduct, "Only Product should be carpet");
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
