package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.OrderDao;
import com.sg.flooringmastery.dao.OrderPersistenceException;
import com.sg.flooringmastery.model.Order;
import com.sg.flooringmastery.model.Product;
import com.sg.flooringmastery.model.State;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class OrderDaoImplStub implements OrderDao {

    public Order onlyOrder;

    public OrderDaoImplStub() {
        final LocalDate testDate = LocalDate.parse("2020-1-1");
        final State testTexas = new State("TX", new BigDecimal("4.45"));
        final Product testCarpet = new Product("Carpet", new BigDecimal("2.25"), new BigDecimal("2.10"));
        final BigDecimal testArea100 = new BigDecimal("100");
        final BigDecimal testMatCost = testCarpet.getCostPerSqFt().multiply(testArea100);
        final BigDecimal testLaborCost = testArea100.multiply(testCarpet.getLaborCostPerSqFt());
        final BigDecimal testTax = (testMatCost.add(testLaborCost)).multiply((testTexas.getTaxRate().divide(new BigDecimal("100"))));
        final BigDecimal testTotal = testMatCost.add(testLaborCost).add(testTax);

        this.onlyOrder = new Order(testDate, 0, "John Doe", testTexas, testCarpet, testArea100, testMatCost, testLaborCost, testTax, testTotal);
    }

    public OrderDaoImplStub(Order onlyOrder) {
        this.onlyOrder = onlyOrder;
    }

    @Override
    public Order addOrder(Order newOrder) throws OrderPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order removeOrder(LocalDate removalDate, int removalID) throws OrderPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order getOrder(LocalDate Date, int id) throws OrderPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order editOrder(Order orderToReplace, Order orderEdit) throws OrderPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Order> getOrdersByDate(LocalDate date) throws OrderPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Order> getAllOrders() throws OrderPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getHighestOrderNumber() throws OrderPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
