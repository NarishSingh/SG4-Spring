package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.OrderPersistenceException;
import com.sg.flooringmastery.model.Order;
import com.sg.flooringmastery.model.Product;
import com.sg.flooringmastery.model.State;
import java.time.LocalDate;
import java.util.List;

public interface FlService {

    /**
     * Validate an Order before proceeding with persisting an add or edit
     *
     * @param orderRequest {Order} a queued order
     * @return {Order} a valid Order obj for persistance
     * @throws InvalidProductException if an invalid product type is request
     * @throws InvalidStateException   if an invalid state is requested
     */
    Order validateOrder(Order orderRequest) throws InvalidProductException,
            InvalidStateException;

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
     */
    Order getOrder(LocalDate date, int orderNum);

    /**
     * Retrieve the info for all active orders on a given date
     *
     * @param date {LocalDate}a valid date for orders
     * @return {List} all active orders on a given date
     */
    List<Order> getOrdersByDate(LocalDate date);

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
     */
    List<State> getValidStateList();

    /**
     * Retrieve all valid products for purchase from file
     *
     * @return {List} a list of all product obj's
     */
    List<Product> getValidProductList();

}
