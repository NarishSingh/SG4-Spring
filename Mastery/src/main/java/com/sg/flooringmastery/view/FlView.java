package com.sg.flooringmastery.view;

import com.sg.flooringmastery.model.Order;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;

public class FlView {

    private UserIO io;

    public FlView(UserIO io) {
        this.io = io;
    }

    /**
     * Print main menu and get a user action
     *
     * @return {int} 0-5 corresponding with user's desired menu action
     */
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

    public Order getNewOrderInfo() {
        
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
        LocalDate date = LocalDate.now(); //just for initialization

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

    /**
     * Display all orders for a given date
     *
     * @param ordersOnDate {Map} all orders on a given date, sorted by order
     *                     number
     */
    public void displayOrdersByDate(Map<Integer, Order> ordersOnDate) {
        ordersOnDate.forEach((orderNum, Order) -> {
            //TODO print out all the fields
        });

        io.readString("Press ENTER to continue");
    }

    /**
     * Display closing Display Order banner for a failed date entry
     */
    public void displayDisplayOrdersFailBanner() {
        io.print("***No Orders to Show***");
        io.readString("Press ENTER to continue");
    }

    /*EDIT ORDER*/
    /**
     * Display opening Edit Order banner to UI
     */
    public void displayEditOrderBanner() {
        io.print("===EDIT ORDER===");
    }

    public String[] getEditedOrderInfo(Order orderToEdit) {
        
    }
    
    //recycles displayOrder()
    
    /**
     * Display closing Edit Order banner for a successful edit
     */
    public void displayEditOrderSuccessBanner(){
        io.print("***Order Edit Not Persisted***");
        io.readString("Press ENTER to continue");
    }
    
    /**
     * Display closing Edit Order banner for a failed edit
     */
    public void displayEditOrderFailBanner(){
        io.print("***Order Sucessfully Edited***");
        io.readString("Press ENTER to continue");
    }
    
    /*REMOVE ORDER*/
    /**
     * Display opening Remove Order banner to UI
     */
    public void displayRemoveOrderBanner() {
        io.print("===REMOVE ORDER===");
    }
    
    //recycles getOrdersDate()
    
    /**
     * Get ID of order to be removed
     * @return {int} an existing order ID
     */
    public int getOrderRemovalNumber(){
        boolean hasErrors;
        int orderID = 0;
        
        do {            
            try {
                orderID = io.readInt("Enter Order ID: ");
                hasErrors=false;
            } catch (NumberFormatException e) {
                hasErrors=true;
                displayErrorMessage(e.getMessage());
            }
        } while (hasErrors);
        
        return orderID;
    }
    
    /**
     * Display closing Remove Order banner for a successful edit
     */
    public void displayRemoveOrderSuccessBanner(){
        io.print("***Order Not Removed***");
        io.readString("Press ENTER to continue");
    }
    
    /**
     * Display closing Remove Order banner for a failed edit
     */
    public void displayRemoveOrderFailBanner(){
        io.print("***Order Sucessfully Removed***");
        io.readString("Press ENTER to continue");
    }

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
