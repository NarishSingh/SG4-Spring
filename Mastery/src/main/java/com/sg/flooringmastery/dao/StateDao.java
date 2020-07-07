package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.model.State;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class StateDao {

    private Map<String, State> states = new TreeMap<>();
    private final String STATES_FILE;
    private final String DELIMITER = ",";

    /*CTOR*/
    //production
    public StateDao() {
        this.STATES_FILE = ".\\MasteryFileData\\Data\\Taxes.txt";
    }

    //testing
    public StateDao(String statesTextFile) {
        this.STATES_FILE = statesTextFile;
    }

    /**
     * Unmarshall a delimited string into a State obj
     *
     * @param stateAsText {String} a delimited string from the states' data file
     */
    private State unmarshallState(String stateAsText) {

    }

    /**
     * Load all states from data file into treemap
     */
    private void loadStates() {

    }

    /**
     * Validate the user's state request for a new or edited order
     *
     * @param stateAsText {String} user's inputted state name or abbreviation
     * @return {State} the proper state obj corresponding to user's request
     */
    public State readStateByID(String stateAsText) {

    }

    /**
     * Return all valid states from treemap as a list
     *
     * @return {List} all valid states
     */
    public List<State> getValidStates() {

    }
}
