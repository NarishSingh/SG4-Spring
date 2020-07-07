package com.sg.flooringmastery.view;

import com.sg.flooringmastery.model.Order;
import com.sg.flooringmastery.model.Product;
import com.sg.flooringmastery.model.State;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
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

    /**
     * Get date from user in MM-DD-YYYY format
     *
     * @return {LocalDate} a future date
     */
    public LocalDate inputOrderDate() {

    }

    /**
     * Get a customer or company name from user, supports A-Z chars, 0-9, commas
     * and periods
     *
     * @return {String} the customer's name
     */
    public String inputCustomerName() {

    }

    /**
     * Get a state from user
     *
     * @param validStates {List} all valid states read in from file
     * @return {String} the state's full name or abbreviation
     */
    public String inputState(List<State> validStates) {

    }

    /**
     * Get a product type from user
     *
     * @param validProducts {List} all valid products read in from file
     * @return {String} the product type being ordered
     */
    public String inputProductType(List<Product> validProducts) {

    }

    /**
     * Get an area from user
     *
     * @return {BigDecimal} an area at or over 100 sq.ft.
     */
    public BigDecimal inputArea() {

    }
    
    /**
     * Display the order and get confirmation before writing it to the record
     * @param userOrder {Order} the user's newly create, valid order
     * @return {boolean} confirmation to persist order to file
     */
    public boolean confirmNewOrder(Order userOrder){
        
    }

    /**
     * Display all fields of an order object for user
     *
     * @param userOrder {Order} a newly constructed Order obj from service
     */
    public void displayOrderInfo(Order userOrder) {

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
        //TODO move this to the userIO new method to read LocalDate
        /*
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
        */
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

    /**
     * Edit the date of an order
     *
     * @param orderToEdit {Order} the original order obj, used to fill in
     *                    original value if user elects not to edit by inputting
     *                    \n
     * @return {LocalDate} the new date field for the order
     */
    public LocalDate inputEditedOrderDate(Order orderToEdit) {

    }

    /**
     * Edit the name on an order
     *
     * @param orderToEdit {Order} the original order obj, used to fill in
     *                    original value if user elects not to edit by inputting
     *                    \n
     * @return {String} the new customer name field for the order
     */
    public String inputEditedCustomerName(Order orderToEdit) {

    }

    /**
     * Edit the state name of an order
     *
     * @param orderToEdit {Order} the original order obj, used to fill in
     *                    original value if user elects not to edit by inputting
     *                    \n
     * @param validStates {List} all valid states for business, read in from file
     * @return {String} the new State name or abbreviation for the respective
     *         field of the order
     */
    public String inputEditedState(Order orderToEdit, List<String> validStates) {

    }

    /**
     * Edit the product type of an order
     *
     * @param orderToEdit {Order} the original order obj, used to fill in
     *                    original value if user elects not to edit by inputting
     *                    \n
     * @param validProducts {List} all valid products for purchase, read in from file
     * @return {String} the new product name for the respective field for the
     *         order
     */
    public String inputEditedProductType(Order orderToEdit, List<Product> validProducts) {

    }

    /**
     * Edit the date of an order
     *
     * @param orderToEdit {Order} the original order obj, used to fill in
     *                    original value if user elects not to edit by inputting
     *                    \n
     * @return {BigDecimal}
     */
    public BigDecimal inputEditedArea(Order orderToEdit) {

    }
    
    /**
     * Display the edited order and get confirmation before writing to file
     * @param orderEdits {Order} the edited Order
     * @return {boolean} confirmation to persist order edits to file
     */
    public boolean confirmOrderEdit(Order orderEdits){
        
    }

    //recycles displayOrder()
    /**
     * Display closing Edit Order banner for a successful edit
     */
    public void displayEditOrderSuccessBanner() {
        io.print("***Order Edit Not Persisted***");
        io.readString("Press ENTER to continue");
    }

    /**
     * Display closing Edit Order banner for a failed edit
     */
    public void displayEditOrderFailBanner() {
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
     *
     * @return {int} an existing order ID
     */
    public int getOrderRemovalNumber() {
        boolean hasErrors;
        int orderID = 0;

        do {
            try {
                orderID = io.readInt("Enter Order ID: ");
                hasErrors = false;
            } catch (NumberFormatException e) {
                hasErrors = true;
                displayErrorMessage(e.getMessage());
            }
        } while (hasErrors);

        return orderID;
    }
    
    /**
     * Get confirmation for removing an active order
     * @return {boolean} confirmation to persist the removal
     */
    public boolean confirmOrderRemoval(){
        
    }
    

    /**
     * Display closing Remove Order banner for a successful edit
     */
    public void displayRemoveOrderSuccessBanner() {
        io.print("***Order Not Removed***");
        io.readString("Press ENTER to continue");
    }

    /**
     * Display closing Remove Order banner for a failed edit
     */
    public void displayRemoveOrderFailBanner() {
        io.print("***Order Sucessfully Removed***");
        io.readString("Press ENTER to continue");
    }

    /*EXPORT ORDER*/
    /**
     * Display opening Export Order banner to UI
     */
    public void displayExportOrderBanner(){
        io.print("===EXPORT ORDERS===");
    }
    
    /**
     * Display closing Export Order banner for a successful persistence
     */
    public void displayExportOrderSuccessBanner(){
        io.print("***Orders Sucessfully Persisted***");
        io.print("Check DataExport.txt of BackUp Folder for all active orders");
        io.readString("Press ENTER to continue");
    }
    
    /**
     * Display closing Export Order banner for a failed persistence
     */
    public void displayExportOrderFailBanner(){
        io.print("***Orders Unsucessfully Persisted***");
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
