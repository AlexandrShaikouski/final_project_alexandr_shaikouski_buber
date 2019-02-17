package com.alexshay.buber.domain;

public enum DriverStatus {
    OFF_LINE("off-line"),
    ONLINE("online"),
    IN_PROGRESS("in-progress");
    private final String value;


    DriverStatus(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DriverStatus fromValue(String v) {
        for (DriverStatus c: DriverStatus.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
