package gui;

import model.Manager;
import model.Book;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * ManagerFrame - GUI for Manager operations
 */
public class ManagerFrame extends JFrame {
    
    private Manager manager;
    private JTable bookTable;
    private DefaultTableModel tableModel;
    private JTextField searchField;
    private JComboBox<String> searchTypeCombo;
    
    public ManagerFrame(Manager manager) {
        this.manager = manager;
        setTitle("City Bookshop - Manager Dashboard");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        initComponents();
        loadAllBooks();
    }
    
    private void initComponents() {
        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Top panel
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(184, 134, 11));
        JLabel welcomeLabel = new JLabel("Welcome, " + manager.getUsername() + " (Manager)");
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
        
        // Action buttons panel
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        actionPanel.setBorder(BorderFactory.createTitledBorder("Manager Actions"));
        
        JButton addBookButton = new JButton("Add New Book");
        addBookButton.setBackground(new Color(60, 179, 113));
        addBookButton.setForeground(Color.WHITE);
        addBookButton.addActionListener(e -> showAddBookDialog());
        
        JButton updateStockButton = new JButton("Update Stock");
        updateStockButton.setBackground(new Color(255, 140, 0));
        updateStockButton.setForeground(Color.WHITE);
        updateStockButton.addActionListener(e -> showUpdateStockDialog());
        
        JButton createAccountButton = new JButton("Create Account");
        createAccountButton.setBackground(new Color(106, 90, 205));
        createAccountButton.setForeground(Color.WHITE);
        createAccountButton.addActionListener(e -> showCreateAccountDialog());
        
        actionPanel.add(addBookButton);
        actionPanel.add(updateStockButton);
        actionPanel.add(createAccountButton);
        
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
        
        // Combine action and search panels
        JPanel controlPanel = new JPanel(new BorderLayout());
        controlPanel.add(actionPanel, BorderLayout.NORTH);
        controlPanel.add(searchPanel, BorderLayout.CENTER);
        
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
        bookTable.getTableHeader().setBackground(new Color(184, 134, 11));
        bookTable.getTableHeader().setForeground(Color.WHITE);
        
        JScrollPane scrollPane = new JScrollPane(bookTable);
        
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(controlPanel, BorderLayout.CENTER);
        mainPanel.add(scrollPane, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    private void loadAllBooks() {
        ArrayList<Book> books = manager.viewAllBooks();
        displayBooks(books);
    }
    
    private void performSearch() {
        String searchType = (String) searchTypeCombo.getSelectedItem();
        String searchText = searchField.getText().trim();
        ArrayList<Book> results = new ArrayList<>();
        
        switch (searchType) {
            case "All Books":
                results = manager.viewAllBooks();
                break;
                
            case "By ID":
                if (searchText.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter Book ID!");
                    return;
                }
                Book book = manager.searchBookById(searchText);
                if (book != null) {
                    results.add(book);
                }
                break;
                
            case "By Name":
                if (searchText.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter book name!");
                    return;
                }
                results = manager.searchBooksByName(searchText);
                break;
                
            case "By Category":
                if (searchText.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter category!");
                    return;
                }
                results = manager.searchBooksByCategory(searchText);
                break;
                
            case "By Price Range":
                String input = JOptionPane.showInputDialog(this, 
                    "Enter price range (e.g., 100-500):");
                if (input != null && input.contains("-")) {
                    try {
                        String[] parts = input.split("-");
                        double minPrice = Double.parseDouble(parts[0].trim());
                        double maxPrice = Double.parseDouble(parts[1].trim());
                        results = manager.searchBooksByPrice(minPrice, maxPrice);
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
    
    private void showAddBookDialog() {
        JDialog dialog = new JDialog(this, "Add New Book", true);
        dialog.setSize(400, 350);
        dialog.setLocationRelativeTo(this);
        
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JTextField idField = new JTextField(15);
        JTextField nameField = new JTextField(15);
        JTextField categoryField = new JTextField(15);
        JTextField priceField = new JTextField(15);
        JTextField quantityField = new JTextField(15);
        
        // Add components
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Book ID:"), gbc);
        gbc.gridx = 1;
        panel.add(idField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Book Name:"), gbc);
        gbc.gridx = 1;
        panel.add(nameField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Category:"), gbc);
        gbc.gridx = 1;
        panel.add(categoryField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Price (Rs.):"), gbc);
        gbc.gridx = 1;
        panel.add(priceField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(new JLabel("Quantity:"), gbc);
        gbc.gridx = 1;
        panel.add(quantityField, gbc);
        
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add");
        addButton.setBackground(new Color(60, 179, 113));
        addButton.setForeground(Color.WHITE);
        addButton.addActionListener(e -> {
            try {
                String id = idField.getText().trim();
                String name = nameField.getText().trim();
                String category = categoryField.getText().trim();
                double price = Double.parseDouble(priceField.getText().trim());
                int quantity = Integer.parseInt(quantityField.getText().trim());
                
                if (id.isEmpty() || name.isEmpty() || category.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Please fill all fields!");
                    return;
                }
                
                if (manager.addNewBook(id, name, category, price, quantity)) {
                    JOptionPane.showMessageDialog(dialog, "Book added successfully!");
                    dialog.dispose();
                    loadAllBooks();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Book ID already exists!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Invalid price or quantity!");
            }
        });
        
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBackground(new Color(220, 20, 60));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.addActionListener(e -> dialog.dispose());
        
        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);
        
        gbc.gridx = 0; gbc.gridy = 5;
        gbc.gridwidth = 2;
        panel.add(buttonPanel, gbc);
        
        dialog.add(panel);
        dialog.setVisible(true);
    }
    
    private void showUpdateStockDialog() {
        String bookId = JOptionPane.showInputDialog(this, "Enter Book ID:");
        if (bookId == null || bookId.trim().isEmpty()) {
            return;
        }
        
        Book book = manager.searchBookById(bookId.trim());
        if (book == null) {
            JOptionPane.showMessageDialog(this, "Book not found!");
            return;
        }
        
        String currentStock = "Current Stock: " + book.getQuantity();
        String input = JOptionPane.showInputDialog(this, 
            currentStock + "\nEnter new stock quantity:");
        
        if (input != null) {
            try {
                int newQuantity = Integer.parseInt(input.trim());
                if (newQuantity < 0) {
                    JOptionPane.showMessageDialog(this, "Quantity cannot be negative!");
                    return;
                }
                
                if (manager.updateBookStock(bookId.trim(), newQuantity)) {
                    JOptionPane.showMessageDialog(this, "Stock updated successfully!");
                    loadAllBooks();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to update stock!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid quantity!");
            }
        }
    }
    
    private void showCreateAccountDialog() {
        JDialog dialog = new JDialog(this, "Create New Account", true);
        dialog.setSize(350, 250);
        dialog.setLocationRelativeTo(this);
        
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JTextField usernameField = new JTextField(15);
        JPasswordField passwordField = new JPasswordField(15);
        JComboBox<String> roleCombo = new JComboBox<>(new String[]{"Cashier", "Manager"});
        
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        panel.add(usernameField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        panel.add(passwordField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Role:"), gbc);
        gbc.gridx = 1;
        panel.add(roleCombo, gbc);
        
        JPanel buttonPanel = new JPanel();
        JButton createButton = new JButton("Create");
        createButton.setBackground(new Color(60, 179, 113));
        createButton.setForeground(Color.WHITE);
        createButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());
            String role = (String) roleCombo.getSelectedItem();
            
            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Please fill all fields!");
                return;
            }
            
            if (manager.createNewAccount(username, password, role)) {
                JOptionPane.showMessageDialog(dialog, "Account created successfully!");
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog, "Username already exists!");
            }
        });
        
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBackground(new Color(220, 20, 60));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.addActionListener(e -> dialog.dispose());
        
        buttonPanel.add(createButton);
        buttonPanel.add(cancelButton);
        
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(buttonPanel, gbc);
        
        dialog.add(panel);
        dialog.setVisible(true);
    }
}
