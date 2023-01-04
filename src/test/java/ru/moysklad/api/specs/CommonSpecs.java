package ru.moysklad.api.specs;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.ALL;
import static io.restassured.http.ContentType.JSON;

public class CommonSpecs {
    public static RequestSpecification CommonRequestSpec = with()
            .filter(new AllureRestAssured())
            .baseUri("https://online.moysklad.ru/api/remap/1.2")
            .log().all()
            .contentType(JSON)
            .header("Authorization", "Basic YWRtaW5AdmJhZ3JvdmEyOjEyMzEyMw==");

    public static ResponseSpecification CommonResponseSpec = new ResponseSpecBuilder()
            .log(ALL)
            .build().statusCode(200);
}
