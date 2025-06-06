package com.example.project55;

/**
 * Enum for protein options.
 */
public enum Protein {
    ROAST_BEEF,
    SALMON,
    CHICKEN;

    /**
     * Converts enum to user-friendly string for Android UI.
     */
    public String toDisplayString() {
        return this.name().replace("_", " ").toLowerCase().replaceFirst(".",
                this.name().substring(0,1).toUpperCase());
    }
}
