package com.example.project55;

/**
 * Enum representing available sizes for menu items.
 * Includes helper for user-friendly Android display.
 * @author Yakelin Melendez-Gonzalez, Nivedha Sundar
 */
public enum Size {
    SMALL,
    MEDIUM,
    LARGE;

    /**
     * Converts enum to a user-friendly string for Android UI components.
     * @return Formatted size string (e.g., "Small", "Medium", "Large").
     */
    public String toDisplayString() {
        return this.name().charAt(0) + this.name().substring(1).toLowerCase();
    }
}
