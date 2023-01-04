package ru.moysklad.api.lombok;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)

public class SingleCountryRequest {
    private String name;
    private String description;
    private String externalCode;
    private String code;


}
