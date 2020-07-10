package com.sg.flooringmastery.controller;

import com.sg.flooringmastery.dao.InvalidOrderNumberException;
import com.sg.flooringmastery.dao.NoOrdersOnDateException;
import com.sg.flooringmastery.dao.OrderPersistenceException;
import com.sg.flooringmastery.dao.ProductReadException;
import com.sg.flooringmastery.dao.StateReadException;
import com.sg.flooringmastery.model.Order;
import com.sg.flooringmastery.model.Product;
import com.sg.flooringmastery.model.State;
import com.sg.flooringmastery.service.InvalidProductException;
import com.sg.flooringmastery.service.InvalidStateException;
import com.sg.flooringmastery.view.View;
import com.sg.flooringmastery.service.Service;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Controller {

    Service serv;
    View view;

    public Controller(Service serv, View view) {
        this.serv = serv;
        this.view = view;
    }

    /**
     * App controller
     */
    public void run() {
        boolean inProgram = true;

        try {
            while (inProgram) {
                int menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1: {
                        displayOrders();
                        break;
                    }
                    case 2: {
                        addOrder();
                        break;
                    }
                    case 3: {
                        editOrder();
                        break;
                    }
                    case 4: {
                        removeOrder();
                        break;
                    }
                    case 5: {
                        exportOrders();
                        break;
                    }
                    case 0: {
                        inProgram = false;
                        break;
                    }
                    default: {
                        unknownCommand();
                    }
                }
            }
        } catch (OrderPersistenceException
                | StateReadException
                | ProductReadException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    /**
     * Get a menu selection from user
     *
     * @return {int} 0-5 corresponding to a menu action
     */
    private int getMenuSelection() {
        return view.mainMenuAndSelection();
    }

    /**
     * Get a date from user and display all orders for that date
     *
     * @throws OrderPersistenceException if cannot read from data files
     */
    public void displayOrders() throws OrderPersistenceException {
        view.displayDisplayOrderBanner();
        LocalDate ordersDate = view.inputOrderDate();
        List<Order> ordersOnDate = serv.getOrdersByDate(ordersDate);
        view.displayOrdersByDate(ordersOnDate);
    }

    /**
     * Create a new order, getting inputs on customer info and their order, and
     * calculating the remaining order info. A successful Order creation will be
     * displayed in full for the user
     *
     * @throws OrderPersistenceException if cannot read from or write to data
     *                                   files
     */
    private void addOrder() throws StateReadException, ProductReadException, OrderPersistenceException {
        boolean hasErrors;

        view.displayAddOrderBanner();

        //future date
        LocalDate validOrderDate = view.inputOrderDate();

        //cust name, validated
        String customerName = view.inputCustomerName();

        //state of business
        List<State> validStates = serv.getValidStateList();
        State stateSelection = null;
        do {
            try {
                String stateSelectionString = view.inputState(validStates);
                stateSelection = serv.validateState(stateSelectionString);
                hasErrors = false;
            } catch (InvalidStateException e) {
                view.displayErrorMessage(e.getMessage());
                hasErrors = true;
            }
        } while (hasErrors);

        //Product type
        List<Product> validProducts = serv.getValidProductList();
        Product productSelection = null;
        do {
            try {
                String productSelectionString = view.inputProductType(validProducts);
                productSelection = serv.validateProduct(productSelectionString);
                hasErrors = false;
            } catch (InvalidProductException e) {
                view.displayErrorMessage(e.getMessage());
                hasErrors = true;
            }
        } while (hasErrors);

        //area
        BigDecimal userArea = view.inputArea();

        //order request validation
        Order orderRequest = new Order(validOrderDate, customerName, stateSelection, productSelection, userArea);
        Order newOrder = serv.validateOrder(orderRequest);

        //confirmation and add new order
        if (view.confirmNewOrder(newOrder)) {
            serv.addOrder(newOrder);
            view.displayAddOrderBanner();
        } else {
            view.displayAddOrderFailBanner();
        }
    }

    /**
     * Edit an existing order, getting new inputs for fields of the order or
     * keeping old ones by hitting ENTER, and re-calculating data for the order.
     * A successful edit will be displayed in full for user
     *
     * @throws OrderPersistenceException   if cannot read from or write to data
     *                                     files
     * @throws NoOrdersOnDateException     if user inputs invalid date
     * @throws InvalidOrderNumberException if user inputs invalid order number
     */
    private void editOrder() throws OrderPersistenceException, NoOrdersOnDateException, InvalidOrderNumberException {
        boolean hasErrors;
        Order orderToEdit;

        view.displayEditOrderBanner();

        //retrieve order
        do {
            try {
                LocalDate orderDate = view.inputOrderDate();
                int orderNum = view.inputOrderNumber();
                orderToEdit = serv.getOrder(orderDate, orderNum);
                hasErrors = false;
            } catch (NoOrdersOnDateException
                    | InvalidOrderNumberException e) {
                view.displayErrorMessage(e.getMessage());
                hasErrors = true;
            }
        } while (hasErrors);
        
        //TODO continue here
    }

    /**
     * Remove an existing order, getting a date and order number from user to
     * delete from order directory
     *
     * @throws OrderPersistenceException if cannot read from or write to data
     *                                   files
     */
    private void removeOrder() throws OrderPersistenceException {

    }

    /**
     * Export all active orders, persisting them to a comma delimited file. All
     * info for these orders are viewable from the text file in the Backup
     * folder
     *
     * @throws OrderPersistenceException if cannot read from or write to data
     *                                   files
     */
    private void exportOrders() throws OrderPersistenceException {

    }

    /**
     * Display banner for invalid menu choices
     */
    public void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    /**
     * Display exit banner for User exiting the program
     */
    public void exitMessage() {
        view.displayExitBanner();
    }
}
