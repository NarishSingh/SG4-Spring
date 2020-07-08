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

public class ProductDaoTest {

    ProductDao testDao;

    public ProductDaoTest() {
    }

    @BeforeEach
    public void setUp() throws IOException {
        String testFile = ".//TestingFileData//testProducts.txt";
        new FileWriter(testFile);

        ApplicationContext actx = new ClassPathXmlApplicationContext("applicationContext.xml");
        testDao = actx.getBean("testProductDao", ProductDao.class);
    }

    /**
     * Test of readProductByID method, of class ProductDao.
     */
    @Test
    public void testReadProductByID() {
        System.out.println("readProductByID");
        String productAsText = "";
        ProductDao instance = new ProductDao();
        Product expResult = null;
        Product result = instance.readProductByID(productAsText);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValidProducts method, of class ProductDao.
     */
    @Test
    public void testGetValidProducts() {
        System.out.println("getValidProducts");
        ProductDao instance = new ProductDao();
        List<Product> expResult = null;
        List<Product> result = instance.getValidProducts();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
