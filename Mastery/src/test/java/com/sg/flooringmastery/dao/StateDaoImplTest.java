package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.model.State;
import com.sg.flooringmastery.service.InvalidStateException;
import java.io.IOException;
import java.util.ArrayList;
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
        ApplicationContext actx = new ClassPathXmlApplicationContext("applicationContext.xml");
        testDao = actx.getBean("testStateDao", StateDaoImpl.class);
    }

    /**
     * Test of readStateByID method, of class StateDaoImpl.
     */
    @Test
    public void testReadStateByID() throws InvalidStateException, StateReadException {
        System.out.println("readStateByID");

        //arrange
        final String testStateKeyTX = "TX";
        final String testStateKeyWA = "WA";
        State testTX = null;
        State testWA = null;

        //act
        try {
            testTX = testDao.readStateByID(testStateKeyTX);
            testWA = testDao.readStateByID(testStateKeyWA);
        } catch (InvalidStateException | StateReadException e) {
            fail("Valid states");
        }

        //assert
        assertEquals(testTX.getStateAbbreviation(), testStateKeyTX, "Should've retrieved Texas");
        assertEquals(testWA.getStateAbbreviation(), testStateKeyWA, "Should've retrieved Washington");
    }

    /**
     * Test of readStateByID method's InvalidStateException, of class
     * StateDaoImpl.
     */
    @Test
    public void testReadStateByIDInvalidStateFail() throws InvalidStateException, StateReadException {
        System.out.println("readStateByID");

        //arrange
        final String testStateKeyCA = "CA";
        State testCA = null;

        //act and assert
        try {
            testCA = testDao.readStateByID(testStateKeyCA);
        } catch (InvalidStateException e) {
            return; //pass
        } catch (StateReadException e) {
            fail("Valid state data file");
        }
    }

    /**
     * Test of getValidStates method, of class StateDaoImpl.
     */
    @Test
    public void testGetValidStates() throws StateReadException, InvalidStateException {
        System.out.println("getValidStates");

        //arrange
        final String testStateKeyTX = "TX";
        final String testStateKeyWA = "WA";
        State testTX = null;
        State testWA = null;
        List<State> allStatesFromFile = new ArrayList<>();

        try {
            testTX = testDao.readStateByID(testStateKeyTX);
            testWA = testDao.readStateByID(testStateKeyWA);
        } catch (InvalidStateException | StateReadException e) {
            fail("Valid states");
        }

        //act
        try {
            allStatesFromFile = testDao.getValidStates();
        } catch (StateReadException e) {
            fail("Valid state data file");
        }

        //assert
        assertTrue(allStatesFromFile.contains(testTX), "List should contain Texas");
        assertTrue(allStatesFromFile.contains(testWA), "List should contain Washington");
    }

}
