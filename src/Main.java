import model.Book;
import util.FileHandler;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Book b1 = new Book("Java Basics", 2500, "Programming");
        FileHandler.saveBook(b1);

        ArrayList<Book> books = FileHandler.getAllBooks();

        for (Book b : books) {
            System.out.println(b.getName());
        }
    }
}