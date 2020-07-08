package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.model.State;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StateDaoTest {

    StateDao testDao;

    public StateDaoTest() {
    }

    @BeforeEach
    public void setUp() throws IOException {
        String testFile = ".//TestingFileData//testTaxes.txt";
        new FileWriter(testFile);

        ApplicationContext actx = new ClassPathXmlApplicationContext("applicationContext.xml");
        testDao = actx.getBean("testStateDao", StateDao.class);
    }

    /**
     * Test of readStateByID method, of class StateDao.
     */
    @Test
    public void testReadStateByID() {
        System.out.println("readStateByID");
        String stateAsText = "";
        StateDao instance = new StateDao();
        State expResult = null;
        State result = instance.readStateByID(stateAsText);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValidStates method, of class StateDao.
     */
    @Test
    public void testGetValidStates() {
        System.out.println("getValidStates");
        StateDao instance = new StateDao();
        List<State> expResult = null;
        List<State> result = instance.getValidStates();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
