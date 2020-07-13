package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.ProductDao;
import com.sg.flooringmastery.model.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class ProductDaoImplStub implements ProductDao {

    public TreeMap<String, Product> onlyProduct = new TreeMap<>();

    public ProductDaoImplStub() {
        onlyProduct.put("Carpet", new Product("Carpet", new BigDecimal("2.25"), new BigDecimal("2.10")));
    }
    
    public ProductDaoImplStub(TreeMap<String, Product> onlyProduct){
        this.onlyProduct = onlyProduct;
    }
    
    @Override
    public List<Product> getValidProducts() {
        return new ArrayList<>(onlyProduct.values());
    }

    @Override
    public Product readProductByID(String productAsText) {
        return onlyProduct.get(productAsText);
    }

}
