package com.example.interactrivemenu.enums;

public enum Role {
    ADMIN("Админ");

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    Role(String name) {
        this.name = name;
    }
}
