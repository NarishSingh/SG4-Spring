package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.ProductDao;
import com.sg.flooringmastery.model.Product;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ProductDaoImplStub implements ProductDao {

    private Map<String, Product> testProducts = new TreeMap<>();
    private String TEST_PRODUCTS_FILE;
    private final String DELIMITER = ",";
    public Product onlyProduct;

    public ProductDaoImplStub() {
        this.TEST_PRODUCTS_FILE = ".\\TestingFileData\\Data\\testProducts.txt";
    }

    public ProductDaoImplStub(String TEST_PRODUCTS_FILE) {
        this.TEST_PRODUCTS_FILE = TEST_PRODUCTS_FILE;
    }

    /*STUBS*/
    @Override
    public List<Product> getValidProducts() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Product readProductByID(String productAsText) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
