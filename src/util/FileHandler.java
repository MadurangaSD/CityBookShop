package util;

import model.Book;
import model.User;
import model.Cashier;
import model.Manager;
import java.io.*;
import java.util.ArrayList;

/**
 * FileHandler class - Handles all file operations for the bookshop
 */
public class FileHandler {
    
    private static final String BOOKS_FILE = "books.txt";
    private static final String USERS_FILE = "users.txt";

    // =============== BOOK FILE OPERATIONS ===============
    
    /**
     * Save a single book to file
     */
    public static void saveBook(Book book) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(BOOKS_FILE, true))) {
            writer.write(book.toString());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving book: " + e.getMessage());
        }
    }

    /**
     * Get all books from file
     */
    public static ArrayList<Book> getAllBooks() {
        ArrayList<Book> books = new ArrayList<>();
        File file = new File(BOOKS_FILE);
        
        if (!file.exists()) {
            return books;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(BOOKS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String id = parts[0];
                    String name = parts[1];
                    String category = parts[2];
                    double price = Double.parseDouble(parts[3]);
                    int quantity = Integer.parseInt(parts[4]);
                    books.add(new Book(id, name, category, price, quantity));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading books: " + e.getMessage());
        }
        return books;
    }

    /**
     * Search books by name
     */
    public static ArrayList<Book> searchBooksByName(String searchName) {
        ArrayList<Book> results = new ArrayList<>();
        ArrayList<Book> allBooks = getAllBooks();
        
        for (Book book : allBooks) {
            if (book.getName().toLowerCase().contains(searchName.toLowerCase())) {
                results.add(book);
            }
        }
        return results;
    }

    /**
     * Search books by category
     */
    public static ArrayList<Book> searchBooksByCategory(String category) {
        ArrayList<Book> results = new ArrayList<>();
        ArrayList<Book> allBooks = getAllBooks();
        
        for (Book book : allBooks) {
            if (book.getCategory().equalsIgnoreCase(category)) {
                results.add(book);
            }
        }
        return results;
    }

    /**
     * Search books by price range
     */
    public static ArrayList<Book> searchBooksByPrice(double minPrice, double maxPrice) {
        ArrayList<Book> results = new ArrayList<>();
        ArrayList<Book> allBooks = getAllBooks();
        
        for (Book book : allBooks) {
            if (book.getPrice() >= minPrice && book.getPrice() <= maxPrice) {
                results.add(book);
            }
        }
        return results;
    }

    /**
     * Search book by ID
     */
    public static Book searchBookById(String bookId) {
        ArrayList<Book> allBooks = getAllBooks();
        
        for (Book book : allBooks) {
            if (book.getBookId().equalsIgnoreCase(bookId)) {
                return book;
            }
        }
        return null;
    }

    /**
     * Update all books in file (used after modifying book data)
     */
    public static void updateAllBooks(ArrayList<Book> books) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(BOOKS_FILE))) {
            for (Book book : books) {
                writer.write(book.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error updating books: " + e.getMessage());
        }
    }

    // =============== USER FILE OPERATIONS ===============
    
    /**
     * Save a user account to file
     */
    public static void saveUser(User user, String role) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_FILE, true))) {
            writer.write(user.getUsername() + "," + user.getPassword() + "," + role);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving user: " + e.getMessage());
        }
    }

    /**
     * Authenticate user login
     */
    public static User authenticateUser(String username, String password) {
        File file = new File(USERS_FILE);
        
        if (!file.exists()) {
            return null;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String storedUsername = parts[0];
                    String storedPassword = parts[1];
                    String role = parts[2];
                    
                    if (storedUsername.equals(username) && storedPassword.equals(password)) {
                        if (role.equalsIgnoreCase("Manager")) {
                            return new Manager(username, password);
                        } else if (role.equalsIgnoreCase("Cashier")) {
                            return new Cashier(username, password);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error authenticating user: " + e.getMessage());
        }
        return null;
    }

    /**
     * Check if username exists
     */
    public static boolean usernameExists(String username) {
        File file = new File(USERS_FILE);
        
        if (!file.exists()) {
            return false;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 1 && parts[0].equals(username)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error checking username: " + e.getMessage());
        }
        return false;
    }
}