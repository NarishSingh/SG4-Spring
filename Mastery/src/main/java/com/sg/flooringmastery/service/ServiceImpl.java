package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.ExportFileDao;
import com.sg.flooringmastery.dao.OrderDao;
import com.sg.flooringmastery.dao.OrderPersistenceException;
import com.sg.flooringmastery.dao.ProductDao;
import com.sg.flooringmastery.dao.StateDao;
import com.sg.flooringmastery.model.Order;
import com.sg.flooringmastery.model.Product;
import com.sg.flooringmastery.model.State;
import java.time.LocalDate;
import java.util.List;
import com.sg.flooringmastery.dao.AuditDao;

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
    public Order validateOrder(Order orderRequest) throws InvalidProductException, InvalidStateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order addOrder(Order newOrder) throws OrderPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order removeOrder(LocalDate date, int orderNum) throws OrderPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order editOrder(Order editedOrder) throws OrderPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order getOrder(LocalDate date, int orderNum) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Order> getOrdersByDate(LocalDate date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void exportOrder() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Order> getAllOrders() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<State> getValidStateList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Product> getValidProductList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /*HELPER METHODS*/
    /**
     * Validate a date passed from view to ensure that it is a future date
     *
     * @return {LocalDate} a future date
     */
    private LocalDate validateOrderDate() {

    }

    /**
     * Validate user's state selection and construct a new State obj from file
     *
     * @param userState {String} user's inputted state
     * @return {State} a valid State object
     */
    private State validateState(String userState) {

    }

    /**
     * Validate user's product selection and construct a new Product obj from
     * file
     *
     * @param userProduct {String} user's inputted product type
     * @return {Product} a valid Product object
     */
    private Product validateProduct(String userProduct) {

    }

    /**
     * Calculate and set the costs for the remaining fields of a order obj
     *
     * @param orderRequest {Order} a valid but incomplete order obj request
     */
    private void calculateOrderCosts(Order orderRequest) {

    }

    /**
     * Find the highest order number so far, and add 1 to set the next order
     * request's number
     *
     * @return {int} a number > 0
     */
    private int generateOrderNumber() {

    }
}
