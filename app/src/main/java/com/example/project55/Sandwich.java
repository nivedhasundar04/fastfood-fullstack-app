package com.example.project55;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents a customizable sandwich.
 */
public class Sandwich extends MenuItem {
    protected Bread bread;
    protected Protein protein;
    protected ArrayList<AddOns> addOns;

    public Sandwich(Bread bread, Protein protein, ArrayList<AddOns> addOns, int quantity) {
        this.bread = bread;
        this.protein = protein;
        this.quantity = quantity;
        this.addOns = addOns;
    }

    @Override
    public double price() {
        double basePrice;
        switch (protein) {
            case ROAST_BEEF:
                basePrice = 10.99;
                break;
            case SALMON:
                basePrice = 9.99;
                break;
            case CHICKEN:
                basePrice = 8.99;
                break;
            default:
                throw new IllegalArgumentException();
        }

        double addOnTotal = 0.0;
        for (AddOns addOn : addOns) {
            addOnTotal += addOn.getPrice();
        }

        return (basePrice + addOnTotal) * quantity;
    }

    /**
     * User-friendly string for Android ListView or RecyclerView.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(quantity).append("x ");
        sb.append(protein.toDisplayString()).append(" Sandwich on ").append(bread.toDisplayString());

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
        if (!(obj instanceof Sandwich)) return false;
        Sandwich other = (Sandwich) obj;
        return quantity == other.quantity &&
                bread == other.bread &&
                protein == other.protein &&
                addOns.equals(other.addOns);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bread, protein, addOns, quantity);
    }

    // Getters for Android adapters if needed
    public Bread getBread() { return bread; }
    public Protein getProtein() { return protein; }
    public ArrayList<AddOns> getAddOns() { return addOns; }
}
