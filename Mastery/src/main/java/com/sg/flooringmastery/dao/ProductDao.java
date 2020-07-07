package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.model.Product;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ProductDao {
    private Map<String, Product> products = new TreeMap<>();
    private final String PRODUCTS_FILE;
    private final String DELIMITER = ",";

    /*CTORS*/
    //production
    public ProductDao() {
        this.PRODUCTS_FILE = ".\\MasteryFileData\\Data\\Products.txt";
    }

    //testing
    public ProductDao(String productsFileAsText) {
        this.PRODUCTS_FILE = productsFileAsText;
    }
    
    /**
     * Unmarshall a delimited string into a Product obj
     *
     * @param productAsText {String} a delimited string from the products' data file
     */
    private Product unmarshallProduct(String productAsText){
        
    }
    
    /**
     * Load all products from data file into treemap
     */
    private void loadProducts(){
        
    }
    
    /**
     * Validate the user's product request for a new or edited order
     *
     * @param productAsText {String} user's inputted  product name
     * @return {State} the proper product obj corresponding to user's request
     */
    public Product readProductByID(String productAsText){
        
    }
    
    /**
     * Return all valid products from treemap as a list
     * 
     * @return {List} all valid products
     */
    public List<Product> getValidProducts(){
        
    }
}
