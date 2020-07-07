package com.sg.flooringmastery.controller;

import com.sg.flooringmastery.view.View;
import com.sg.flooringmastery.service.Service;

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

    }

    /**
     * Get a menu selection from user
     *
     * @return {int} 0-5 corresponding to a menu action
     */
    private int getMenuSelection() {

    }

    /**
     * Get a date from user and display all orders for that date
     */
    public void displayOrders() {

    }

    /**
     * Create a new order, getting inputs on customer info and their order, and
     * calculating the remaining order info. A successful Order creation will be
     * displayed in full for the user
     */
    private void addOrder() {

    }

    /**
     * Edit an existing order, getting new inputs for fields of the order or
     * keeping old ones by hitting ENTER, and re-calculating data for the order.
     * A successful edit will be displayed in full for user
     */
    private void editOrder() {

    }

    /**
     * Remove an existing order, getting a date and order number from user to
     * delete from order directory
     */
    private void removeOrder() {

    }

    /**
     * Export all active orders, persisting them to a comma delimited file. All
     * info for these orders are viewable from the text file in the Backup
     * folder
     */
    private void exportOrders() {

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
