package main.java.book_service;

public interface BookServiceInterface {
    void AddBuyOrder(int id, float price, int quantity);
    void AddSellOrder(int id, float price, int quantity);
    void CancelOrder(int id);
    void ListBuyOrder();
    void ListSellOrder();
}
