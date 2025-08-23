package com.example.stockmanager.domain.model;

public enum MovementType {

    DISPONIVEL("01"),
    SAIDA("02"),
    RESERVA("03"),
    PERDA("04");


    private final String code;

    MovementType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static MovementType fromCode(String code) {
        for(MovementType type : MovementType.values()) {
            if(type.getCode().equals(code)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid movement code :  " + code);
    }
}
