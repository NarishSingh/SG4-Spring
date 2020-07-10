package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.model.Product;
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
    public void testReadProductByID() {
        System.out.println("readProductByID");
        String productAsText = "";
        ProductDaoImpl instance = new ProductDaoImpl();
        Product expResult = null;
        Product result = instance.readProductByID(productAsText);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValidProducts method, of class ProductDaoImpl.
     */
    @Test
    public void testGetValidProducts() {
        System.out.println("getValidProducts");
        ProductDaoImpl instance = new ProductDaoImpl();
        List<Product> expResult = null;
        List<Product> result = instance.getValidProducts();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
