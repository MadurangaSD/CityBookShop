package bookshop;

import java.util.*;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("===== CITY BOOKSHOP SYSTEM =====");
        System.out.println("Login As:");
        System.out.println("1. Manager");
        System.out.println("2. Cashier");

        int choice = sc.nextInt();
        User user;

        if (choice == 1) {
            user = new Manager("manager", "123");
        } else {
            user = new Cashier("cashier", "123");
        }

        user.showMenu();

        if (user instanceof Manager) {

            System.out.println("\nEnter Book ID:");
            String id = sc.next();

            System.out.println("Enter Book Name:");
            String name = sc.next();

            System.out.println("Enter Category:");
            String category = sc.next();

            System.out.println("Enter Price:");
            double price = sc.nextDouble();

            System.out.println("Enter Quantity:");
            int quantity = sc.nextInt();

            Book book = new Book(id, name, category, price, quantity);
            FileHandler.saveBook(book);

            System.out.println("Book Added Successfully!");
        }

        System.out.println("\nAll Books:");
        List<Book> books = FileHandler.loadBooks();

        for (Book b : books) {
            System.out.println(b.getName() + " - Rs." + b.getPrice());
        }
    }
}