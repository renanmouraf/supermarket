package com.supermarkt.infra.erroapi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class ApiErroValidacao extends ApiSubErro {
    private String object;
    private String field;
    private Object rejectedValue;
    private String message;

    ApiErroValidacao(String object, String message) {
        this.object = object;
        this.message = message;
    }
}