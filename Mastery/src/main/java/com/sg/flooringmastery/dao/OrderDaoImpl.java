package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.model.Order;
import com.sg.flooringmastery.model.Product;
import com.sg.flooringmastery.model.State;
import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class OrderDaoImpl implements OrderDao {

    private TreeMap<LocalDate, TreeMap<Integer, Order>> orders = new TreeMap<>();
    private String ORDER_DIRECTORY;
    private final String DELIMITER = ",";
    private final String DELIMITER_REPLACEMENT = "::";

    //production
    public OrderDaoImpl() {
        this.ORDER_DIRECTORY = ".\\MasteryFileData\\Orders";
    }

    //testing
    public OrderDaoImpl(String orderDirAsText) {
        this.ORDER_DIRECTORY = orderDirAsText;
    }

    @Override
    public Order addOrder(Order newOrder) throws OrderPersistenceException {
        loadAllOrders();

        //retrieve inner map or create new one, then add order
        TreeMap<Integer, Order> incomingOrders = orders.get(newOrder.getOrderDate());
        if (incomingOrders == null) {
            incomingOrders = new TreeMap<>();
        }

        incomingOrders.put(newOrder.getOrderNum(), newOrder);

        //add to outer map field and write
        orders.put(newOrder.getOrderDate(), incomingOrders);

        writeAllOrders();

        if (orders.containsValue(incomingOrders)) {
            return newOrder;
        } else {
            throw new OrderPersistenceException("Could not persist order");
        }
    }

    @Override
    public Order removeOrder(LocalDate removalDate, int removalID) throws OrderPersistenceException {
        loadAllOrders();

        //retrieve inner treemap then remove order
        TreeMap<Integer, Order> deletionMap = orders.get(removalDate);
        Order deleted = deletionMap.remove(removalID);

        //re-enter new map to outer treemap
        orders.put(removalDate, deletionMap);

        writeAllOrders();

        if (orders.containsValue(deletionMap) && !deletionMap.containsValue(deleted)) {
            return deleted;
        } else {
            throw new OrderPersistenceException("Could not persist order deletion");
        }
    }

    @Override
    public Order getOrder(LocalDate date, int id) throws OrderPersistenceException,
            NoOrdersOnDateException, InvalidOrderNumberException {
        loadAllOrders();

        Map<Integer, Order> orderByDate = orders.get(date);
        if (orderByDate == null) {
            throw new NoOrdersOnDateException("No orders on this date to retrieve");
        }

        Order retrievedOrder = orderByDate.get(id);
        if (retrievedOrder == null) {
            throw new InvalidOrderNumberException("No such order exists");
        }

        return retrievedOrder;
    }

    @Override
    public Order editOrder(Order orderToReplace, Order orderEdit) throws OrderPersistenceException {
        //validate and add since both use .put()
        if (orderEdit.getOrderDate() == orderToReplace.getOrderDate() && orderEdit.getOrderNum() == orderToReplace.getOrderNum()) {
            return addOrder(orderEdit);
        } else {
            throw new OrderPersistenceException("Could not edit order.");
        }

    }

    @Override
    public List<Order> getOrdersByDate(LocalDate date) throws OrderPersistenceException,
            NoOrdersOnDateException {
        loadAllOrders();
        List<Order> ordersOnDate;

        try {
            return new ArrayList<>(orders.get(date).values());
        } catch (NullPointerException e) {
            throw new NoOrdersOnDateException("No orders to display");
        }
    }

    @Override
    public List<Order> getAllOrders() throws OrderPersistenceException {
        loadAllOrders();

        List<Order> allOrdersList = new ArrayList<>();
        orders.forEach((date, orderTree) -> {
            allOrdersList.addAll(orderTree.values());
        });

        return allOrdersList;
    }

    @Override
    public int getHighestOrderNumber() throws OrderPersistenceException {
        loadAllOrders();

        //dump all orders to a simple TreeMap and get highest key
        TreeMap<Integer, Order> allOrders = new TreeMap<>();
        orders.forEach((date, orderTree) -> {
            allOrders.putAll(orderTree);
        });

        if (allOrders.isEmpty()) {
            return 0;
        } else {
            return allOrders.lastKey();
        }
    }

    /*DATA (UN)MARSHALLING*/
    /**
     * Retrieve the date from the orders dir filenames and
     *
     * @param filename {String} the filename of the orders file for that day
     * @return new LocalDate obj formatted as MMddyyyy
     */
    private LocalDate parseDateFromFilename(String filename) {
        String dateString = filename.substring(7, 14); //filename format is Orders_MMddyyyy.txt
        LocalDate orderDate = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("MMddyyyy"));

        return orderDate;
    }

    /**
     * Marshall an Order obj to text
     *
     * @param anOrder {Order} an active order
     * @return {String} a delimited string of text with all obj information
     */
    private String marshallOrder(Order anOrder) {
//        OrderNumber,CustomerName,StateAbbrev,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total
        String orderAsText = anOrder.getOrderNum() + DELIMITER;

        String convertedName = anOrder.getCustomerName().replace(DELIMITER, DELIMITER_REPLACEMENT);
        orderAsText += convertedName + DELIMITER;

        orderAsText += anOrder.getState().getStateAbbreviation();
        orderAsText += anOrder.getState().getTaxRate().toString();
        orderAsText += anOrder.getProduct().getProductType();
        orderAsText += anOrder.getArea().toString();
        orderAsText += anOrder.getProduct().getCostPerSqFt().toString();
        orderAsText += anOrder.getProduct().getLaborCostPerSqFt().toString();
        orderAsText += anOrder.getMaterialCost().toString();
        orderAsText += anOrder.getLaborCost().toString();
        orderAsText += anOrder.getTax().toString();
        orderAsText += anOrder.getTotal().toString();

        return orderAsText;
    }

    /**
     * Unmarshall delimited text into Order obj's
     *
     * @param orderAsText {String} delimited lines of text in the order
     *                    directory's files
     * @return {Order} a fully constructed Order obj
     */
    private Order unmarshallOrder(String orderAsText, String filename) {
        String[] orderTokens = orderAsText.split(DELIMITER);

        LocalDate orderDate = parseDateFromFilename(filename);

        int orderNum = Integer.parseInt(orderTokens[0]);
        String orderCustName = orderTokens[1].replace(DELIMITER_REPLACEMENT, DELIMITER);

        State orderState = new State(orderTokens[2], new BigDecimal(orderTokens[3]));
        Product orderProduct = new Product(orderTokens[4], new BigDecimal(orderTokens[6]), new BigDecimal(orderTokens[7]));
        BigDecimal orderArea = new BigDecimal(orderTokens[5]);
        BigDecimal orderMatCost = new BigDecimal(orderTokens[8]);
        BigDecimal orderLaborCost = new BigDecimal(orderTokens[9]);
        BigDecimal orderTax = new BigDecimal(orderTokens[10]);
        BigDecimal orderTotal = new BigDecimal(orderTokens[11]);

        return new Order(orderDate, orderNum, orderCustName, orderState, orderProduct, orderArea, orderMatCost, orderLaborCost, orderTax, orderTotal);
    }

    /**
     * Load all persisted orders to treemap
     *
     * @throws OrderPersistenceException if cannot read from orders directory or
     *                                   if directory is empty
     */
    private void loadAllOrders() throws OrderPersistenceException {
        File dir = new File(ORDER_DIRECTORY);

        File[] orderDirList = dir.listFiles();

        if (orderDirList.length == 0) {
            return; //nothing to load
        } else {
            for (File file : dir.listFiles()) {

                TreeMap<Integer, Order> ordersOnDate = new TreeMap<>(); //inner tree map
                String currentLine;
                Order currentOrder;
                LocalDate ordersDate = null;

                Scanner sc;
                try {
                    sc = new Scanner(new BufferedReader(new FileReader(file)));
                } catch (FileNotFoundException e) {
                    throw new OrderPersistenceException("Could not load from Order directory", e);
                }

                ordersOnDate.clear();
                sc.nextLine(); //skip header

                while (sc.hasNextLine()) {
                    currentLine = sc.nextLine();
                    currentOrder = unmarshallOrder(currentLine, file.getName());

                    ordersOnDate.put(currentOrder.getOrderNum(), currentOrder);

                    ordersDate = parseDateFromFilename(file.getName());
                }

                orders.put(ordersDate, ordersOnDate);

                sc.close();
            }
        }
    }

    /**
     * Persist all active orders to order's directory
     */
    private void writeAllOrders() throws OrderPersistenceException {
        orders.forEach((date, ordersOnDate) -> {
            //create new file
            String filename = "Orders_" + date.format(DateTimeFormatter.ofPattern("MMddyyyy")) + ".txt";

            PrintWriter out;
            try {
                out = new PrintWriter(new FileWriter(new File(ORDER_DIRECTORY, filename)));

                //header
                out.println("OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total");

                //marshall the orders on date to file
                ordersOnDate.values().stream()
                        .forEach((order) -> {
                            String orderAsText = marshallOrder(order);
                            out.println(orderAsText);
                            out.flush();
                        });

                out.close();
            } catch (IOException e) {
                //FIXME I have no idea why but it wants a try catch to catch itself...
//              throw new OrderPersistenceException("Could not create order file");
            }
        });
    }

}
