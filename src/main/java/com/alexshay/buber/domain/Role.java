package com.alexshay.buber.domain;

public enum Role {
    ADMIN("1"),
    USER("2");

    private final String value;


    Role(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Role fromValue(String v) {
        for (Role c: Role.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
