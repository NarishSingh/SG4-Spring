package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.model.Product;
import com.sg.flooringmastery.service.InvalidProductException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ProductDaoImpl implements ProductDao {

    private Map<String, Product> products = new HashMap<>();
    private final String PRODUCTS_FILE;
    private final String DELIMITER = ",";

    /*CTORS*/
    //production
    public ProductDaoImpl() {
        this.PRODUCTS_FILE = ".\\MasteryFileData\\Data\\Products.txt";
    }

    //testing
    public ProductDaoImpl(String productsFileAsText) {
        this.PRODUCTS_FILE = productsFileAsText;
    }

    /**
     * Unmarshall a delimited string into a Product obj
     *
     * @param productAsText {String} a delimited string from the products' data
     *                      file
     */
    private Product unmarshallProduct(String productAsText) {
        String[] productTokens = productAsText.split(DELIMITER);

        String productName = productTokens[0];
        BigDecimal costPerSqFt = new BigDecimal(productTokens[1]);
        BigDecimal laborCostPerSqFt = new BigDecimal(productTokens[2]);

        return new Product(productName, costPerSqFt, laborCostPerSqFt);
    }

    /**
     * Load all products from data file into map
     *
     * @throws ProductReadException if cannot read from product data file
     */
    private void loadProducts() throws ProductReadException {
        Scanner sc;

        try {
            sc = new Scanner(new BufferedReader(new FileReader(PRODUCTS_FILE)));
        } catch (FileNotFoundException e) {
            throw new ProductReadException("Couldn't load Product data roster", e);
        }

        String currentLine;
        Product currentProduct;
        products.clear();

        sc.nextLine(); //skip header of file

        while (sc.hasNextLine()) {
            currentLine = sc.nextLine();
            currentProduct = unmarshallProduct(currentLine);

            products.put(currentProduct.getProductType(), currentProduct);
        }
    }

    @Override
    public Product readProductByID(String userProduct) throws InvalidProductException, ProductReadException {
        loadProducts();
        
        if (products.containsKey(userProduct)) {
            return products.get(userProduct);
        } else {
            throw new InvalidProductException("We do not floor with this type of material at this time");
        }
    }

    @Override
    public List<Product> getValidProducts() throws ProductReadException {
        loadProducts();
        List<Product> validProducts = new ArrayList<>();

        products.values().stream()
                .forEach((product) -> validProducts.add(product));

        return validProducts;
    }
}
