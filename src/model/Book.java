package model;

public class Book {

    private String name;
    private double price;
    private String category;
    private int quantity;

    public Book(String name, double price, String category, int quantity) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
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

    public int getQuantity(){
        return quantity;
    }

    @Override
    public String toString() {
        return name + "," + price + "," + category;
    }
}