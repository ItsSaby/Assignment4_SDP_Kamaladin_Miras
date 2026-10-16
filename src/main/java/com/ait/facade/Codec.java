package com.ait.facade;

public class Codec {
    private final String name;
    private final String format;

    public Codec(String name, String format) {
        this.name = name;
        this.format = format;
    }

    public String getName() {
        return name;
    }

    public String getFormat() {
        return format;
    }
}
