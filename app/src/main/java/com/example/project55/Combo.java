package com.example.project55;

import java.util.Objects;

/**
 * Represents a combo meal with a sandwich, side, and beverage.
 */
public class Combo extends MenuItem {

    private Sandwich sandwich;
    private Beverage drink;
    private Side side;

    public Combo(Sandwich sandwich, Beverage drink, Side side, int quantity) {
        this.sandwich = sandwich;
        this.drink = drink;
        this.side = side;
        this.quantity = quantity;
    }

    @Override
    public double price() {
        return (sandwich.price() + 2.00) * quantity;
    }

    /**
     * Clean display for Android UI (ListView / RecyclerView).
     */
    @Override
    public String toString() {
        return String.format("%dx Combo [%s | %s | %s] - $%.2f",
                quantity,
                sandwichSummary(),
                sideSummary(),
                drinkSummary(),
                price());
    }

    private String sandwichSummary() {
        return sandwich.getQuantity() + "x " + sandwich.getProtein().toDisplayString() + " Sandwich";
    }

    private String sideSummary() {
        return side.getQuantity() + "x " + side.getSize().toString() + " " + side.getSideType().toString();
    }

    private String drinkSummary() {
        return drink.getQuantity() + "x " + drink.getSize() + " " + drink.getFlavor().toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Combo)) return false;
        Combo other = (Combo) obj;
        return quantity == other.quantity &&
                sandwich.equals(other.sandwich) &&
                drink.equals(other.drink) &&
                side.equals(other.side);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sandwich, drink, side, quantity);
    }
}
