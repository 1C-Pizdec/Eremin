package ru.pechenkindd.core;

public class Order {
    private final String id;
    private final OrderSide side;
    private final double price;
    private final int quantity;
    
    public Order(String id, OrderSide side, double price, int quantity) {
        this.id = id;
        this.side = side;
        this.price = price;
        this.quantity = quantity;
    }
    
    // Getters
    public String getId() { return id; }
    public OrderSide getSide() { return side; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    
    @Override
    public String toString() {
        return String.format("Order{id=%s, side=%s, price=%.2f, quantity=%d}", 
            id, side, price, quantity);
    }
}