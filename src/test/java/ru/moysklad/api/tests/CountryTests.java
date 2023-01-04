package ru.moysklad.api.tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import ru.moysklad.api.lombok.CreateCountryRequest;
import ru.moysklad.api.lombok.CreateCountryResponse;


import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.moysklad.api.specs.CommonSpecs.CommonRequestSpec;
import static ru.moysklad.api.specs.CommonSpecs.CommonResponseSpec;


public class CountryTests {
    static String IdOfCountryWithRequiredFields;
    static String IdOfCountryWithAllFields;
    @Test
    void createCountryWithRequiredFields () {

        CreateCountryRequest body = new CreateCountryRequest();
        body.setName("Монштадт");


        CreateCountryResponse response = given()
                .spec(CommonRequestSpec)
                .body(body)
                .when()
                .post("/entity/country/")
                .then()
                .spec(CommonResponseSpec)
                .extract().as(CreateCountryResponse.class);

        assertThat(response.getId()).isNotNull();
        assertThat(response.getName()).isEqualTo("Монштадт");
        assertThat(response.getShared()).isEqualTo(true);

        IdOfCountryWithRequiredFields = response.getId();


    }

    @Test
    void createCountryWithAllFields () {

        CreateCountryRequest body = new CreateCountryRequest();
        body.setName("Монштадт");
        body.setDescription("Лучший город Тейвата");
        body.setCode("123");
        body.setExternalCode("ExtCode");

        CreateCountryResponse response = given()
                .spec(CommonRequestSpec)
                .log().uri()
                .body(body)
                .when()
                .post("/entity/country/")
                .then()
                .spec(CommonResponseSpec)
                .extract().as(CreateCountryResponse.class);

        assertThat(response.getId()).isNotNull();
        assertThat(response.getName()).isEqualTo("Монштадт");
        assertThat(response.getShared()).isEqualTo(true);
        assertThat(response.getDescription()).isEqualTo("Лучший город Тейвата");
        assertThat(response.getCode()).isEqualTo("123");
        assertThat(response.getExternalCode()).isEqualTo("ExtCode");

        IdOfCountryWithAllFields = response.getId();


    }

    @AfterAll
    static void cleanAll() {
        given()
                .spec(CommonRequestSpec)
                .when()
                .delete("/entity/country/" + IdOfCountryWithRequiredFields)
                .then()
                .spec(CommonResponseSpec);

        given()
                .spec(CommonRequestSpec)
                .when()
                .delete("/entity/country/" + IdOfCountryWithAllFields)
                .then()
                .spec(CommonResponseSpec);

    }


}
