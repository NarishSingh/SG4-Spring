package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.InvalidOrderNumberException;
import com.sg.flooringmastery.dao.NoOrdersOnDateException;
import com.sg.flooringmastery.dao.OrderDao;
import com.sg.flooringmastery.dao.OrderPersistenceException;
import com.sg.flooringmastery.model.Order;
import com.sg.flooringmastery.model.Product;
import com.sg.flooringmastery.model.State;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class OrderDaoImplStub implements OrderDao {

    public Order onlyOrder;
    public TreeMap<Integer, Order> oneOrder = new TreeMap<>();

    public OrderDaoImplStub() {
        final LocalDate testDate = LocalDate.parse("01-01-2021", DateTimeFormatter.ofPattern("MM-dd-yyyy"));
        final int testNum = 1;
        final String testName = "John Doe";
        final State testTexas = new State("TX", new BigDecimal("4.45").setScale(2, RoundingMode.HALF_UP));
        final Product testCarpet = new Product("Carpet", new BigDecimal("2.25"), new BigDecimal("2.10").setScale(2, RoundingMode.HALF_UP));
        final BigDecimal testArea100 = new BigDecimal("100").setScale(2, RoundingMode.HALF_UP);
        final BigDecimal testMatCost = (testCarpet.getCostPerSqFt().multiply(testArea100)).setScale(2, RoundingMode.HALF_UP);
        final BigDecimal testLaborCost = (testArea100.multiply(testCarpet.getLaborCostPerSqFt())).setScale(2, RoundingMode.HALF_UP);
        final BigDecimal testTax = ((testMatCost.add(testLaborCost)).multiply((testTexas.getTaxRate().divide(new BigDecimal("100").setScale(2, RoundingMode.HALF_UP))))).setScale(2, RoundingMode.HALF_UP);
        final BigDecimal testTotal = (testMatCost.add(testLaborCost).add(testTax)).setScale(2, RoundingMode.HALF_UP);

        this.onlyOrder = new Order(testDate, testNum, testName, testTexas, testCarpet, testArea100, testMatCost, testLaborCost, testTax, testTotal);

        this.oneOrder.put(testNum, onlyOrder);
    }

    public OrderDaoImplStub(Order onlyOrder) {
        this.onlyOrder = onlyOrder;
    }

    @Override
    public Order addOrder(Order newOrder) throws OrderPersistenceException {
        if (newOrder.equals(onlyOrder)) {
            return onlyOrder;
        } else {
            return null;
        }
    }

    @Override
    public Order removeOrder(LocalDate removalDate, int removalID) throws OrderPersistenceException,
            NoOrdersOnDateException, InvalidOrderNumberException {
        if (!removalDate.equals(onlyOrder.getOrderDate())) {
            throw new NoOrdersOnDateException("No order to remove");
        } else if (removalID != onlyOrder.getOrderNum()) {
            throw new InvalidOrderNumberException("Invalid order number");
        } else {
            return onlyOrder;
        }

    }

    @Override
    public Order getOrder(LocalDate date, int orderNum) throws OrderPersistenceException,
            NoOrdersOnDateException, InvalidOrderNumberException {
        if (!date.equals(onlyOrder.getOrderDate())) {
            throw new NoOrdersOnDateException("No order to retrieve");
        } else if (orderNum != onlyOrder.getOrderNum()) {
            throw new InvalidOrderNumberException("Invalid order number");
        } else {
            return onlyOrder;
        }
    }

    @Override
    public Order editOrder(Order orderToReplace, Order orderEdit) throws OrderPersistenceException,
            NoOrdersOnDateException, InvalidOrderNumberException {
        if (orderToReplace.equals(orderEdit)) {
            return onlyOrder;
        } else {
            return null;
        }
    }

    @Override
    public List<Order> getOrdersByDate(LocalDate date) throws OrderPersistenceException,
            NoOrdersOnDateException {
        if (date.equals(onlyOrder.getOrderDate())) {
            return new ArrayList<>(oneOrder.values());
        } else {
            return null;
        }
    }

    @Override
    public List<Order> getAllOrders() throws OrderPersistenceException {
        return new ArrayList<>(oneOrder.values());
    }

    @Override
    public int getHighestOrderNumber() throws OrderPersistenceException {
        return 1;
    }

}
