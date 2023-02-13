package com.ea.util;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

import java.io.InputStream;

public class ApiOperations {

    public static void main(String[] args) {
       /* // Specify the base URL to the RESTful web service
        RestAssured.baseURI = "https://eacp.energyaustralia.com.au/codingtest/api/v1/festivals";
        // Get the RequestSpecification of the request to be sent to the server.
        RequestSpecification httpRequest = RestAssured.given();
        // specify the method type (GET) and the parameters if any.
        //In this case the request does not take any parameters
        Response response = httpRequest.request(Method.GET, "");
        // Print the status and message body of the response received from the server
        System.out.println("Status received => " + response.getStatusLine());
        System.out.println("Response=>" + response.prettyPrint());*/

        ApiOperations apiOperations = new ApiOperations();
        apiOperations.schemaValidator("https://eacp.energyaustralia.com.au/codingtest/api/v1/festivals", "schemas/festivalSchema.json");
    }

    public Response getCall(String url){
        RestAssured.baseURI=url;
        RequestSpecification httpRequest = RestAssured.given();
        return httpRequest.request(Method.GET, "");
    }

    public void schemaValidator(String url,String schemaJsonFileName){
        InputStream schema = getClass ().getClassLoader ()
                .getResourceAsStream (schemaJsonFileName);
        RestAssured
                .given()
                .when()
                .get(url)
                .then()
                .statusCode(200)
                .and()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(schema));
    }


}
