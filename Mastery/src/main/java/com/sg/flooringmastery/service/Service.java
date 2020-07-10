package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.InvalidOrderNumberException;
import com.sg.flooringmastery.dao.NoOrdersOnDateException;
import com.sg.flooringmastery.dao.OrderPersistenceException;
import com.sg.flooringmastery.dao.ProductReadException;
import com.sg.flooringmastery.dao.StateReadException;
import com.sg.flooringmastery.model.Order;
import com.sg.flooringmastery.model.Product;
import com.sg.flooringmastery.model.State;
import java.time.LocalDate;
import java.util.List;

public interface Service {

    /**
     * Validate an Order before proceeding with persisting an add or edit
     *
     * @param orderRequest {Order} a queued order
     * @return {Order} a valid Order obj for persistence
     * @throws OrderPersistenceException if cannot read from orders directory
     */
    Order validateOrder(Order orderRequest) throws OrderPersistenceException;

    /**
     * Add an active order to treemap and persist to file
     *
     * @param newOrder {Order} a validated new Order
     * @return {Order} the successfully added order
     * @throws OrderPersistenceException if cannot persist addition to file
     */
    Order addOrder(Order newOrder) throws OrderPersistenceException;

    /**
     * Remove an active order from treemap and persist to file
     *
     * @param date     {LocalDate} a valid date for an order
     * @param orderNum {int} a valid order number
     * @return {Order} the successfully removed order
     * @throws OrderPersistenceException if cannot persist removal to file
     */
    Order removeOrder(LocalDate date, int orderNum) throws OrderPersistenceException;

    /**
     * Edit an active order in treemap and persist to file
     *
     * @param editedOrder {Order} a validated, edited Order obj
     * @return {Order} the successfully edited order
     * @throws OrderPersistenceException if cannot persist edit to file
     */
    Order editOrder(Order editedOrder) throws OrderPersistenceException;

    /**
     * Retrieve the info from a single active order
     *
     * @param date     {LocalDate} a valid date for an order
     * @param orderNum {int} a valid number for an order
     * @return {Order} the order retrieved
     * @throws OrderPersistenceException   if cannot read from order directory
     * @throws NoOrdersOnDateException     if inputted date is invalid
     * @throws InvalidOrderNumberException if inputted order number is invalid
     */
    Order getOrder(LocalDate date, int orderNum) throws OrderPersistenceException,
            NoOrdersOnDateException, InvalidOrderNumberException;

    /**
     * Retrieve the info for all active orders on a given date
     *
     * @param date {LocalDate}a valid date for orders
     * @return {List} all active orders on a given date
     * @throws OrderPersistenceException if cannot read from orders directory
     *                                   files
     */
    List<Order> getOrdersByDate(LocalDate date) throws OrderPersistenceException;

    /**
     * Export and persist all active orders to a single file
     */
    void exportOrder();

    /**
     * Retrieve all active Orders from file
     *
     * @return {List} a list of all orders
     */
    List<Order> getAllOrders();

    /**
     * Retrieve all valid states for business from file
     *
     * @return {List} a list of all state obj's
     * @throws StateReadException if cannot load in state tax data roster
     */
    List<State> getValidStateList() throws StateReadException;

    /**
     * Retrieve all valid products for purchase from file
     *
     * @return {List} a list of all product obj's
     * @throws ProductReadException if cannot load in product data roster
     */
    List<Product> getValidProductList() throws ProductReadException;

    /**
     * Validate user's state selection and construct a new State obj from file
     *
     * @param userState {String} user's inputted state
     * @return {State} a valid State object
     * @throws InvalidStateException if user enters an invalid state
     */
    State validateState(String userState) throws InvalidStateException;

    /**
     * Validate user's product selection and construct a new Product obj from
     * file
     *
     * @param userProduct {String} user's inputted product type
     * @return {Product} a valid Product object
     * @throws InvalidProductException if user enters an invalid product
     */
    Product validateProduct(String userProduct) throws InvalidProductException;
}
