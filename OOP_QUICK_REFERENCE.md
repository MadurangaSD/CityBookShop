# OOP CONCEPTS - QUICK REFERENCE GUIDE
## For Presentation and Viva Preparation

---

## 1. CLASSES AND OBJECTS

### Definition
- **Class:** A blueprint or template for creating objects
- **Object:** An instance of a class with actual values

### In City Bookshop:
```java
// CLASS DEFINITION
public class Book {
    private String bookId;
    private String name;
    private double price;
    
    // Constructor
    public Book(String id, String name, double price) {
        this.bookId = id;
        this.name = name;
        this.price = price;
    }
}

// OBJECT CREATION
Book book1 = new Book("B001", "Java Programming", 2500.0);
Book book2 = new Book("B002", "Python Basics", 2000.0);
```

### Real-World Analogy:
- **Class = Blueprint of a House**
  - Book class is the blueprint
- **Object = Actual House Built from Blueprint**
  - book1 and book2 are actual house instances

### Key Points for Viva:
✅ Class is a template, object is an instance  
✅ One class can create multiple objects  
✅ Each object has its own data  
✅ Classes contain fields (data) and methods (behavior)  

---

## 2. ENCAPSULATION

### Definition
Wrapping data (fields) and methods together, and hiding internal details using access modifiers.

### In City Bookshop:
```java
public class Book {
    // PRIVATE fields - cannot be accessed directly from outside
    private String bookId;
    private String name;
    private double price;
    
    // PUBLIC getters - controlled READ access
    public String getBookId() {
        return bookId;
    }
    
    public String getName() {
        return name;
    }
    
    // PUBLIC setters - controlled WRITE access
    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
    
    public void setPrice(double price) {
        if (price > 0) { // VALIDATION
            this.price = price;
        }
    }
}
```

### Why Encapsulation?
❌ **Without Encapsulation:**
```java
Book book = new Book();
book.price = -500; // BAD! Negative price allowed
```

✅ **With Encapsulation:**
```java
Book book = new Book();
book.setPrice(-500); // Setter validates and rejects
```

### Real-World Analogy:
**ATM Machine:**
- You can't directly access the bank's money vault (private)
- You use ATM buttons/interface to interact (public methods)
- ATM validates your transactions (setter validation)

### Key Points for Viva:
✅ Data hiding using `private` keyword  
✅ Controlled access using getters/setters  
✅ Protects data from invalid modifications  
✅ Internal implementation can change without affecting other classes  

---

## 3. INHERITANCE

### Definition
A mechanism where one class (child) acquires properties and methods from another class (parent).

### In City Bookshop:
```java
// PARENT CLASS (Superclass)
public abstract class User {
    protected String username;
    protected String password;
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public String getUsername() {
        return username;
    }
    
    public abstract void showMenu();
}

// CHILD CLASS 1 (Subclass)
public class Cashier extends User {
    public Cashier(String username, String password) {
        super(username, password); // Call parent constructor
    }
    
    @Override
    public void showMenu() {
        System.out.println("Cashier Menu");
    }
}

// CHILD CLASS 2 (Subclass)
public class Manager extends User {
    public Manager(String username, String password) {
        super(username, password); // Call parent constructor
    }
    
    @Override
    public void showMenu() {
        System.out.println("Manager Menu with extra options");
    }
}
```

### Inheritance Tree:
```
           User (Abstract Parent)
            |
    +-------+-------+
    |               |
Cashier          Manager
(Child 1)       (Child 2)
```

### What's Inherited?
✅ Username field  
✅ Password field  
✅ getUsername() method  
✅ Constructor logic  

### Real-World Analogy:
**Family Inheritance:**
- **Parent:** Has properties (eyes, hair color)
- **Children:** Inherit those properties
- **Children can also have their own unique properties**

### Key Points for Viva:
✅ Promotes code reusability  
✅ Establishes "IS-A" relationship (Manager IS-A User)  
✅ Child has all parent features plus its own  
✅ Uses `extends` keyword  
✅ `super()` calls parent constructor  

---

## 4. ABSTRACTION

### Definition
Hiding complex implementation details and showing only essential features.

### In City Bookshop:
```java
// ABSTRACT CLASS - cannot be instantiated
public abstract class User {
    protected String username;
    protected String password;
    
    // ABSTRACT METHOD - no implementation
    public abstract void showMenu();
    public abstract String getRole();
}

// CANNOT DO THIS:
// User user = new User("john", "pass"); // ERROR!

// MUST CREATE CONCRETE CLASS:
User cashier = new Cashier("john", "pass"); // OK!
User manager = new Manager("admin", "pass"); // OK!
```

### Why Abstract?
- **User class is too general** - we don't create just "users"
- **We create specific types** - Cashier or Manager
- **Forces child classes to implement methods** - each role must define showMenu()

### Abstract vs Concrete:
```java
// ABSTRACT - has abstract methods
public abstract class User {
    public abstract void showMenu(); // No body
}

// CONCRETE - implements all methods
public class Cashier extends User {
    public void showMenu() {
        // Implementation here
    }
}
```

### Real-World Analogy:
**Vehicle Concept:**
- "Vehicle" is abstract - you can't drive a generic "vehicle"
- You drive specific vehicles: Car, Bike, Truck
- Each vehicle type has its own way of starting, but all are vehicles

```java
abstract class Vehicle {
    abstract void start();
}

class Car extends Vehicle {
    void start() { System.out.println("Turn key"); }
}

class Bike extends Vehicle {
    void start() { System.out.println("Kick start"); }
}
```

### Key Points for Viva:
✅ Abstract class cannot be instantiated  
✅ Uses `abstract` keyword  
✅ Can have abstract methods (no implementation)  
✅ Can have concrete methods (with implementation)  
✅ Child classes must implement all abstract methods  
✅ Provides a template for child classes  

---

## 5. POLYMORPHISM

### Definition
The ability of objects to take many forms. Same method name behaves differently in different classes.

### Types of Polymorphism:

#### A) Method Overriding (Runtime Polymorphism)
```java
// PARENT CLASS
public abstract class User {
    public abstract void showMenu();
}

// CHILD 1
public class Cashier extends User {
    @Override
    public void showMenu() {
        System.out.println("1. View Books");
        System.out.println("2. Search Books");
    }
}

// CHILD 2
public class Manager extends User {
    @Override
    public void showMenu() {
        System.out.println("1. View Books");
        System.out.println("2. Search Books");
        System.out.println("3. Add Books");      // Extra
        System.out.println("4. Create Accounts"); // Extra
    }
}

// USAGE - Same method, different behavior
User user1 = new Cashier("john", "pass");
User user2 = new Manager("admin", "pass");

user1.showMenu(); // Calls Cashier's showMenu()
user2.showMenu(); // Calls Manager's showMenu()
```

#### B) Runtime Polymorphism Example:
```java
// In Login System
User user = FileHandler.authenticateUser(username, password);

// user variable type: User
// actual object: Could be Cashier or Manager

if (user instanceof Manager) {
    // Open Manager interface
    new ManagerFrame((Manager) user);
} else if (user instanceof Cashier) {
    // Open Cashier interface
    new CashierFrame((Cashier) user);
}
```

### Real-World Analogy:
**Animal Sounds:**
```java
abstract class Animal {
    abstract void makeSound();
}

class Dog extends Animal {
    void makeSound() { System.out.println("Woof!"); }
}

class Cat extends Animal {
    void makeSound() { System.out.println("Meow!"); }
}

// Polymorphism in action
Animal animal1 = new Dog();
Animal animal2 = new Cat();

animal1.makeSound(); // Output: Woof!
animal2.makeSound(); // Output: Meow!
```

### Key Points for Viva:
✅ "Poly" = Many, "Morph" = Forms  
✅ Same method name, different implementations  
✅ Uses `@Override` annotation  
✅ Decided at runtime (Runtime Polymorphism)  
✅ Enables flexibility and extensibility  
✅ Parent reference can hold child object  

---

## COMPARISON TABLE

| Concept | Keyword | Purpose | Example in Project |
|---------|---------|---------|-------------------|
| **Class** | `class` | Blueprint for objects | `Book`, `User`, `Cashier` |
| **Object** | `new` | Instance of class | `new Book()`, `new Manager()` |
| **Encapsulation** | `private`, `public` | Data hiding | Private fields in `Book` |
| **Inheritance** | `extends` | Code reusability | `Cashier extends User` |
| **Abstraction** | `abstract` | Hide complexity | `abstract class User` |
| **Polymorphism** | `@Override` | Multiple forms | `showMenu()` in child classes |

---

## RELATIONSHIPS BETWEEN CONCEPTS

```
┌─────────────────────────────────────────────────┐
│                    CLASS                        │
│  (Blueprint containing fields and methods)      │
└─────────────────────────────────────────────────┘
                      │
                      ├─► OBJECT (Instance)
                      │
                      ├─► ENCAPSULATION (Private fields)
                      │
                      ├─► INHERITANCE (extends)
                      │       │
                      │       └─► POLYMORPHISM (@Override)
                      │
                      └─► ABSTRACTION (abstract)
```

---

## VIVA QUESTIONS & ANSWERS

### Q1: What is the difference between abstraction and encapsulation?

**Answer:**
- **Encapsulation** = Data hiding (using private fields)
  - Example: `private String bookId;`
  - Purpose: Protect data
  
- **Abstraction** = Implementation hiding (using abstract classes/methods)
  - Example: `abstract class User`
  - Purpose: Show only essential features

### Q2: Why did you make User class abstract?

**Answer:**
- User is too generic - we don't create just "users"
- We always create specific types: Cashier or Manager
- Abstract class forces child classes to implement specific behavior
- Provides common template for all user types

### Q3: Explain inheritance in your project.

**Answer:**
- User is parent class with username and password
- Cashier and Manager are child classes
- Both inherit username and password from User
- Manager has all Cashier features plus extra management features
- Eliminates code duplication

### Q4: Where is polymorphism used?

**Answer:**
Three places:
1. **showMenu()** - Different implementation in Cashier and Manager
2. **getRole()** - Returns "Cashier" or "Manager"
3. **Authentication** - Returns User reference but actual object is Cashier/Manager

### Q5: What is encapsulation benefit?

**Answer:**
- Data protection from invalid values
- Example: Can't set negative price directly
- Validation in setters ensures data integrity
- Internal changes don't affect external code

---

## CODE WALKTHROUGH FOR VIVA

### Demonstrate Class & Object:
```java
Book book = new Book("B001", "Java", "Programming", 2500.0, 15);
// Book = Class (blueprint)
// book = Object (instance)
```

### Demonstrate Encapsulation:
```java
public class Book {
    private String name; // Can't access directly
    public String getName() { return name; } // Controlled access
}
```

### Demonstrate Inheritance:
```java
public class Manager extends User {
    // Inherits username, password from User
    // Adds own methods: addBook(), createAccount()
}
```

### Demonstrate Abstraction:
```java
public abstract class User {
    public abstract void showMenu(); // No implementation
}
// Forces child classes to implement
```

### Demonstrate Polymorphism:
```java
User user = new Manager("admin", "pass");
user.showMenu(); // Calls Manager's version
```

---

## TIPS FOR PRESENTATION

### Do:
✅ Show code examples for each concept  
✅ Explain real-world analogies  
✅ Demonstrate the running application  
✅ Explain design decisions  
✅ Show class diagram  

### Don't:
❌ Just read from documentation  
❌ Skip explaining why you used each concept  
❌ Forget to test the application before demo  

### Demo Flow:
1. Show class hierarchy (User → Cashier, Manager)
2. Explain each OOP concept with code
3. Run application and login as both roles
4. Show different behaviors (polymorphism)
5. Explain file handling

---

## QUICK RECAP

**Remember the 5 Pillars:**

1. **CLASS & OBJECT**
   - Class = Blueprint
   - Object = Instance

2. **ENCAPSULATION**
   - private + getter/setter
   - Data hiding

3. **INHERITANCE**
   - extends keyword
   - Code reusability

4. **ABSTRACTION**
   - abstract keyword
   - Hide complexity

5. **POLYMORPHISM**
   - @Override
   - Many forms

---

## FINAL CHECKLIST FOR VIVA

- [ ] Can explain what a class is
- [ ] Can explain what an object is
- [ ] Can explain encapsulation with example
- [ ] Can explain inheritance hierarchy
- [ ] Can explain why User is abstract
- [ ] Can explain polymorphism with showMenu()
- [ ] Can demonstrate the running application
- [ ] Can explain file handling
- [ ] Can explain role-based access
- [ ] Can show UML diagram

---

**Good Luck with Your Presentation! 🎓**

---

**End of Quick Reference Guide**
