package ru.pechenkindd.core;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class OrderBook {
    private static OrderBook instance;
    
    // TreeMap автоматически сортирует ключи. Для BUY - по убыванию, для SELL - по возрастанию
    private final Map<Double, Integer> bids = new TreeMap<>(Comparator.reverseOrder());
    private final Map<Double, Integer> asks = new TreeMap<>();
    private final Map<String, Order> allOrders = new ConcurrentHashMap<>();
    
    // Список наблюдателей для рассылки обновлений
    private final List<OrderBookObserver> observers = new ArrayList<>();
    
    private OrderBook() {}
    
    public static synchronized OrderBook getInstance() {
        if (instance == null) {
            instance = new OrderBook();
        }
        return instance;
    }

    public synchronized void addOrder(Order order) {
        allOrders.put(order.getId(), order);
        Map<Double, Integer> targetBook = order.getSide() == OrderSide.BUY ? bids : asks;
        
        // Упрощенная логика - просто добавляем объем по цене
        targetBook.merge(order.getPrice(), order.getQuantity(), Integer::sum);
        
        // Пытаемся исполнить заявку (упрощенная версия)
        tryMatchOrders();
        
        // Уведомляем наблюдателей об изменении
        notifyObservers();
    }

    public synchronized void cancelOrder(String orderId) {
        Order order = allOrders.remove(orderId);
        if (order != null) {
            Map<Double, Integer> targetBook = order.getSide() == OrderSide.BUY ? bids : asks;
            Integer currentQty = targetBook.get(order.getPrice());
            if (currentQty != null) {
                int newQty = currentQty - order.getQuantity();
                if (newQty <= 0) {
                    targetBook.remove(order.getPrice());
                } else {
                    targetBook.put(order.getPrice(), newQty);
                }
            }
            notifyObservers();
        }
    }
    
    private void tryMatchOrders() {
        // Упрощенный мэтчинг - проверяем, пересекаются ли лучшие цены
        if (!bids.isEmpty() && !asks.isEmpty()) {
            double bestBid = bids.keySet().iterator().next();
            double bestAsk = asks.keySet().iterator().next();
            
            if (bestBid >= bestAsk) {
                // Находим заявки для исполнения (упрощенно)
                executeTrade(bestAsk, Math.min(bids.get(bestBid), asks.get(bestAsk)));
            }
        }
    }
    
    private void executeTrade(double price, int quantity) {
        // Упрощенная логика исполнения - просто уменьшаем объемы
        updateBookQuantity(bids, price, -quantity);
        updateBookQuantity(asks, price, -quantity);
        
        // Рассылаем информацию о сделке
        notifyTrade(price, quantity);
    }
    
    private void updateBookQuantity(Map<Double, Integer> book, double price, int delta) {
        book.merge(price, delta, (old, change) -> {
            int newValue = old + change;
            return newValue <= 0 ? null : newValue;
        });
    }
    
    // Методы для наблюдателей
    public void addObserver(OrderBookObserver observer) {
        System.out.println("add observer");
        observers.add(observer);
    }
    
    public void removeObserver(OrderBookObserver observer) {
        observers.remove(observer);
    }
    
    private void notifyObservers() {
        String snapshot = getSnapshot();
        for (OrderBookObserver observer : observers) {
            observer.onOrderBookUpdate(snapshot);
        }
    }

    private void notifyTrade(double price, int quantity) {
        String tradeMessage = String.format("TRADE,%.2f,%d", price, quantity);
        for (OrderBookObserver observer : observers) {
            observer.onTrade(tradeMessage);
        }
    }
    
    public String getSnapshot() {
        StringBuilder sb = new StringBuilder("BOOK,");
        
        // Добавляем лучшие 3 уровня покупок
        bids.entrySet().stream().limit(3).forEach(entry -> 
            sb.append(String.format("%.2f:%d,", entry.getKey(), entry.getValue())));
        
        // Добавляем лучшие 3 уровня продаж  
        asks.entrySet().stream().limit(3).forEach(entry -> 
            sb.append(String.format("%.2f:%d,", entry.getKey(), entry.getValue())));
        
        // Убираем последнюю запятую
        if (sb.charAt(sb.length()-1) == ',') {
            sb.setLength(sb.length()-1);
        }
        
        return sb.toString();
    }
}
