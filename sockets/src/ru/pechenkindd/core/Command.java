package ru.pechenkindd.core;

public interface Command {
    String execute(OrderBook orderBook);
}
