package ru.pechenkindd.core;

public interface OrderBookObserver {
    void onOrderBookUpdate(String snapshot);
    void onTrade(String tradeMessage);
}