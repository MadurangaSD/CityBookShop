package model;

/**
 * User abstract class - Demonstrates ABSTRACTION and INHERITANCE
 * This is the parent class for Cashier and Manager classes
 */
public abstract class User {

    protected String username;
    protected String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters for encapsulation
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // Abstract method - forces child classes to implement their own version
    public abstract void showMenu();
    
    // Abstract method for displaying role
    public abstract String getRole();
}
