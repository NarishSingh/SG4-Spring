package com.sg.flooringmastery.view;

import com.sg.flooringmastery.model.Order;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class FlView {

    private UserIO io;

    public FlView(UserIO io) {
        this.io = io;
    }

    public int mainMenuAndSelection() {
        io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        io.print("* 1. Display Orders");
        io.print("* 2. Add an Order");
        io.print("* 3. Edit an Order");
        io.print("* 4. Remove an Order");
        io.print("* 5. Export All Data");
        io.print("* 0. Quit");
        io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");

        boolean hasErrors;
        int selection = 0;

        do {
            try {
                selection = io.readInt("Enter Action: ", 0, 5);
                hasErrors = false;
            } catch (NumberFormatException e) {
                hasErrors = true;
                displayErrorMessage(e.getMessage());
            }
        } while (hasErrors);

        return selection;
    }

    /*ADD ORDER*/
    /**
     * Display opening Add Order banner to UI
     */
    public void displayAddOrderBanner() {
        io.print("===ADD NEW ORDER===");
    }

    /**
     * Create a new order request to send to service
     *
     * @return {String[]} user inputs all as strings - service layer will handle
     *         proper typing and obj construction
     */
    public String[] getNewOrderInfo() {
        String[] orderRequest = new String[5];
        orderRequest[0] = io.readString("Please enter order date in MM-DD-YYYY format:");
        orderRequest[1] = io.readString("Please enter customer name: ");
        orderRequest[2] = io.readString("Please enter state - TX, WA, KY, CA: ");
        orderRequest[3] = io.readString("Please choose your desired product type - CARPET, LAMINATE, TILE, WOOD: ");
        orderRequest[4] = io.readString("Please enter area - min. 100 sq. ft: ");

        return orderRequest;
    }

    /**
     * Display all fields of an order object for user
     *
     * @param newOrder {Order} a newly constructed Order obj from service
     */
    public void displayOrder(Order newOrder) {
        //TODO add logic
    }

    /**
     * Display closing Add Order banner for a successful order request
     */
    public void displayAddOrderSuccessBanner() {
        io.print("***New Order Added***");
        io.readString("Press ENTER to continue");
    }

    /**
     * Display closing Add Order banner for a failed order request
     */
    public void displayAddOrderFailBanner() {
        io.print("***Order Request Failed***");
        io.readString("Press ENTER to continue");
    }

    /*DISPLAY ORDER*/
    /**
     * Display opening Display Order banner to UI
     */
    public void displayDisplayOrderBanner() {
        io.print("===VIEW ORDERS===");
    }

    /**
     * Get a date from customer
     *
     * @return date {LocalDate} a future date to retrieve order data from
     */
    public LocalDate getOrdersDate() {
        boolean hasErrors;
        LocalDate date = LocalDate.now(); //just for initialization purposes

        do {
            try {
                date = LocalDate.parse(io.readString("Enter order date in MM-DD-YYYY format: "), DateTimeFormatter.ofPattern("MM-DD-YYYY"));
                hasErrors = false;
            } catch (DateTimeParseException e) {
                displayErrorMessage(e.getMessage());
                hasErrors = true;
            }
        } while (hasErrors);

        return date;
    }
    
    //TODO continue here

    /*EXIT*/
    /**
     * Display Exit banner in UI
     */
    public void displayExitBanner() {
        io.print("***Thank you***");
    }

    /*EXCEPTION HANDLING*/
    /**
     * Display Unknown Command banner in UI
     */
    public void displayUnknownCommandBanner() {
        io.print("Unknown command");
    }

    /**
     * Display error message for issues with IO
     *
     * @param errorMsg {String} error message to user
     */
    public void displayErrorMessage(String errorMsg) {
        io.print("===Error===");
        io.print(errorMsg);
    }
}
