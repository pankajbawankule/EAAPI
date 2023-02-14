package com.ea.util;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

import java.io.InputStream;

public class ApiOperations {

    //Method for testing
    public static void main(String[] args) {
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
