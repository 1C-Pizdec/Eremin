package ru.pechenkindd.core;

public class CancelOrderCommand implements Command {
    private final String orderId;
    
    public CancelOrderCommand(String orderId) {
        this.orderId = orderId;
    }
    
    @Override
    public String execute(OrderBook orderBook) {
        orderBook.cancelOrder(orderId);
        return "ORDER_CANCELLED," + orderId;
    }
}