package util;

import java.io.*;
import java.util.ArrayList;
import model.Book;

public class FileHandler {

    private static final String FILE_NAME = "books.txt";

    public static void saveBook(Book book) {
        try (FileWriter fw = new FileWriter(FILE_NAME, true)) {
            fw.write(book.toString() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Book> getAllBooks() {
        ArrayList<Book> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Book b = new Book(
                        data[0],
                        Double.parseDouble(data[1]),
                        data[2]
                );
                list.add(b);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }
}