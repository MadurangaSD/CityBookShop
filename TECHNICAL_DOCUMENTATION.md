# CITY BOOKSHOP SYSTEM - TECHNICAL DOCUMENTATION
## Object-Oriented Programming Assignment - Task 1

---

## TABLE OF CONTENTS
1. System Overview
2. OOP Concepts Applied
3. Class Structure
4. Source Code with Explanations
5. Features Implementation

---

## 1. SYSTEM OVERVIEW

**Application Name:** City Bookshop Management System  
**Programming Language:** Java  
**GUI Framework:** Java Swing  
**Data Storage:** File-based (Text Files)

### Purpose
The City Bookshop System is designed to automate the transaction and management process of a bookshop. It provides different functionalities based on user roles (Cashier and Manager).

### Key Features
- User authentication with role-based access
- Book inventory management
- Search functionality (by ID, name, category, price)
- User account creation
- Stock management
- File-based data persistence

---

## 2. OOP CONCEPTS APPLIED

### 2.1 CLASSES AND OBJECTS

**Concept:** A class is a blueprint for creating objects. An object is an instance of a class.

**Implementation in Project:**

```java
// Class Definition
public class Book {
    private String bookId;
    private String name;
    private double price;
    // ... other fields
}

// Object Creation
Book book1 = new Book("B001", "Java Programming", "Programming", 2500.00, 15);
Book book2 = new Book("B002", "Python Basics", "Programming", 2000.00, 20);
```

**Classes in the System:**
- `Book` - Represents a book entity
- `User` - Represents a system user (abstract)
- `Cashier` - Represents a cashier user
- `Manager` - Represents a manager user
- `FileHandler` - Handles file operations
- `LoginFrame`, `CashierFrame`, `ManagerFrame` - GUI classes

---

### 2.2 ENCAPSULATION

**Concept:** Bundling data (fields) and methods that operate on the data within a single unit (class), and restricting direct access to some of the object's components.

**Implementation - Book.java:**

```java
public class Book {
    // Private fields - data hiding
    private String bookId;
    private String name;
    private double price;
    private String category;
    private int quantity;

    // Public getters - controlled read access
    public String getBookId() {
        return bookId;
    }

    public String getName() {
        return name;
    }

    // Public setters - controlled write access
    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
```

**Benefits:**
- Data is protected from unauthorized access
- Internal implementation can be changed without affecting other classes
- Validation can be added in setters

---

### 2.3 INHERITANCE

**Concept:** A mechanism where a new class (child/subclass) inherits properties and behaviors from an existing class (parent/superclass).

**Implementation - User Hierarchy:**

```java
// Parent Class (Superclass)
public abstract class User {
    protected String username;
    protected String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public abstract void showMenu();
    public abstract String getRole();
}

// Child Class 1 (Subclass)
public class Cashier extends User {
    public Cashier(String username, String password) {
        super(username, password); // Call parent constructor
    }

    @Override
    public void showMenu() {
        // Cashier-specific menu
    }

    @Override
    public String getRole() {
        return "Cashier";
    }
}

// Child Class 2 (Subclass)
public class Manager extends User {
    public Manager(String username, String password) {
        super(username, password); // Call parent constructor
    }

    @Override
    public void showMenu() {
        // Manager-specific menu
    }

    @Override
    public String getRole() {
        return "Manager";
    }
}
```

**Benefits:**
- Code reusability (username and password inherited)
- Establishes a relationship between classes
- Promotes hierarchical classification

**Inheritance Tree:**
```
        User (Abstract)
           |
    +------+------+
    |             |
 Cashier      Manager
```

---

### 2.4 ABSTRACTION

**Concept:** Hiding complex implementation details and showing only essential features of an object.

**Implementation - Abstract User Class:**

```java
public abstract class User {
    protected String username;
    protected String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Abstract methods - no implementation in parent class
    public abstract void showMenu();
    public abstract String getRole();
}
```

**Why Abstract:**
- User class itself is not meant to be instantiated
- It provides a common template for Cashier and Manager
- Forces child classes to implement specific behaviors
- Provides method signatures without implementation details

**Usage:**
```java
// Cannot create: User user = new User("test", "123"); // ERROR!

// Must create specific types:
User cashier = new Cashier("john", "pass123");
User manager = new Manager("admin", "admin123");
```

---

### 2.5 POLYMORPHISM

**Concept:** The ability of objects to take many forms. Same method name can behave differently in different classes.

**Type 1: Method Overriding**

```java
// In User class (abstract)
public abstract void showMenu();

// In Cashier class (override)
@Override
public void showMenu() {
    System.out.println("\n===== CASHIER MENU =====");
    System.out.println("1. View All Books");
    System.out.println("2. Search Book by ID");
    // ... cashier options
}

// In Manager class (override)
@Override
public void showMenu() {
    System.out.println("\n===== MANAGER MENU =====");
    System.out.println("1. View All Books");
    System.out.println("2. Search Book by ID");
    // ... manager options (more than cashier)
}
```

**Type 2: Runtime Polymorphism**

```java
// Same reference type, different object types
User user1 = new Cashier("cashier", "pass");
User user2 = new Manager("manager", "pass");

// Calls appropriate showMenu() based on actual object type
user1.showMenu(); // Calls Cashier's showMenu()
user2.showMenu(); // Calls Manager's showMenu()
```

**Type 3: instanceof Operator**

```java
User user = authenticateUser(username, password);

if (user instanceof Manager) {
    // Open Manager interface
    new ManagerFrame((Manager) user).setVisible(true);
} else if (user instanceof Cashier) {
    // Open Cashier interface
    new CashierFrame((Cashier) user).setVisible(true);
}
```

**Benefits:**
- Single interface for different implementations
- Code flexibility and extensibility
- Enables dynamic behavior at runtime

---

## 3. CLASS STRUCTURE AND RELATIONSHIPS

### 3.1 Package Structure
```
CityBookShop/
├── src/
│   ├── model/
│   │   ├── Book.java           (Entity class)
│   │   ├── User.java           (Abstract parent class)
│   │   ├── Cashier.java        (Child class)
│   │   └── Manager.java        (Child class)
│   ├── util/
│   │   └── FileHandler.java    (Utility class)
│   ├── gui/
│   │   ├── LoginFrame.java     (GUI class)
│   │   ├── CashierFrame.java   (GUI class)
│   │   └── ManagerFrame.java   (GUI class)
│   ├── Main.java               (Entry point)
│   ├── books.txt               (Data file)
│   └── users.txt               (Data file)
```

### 3.2 Class Relationships

**1. Association:**
- FileHandler uses Book class
- GUI classes use model classes

**2. Inheritance:**
- Cashier extends User
- Manager extends User

**3. Composition:**
- Frame classes contain panel components
- Objects contain primitive data types

### 3.3 UML Class Diagram (Simplified)

```
┌─────────────────────┐
│      Book           │
├─────────────────────┤
│ - bookId: String    │
│ - name: String      │
│ - price: double     │
│ - category: String  │
│ - quantity: int     │
├─────────────────────┤
│ + getters/setters   │
│ + toString()        │
│ + getDetails()      │
└─────────────────────┘

┌─────────────────────┐
│   «abstract»        │
│      User           │
├─────────────────────┤
│ # username: String  │
│ # password: String  │
├─────────────────────┤
│ + getUsername()     │
│ + getPassword()     │
│ + showMenu()*       │
│ + getRole()*        │
└─────────────────────┘
         △
         │
    ┌────┴────┐
    │         │
┌───┴───┐ ┌──┴────┐
│Cashier│ │Manager│
└───────┘ └───────┘

┌─────────────────────┐
│   FileHandler       │
├─────────────────────┤
│ + saveBook()        │
│ + getAllBooks()     │
│ + searchBooks()     │
│ + saveUser()        │
│ + authenticateUser()│
└─────────────────────┘
```

---

## 4. DETAILED FEATURE IMPLEMENTATION

### 4.1 User Authentication System

**FileHandler.java - authenticateUser() method:**

```java
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
```

**How It Works:**
1. Reads users.txt file line by line
2. Splits each line by comma (format: username,password,role)
3. Compares input credentials with stored credentials
4. Returns appropriate User object (Manager or Cashier) based on role
5. Demonstrates polymorphism - returns User reference to Manager/Cashier object

---

### 4.2 Book Search Functionality

**Multiple Search Methods in FileHandler.java:**

```java
// Search by Name
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

// Search by Category
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

// Search by Price Range
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
```

**OOP Principles Applied:**
- Encapsulation: Uses getters to access private Book fields
- Abstraction: Hides complex search logic from GUI classes
- Code reusability: Same methods used by both Cashier and Manager

---

### 4.3 Manager-Specific Functionalities

**Add New Book:**

```java
public boolean addNewBook(String bookId, String name, String category, 
                         double price, int quantity) {
    // Check if book ID already exists
    if (FileHandler.searchBookById(bookId) != null) {
        return false; // Book ID already exists
    }
    
    Book newBook = new Book(bookId, name, category, price, quantity);
    FileHandler.saveBook(newBook);
    return true;
}
```

**Create New Account:**

```java
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
```

**Why Manager Has More Features:**
- Demonstrates inheritance - Manager has all Cashier features
- Plus additional methods for management tasks
- Shows hierarchical access control

---

### 4.4 File Handling System

**Save Book to File:**

```java
public static void saveBook(Book book) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(BOOKS_FILE, true))) {
        writer.write(book.toString());
        writer.newLine();
    } catch (IOException e) {
        System.out.println("Error saving book: " + e.getMessage());
    }
}
```

**Load Books from File:**

```java
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
```

**File Format:**
- books.txt: `bookId,name,category,price,quantity`
- users.txt: `username,password,role`

---

## 5. GUI IMPLEMENTATION

### 5.1 Login Frame

**Features:**
- Username and password fields
- Login button
- Input validation
- Role-based redirection

**Key Code:**

```java
private void handleLogin() {
    String username = usernameField.getText().trim();
    String password = new String(passwordField.getPassword());
    
    User user = FileHandler.authenticateUser(username, password);
    
    if (user != null) {
        this.dispose();
        
        // Polymorphism: checking actual object type
        if (user instanceof Manager) {
            new ManagerFrame((Manager) user).setVisible(true);
        } else if (user instanceof Cashier) {
            new CashierFrame((Cashier) user).setVisible(true);
        }
    } else {
        JOptionPane.showMessageDialog(this, 
            "Invalid username or password!", 
            "Login Failed", 
            JOptionPane.ERROR_MESSAGE);
    }
}
```

---

### 5.2 Cashier Frame

**Features:**
- View all books in a table
- Search functionality with multiple criteria
- Read-only access
- Formatted book display

**Components:**
- JTable for displaying books
- JComboBox for search type selection
- JTextField for search input
- JButtons for actions

---

### 5.3 Manager Frame

**Features:**
- All Cashier features (inheritance concept)
- Add new books dialog
- Update stock dialog
- Create user accounts dialog
- Enhanced permissions

**Additional Dialogs:**
1. Add Book Dialog - Form with validation
2. Update Stock Dialog - Shows current stock
3. Create Account Dialog - User management

---

## 6. SUMMARY OF OOP CONCEPTS

| Concept | Classes/Methods | Explanation |
|---------|----------------|-------------|
| **Class** | Book, User, Cashier, Manager | Blueprint for objects |
| **Object** | book1, user1, etc. | Instance of a class |
| **Encapsulation** | Private fields + getters/setters | Data hiding and controlled access |
| **Inheritance** | Cashier extends User, Manager extends User | Code reusability |
| **Abstraction** | abstract class User | Hiding implementation details |
| **Polymorphism** | showMenu() method overriding | Same method, different behavior |

---

## 7. ADDITIONAL FEATURES

1. **Data Persistence:** All data saved to text files
2. **Input Validation:** Checks for empty fields, invalid formats
3. **Error Handling:** Try-catch blocks for file operations
4. **User Feedback:** Success/error messages via dialogs
5. **GUI Design:** Professional look with colors and layouts
6. **Default Accounts:** Auto-created for testing

---

## 8. SYSTEM REQUIREMENTS

**Software Requirements:**
- Java Development Kit (JDK) 8 or higher
- Java Runtime Environment (JRE)
- Any Java IDE (Eclipse, IntelliJ IDEA, NetBeans) or text editor

**Hardware Requirements:**
- Minimum 2GB RAM
- 100MB free disk space
- Display resolution: 1024x768 or higher

---

## 9. COMPILATION AND EXECUTION

**Using Command Line:**

```bash
# Navigate to src directory
cd e:\projects\CityBookShop\src

# Compile all Java files
javac Main.java model/*.java util/*.java gui/*.java

# Run the application
java Main
```

**Using IDE:**
1. Open project in IDE
2. Right-click on Main.java
3. Select "Run as Java Application"

---

## 10. DEFAULT CREDENTIALS

**Manager Account:**
- Username: `admin`
- Password: `admin123`

**Cashier Account:**
- Username: `cashier`
- Password: `cash123`

---

## CONCLUSION

This City Bookshop System successfully demonstrates all fundamental Object-Oriented Programming concepts:

1. ✅ **Classes and Objects** - Multiple classes with object creation
2. ✅ **Encapsulation** - Private fields with controlled access
3. ✅ **Inheritance** - User hierarchy with Cashier and Manager
4. ✅ **Abstraction** - Abstract User class with abstract methods
5. ✅ **Polymorphism** - Method overriding and runtime polymorphism

The system provides a complete solution for bookshop management with role-based access control, data persistence, and a user-friendly graphical interface.

---

**End of Technical Documentation**
