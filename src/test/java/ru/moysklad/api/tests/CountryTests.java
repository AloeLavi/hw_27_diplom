package ru.moysklad.api.tests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.moysklad.api.lombok.SingleCountryRequest;
import ru.moysklad.api.lombok.SingleCountryResponse;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.moysklad.api.specs.CommonSpecs.commonRequestSpec;
import static ru.moysklad.api.specs.CommonSpecs.commonResponseSpec;


public class CountryTests extends ApiTestBase {
    @Test
    @DisplayName("Создание страны с параметром Name")
    void createCountryWithRequiredFields () {

        SingleCountryRequest body = new SingleCountryRequest();
        body.setName(countryName);

        SingleCountryResponse response = given()
                .spec(commonRequestSpec)
                .body(body)
                .when()
                .post("/entity/country/")
                .then()
                .spec(commonResponseSpec)
                .extract().as(SingleCountryResponse.class);

        assertThat(response.getId()).isNotNull();
        assertThat(response.getName()).isEqualTo(countryName);
        assertThat(response.getShared()).isEqualTo(true);

        //вычищаем данные
        String CountryId = response.getId();
        deleteCountry(CountryId);

    }

    @Test
    @DisplayName("Создание страны с параметрами Name, Description, Code, ExternalCode")
    void createCountryWithAllFields () {

        SingleCountryRequest body = new SingleCountryRequest();
        body.setName(countryName);
        body.setDescription(countryDescription);
        body.setCode(countryCode);
        body.setExternalCode(countryExtCode);

        SingleCountryResponse response = given()
                .spec(commonRequestSpec)
                .body(body)
                .when()
                .post("/entity/country/")
                .then()
                .spec(commonResponseSpec)
                .extract().as(SingleCountryResponse.class);

        assertThat(response.getId()).isNotNull();
        assertThat(response.getName()).isEqualTo(countryName);
        assertThat(response.getShared()).isEqualTo(true);
        assertThat(response.getDescription()).isEqualTo(countryDescription);
        assertThat(response.getCode()).isEqualTo(countryCode);
        assertThat(response.getExternalCode()).isEqualTo(countryExtCode);

        //вычищаем данные
        String CountryId = response.getId();
        deleteCountry(CountryId);


    }

    @Test
    @DisplayName("Редактирование страны")
    void editCountry(){
        SingleCountryRequest CreateCountryBody = new SingleCountryRequest();
        CreateCountryBody.setName(countryName);
        CreateCountryBody.setDescription(countryDescription);

        SingleCountryResponse CreateCountryResponse = given()
                .spec(commonRequestSpec)
                .body(CreateCountryBody)
                .when()
                .post("/entity/country/")
                .then()
                .spec(commonResponseSpec)
                .extract().as(SingleCountryResponse.class);

        String CountryId = CreateCountryResponse.getId(); // получаем ID для запроса на редактирование

        SingleCountryRequest EditCountryBody = new SingleCountryRequest();
        EditCountryBody.setDescription(countryDescription2);

        SingleCountryResponse EditCountryResponse = given()
                .spec(commonRequestSpec)
                .body(EditCountryBody)
                .when()
                .put("/entity/country/" + CountryId)
                .then()
                .spec(commonResponseSpec)
               .extract().as(SingleCountryResponse.class);

        assertThat(EditCountryResponse.getDescription()).isEqualTo(countryDescription2);

        //вычищаем данные
        deleteCountry(CountryId);

    }


}
