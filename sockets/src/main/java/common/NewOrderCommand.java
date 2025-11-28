package main.java.common;

import main.java.book_service.BookServiceInterface;

public class NewOrderCommand implements OrderCommand {

    private Type type;
    private int id;
    private float price;
    private int quantity;

    public static enum Type {
        BUY,
        SELL
    }

    public NewOrderCommand(BookServiceInterface serviceReceiver, Type type, int id, float price, int quantity) {
        this.type = type;
        this.id = id;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public void Execute() {
        System.out.println("NEW CMD");
        // throw new UnsupportedOperationException("Unimplemented method 'Execute'");
    }
    
}
