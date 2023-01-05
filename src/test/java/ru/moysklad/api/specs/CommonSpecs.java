package ru.moysklad.api.specs;

import config.ApiConfig;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.aeonbits.owner.ConfigFactory;

import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.ALL;
import static io.restassured.http.ContentType.JSON;

public class CommonSpecs {

    public static ApiConfig config = ConfigFactory.create(ApiConfig.class, System.getProperties());

    public static RequestSpecification CommonRequestSpec = with()
            .filter(new AllureRestAssured())
            .baseUri("https://online.moysklad.ru/api/remap/1.2")
            .log().all()
            .contentType(JSON)
            .header("Authorization", config.token());

    public static ResponseSpecification CommonResponseSpec = new ResponseSpecBuilder()
            .log(ALL)
            .build().statusCode(200);
}
