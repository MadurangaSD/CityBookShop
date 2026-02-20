package model;

public class Cashier extends User {

    public Cashier(String username, String password) {
        super(username, password);
    }

    @Override
    public void showMenu() {
        System.out.println("Cashier Menu");
    }
}