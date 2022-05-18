package com.getir.readingisgood.Enum;

import lombok.Getter;

@Getter
public enum OrderStatus {
    PENDING("PENDING"),
    CONFIRMED("CONFIRMED"),
    CANCELED("CANCELED");


    private final String value;

    OrderStatus(String value) {
        this.value = value;
    }

}
