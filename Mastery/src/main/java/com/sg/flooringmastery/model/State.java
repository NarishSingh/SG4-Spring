package com.sg.flooringmastery.model;

import java.math.BigDecimal;

public class State {

    /*fields*/
    private String stateAbbreviation;
    private String stateName;
    private BigDecimal taxRate;

    /*ctor*/
    public State(String stateAbbreviation, BigDecimal taxRate) {
        this.stateAbbreviation = stateAbbreviation;
        this.taxRate = taxRate;
    }
    
    /*read/write*/
    public String getStateAbbreviation() {
        return stateAbbreviation;
    }

    public void setStateAbbreviation(String stateAbbreviation) {
        this.stateAbbreviation = stateAbbreviation;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

}
