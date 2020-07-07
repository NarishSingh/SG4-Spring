package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.model.Order;
import java.util.List;

public class ExportFileDao {

    public final String BACKUP_FILE;
    public final String DELIMITER = ",";

    public ExportFileDao() {
        this.BACKUP_FILE = ".\\MasteryFileData\\Backup\\DataExport.txt";
    }

    public ExportFileDao(String exportFileAsText) {
        this.BACKUP_FILE = exportFileAsText;
    }

    /**
     * Write all active orders to an export file
     *
     * @param activeOrders {List} all active orders
     */
    public void exportOrders(List<Order> activeOrders) {

    }

    /**
     * Marshall an order to delimited String of text
     *
     * @param anOrder {Order} an active order
     * @return {String} the order obj as a delimited String for the export file
     */
    private String marshallOrder(Order anOrder) {

    }
    
}
