package com.example.project55;

/**
 * Enum representing different types of sides available in the menu.
 * Each side type has a predefined price.
 * @author Yakelin Melendez-Gonzalez, Nivedha Sundar
 */
public enum SideType {
    FRIES(2.49),
    CHIPS(1.99),
    ONION_RINGS(3.29),
    APPLE_SLICES(1.29);

    private final double price;

    SideType(double price) { this.price = price; }

    public double getPrice() { return price; }

    public String toDisplayString() {
        return this.name().replace("_", " ").toLowerCase();
    }
}
