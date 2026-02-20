import gui.LoginFrame;
import util.FileHandler;
import model.*;
import javax.swing.SwingUtilities;

/**
 * Main Application Entry Point
 * 
 * OBJECT-ORIENTED PROGRAMMING CONCEPTS APPLIED:
 * 
 * 1. CLASSES & OBJECTS:
 *    - Book, User, Cashier, Manager, FileHandler classes
 *    - Objects created for books and users
 * 
 * 2. ENCAPSULATION:
 *    - All fields are private with public getters/setters
 *    - Data hiding in Book, User classes
 * 
 * 3. INHERITANCE:
 *    - Cashier and Manager extend User class
 *    - Code reusability through inheritance
 * 
 * 4. ABSTRACTION:
 *    - User is an abstract class with abstract methods
 *    - Interface segregation through abstract methods
 * 
 * 5. POLYMORPHISM:
 *    - Method overriding in Cashier and Manager
 *    - Different implementations of showMenu() and getRole()
 * 
 * 6. FILE HANDLING:
 *    - Data persistence using text files
 *    - FileHandler utility for all I/O operations
 */
public class Main {

    public static void main(String[] args) {
        
        // Initialize default users if users.txt doesn't exist
        initializeDefaultUsers();
        
        // Initialize sample books if books.txt is empty
        initializeSampleBooks();
        
        // Launch the GUI application
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                LoginFrame loginFrame = new LoginFrame();
                loginFrame.setVisible(true);
            }
        });
    }
    
    /**
     * Initialize default users for testing
     */
    private static void initializeDefaultUsers() {
        // Create default manager account
        if (!FileHandler.usernameExists("admin")) {
            Manager defaultManager = new Manager("admin", "admin123");
            FileHandler.saveUser(defaultManager, "Manager");
            System.out.println("Default manager account created - Username: admin, Password: admin123");
        }
        
        // Create default cashier account
        if (!FileHandler.usernameExists("cashier")) {
            Cashier defaultCashier = new Cashier("cashier", "cash123");
            FileHandler.saveUser(defaultCashier, "Cashier");
            System.out.println("Default cashier account created - Username: cashier, Password: cash123");
        }
    }
    
    /**
     * Initialize sample books for testing
     */
    private static void initializeSampleBooks() {
        if (FileHandler.getAllBooks().isEmpty()) {
            // Add sample books
            Book b1 = new Book("B001", "Java Programming", "Programming", 2500.00, 15);
            Book b2 = new Book("B002", "Python Basics", "Programming", 2000.00, 20);
            Book b3 = new Book("B003", "Data Structures", "Computer Science", 3000.00, 10);
            Book b4 = new Book("B004", "Web Development", "Programming", 2800.00, 12);
            Book b5 = new Book("B005", "Database Systems", "Computer Science", 3200.00, 8);
            Book b6 = new Book("B006", "The Great Gatsby", "Fiction", 1500.00, 25);
            Book b7 = new Book("B007", "To Kill a Mockingbird", "Fiction", 1800.00, 18);
            Book b8 = new Book("B008", "Business Management", "Business", 2200.00, 14);
            
            FileHandler.saveBook(b1);
            FileHandler.saveBook(b2);
            FileHandler.saveBook(b3);
            FileHandler.saveBook(b4);
            FileHandler.saveBook(b5);
            FileHandler.saveBook(b6);
            FileHandler.saveBook(b7);
            FileHandler.saveBook(b8);
            
            System.out.println("Sample books added to the database.");
        }
    }
}