package model;

import util.FileHandler;
import java.util.ArrayList;

/**
 * Cashier class - Demonstrates INHERITANCE and POLYMORPHISM
 * Extends User class and provides specific implementation
 */
public class Cashier extends User {

    public Cashier(String username, String password) {
        super(username, password); // Calling parent constructor
    }

    // Polymorphism - Overriding abstract method from User class
    @Override
    public void showMenu() {
        System.out.println("\n===== CASHIER MENU =====");
        System.out.println("1. View All Books");
        System.out.println("2. Search Book by ID");
        System.out.println("3. Search Books by Name");
        System.out.println("4. Search Books by Category");
        System.out.println("5. Search Books by Price Range");
        System.out.println("6. Logout");
    }

    @Override
    public String getRole() {
        return "Cashier";
    }

    // Cashier-specific methods
    
    /**
     * View all books
     */
    public ArrayList<Book> viewAllBooks() {
        return FileHandler.getAllBooks();
    }

    /**
     * Search for stock details by book ID
     */
    public Book searchBookById(String bookId) {
        return FileHandler.searchBookById(bookId);
    }

    /**
     * Search books by name
     */
    public ArrayList<Book> searchBooksByName(String name) {
        return FileHandler.searchBooksByName(name);
    }

    /**
     * Search books by category
     */
    public ArrayList<Book> searchBooksByCategory(String category) {
        return FileHandler.searchBooksByCategory(category);
    }

    /**
     * Search books by price range
     */
    public ArrayList<Book> searchBooksByPrice(double minPrice, double maxPrice) {
        return FileHandler.searchBooksByPrice(minPrice, maxPrice);
    }

    /**
     * Display book details in formatted way
     */
    public void displayBooks(ArrayList<Book> books) {
        if (books.isEmpty()) {
            System.out.println("No books found!");
            return;
        }
        
        System.out.println("\n" + "=".repeat(80));
        System.out.printf("%-10s %-25s %-15s %-12s %-10s%n", 
                          "ID", "Name", "Category", "Price", "Stock");
        System.out.println("=".repeat(80));
        
        for (Book book : books) {
            System.out.printf("%-10s %-25s %-15s Rs.%-9.2f %-10d%n",
                            book.getBookId(),
                            book.getName(),
                            book.getCategory(),
                            book.getPrice(),
                            book.getQuantity());
        }
        System.out.println("=".repeat(80));
    }
}