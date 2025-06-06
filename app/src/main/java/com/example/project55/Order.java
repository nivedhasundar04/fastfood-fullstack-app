package com.example.project55;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a single order containing a list of menu items.
 * Provides methods to manage items and calculate totals including tax.
 *
 * Tax rate is fixed at 6.625%.
 *
 * @author You
 */
public class Order {
    private final int number;                // Unique order number
    private List<MenuItem> items;            // List of menu items in the order
    private static final double TAX_RATE = 0.06625;

    /**
     * Constructs an Order with a given order number and initial items list.
     * @param number The unique number identifying this order.
     * @param items The list of menu items in this order.
     */
    public Order(int number, List<MenuItem> items) {
        this.number = number;
        this.items = new ArrayList<>(items);  // Defensive copy to prevent external modification
    }

    /**
     * Gets the unique order number.
     * @return The order number.
     */
    public int getNumber() {
        return number;
    }

    /**
     * Retrieves the list of items in the order.
     * @return List of MenuItem objects.
     */
    public List<MenuItem> getItems() {
        return items;
    }

    /**
     * Replaces the current list of items.
     * @param items New list of menu items.
     */
    public void setItems(List<MenuItem> items) {
        this.items = new ArrayList<>(items);
    }

    /**
     * Adds a single menu item to the order.
     * @param item The MenuItem to add.
     */
    public void addItem(MenuItem item) {
        items.add(item);
    }

    /**
     * Removes a specific menu item from the order.
     * @param item The MenuItem to remove.
     */
    public void removeItem(MenuItem item) {
        items.remove(item);
    }

    /**
     * Clears all items from the order.
     */
    public void clearItems() {
        items.clear();
    }

    /**
     * Calculates and returns the subtotal (before tax).
     * @return Subtotal amount.
     */
    public double getSubtotal() {
        double subTotal = 0.0;
        for (MenuItem item : items) {
            subTotal += item.price();
        }
        return subTotal;
    }

    /**
     * Calculates the tax amount for the current order.
     * @return Tax amount.
     */
    public double getTax() {
        return getSubtotal() * TAX_RATE;
    }

    /**
     * Calculates the total amount including tax.
     * @return Total price.
     */
    public double getTotal() {
        return getSubtotal() + getTax();
    }

    /**
     * Returns a string summary of the order.
     * @return Formatted order summary.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order #").append(number).append("\n");
        for (MenuItem item : items) {
            sb.append(item.toString()).append("\n");
        }
        sb.append(String.format("Subtotal: $%.2f\n", getSubtotal()));
        sb.append(String.format("Tax: $%.2f\n", getTax()));
        sb.append(String.format("Total: $%.2f\n", getTotal()));
        return sb.toString();
    }
}
