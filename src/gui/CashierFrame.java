package gui;

import model.Cashier;
import model.Book;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * CashierFrame - GUI for Cashier operations
 */
public class CashierFrame extends JFrame {
    
    private Cashier cashier;
    private JTable bookTable;
    private DefaultTableModel tableModel;
    private JTextField searchField;
    private JComboBox<String> searchTypeCombo;
    
    public CashierFrame(Cashier cashier) {
        this.cashier = cashier;
        setTitle("City Bookshop - Cashier Dashboard");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        initComponents();
        loadAllBooks();
    }
    
    private void initComponents() {
        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Top panel with welcome message
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(70, 130, 180));
        JLabel welcomeLabel = new JLabel("Welcome, " + cashier.getUsername() + " (Cashier)");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        topPanel.add(welcomeLabel, BorderLayout.WEST);
        
        JButton logoutButton = new JButton("Logout");
        logoutButton.setBackground(new Color(220, 20, 60));
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setFocusPainted(false);
        logoutButton.addActionListener(e -> {
            this.dispose();
            new LoginFrame().setVisible(true);
        });
        topPanel.add(logoutButton, BorderLayout.EAST);
        
        // Search panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBorder(BorderFactory.createTitledBorder("Search Books"));
        
        searchTypeCombo = new JComboBox<>(new String[]{
            "All Books", "By ID", "By Name", "By Category", "By Price Range"
        });
        searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        searchButton.setBackground(new Color(70, 130, 180));
        searchButton.setForeground(Color.WHITE);
        searchButton.addActionListener(e -> performSearch());
        
        JButton refreshButton = new JButton("Refresh All");
        refreshButton.setBackground(new Color(60, 179, 113));
        refreshButton.setForeground(Color.WHITE);
        refreshButton.addActionListener(e -> loadAllBooks());
        
        searchPanel.add(new JLabel("Search Type:"));
        searchPanel.add(searchTypeCombo);
        searchPanel.add(new JLabel("Search:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(refreshButton);
        
        // Table panel
        String[] columnNames = {"Book ID", "Name", "Category", "Price (Rs.)", "Stock"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        bookTable = new JTable(tableModel);
        bookTable.setRowHeight(25);
        bookTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        bookTable.getTableHeader().setBackground(new Color(70, 130, 180));
        bookTable.getTableHeader().setForeground(Color.WHITE);
        
        JScrollPane scrollPane = new JScrollPane(bookTable);
        
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(searchPanel, BorderLayout.CENTER);
        mainPanel.add(scrollPane, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    private void loadAllBooks() {
        ArrayList<Book> books = cashier.viewAllBooks();
        displayBooks(books);
    }
    
    private void performSearch() {
        String searchType = (String) searchTypeCombo.getSelectedItem();
        String searchText = searchField.getText().trim();
        ArrayList<Book> results = new ArrayList<>();
        
        switch (searchType) {
            case "All Books":
                results = cashier.viewAllBooks();
                break;
                
            case "By ID":
                if (searchText.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter Book ID!");
                    return;
                }
                Book book = cashier.searchBookById(searchText);
                if (book != null) {
                    results.add(book);
                }
                break;
                
            case "By Name":
                if (searchText.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter book name!");
                    return;
                }
                results = cashier.searchBooksByName(searchText);
                break;
                
            case "By Category":
                if (searchText.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter category!");
                    return;
                }
                results = cashier.searchBooksByCategory(searchText);
                break;
                
            case "By Price Range":
                String input = JOptionPane.showInputDialog(this, 
                    "Enter price range (e.g., 100-500):");
                if (input != null && input.contains("-")) {
                    try {
                        String[] parts = input.split("-");
                        double minPrice = Double.parseDouble(parts[0].trim());
                        double maxPrice = Double.parseDouble(parts[1].trim());
                        results = cashier.searchBooksByPrice(minPrice, maxPrice);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, "Invalid price range format!");
                        return;
                    }
                }
                break;
        }
        
        displayBooks(results);
    }
    
    private void displayBooks(ArrayList<Book> books) {
        tableModel.setRowCount(0);
        
        if (books.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No books found!");
            return;
        }
        
        for (Book book : books) {
            Object[] row = {
                book.getBookId(),
                book.getName(),
                book.getCategory(),
                String.format("%.2f", book.getPrice()),
                book.getQuantity()
            };
            tableModel.addRow(row);
        }
    }
}
