package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.model.State;
import com.sg.flooringmastery.service.InvalidStateException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StateDaoImplTest {

    StateDaoImpl testDao;

    public StateDaoImplTest() {
    }

    @BeforeEach
    public void setUp() throws IOException {
        String testFile = ".//TestingFileData//testTaxes.txt";
        new FileWriter(testFile);

        ApplicationContext actx = new ClassPathXmlApplicationContext("applicationContext.xml");
        testDao = actx.getBean("testStateDao", StateDaoImpl.class);
    }

    /**
     * Test of readStateByID method, of class StateDaoImpl.
     */
    @Test
    public void testReadStateByID() throws InvalidStateException {
        System.out.println("readStateByID");
        String stateAsText = "";
        StateDaoImpl instance = new StateDaoImpl();
        State expResult = null;
        State result = instance.readStateByID(stateAsText);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValidStates method, of class StateDaoImpl.
     */
    @Test
    public void testGetValidStates() throws StateReadException {
        System.out.println("getValidStates");
        StateDaoImpl instance = new StateDaoImpl();
        List<State> expResult = null;
        List<State> result = instance.getValidStates();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
