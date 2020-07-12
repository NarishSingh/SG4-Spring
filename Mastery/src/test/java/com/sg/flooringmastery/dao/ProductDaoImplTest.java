package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.model.Product;
import com.sg.flooringmastery.service.InvalidProductException;
import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProductDaoImplTest {

    ProductDaoImpl testDao;

    public ProductDaoImplTest() {
    }

    @BeforeEach
    public void setUp() throws IOException {

        ApplicationContext actx = new ClassPathXmlApplicationContext("applicationContext.xml");
        testDao = actx.getBean("testProductDao", ProductDaoImpl.class);
    }

    /**
     * Test of readProductByID method, of class ProductDaoImpl.
     */
    @Test
    public void testReadProductByID() throws InvalidProductException, ProductReadException {
        System.out.println("readProductByID");
        
        //arrange
        final String testProductKeyCarpet = "Carpet";
        final String testProductKeyLaminate = "Laminate";
        
        //act
        Product testCarpet = testDao.readProductByID(testProductKeyCarpet);
        Product testLaminate = testDao.readProductByID(testProductKeyLaminate);
        
        //assert
        assertEquals(testCarpet.getProductType(), testProductKeyCarpet, "Should've retrieved Carpet");
        assertEquals(testLaminate.getProductType(), testProductKeyLaminate, "Should've retrieved Carpet");
    }

    /**
     * Test of getValidProducts method, of class ProductDaoImpl.
     */
    @Test
    public void testGetValidProducts() throws ProductReadException, InvalidProductException {
        System.out.println("getValidProducts");
        
        //arrange
        final String testProductKeyCarpet = "Carpet";
        final String testProductKeyLaminate = "Laminate";
        
        Product testCarpet = testDao.readProductByID(testProductKeyCarpet);
        Product testLaminate = testDao.readProductByID(testProductKeyLaminate);
        
        //act
        List<Product> allProducts = testDao.getValidProducts();
        
        //assert
        assertTrue(allProducts.contains(testCarpet), "List should contain Carpet");
        assertTrue(allProducts.contains(testLaminate), "List should contain Laminate");
    }

}
