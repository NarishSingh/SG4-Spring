package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.StateDao;
import com.sg.flooringmastery.model.State;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class StateDaoStub implements StateDao {

    private Map<String, State> testStates = new TreeMap<>();
    private final String TEST_STATES_FILE;
    private final String DELIMITER = ",";

    public StateDaoStub() {
        this.TEST_STATES_FILE=".\\TestingFileData\\Data\\testStates.txt";
    }

    public StateDaoStub(String TEST_STATES_FILE) {
        this.TEST_STATES_FILE = TEST_STATES_FILE;
    }
    
    @Override
    public List<State> getValidStates() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public State readStateByID(String stateAsText) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
