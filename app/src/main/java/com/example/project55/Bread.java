package com.example.project55;

/**
 * Enum representing bread types for sandwiches.
 */
public enum Bread {
    BRIOCHE,
    WHEAT,
    PRETZEL,
    BAGEL,
    SOURDOUGH;

    /**
     * Converts enum name to a user-friendly display string.
     * @return Formatted bread name.
     */
    public String toDisplayString() {
        return this.name().charAt(0) + this.name().substring(1).toLowerCase();
    }
}
