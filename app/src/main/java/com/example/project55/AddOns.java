package com.example.project55;

/**
 * Enum for available add-ons with associated prices.
 */
public enum AddOns {
    LETTUCE(0.30),
    TOMATOES(0.30),
    ONIONS(0.30),
    AVOCADO(0.50),
    CHEESE(1.00);

    private final double price;

    AddOns(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    /**
     * Returns a user-friendly name for UI display.
     * @return Formatted add-on name.
     */
    public String toDisplayString() {
        return this.name().substring(0,1) + this.name().substring(1).toLowerCase().replace("_", " ");
    }
}
