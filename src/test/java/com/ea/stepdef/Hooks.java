package com.ea.stepdef;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {

    //This method will be used to set up environment before execution
    @Before
    public void setUp(Scenario scenario){
        System.out.println("Executing before hooks");
    }

    //This method will be used to release all test resources and generate user defined report
    @After
    public void tearDown(){

    }
}
