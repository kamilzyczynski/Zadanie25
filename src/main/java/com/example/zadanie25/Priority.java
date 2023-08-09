package com.example.zadanie25;

public enum Priority {
    HIGH("Wysoki"),
    MEDIUM("Średni"),
    LOW("Niski");

    private String plTranslation;

    Priority(String plTranslation) {
        this.plTranslation = plTranslation;
    }

    public String getPlTranslation() {
        return plTranslation;
    }

    public void setPlTranslation(String plTranslation) {
        this.plTranslation = plTranslation;
    }
}
