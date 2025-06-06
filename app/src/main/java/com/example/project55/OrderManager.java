package com.example.project55;

import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    private static OrderManager instance;
    private Order currentOrder;
    private int orderNumberCounter = 1;

    private final List<Order> pastOrders = new ArrayList<>();

    private OrderManager() {
        currentOrder = new Order(orderNumberCounter, new ArrayList<>());
    }

    public static OrderManager getInstance() {
        if (instance == null) {
            instance = new OrderManager();
        }
        return instance;
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public void finalizeOrder() {
        pastOrders.add(currentOrder);
        orderNumberCounter++;
        currentOrder = new Order(orderNumberCounter, new ArrayList<>());
    }

    public List<Order> getPastOrders() {
        return pastOrders;
    }
}
