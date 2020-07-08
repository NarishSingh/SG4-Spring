package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.ExportFileDao;
import com.sg.flooringmastery.model.Order;
import java.util.List;

public class ExportFileDaoStub extends ExportFileDao{

    @Override
    public void exportOrders(List<Order> activeOrders) {
        //do nothing
    }
    
}
