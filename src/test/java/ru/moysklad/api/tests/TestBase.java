package ru.moysklad.api.tests;

import static io.restassured.RestAssured.given;
import static ru.moysklad.api.specs.CommonSpecs.CommonRequestSpec;
import static ru.moysklad.api.specs.CommonSpecs.CommonResponseSpec;

public class TestBase {
    public void deleteCountry(String id){
        given()
                .spec(CommonRequestSpec)
                .when()
                .delete("/entity/country/" + id)
                .then()
                .spec(CommonResponseSpec);
    }
}
