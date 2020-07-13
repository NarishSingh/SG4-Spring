package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.OrderDao;
import com.sg.flooringmastery.dao.OrderPersistenceException;
import com.sg.flooringmastery.model.Order;
import com.sg.flooringmastery.model.Product;
import com.sg.flooringmastery.model.State;
import java.time.LocalDate;
import java.util.List;
import com.sg.flooringmastery.dao.AuditDao;
import com.sg.flooringmastery.dao.ExportFileDao;
import com.sg.flooringmastery.dao.InvalidOrderNumberException;
import com.sg.flooringmastery.dao.NoOrdersOnDateException;
import com.sg.flooringmastery.dao.ProductDao;
import com.sg.flooringmastery.dao.ProductReadException;
import com.sg.flooringmastery.dao.StateDao;
import com.sg.flooringmastery.dao.StateReadException;
import java.math.BigDecimal;

public class ServiceImpl implements Service {

    StateDao state;
    ProductDao product;
    OrderDao dao;
    AuditDao auditDao;
    ExportFileDao export;

    public ServiceImpl(StateDao state, ProductDao product, OrderDao dao, AuditDao auditDao, ExportFileDao export) {
        this.state = state;
        this.product = product;
        this.dao = dao;
        this.auditDao = auditDao;
        this.export = export;
    }

    @Override
    public Order validateOrder(Order orderRequest) throws OrderPersistenceException {
        calculateOrderCosts(orderRequest);

        int newOrderNum;
        if (orderRequest.getOrderNum() == 0) {
            newOrderNum = generateOrderNumber(); //new order
        } else {
            newOrderNum = orderRequest.getOrderNum(); //edit order
        }

        return new Order(orderRequest.getOrderDate(), newOrderNum, orderRequest.getCustomerName(),
                orderRequest.getState(), orderRequest.getProduct(), orderRequest.getArea(),
                orderRequest.getMaterialCost(), orderRequest.getLaborCost(), orderRequest.getTax(), orderRequest.getTotal());
    }

    @Override
    public Order addOrder(Order newOrder) throws OrderPersistenceException {
        return dao.addOrder(newOrder);
    }

    @Override
    public Order removeOrder(LocalDate date, int orderNum) throws OrderPersistenceException {
        return dao.removeOrder(date, orderNum);
    }

    @Override
    public Order editOrder(Order editedOrder, Order originalOrder) throws OrderPersistenceException {
        return dao.editOrder(originalOrder, editedOrder);
    }

    @Override
    public Order getOrder(LocalDate date, int orderNum) throws OrderPersistenceException,
            NoOrdersOnDateException, InvalidOrderNumberException {
        return dao.getOrder(date, orderNum);
    }

    @Override
    public List<Order> getOrdersByDate(LocalDate date) throws OrderPersistenceException {
        return dao.getOrdersByDate(date);
    }

    @Override
    public void exportOrder() throws OrderPersistenceException {
        List<Order> allActiveOrders = getAllOrders();
        export.exportOrders(allActiveOrders);
    }

    @Override
    public List<Order> getAllOrders() throws OrderPersistenceException {
        return dao.getAllOrders();
    }

    @Override
    public List<State> getValidStateList() throws StateReadException {
        try {
            return state.getValidStates();
        } catch (StateReadException e) {
            throw new StateReadException("Could not load State tax data roster", e);
        }
    }

    @Override
    public List<Product> getValidProductList() throws ProductReadException {
        try {
            return product.getValidProducts();
        } catch (ProductReadException e) {
            throw new ProductReadException("Could not load Product data roster", e);
        }
    }

    @Override
    public State validateState(String userState) throws InvalidStateException {
        try {
            return state.readStateByID(userState);
        } catch (InvalidStateException | StateReadException e) {
            throw new InvalidStateException("We are unavailable for business in this state for now", e);
        }
    }

    @Override
    public Product validateProduct(String userProduct) throws InvalidProductException {
        try {
            return product.readProductByID(userProduct);
        } catch (InvalidProductException | ProductReadException e) {
            throw new InvalidProductException("We do not floor with this type of material at this time", e);
        }
    }

    /*HELPER METHODS*/
    /**
     * Calculate and set the costs for the remaining fields of a order obj
     *
     * @param newOrder {Order} a valid but incomplete order obj request
     */
    private void calculateOrderCosts(Order newOrder) {
        BigDecimal matCosts = newOrder.getProduct().getCostPerSqFt().multiply(newOrder.getArea());
        BigDecimal laborCosts = newOrder.getArea().multiply(newOrder.getProduct().getLaborCostPerSqFt());
        BigDecimal taxCosts = matCosts.add(laborCosts).multiply(newOrder.getState().getTaxRate().divide(new BigDecimal("100")));
        BigDecimal totalCosts = matCosts.add(laborCosts).add(taxCosts);

        newOrder.setMaterialCost(matCosts);
        newOrder.setLaborCost(laborCosts);
        newOrder.setTax(taxCosts);
        newOrder.setTotal(totalCosts);
    }

    /**
     * Find the highest order number so far, and add 1 to set the next order
     * request's number
     *
     * @return {int} a number > 0
     */
    private int generateOrderNumber() throws OrderPersistenceException {
        return dao.getHighestOrderNumber() + 1;
    }
}
