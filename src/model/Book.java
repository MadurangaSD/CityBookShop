package model;

/**
 * Book class - Demonstrates ENCAPSULATION
 * All fields are private and accessed through public getters/setters
 */
public class Book {

    private String bookId;
    private String name;
    private double price;
    private String category;
    private int quantity;

    // Constructor
    public Book(String bookId, String name, String category, double price, int quantity) {
        this.bookId = bookId;
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }

    // Encapsulation - Getters
    public String getBookId() {
        return bookId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }

    // Encapsulation - Setters
    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Method to update stock
    public void updateStock(int amount) {
        this.quantity += amount;
    }

    @Override
    public String toString() {
        return bookId + "," + name + "," + category + "," + price + "," + quantity;
    }

    // Method to display book details
    public String getDetails() {
        return String.format("ID: %s | Name: %s | Category: %s | Price: Rs.%.2f | Stock: %d",
                bookId, name, category, price, quantity);
    }
}