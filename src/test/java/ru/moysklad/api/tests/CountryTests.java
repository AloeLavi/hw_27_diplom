package ru.moysklad.api.tests;

import org.junit.jupiter.api.Test;
import ru.moysklad.api.lombok.CreateCountryRequest;
import ru.moysklad.api.lombok.CreateCountryResponse;


import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;


public class CountryTests {
    @Test
    void CreateCountryWithRequiredFields () {

        CreateCountryRequest body = new CreateCountryRequest();
        body.setName("Монштадт1");


        CreateCountryResponse response = given()
                .log().all()
                .contentType(JSON)
                .header("Authorization", "Basic YWRtaW5AdmJhZ3JvdmEyOjEyMzEyMw==")
                .body(body)
                .when()
                .post("https://online.moysklad.ru/api/remap/1.2/entity/country/")
                .then()
                .log().all()
                .statusCode(200)
                .extract().as(CreateCountryResponse.class);

        assertThat(response.getId()).isNotNull();
        assertThat(response.getName()).isEqualTo("Монштадт1");
        assertThat(response.getShared()).isEqualTo(true);

     //    String idOfCountryWithoutDescription = response.getId();

    }
    @Test
    void CreateCountryWithAllFields () {

        CreateCountryRequest body = new CreateCountryRequest();
        body.setName("Монштадт1");
        body.setDescription("Лучший город Тейвата");
        body.setCode("123");
        body.setExternalCode("ExtCode");

        CreateCountryResponse response = given()
                .log().uri()
                .contentType(JSON)
                .header("Authorization", "Basic YWRtaW5AdmJhZ3JvdmEyOjEyMzEyMw==")
                .body(body)
                .when()
                .post("https://online.moysklad.ru/api/remap/1.2/entity/country/")
                .then()
                .log().all()
                .statusCode(200)
                .extract().as(CreateCountryResponse.class);

        assertThat(response.getId()).isNotNull();
        assertThat(response.getName()).isEqualTo("Монштадт1");
        assertThat(response.getShared()).isEqualTo(true);



    }
}
