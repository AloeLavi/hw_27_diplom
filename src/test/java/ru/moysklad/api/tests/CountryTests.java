package ru.moysklad.api.tests;

import org.junit.jupiter.api.Test;
import ru.moysklad.api.lombok.SingleCountryRequest;
import ru.moysklad.api.lombok.SingleCountryResponse;


import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.moysklad.api.specs.CommonSpecs.CommonRequestSpec;
import static ru.moysklad.api.specs.CommonSpecs.CommonResponseSpec;


public class CountryTests extends ApiTestBase {
    @Test
    void createCountryWithRequiredFields () {

        SingleCountryRequest body = new SingleCountryRequest();
        body.setName("Монштадт");


        SingleCountryResponse response = given()
                .spec(CommonRequestSpec)
                .body(body)
                .when()
                .post("/entity/country/")
                .then()
                .spec(CommonResponseSpec)
                .extract().as(SingleCountryResponse.class);

        assertThat(response.getId()).isNotNull();
        assertThat(response.getName()).isEqualTo("Монштадт");
        assertThat(response.getShared()).isEqualTo(true);

        //вычищаем данные
        String CountryId = response.getId();
        deleteCountry(CountryId);


    }

    @Test
    void createCountryWithAllFields () {

        SingleCountryRequest body = new SingleCountryRequest();
        body.setName("Монштадт");
        body.setDescription("Лучший город Тейвата");
        body.setCode("123");
        body.setExternalCode("ExtCode");

        SingleCountryResponse response = given()
                .spec(CommonRequestSpec)
                .body(body)
                .when()
                .post("/entity/country/")
                .then()
                .spec(CommonResponseSpec)
                .extract().as(SingleCountryResponse.class);

        assertThat(response.getId()).isNotNull();
        assertThat(response.getName()).isEqualTo("Монштадт");
        assertThat(response.getShared()).isEqualTo(true);
        assertThat(response.getDescription()).isEqualTo("Лучший город Тейвата");
        assertThat(response.getCode()).isEqualTo("123");
        assertThat(response.getExternalCode()).isEqualTo("ExtCode");

        //вычищаем данные
        String CountryId = response.getId();
        deleteCountry(CountryId);


    }

    @Test
    void editCountry(){
        SingleCountryRequest CreateCountryBody = new SingleCountryRequest();
        CreateCountryBody.setName("Снежная");
        CreateCountryBody.setDescription("Там очень холодно");

        SingleCountryResponse CreateCountryResponse = given()
                .spec(CommonRequestSpec)
                .body(CreateCountryBody)
                .when()
                .post("/entity/country/")
                .then()
                .spec(CommonResponseSpec)
                .extract().as(SingleCountryResponse.class);

        String CountryId = CreateCountryResponse.getId();

        SingleCountryRequest EditCountryBody = new SingleCountryRequest();
        EditCountryBody.setDescription("Там ОЧЕНЬ-ОЧЕНЬ холодно");

        SingleCountryResponse EditCountryResponse = given()
                .spec(CommonRequestSpec)
                .body(EditCountryBody)
                .when()
                .put("/entity/country/" + CountryId)
                .then()
                .spec(CommonResponseSpec)
               .extract().as(SingleCountryResponse.class);

        assertThat(EditCountryResponse.getDescription()).isEqualTo("Там ОЧЕНЬ-ОЧЕНЬ холодно");

        //вычищаем данные
        deleteCountry(CountryId);

    }


}
