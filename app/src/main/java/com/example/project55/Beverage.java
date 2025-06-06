package com.example.project55;

import java.util.Objects;

/**
 * Represents a beverage item with size, flavor, and quantity.
 * Calculates price based on size and quantity.
 */
public class Beverage extends MenuItem {

    private final Size size;
    private final Flavor flavor;

    /**
     * Constructs a Beverage with specified size, flavor, and quantity.
     * @param size     The size of the beverage.
     * @param flavor   The flavor of the beverage.
     * @param quantity The quantity ordered.
     */
    public Beverage(Size size, Flavor flavor, int quantity) {
        this.size = size;
        this.flavor = flavor;
        this.quantity = quantity;
    }

    /**
     * Calculates the total price based on size and quantity.
     * @return Total price.
     */
    @Override
    public double price() {
        double base;
        switch (size) {
            case SMALL:
                base = 1.99;
                break;
            case MEDIUM:
                base = 2.49;
                break;
            case LARGE:
                base = 2.99;
                break;
            default:
                throw new IllegalArgumentException();
        }
        return base * quantity;
    }

    public Size getSize() {
        return size;
    }

    public Flavor getFlavor() {
        return flavor;
    }

    /**
     * Returns a user-friendly string for display in UI lists.
     */
    @Override
    public String toString() {
        String sizeStr = capitalize(size.name());
        String flavorStr = flavor.name().replace("_", " ").toLowerCase();

        return String.format("%dx %s %s drink - $%.2f", quantity, sizeStr, flavorStr, price());
    }

    /**
     * Helper method to capitalize enum names.
     */
    private String capitalize(String text) {
        return text.charAt(0) + text.substring(1).toLowerCase();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Beverage)) return false;
        Beverage other = (Beverage) obj;
        return quantity == other.quantity && size == other.size && flavor == other.flavor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, flavor, quantity);
    }
}
