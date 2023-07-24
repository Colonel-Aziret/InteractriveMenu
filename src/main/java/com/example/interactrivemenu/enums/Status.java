package com.example.interactrivemenu.enums;

public enum Status {
    NEW("Новый"),
    IN_PROGRESS("В процессе"),
    COMPLETED("Завершен"),
    CANCELLED("Отменен");

    private final String displayName;

    Status(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
