package com.ea.stepdef;

import com.cucumber.listener.Reporter;
import com.ea.util.ApiOperations;
import com.ea.util.Props;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.List;


public class ApiStepDef {

    public  String baseURL = null;
    String getFestivalEndpoint = null;

    @Given("^Endpoint to get festivals$")
    public void getFestivalEndpoint() throws Throwable{
        Props props = new Props();
        baseURL = props.getProperty("baseurl");
        Reporter.addStepLog("Base url is:"+baseURL);
        getFestivalEndpoint = baseURL+props.getProperty("getFestival");
    }

    @Then("^User validates getFestival enpoint$")
    public void validateGetFestivalEndpoint(DataTable dataTable){
        List<String> data = dataTable.asList(String.class);
        Reporter.addStepLog("Expected endpoint:"+getFestivalEndpoint);
        for(String d : data){
            Reporter.addStepLog("Actual endpoint:"+baseURL+d);
            Assert.assertEquals("Invalid get Festival endpoint",baseURL+d,getFestivalEndpoint);
        }
    }

    @When("^User triggers getfestival for success response$")
    public void triggergetFestivalApi(){
        ApiOperations apiOperations = new ApiOperations();
        Response response = apiOperations.getCall(getFestivalEndpoint);
        Reporter.addStepLog("Response status code:"+response.getStatusCode());
        Reporter.addStepLog("Response status code:"+response.prettyPrint());
        Assert.assertEquals("Expected status code doesnt match",200,response.getStatusCode());
    }

    @When("^User triggers invalid endpoint and validates response$")
    public void triggerInvalidgetFestivalApi(){
        ApiOperations apiOperations = new ApiOperations();
        Response response = apiOperations.getCall( baseURL+"/api/v1/restivals");
        Reporter.addStepLog("Response status code:"+response.getStatusCode());
        Reporter.addStepLog("Response status code:"+response.prettyPrint());
        Assert.assertEquals("Expected status code doesnt match",404,response.getStatusCode());
    }

    @When("^User triggers getfestival for Throttled response$")
    public void triggergetFestivalApiForThrotlingResponse(){
        ApiOperations apiOperations = new ApiOperations();
        Response response =null;
        for(int i=1; i<=10; i++){
            response= apiOperations.getCall(getFestivalEndpoint);
            Reporter.addStepLog("Triggere number:"+i);
            if(response.getStatusCode()==429){
                Reporter.addStepLog("received 429 response code in Triggere number:"+i);
                break;
            }
        }
        Reporter.addStepLog("Response status code:"+response.getStatusCode());
        Reporter.addStepLog("Response status message:"+response.prettyPrint());
        Assert.assertEquals("",429,response.getStatusCode());
        Assert.assertEquals("throttling response is not as expected","Too many requests, throttling",response.getBody().asString());
    }

    @When("^User validates the server in response header$")
    public void IteratingHeaders()
    {
        ApiOperations apiOperations = new ApiOperations();
        Response response = response= apiOperations.getCall(getFestivalEndpoint);
        Headers allHeaders = response.headers();
        // Print all headfers in report and validate server. Will not print all headers in case of failure
        for(Header header : allHeaders) {
            Reporter.addStepLog("Key: " + header.getName() + " Value: " + header.getValue());
            if(header.getName().equals("server")){
                Assert.assertEquals("Server name not matched in the header","nginx",header.getValue());
            }
        }
    }

    @When("^User validates the festival schema$")
    public void validateSchema() {
        ApiOperations apiOperations = new ApiOperations();
        apiOperations.schemaValidator(getFestivalEndpoint, "schemas/festivalSchema.json");
    }
}
