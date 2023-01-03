package ru.moysklad.api.lombok;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)

public class CreateCountryResponse {
    private String id;
     @JsonIgnore
     private Meta meta;
    private String accountId;
    @JsonIgnore
    private Owner owner;
    private Boolean shared;
    @JsonIgnore
    private Group group;
    private String updated;
    private String name;
    private String externalCode;
    private String description;
    private String code;





}
