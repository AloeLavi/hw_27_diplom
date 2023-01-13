package ru.moysklad.api.tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import ru.moysklad.api.lombok.SingleCountryRequest;
import ru.moysklad.api.lombok.SingleCountryResponse;

import static io.restassured.RestAssured.given;
import static ru.moysklad.api.specs.CommonSpecs.commonRequestSpec;
import static ru.moysklad.api.specs.CommonSpecs.commonResponseSpec;

public class ApiTestBase {


    String countryName, countryDescription, countryDescription2, countryCode, countryExtCode;
    Faker faker = new Faker();
    @BeforeEach
    void prepareTestData() {
        countryName = faker.country().name();
        countryDescription = faker.chuckNorris().fact();
        countryDescription2 = faker.harryPotter().quote();
        countryCode = faker.country().countryCode2();
        countryExtCode = faker.country().countryCode3();
    }

    public void deleteCountry(String id){
        given()
                .spec(commonRequestSpec)
                .when()
                .delete("/entity/country/" + id)
                .then()
                .spec(commonResponseSpec);
    }

}
