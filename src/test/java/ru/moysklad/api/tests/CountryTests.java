package ru.moysklad.api.tests;

import org.junit.jupiter.api.Test;
import ru.moysklad.api.lombok.CreateCountryResponse;


import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;


public class CountryTests {
    @Test
    void CreateCountry () {
        String data = "{ \"name\": \"Нарния\"}";

        CreateCountryResponse response = given()
                .log().uri()
                .contentType(JSON)
                .header("Authorization", "Basic YWRtaW5AdmJhZ3JvdmEyOjEyMzEyMw==")
                .body(data)
                .when()
                .post("https://online.moysklad.ru/api/remap/1.2/entity/country/")
                .then()
                .log().all()
                .statusCode(200)
                .extract().as(CreateCountryResponse.class);
        assertThat(response.getId()).isNotNull();
        assertThat(response.getName()).isEqualTo("Нарния");

        String countryId = response.getId();

    }
}
