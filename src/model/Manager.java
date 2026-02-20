package model;

import util.FileHandler;
import java.util.ArrayList;

/**
 * Manager class - Demonstrates INHERITANCE and POLYMORPHISM
 * Extends User class and inherits all Cashier functionalities plus additional features
 */
public class Manager extends User {

    public Manager(String username, String password) {
        super(username, password);
    }

    // Polymorphism - Overriding abstract method
    @Override
    public void showMenu() {
        System.out.println("\n===== MANAGER MENU =====");
        System.out.println("1. View All Books");
        System.out.println("2. Search Book by ID");
        System.out.println("3. Search Books by Name");
        System.out.println("4. Search Books by Category");
        System.out.println("5. Search Books by Price Range");
        System.out.println("6. Add New Book");
        System.out.println("7. Update Book Stock");
        System.out.println("8. Create New User Account");
        System.out.println("9. Logout");
    }

    @Override
    public String getRole() {
        return "Manager";
    }

    // Manager inherits all Cashier functionalities
    
    /**
     * View all books (same as Cashier)
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
     * Display books in formatted way
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

    // Manager-specific additional functionalities
    
    /**
     * Add a new book (Manager only)
     */
    public boolean addNewBook(String bookId, String name, String category, double price, int quantity) {
        // Check if book ID already exists
        if (FileHandler.searchBookById(bookId) != null) {
            return false; // Book ID already exists
        }
        
        Book newBook = new Book(bookId, name, category, price, quantity);
        FileHandler.saveBook(newBook);
        return true;
    }

    /**
     * Update book stock (Manager only)
     */
    public boolean updateBookStock(String bookId, int newQuantity) {
        ArrayList<Book> allBooks = FileHandler.getAllBooks();
        boolean found = false;
        
        for (Book book : allBooks) {
            if (book.getBookId().equalsIgnoreCase(bookId)) {
                book.setQuantity(newQuantity);
                found = true;
                break;
            }
        }
        
        if (found) {
            FileHandler.updateAllBooks(allBooks);
            return true;
        }
        return false;
    }

    /**
     * Create new user account (Manager only)
     */
    public boolean createNewAccount(String username, String password, String role) {
        // Check if username already exists
        if (FileHandler.usernameExists(username)) {
            return false;
        }
        
        User newUser;
        if (role.equalsIgnoreCase("Manager")) {
            newUser = new Manager(username, password);
        } else if (role.equalsIgnoreCase("Cashier")) {
            newUser = new Cashier(username, password);
        } else {
            return false; // Invalid role
        }
        
        FileHandler.saveUser(newUser, role);
        return true;
    }
}