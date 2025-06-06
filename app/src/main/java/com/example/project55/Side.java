package com.example.project55;

import java.util.Objects;

/**
 * Represents a side item in the menu.
 */
public class Side extends MenuItem {
    private Size size;
    private SideType sideType;

    public Side(Size size, SideType sideType, int quantity) {
        this.size = size;
        this.sideType = sideType;
        this.quantity = quantity;
    }

    @Override
    public double price() {
        double sidePrice;
        switch (size) {
            case SMALL:
                sidePrice = sideType.getPrice();
                break;
            case MEDIUM:
                sidePrice = sideType.getPrice() + 0.50;
                break;
            case LARGE:
                sidePrice = sideType.getPrice() + 1.00;
                break;
            default:
                throw new IllegalArgumentException();
        }
        return sidePrice * quantity;
    }

    @Override
    public String toString() {
        return String.format("%dx %s %s - $%.2f",
                quantity,
                capitalize(size.name()),
                sideType.toDisplayString(),
                price());
    }

    private String capitalize(String str) {
        return str.charAt(0) + str.substring(1).toLowerCase();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Side)) return false;
        Side other = (Side) obj;
        return quantity == other.quantity &&
                sideType == other.sideType &&
                size == other.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sideType, size, quantity);
    }

    public SideType getSideType() { return sideType; }
    public Size getSize() { return size; }
}
