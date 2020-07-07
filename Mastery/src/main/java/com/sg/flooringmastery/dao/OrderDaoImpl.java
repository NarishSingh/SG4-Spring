package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.model.Order;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class OrderDaoImpl implements OrderDao {

    private Map<LocalDate, Map<Integer, Order>> orders = new TreeMap<>();
    private String ORDER_DIRECTORY;
    private final String DELIMITER = ",";

    public OrderDaoImpl() {
        this.ORDER_DIRECTORY = ".\\MasteryFileData\\Orders";
    }

    public OrderDaoImpl(String orderDirAsText) {
        this.ORDER_DIRECTORY = orderDirAsText;
    }
    
    @Override
    public Order addOrder(Order newOrder) throws OrderPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order removeOrder(LocalDate removalDate, int removalID) throws OrderPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order getOrder(LocalDate Date, int id) throws OrderPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order editOrder(Order orderToReplace, Order orderEdit) throws OrderPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Order> getOrdersByDate(LocalDate date) throws OrderPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Order> getAllOrders() throws OrderPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /*DATA (UN)MARSHALLING*/
    /**
     * Marshall an Order obj to text
     * @param anOrder {Order} an active order
     * @return {String} a delimited string of text with all obj information
     */
    private String marshallOrder(Order anOrder){
        
    }
    
    /**
     * Unmarshall delimited text into Order obj's
     * @param orderAsText {String} delimited lines of text in the order directory's files
     * @return {Order} a fully constructed Order obj
     */
    private Order unmarshallOrder(String orderAsText){
        
    }
    
    /**
     * Load all persisted orders to treemap
     */
    private void loadAllOrders(){
        
    }
    
    /**
     * Persist all active orders to order's directory
     */
    private void writeAllOrders(){
        
    }
}
