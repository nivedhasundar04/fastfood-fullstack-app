package com.example.project55;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents a customizable burger.
 */
public class Burger extends Sandwich {

    private boolean doublePatty;

    public Burger(Bread bread, Protein protein, ArrayList<AddOns> addOns, int quantity, boolean doublePatty) {
        super(bread, protein, addOns, quantity);
        this.doublePatty = doublePatty;
    }

    @Override
    public double price() {
        double pattyPrice = 6.99;
        if (doublePatty) {
            pattyPrice += 2.50;
        }

        double addOnTotal = 0.0;
        for (AddOns addOn : addOns) {
            addOnTotal += addOn.getPrice();
        }

        return (pattyPrice + addOnTotal) * quantity;
    }

    public boolean isDoublePatty() {
        return doublePatty;
    }

    public void setDoublePatty(boolean doublePatty) {
        this.doublePatty = doublePatty;
    }

    /**
     * Returns a user-friendly description for Android UI.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(quantity).append("x ");
        sb.append(protein.toDisplayString()).append(" Burger on ").append(bread.toDisplayString());

        if (doublePatty) {
            sb.append(" (Double Patty)");
        }

        if (!addOns.isEmpty()) {
            sb.append(" with ");
            for (int i = 0; i < addOns.size(); i++) {
                sb.append(addOns.get(i).toDisplayString());
                if (i < addOns.size() - 1) sb.append(", ");
            }
        }

        sb.append(String.format(" - $%.2f", price()));
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Burger)) return false;
        Burger other = (Burger) obj;
        return doublePatty == other.doublePatty &&
                quantity == other.quantity &&
                bread == other.bread &&
                protein == other.protein &&
                addOns.equals(other.addOns);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bread, protein, addOns, quantity, doublePatty);
    }
}
