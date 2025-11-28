package main.java.common;

import main.java.book_service.BookServiceInterface;

public class CancelOrderCommand implements OrderCommand {

    private int id;

    public static enum Type {
        BUY,
        SELL
    }

    public CancelOrderCommand(BookServiceInterface serviceReceiver, int id) {
        this.id = id;
    }

    @Override
    public void Execute() {
        System.out.println("CANCEL CMD");
        // throw new UnsupportedOperationException("Unimplemented method 'Execute'");
    }

}
