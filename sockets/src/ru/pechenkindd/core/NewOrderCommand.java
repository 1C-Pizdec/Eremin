package ru.pechenkindd.core;

public class NewOrderCommand implements Command {
    private final String orderId;
    private final OrderSide side;
    private final double price;
    private final int quantity;
    
    public NewOrderCommand(String orderId, OrderSide side, double price, int quantity) {
        this.orderId = orderId;
        this.side = side;
        this.price = price;
        this.quantity = quantity;
    }
    
    @Override
    public String execute(OrderBook orderBook) {
        Order order = new Order(orderId, side, price, quantity);
        orderBook.addOrder(order);
        return "ORDER_ADDED," + orderId;
    }
}