package ru.pechenkindd.core;

public class CommandFactory {
    public static Command createCommand(String message) {
        String[] parts = message.split(",");

        if (parts.length < 2) {
            throw new IllegalArgumentException("Invalid message format: " + message);
        }

        String commandType = parts[0];
        
        switch (commandType) {
            case "NEW":
                if (parts.length != 5) {
                    throw new IllegalArgumentException("NEW command requires 5 parameters");
                }
                
                String orderId = parts[1];
                OrderSide side = OrderSide.valueOf(parts[2]);
                double price = Double.parseDouble(parts[3]);
                int quantity = Integer.parseInt(parts[4]);
                return new NewOrderCommand(orderId, side, price, quantity);

            case "CANCEL":
                if (parts.length != 2) {
                    throw new IllegalArgumentException("CANCEL command requires 2 parameters");
                }
                return new CancelOrderCommand(parts[1]);

            default:
                throw new IllegalArgumentException("Unknown command: " + commandType);
        }
    }
}