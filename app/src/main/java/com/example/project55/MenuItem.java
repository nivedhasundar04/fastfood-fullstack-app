package com.example.project55;

/**
 * Abstract base class for all menu items (e.g., sandwiches, beverages, sides).
 * Provides quantity handling and requires price calculation.
 */
public abstract class MenuItem {
    protected int quantity;

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    /**
     * Calculates the price of the menu item.
     * @return Total price.
     */
    public abstract double price();

    /**
     * Provides a user-friendly string for UI display.
     * Subclasses should override toString().
     */
    @Override
    public abstract String toString();
}
