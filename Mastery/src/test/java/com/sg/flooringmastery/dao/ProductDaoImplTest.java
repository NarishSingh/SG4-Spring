package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.model.Product;
import com.sg.flooringmastery.service.InvalidProductException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProductDaoImplTest {

    ProductDaoImpl testDao;

    public ProductDaoImplTest() {
    }

    @BeforeEach
    public void setUp() throws IOException {
        String testFile = ".//TestingFileData//testProducts.txt";
        new FileWriter(testFile);

        ApplicationContext actx = new ClassPathXmlApplicationContext("applicationContext.xml");
        testDao = actx.getBean("testProductDao", ProductDaoImpl.class);
    }

    /**
     * Test of readProductByID method, of class ProductDaoImpl.
     */
    @Test
    public void testReadProductByID() throws InvalidProductException, ProductReadException {
        System.out.println("readProductByID");
    }

    /**
     * Test of getValidProducts method, of class ProductDaoImpl.
     */
    @Test
    public void testGetValidProducts() throws ProductReadException {
        System.out.println("getValidProducts");
    }

}
