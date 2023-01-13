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
        String countryId = response.getId();
        deleteCountry(countryId);

    }

    @Test
    @DisplayName("Создание страны с параметрами Name, Description, Code, ExternalCode")
    void createCountryWithAllFields() {

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
        String countryId = response.getId();
        deleteCountry(countryId);


    }

    @Test
    @DisplayName("Попытка создания страны без обязательных полей")
    void createCountryWithoutRequiredFields(){
        SingleCountryRequest body = new SingleCountryRequest();
        given()
                .spec(commonRequestSpec)
                .body(body)
                .when()
                .post("/entity/country/")
                .then()
                .log().all()
                .statusCode(412);
    }

    @Test
    @DisplayName("Редактирование страны")
    void editCountry(){

        //создаем страну
        SingleCountryRequest createCountryBody = new SingleCountryRequest();
        createCountryBody.setName(countryName);
        createCountryBody.setDescription(countryDescription);

        SingleCountryResponse createCountryResponse = given()
                .spec(commonRequestSpec)
                .body(createCountryBody)
                .when()
                .post("/entity/country/")
                .then()
                .spec(commonResponseSpec)
                .extract().as(SingleCountryResponse.class);

        String countryId = createCountryResponse.getId(); // получаем ID для запроса на редактирование

        SingleCountryRequest editCountryBody = new SingleCountryRequest();
        editCountryBody.setDescription(countryDescription2);

        SingleCountryResponse editCountryResponse = given()
                .spec(commonRequestSpec)
                .body(editCountryBody)
                .when()
                .put("/entity/country/" + countryId)
                .then()
                .spec(commonResponseSpec)
               .extract().as(SingleCountryResponse.class);

        assertThat(editCountryResponse.getDescription()).isEqualTo(countryDescription2);

        //вычищаем данные
        deleteCountry(countryId);

    }

    @Test
    @DisplayName("Получение страны по ID")
    void getCountry(){
        //создаем страну
        SingleCountryRequest createCountryBody = new SingleCountryRequest();
        createCountryBody.setName(countryName);
        createCountryBody.setDescription(countryDescription);
        createCountryBody.setCode(countryCode);
        createCountryBody.setExternalCode(countryExtCode);

        SingleCountryResponse createCountryResponse = given()
                .spec(commonRequestSpec)
                .body(createCountryBody)
                .when()
                .post("/entity/country/")
                .then()
                .spec(commonResponseSpec)
                .extract().as(SingleCountryResponse.class);

        String countryId = createCountryResponse.getId(); // получаем ID для проверки запроса GET по стране

        SingleCountryResponse getCountryResponse = given()
                .spec(commonRequestSpec)
                .when()
                .get("/entity/country/" + countryId)
                .then()
                .spec(commonResponseSpec)
                .extract().as(SingleCountryResponse.class);
        assertThat(getCountryResponse.getId()).isEqualTo(countryId);
        assertThat(getCountryResponse.getName()).isEqualTo(countryName);
        assertThat(getCountryResponse.getDescription()).isEqualTo(countryDescription);
        assertThat(getCountryResponse.getCode()).isEqualTo(countryCode);
        assertThat(getCountryResponse.getExternalCode()).isEqualTo(countryExtCode);

        //вычищаем данные
        deleteCountry(countryId);

    }

}
