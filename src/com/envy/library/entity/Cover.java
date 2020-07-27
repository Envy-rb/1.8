package com.envy.library.entity;

public enum Cover {
    HARD("Твердая"),
    SOFT("Мягкая");

    private final String name;

    Cover(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
