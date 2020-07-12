package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.model.State;
import com.sg.flooringmastery.service.InvalidStateException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class StateDaoImpl implements StateDao {

    private Map<String, State> states = new HashMap<>(); //uses abbrev. as key
    private final String STATES_FILE;
    private final String DELIMITER = ",";

    /*CTOR*/
    //production
    public StateDaoImpl() {
        this.STATES_FILE = ".\\MasteryFileData\\Data\\Taxes.txt";
    }

    //testing
    public StateDaoImpl(String statesTextFile) {
        this.STATES_FILE = statesTextFile;
    }

    /**
     * Unmarshall a delimited string into a State obj
     *
     * @param stateAsText {String} a delimited string from the states' data file
     */
    private State unmarshallState(String stateAsText) {
        String[] stateTokens = stateAsText.split(DELIMITER);

        String stateAbbreviation = stateTokens[0];
        String stateName = stateTokens[1];
        BigDecimal stateTaxRate = new BigDecimal(stateTokens[2]);

        State validState = new State(stateAbbreviation, stateTaxRate);
        validState.setStateName(stateName);

        return validState;
    }

    /**
     * Load all states from data file into treemap
     *
     * @throws StateReadException if cannot read from state tax data file
     */
    private void loadStates() throws StateReadException {
        Scanner sc;

        try {
            sc = new Scanner(new BufferedReader(new FileReader(STATES_FILE)));
        } catch (FileNotFoundException e) {
            throw new StateReadException("Couldn't load State Tax data roster.", e);
        }

        String currentLine;
        State currentState;
        states.clear();

        sc.nextLine(); //skip header of file

        while (sc.hasNextLine()) {
            currentLine = sc.nextLine();
            currentState = unmarshallState(currentLine);

            states.put(currentState.getStateAbbreviation(), currentState);
        }
    }

    @Override
    public State readStateByID(String userState) throws InvalidStateException, StateReadException {
        loadStates();

        if (states.containsKey(userState)) {
            return states.get(userState);
        } else {
            throw new InvalidStateException("We are unavailable for business in this state for now");
        }
    }

    @Override
    public List<State> getValidStates() throws StateReadException {
        loadStates();
        List<State> validStates = new ArrayList<>();

        states.values().stream()
                .forEach((state) -> validStates.add(state));

        return validStates;
    }
}
