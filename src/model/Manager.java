package model;

public class Manager extends User {

    public Manager(String username, String password) {
        super(username, password);
    }

    @Override
    public void showMenu() {
        System.out.println("Manager Menu");
    }
}