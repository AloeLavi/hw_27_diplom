package ru.moysklad.api.specs;

import config.ApiConfig;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.aeonbits.owner.ConfigFactory;

import static helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.*;
import static io.restassured.http.ContentType.JSON;

public class CommonSpecs {

    public static ApiConfig config = ConfigFactory.create(ApiConfig.class, System.getProperties());

    public static RequestSpecification commonRequestSpec = with()
            .filter(withCustomTemplates())
            .baseUri("https://online.moysklad.ru/api/remap/1.2")
            .log().all()
            .contentType(JSON)
            .header("Authorization", config.token());

    public static ResponseSpecification commonResponseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .build().statusCode(200);
}
