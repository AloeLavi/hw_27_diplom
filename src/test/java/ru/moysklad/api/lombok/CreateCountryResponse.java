package ru.moysklad.api.lombok;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)

public class CreateCountryResponse {
    private String id, name, description, code;
}
