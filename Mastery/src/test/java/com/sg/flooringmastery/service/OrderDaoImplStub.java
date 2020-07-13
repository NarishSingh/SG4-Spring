package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.OrderDao;
import com.sg.flooringmastery.dao.OrderPersistenceException;
import com.sg.flooringmastery.model.Order;
import com.sg.flooringmastery.model.Product;
import com.sg.flooringmastery.model.State;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImplStub implements OrderDao {

    public Order onlyOrder;

    public OrderDaoImplStub() {
        //FIXME figure out what is the problem w this parsing
        final LocalDate testDate = LocalDate.parse("1-1-2020", DateTimeFormatter.ofPattern("MM-dd-yyyy"));
        final int testNum = 1;
        final State testTexas = new State("TX", new BigDecimal("4.45"));
        final Product testCarpet = new Product("Carpet", new BigDecimal("2.25"), new BigDecimal("2.10"));
        final BigDecimal testArea100 = new BigDecimal("100");
        final BigDecimal testMatCost = testCarpet.getCostPerSqFt().multiply(testArea100);
        final BigDecimal testLaborCost = testArea100.multiply(testCarpet.getLaborCostPerSqFt());
        final BigDecimal testTax = (testMatCost.add(testLaborCost)).multiply((testTexas.getTaxRate().divide(new BigDecimal("100"))));
        final BigDecimal testTotal = testMatCost.add(testLaborCost).add(testTax);

        onlyOrder = new Order(testDate, testNum, "John Doe", testTexas, testCarpet, testArea100, testMatCost, testLaborCost, testTax, testTotal);

    }
    
    public OrderDaoImplStub(Order onlyOrder){
        this.onlyOrder = onlyOrder;
    }

    @Override
    public Order addOrder(Order newOrder) throws OrderPersistenceException {
        if (newOrder == onlyOrder) {
            return onlyOrder;
        } else {
            return null;
        }
    }

    @Override
    public Order removeOrder(LocalDate removalDate, int removalID) throws OrderPersistenceException {
        if (removalDate == onlyOrder.getOrderDate() && removalID == onlyOrder.getOrderNum()) {
            return onlyOrder;
        } else {
            return null;
        }
    }

    @Override
    public Order getOrder(LocalDate date, int id) throws OrderPersistenceException {
        if (date == onlyOrder.getOrderDate() && id == onlyOrder.getOrderNum()) {
            return onlyOrder;
        } else {
            return null;
        }
    }

    @Override
    public Order editOrder(Order orderToReplace, Order orderEdit) throws OrderPersistenceException {
        if (orderToReplace.getOrderDate() == orderEdit.getOrderDate()
                && orderToReplace.getOrderNum() == orderEdit.getOrderNum()) {
            return onlyOrder;
        } else {
            return null;
        }
    }

    @Override
    public List<Order> getOrdersByDate(LocalDate date) throws OrderPersistenceException {
        if (date == onlyOrder.getOrderDate()) {
            List<Order> orderList = new ArrayList<>();
            orderList.add(onlyOrder);
            return orderList;
        } else {
            return null;
        }
    }

    @Override
    public List<Order> getAllOrders() throws OrderPersistenceException {
        List<Order> orderList = new ArrayList<>();
        orderList.add(onlyOrder);
        return orderList;
    }

    @Override
    public int getHighestOrderNumber() throws OrderPersistenceException {
        return 1;
    }

}
