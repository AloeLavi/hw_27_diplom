package ru.moysklad.api.lombok;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)

public class SingleCountryResponse {
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
