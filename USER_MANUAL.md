# CITY BOOKSHOP SYSTEM - USER MANUAL
## Task 2: Complete User Guide

---

## TABLE OF CONTENTS
1. Introduction
2. System Requirements
3. Installation Guide
4. Getting Started
5. Login Instructions
6. Cashier User Guide
7. Manager User Guide
8. Troubleshooting
9. FAQs

---

## 1. INTRODUCTION

### What is City Bookshop System?

City Bookshop System is a desktop application designed to manage bookshop operations efficiently. The system provides different functionalities based on user roles:

- **Cashier:** Can view and search book inventory
- **Manager:** Can perform all cashier functions plus add books and create user accounts

### Key Features

✅ User authentication with role-based access  
✅ View complete book inventory  
✅ Search books by multiple criteria (ID, Name, Category, Price)  
✅ Add new books to inventory (Manager only)  
✅ Update book stock quantities (Manager only)  
✅ Create new user accounts (Manager only)  
✅ Automatic data saving to files  

---

## 2. SYSTEM REQUIREMENTS

### Software Requirements
- **Operating System:** Windows 7/8/10/11, macOS, or Linux
- **Java:** JDK 8 or higher must be installed
- **Display:** Minimum resolution 1024x768

### Hardware Requirements
- **RAM:** Minimum 2GB
- **Storage:** 100MB free space
- **Processor:** Any modern processor

### Checking Java Installation

**Windows:**
1. Open Command Prompt (Win + R, type `cmd`)
2. Type: `java -version`
3. If Java is installed, you'll see the version number

**Mac/Linux:**
1. Open Terminal
2. Type: `java -version`
3. If Java is installed, you'll see the version number

**If Java is not installed:**
- Download from: https://www.oracle.com/java/technologies/downloads/
- Install following the installer instructions

---

## 3. INSTALLATION GUIDE

### Step 1: Extract Files
1. Extract the `CityBookShop.zip` file to a location on your computer
2. Example: `C:\Programs\CityBookShop` or `E:\projects\CityBookShop`

### Step 2: Verify File Structure
```
CityBookShop/
├── src/
│   ├── model/
│   ├── util/
│   ├── gui/
│   ├── Main.java
│   ├── books.txt
│   └── users.txt
```

### Step 3: Compile the Application (First Time Only)

**Using Command Prompt/Terminal:**

```bash
# Navigate to src directory
cd path/to/CityBookShop/src

# Compile all files
javac Main.java model/*.java util/*.java gui/*.java
```

---

## 4. GETTING STARTED

### Running the Application

**Method 1: Command Line**
```bash
# Navigate to src directory
cd path/to/CityBookShop/src

# Run the application
java Main
```

**Method 2: Using IDE**
1. Open the project in your Java IDE (Eclipse, IntelliJ, NetBeans)
2. Right-click on `Main.java`
3. Select `Run as Java Application`

### First Launch

When you run the application for the first time:
1. The system automatically creates default user accounts
2. Sample books are added to the inventory
3. The login window appears

---

## 5. LOGIN INSTRUCTIONS

### Login Screen

![Login Window]

When the application starts, you'll see the login window with:
- **Username** field
- **Password** field
- **Login** button
- **Exit** button

### Default User Accounts

The system comes with two pre-created accounts for testing:

**Manager Account:**
- Username: `admin`
- Password: `admin123`

**Cashier Account:**
- Username: `cashier`
- Password: `cash123`

### Steps to Login

1. **Enter Username**
   - Type your username in the Username field
   - Example: `admin` or `cashier`

2. **Enter Password**
   - Type your password in the Password field
   - Password is hidden with asterisks (●●●●)
   - Example: `admin123` or `cash123`

3. **Click Login Button**
   - Click the blue "Login" button
   - The system will verify your credentials

4. **Successful Login**
   - If credentials are correct, you'll be redirected to your dashboard
   - Managers see the Manager Dashboard
   - Cashiers see the Cashier Dashboard

5. **Failed Login**
   - If credentials are incorrect, an error message appears
   - Check your username and password
   - Try again

### Exit Application

- Click the red "Exit" button to close the application
- Or close the window using the X button

---

## 6. CASHIER USER GUIDE

### Overview

Cashiers have read-only access to the book inventory. They can view and search for books but cannot modify data or create accounts.

---

### 6.1 Cashier Dashboard

After logging in as cashier, you'll see:
- Welcome message with your username
- Search panel at the top
- Book inventory table at the bottom
- Logout button in the top-right corner

---

### 6.2 View All Books

**What it does:** Displays all books in the inventory

**How to use:**
1. After login, all books are automatically displayed in the table
2. The table shows:
   - Book ID
   - Book Name
   - Category
   - Price (in Rs.)
   - Available Stock

**Example Display:**
```
╔══════════╦═══════════════════╦═════════════╦════════════╦═══════╗
║ Book ID  ║ Name              ║ Category    ║ Price      ║ Stock ║
╠══════════╬═══════════════════╬═════════════╬════════════╬═══════╣
║ B001     ║ Java Programming  ║ Programming ║ 2500.00    ║ 15    ║
║ B002     ║ Python Basics     ║ Programming ║ 2000.00    ║ 20    ║
║ B003     ║ Data Structures   ║ CS          ║ 3000.00    ║ 10    ║
╚══════════╩═══════════════════╩═════════════╩════════════╩═══════╝
```

---

### 6.3 Search Books

The system provides multiple search options:

#### Option 1: Search by Book ID

**Use case:** When you know the exact book ID

**Steps:**
1. Click the "Search Type" dropdown
2. Select "By ID"
3. Enter the book ID in the search field (e.g., `B001`)
4. Click the "Search" button
5. The matching book will be displayed

**Example:**
- Search Type: `By ID`
- Search Field: `B001`
- Result: Shows "Java Programming" book details

---

#### Option 2: Search by Name

**Use case:** When you know part of the book name

**Steps:**
1. Select "By Name" from the dropdown
2. Enter the book name (full or partial) in the search field
3. Click "Search"
4. All matching books will be displayed

**Examples:**
- Search: `Java` → Shows all books with "Java" in the name
- Search: `Programming` → Shows all books with "Programming" in the name

**Note:** Search is case-insensitive (Java = java = JAVA)

---

#### Option 3: Search by Category

**Use case:** To see all books in a specific category

**Steps:**
1. Select "By Category" from the dropdown
2. Enter the category name (e.g., `Programming`, `Fiction`)
3. Click "Search"
4. All books in that category will be displayed

**Example Categories:**
- Programming
- Computer Science
- Fiction
- Business

---

#### Option 4: Search by Price Range

**Use case:** To find books within a specific price range

**Steps:**
1. Select "By Price Range" from the dropdown
2. Click "Search"
3. A dialog box will appear asking for price range
4. Enter range in format: `minimum-maximum`
   - Example: `1000-2500`
5. Click OK
6. All books within that price range will be displayed

**Examples:**
- `500-1500` → Shows books priced between Rs. 500 and Rs. 1500
- `2000-3000` → Shows books priced between Rs. 2000 and Rs. 3000

---

### 6.4 Refresh View

**What it does:** Reloads all books from the database

**When to use:**
- To see recently added books (by a manager)
- To return to full inventory view after searching

**How to use:**
1. Click the green "Refresh All" button
2. The table will reload with all books

---

### 6.5 Logout

**How to logout:**
1. Click the red "Logout" button in the top-right corner
2. You'll be returned to the login screen
3. You can login again or exit the application

---

## 7. MANAGER USER GUIDE

### Overview

Managers have full access to the system. They can perform all cashier functions PLUS:
- Add new books
- Update book stock
- Create new user accounts (cashiers and managers)

---

### 7.1 Manager Dashboard

After logging in as manager, you'll see:
- Welcome message: "Welcome, [username] (Manager)"
- **Manager Actions** panel with three buttons:
  - Add New Book (Green)
  - Update Stock (Orange)
  - Create Account (Purple)
- **Search Books** panel (same as cashier)
- Book inventory table
- Logout button

---

### 7.2 View and Search Books

Managers can use all the same search features as cashiers:
- View all books
- Search by ID
- Search by Name
- Search by Category
- Search by Price Range
- Refresh view

**Refer to Cashier User Guide (Section 6) for detailed instructions**

---

### 7.3 Add New Book

**Purpose:** Add a new book to the inventory

**Steps:**

1. **Click "Add New Book" Button**
   - Click the green "Add New Book" button in the Manager Actions panel
   - A dialog window will open

2. **Fill in Book Details**
   - **Book ID:** Enter a unique book ID (e.g., `B009`)
     - Must not already exist in the system
   - **Book Name:** Enter the full book name (e.g., `Android Development`)
   - **Category:** Enter the book category (e.g., `Programming`)
   - **Price (Rs.):** Enter the book price (e.g., `2800`)
   - **Quantity:** Enter the initial stock quantity (e.g., `15`)

3. **Validate Input**
   - Ensure all fields are filled
   - Price must be a number
   - Quantity must be a whole number

4. **Click "Add" Button**
   - If the book ID is unique, the book will be added
   - Success message: "Book added successfully!"
   - The dialog will close
   - Click "Refresh All" to see the new book in the table

5. **Cancel Operation**
   - Click the red "Cancel" button to close without adding

**Example:**
```
Book ID:   B009
Name:      Android Development
Category:  Mobile Programming
Price:     2800
Quantity:  15
```

**Error Messages:**
- "Please fill all fields!" → One or more fields are empty
- "Book ID already exists!" → Choose a different book ID
- "Invalid price or quantity!" → Enter numerical values

---

### 7.4 Update Book Stock

**Purpose:** Change the stock quantity of an existing book

**Steps:**

1. **Click "Update Stock" Button**
   - Click the orange "Update Stock" button

2. **Enter Book ID**
   - A dialog asks: "Enter Book ID:"
   - Type the ID of the book you want to update (e.g., `B001`)
   - Click OK

3. **View Current Stock**
   - The system displays: "Current Stock: [number]"

4. **Enter New Quantity**
   - Type the new stock quantity
   - Example: If current is 15, enter 20 to increase to 20

5. **Confirm Update**
   - Click OK
   - Success message: "Stock updated successfully!"
   - The table will refresh automatically

6. **Cancel Operation**
   - Click Cancel at any step to abort

**Use Cases:**
- **Receiving new stock:** Increase quantity when new books arrive
- **Inventory adjustment:** Correct mistakes in stock count
- **Sold out:** Set to 0 when book is out of stock

**Example Scenario:**
```
Current stock of B001 (Java Programming): 15 books
Received 10 new books
New stock: 25 books
```

Steps:
1. Click "Update Stock"
2. Enter Book ID: `B001`
3. See "Current Stock: 15"
4. Enter: `25`
5. Click OK

**Error Messages:**
- "Book not found!" → Check the book ID
- "Quantity cannot be negative!" → Enter a positive number
- "Invalid quantity!" → Enter a whole number

---

### 7.5 Create New User Account

**Purpose:** Add new cashier or manager accounts to the system

**Steps:**

1. **Click "Create Account" Button**
   - Click the purple "Create Account" button
   - A dialog window opens

2. **Fill in User Details**
   - **Username:** Enter a unique username (e.g., `john_doe`)
     - Must not already exist
     - No spaces recommended
   - **Password:** Enter a secure password (e.g., `John@2024`)
     - Use a mix of letters and numbers
     - Minimum length recommended: 6 characters
   - **Role:** Select user role from dropdown
     - Choose "Cashier" for cashier account
     - Choose "Manager" for manager account

3. **Click "Create" Button**
   - If username is unique, account will be created
   - Success message: "Account created successfully!"
   - The new user can now login

4. **Cancel Operation**
   - Click the red "Cancel" button to close without creating

**Example - Creating a Cashier:**
```
Username: sarah_jones
Password: Sarah@123
Role:     Cashier
```

**Example - Creating a Manager:**
```
Username: mike_admin
Password: Mike@2024
Role:     Manager
```

**Error Messages:**
- "Please fill all fields!" → Enter username and password
- "Username already exists!" → Choose a different username

**Security Tips:**
- Create strong passwords for all accounts
- Don't share manager credentials with cashiers
- Change default passwords after first login (future feature)

---

### 7.6 Logout

**How to logout:**
1. Click the red "Logout" button in the top-right corner
2. You'll be returned to the login screen

---

## 8. TROUBLESHOOTING

### Problem: Application Won't Start

**Possible Causes:**
1. Java not installed
2. Wrong directory
3. Files missing

**Solutions:**
1. **Check Java Installation**
   ```bash
   java -version
   ```
   If not installed, download from Oracle website

2. **Verify Location**
   - Make sure you're in the `src` directory
   - Run: `cd path/to/CityBookShop/src`

3. **Check Files**
   - Ensure all .java files are present
   - Recompile: `javac Main.java model/*.java util/*.java gui/*.java`

---

### Problem: Login Failed

**Error:** "Invalid username or password!"

**Solutions:**
1. **Check Default Credentials:**
   - Manager: `admin` / `admin123`
   - Cashier: `cashier` / `cash123`

2. **Check Caps Lock**
   - Passwords are case-sensitive

3. **Verify users.txt file exists**
   - Location: `src/users.txt`
   - Should contain account information

---

### Problem: No Books Displayed

**Possible Causes:**
1. books.txt file is empty
2. File not found

**Solutions:**
1. **Check books.txt file**
   - Location: `src/books.txt`
   - Should contain book data

2. **Add Sample Books**
   - Restart the application
   - The system will auto-create sample books

3. **Manual Add**
   - Login as manager
   - Use "Add New Book" feature

---

### Problem: "Book ID Already Exists" Error

**When:** Adding a new book

**Solution:**
- Each book must have a unique ID
- Check existing books first
- Use a different ID (e.g., B010, B011, etc.)

---

### Problem: Search Returns No Results

**Possible Causes:**
1. No books match the search criteria
2. Spelling mistake

**Solutions:**
1. **Check Spelling**
   - Verify the search term

2. **Try Partial Search**
   - For name search, use partial words
   - Example: "Java" instead of "Java Programming"

3. **Try Different Category**
   - Categories are case-sensitive with exact match

4. **Refresh and Try Again**
   - Click "Refresh All" button

---

### Problem: Can't Update Stock

**Error:** "Book not found!"

**Solution:**
- Verify the book ID is correct
- Search for the book first to confirm the ID
- Book IDs are case-sensitive

---

## 9. FREQUENTLY ASKED QUESTIONS (FAQs)

### Q1: Can I change my password?

**A:** Currently, the system doesn't have a built-in password change feature. A manager can create a new account with a new password.

---

### Q2: What happens if I delete books.txt or users.txt?

**A:** 
- If you delete `users.txt`, you'll need to recreate it or the system will have no accounts
- If you delete `books.txt`, all book data will be lost
- Best practice: Always backup these files

---

### Q3: Can a cashier see manager functions?

**A:** No. The system uses role-based access control. Cashiers only see search and view functions, not management functions.

---

### Q4: How many books can the system handle?

**A:** The system can handle thousands of books. Performance depends on your computer's specifications.

---

### Q5: Can I run multiple instances of the application?

**A:** Yes, but be careful. Multiple instances may cause data conflicts when writing to files. Best to run one instance at a time.

---

### Q6: Is there a limit to username/password length?

**A:** No enforced limit, but recommended:
- Username: 4-20 characters
- Password: 6-30 characters

---

### Q7: What categories can I use for books?

**A:** You can use any category name you want. Common examples:
- Programming
- Computer Science
- Fiction
- Non-Fiction
- Business
- Self-Help
- Science

---

### Q8: Can I edit book details after adding?

**A:** Currently, you can only update the stock quantity. To edit other details (name, price, category), you need to manually edit the books.txt file.

---

### Q9: What format is used in the text files?

**A:**
- **books.txt:** `BookID,Name,Category,Price,Quantity`
  - Example: `B001,Java Programming,Programming,2500.0,15`
  
- **users.txt:** `Username,Password,Role`
  - Example: `admin,admin123,Manager`

---

### Q10: Can I backup my data?

**A:** Yes! Simply copy these files to a safe location:
- `src/books.txt`
- `src/users.txt`

To restore, copy them back to the src directory.

---

## 10. TIPS FOR BEST USE

### For Cashiers:
1. ✅ Use search features to quickly find books for customers
2. ✅ Check stock availability before confirming sales
3. ✅ Use price range search for customer budget inquiries
4. ✅ Refresh regularly to see updated inventory

### For Managers:
1. ✅ Update stock immediately after receiving new inventory
2. ✅ Create strong passwords for new accounts
3. ✅ Use unique and sequential book IDs (B001, B002, etc.)
4. ✅ Backup books.txt and users.txt regularly
5. ✅ Verify book details before adding to avoid duplicates

---

## 11. DATA BACKUP AND RECOVERY

### How to Backup

**Manual Backup:**
1. Navigate to `CityBookShop/src/` folder
2. Copy these files:
   - `books.txt`
   - `users.txt`
3. Save them in a safe location (e.g., USB drive, cloud storage)
4. Add date to backup folder name: `Backup_2024_02_20`

**Recommended Backup Schedule:**
- Daily: For active bookshops
- Weekly: For moderate use
- Monthly: For low activity

---

### How to Restore

1. Close the City Bookshop application (if running)
2. Navigate to `CityBookShop/src/` folder
3. Replace current files with backup files:
   - Replace `books.txt` with backup
   - Replace `users.txt` with backup
4. Restart the application
5. Verify data has been restored

---

## 12. CONTACT AND SUPPORT

### Getting Help

If you encounter issues not covered in this manual:

1. **Check Error Messages**
   - Read the error message carefully
   - Refer to Troubleshooting section

2. **Verify File Integrity**
   - Ensure all files are present
   - Check file permissions

3. **Community Support**
   - Post questions in Java forums
   - Search for similar issues online

---

## 13. VERSION INFORMATION

**Application:** City Bookshop Management System  
**Version:** 1.0  
**Release Date:** February 2024  
**Platform:** Java Desktop Application  
**Manual Version:** 1.0  

---

## 14. SYSTEM LIMITATIONS

**Current Limitations:**
1. No password change feature
2. Cannot edit book details (except stock)
3. Cannot delete books from GUI
4. No transaction/sales tracking
5. No report generation
6. Single-user at a time recommended

**Future Enhancements (Possible):**
- Sales transaction recording
- Customer management
- Invoice generation
- Database integration (MySQL)
- Multi-user concurrent access
- Data export (PDF, Excel)

---

## 15. BEST PRACTICES

### Security:
✅ Always logout when finished  
✅ Don't share login credentials  
✅ Change default passwords  
✅ Keep backup of data files  

### Data Management:
✅ Use consistent naming for categories  
✅ Keep book IDs sequential  
✅ Update stock promptly  
✅ Regular backups  

### Performance:
✅ Close unused applications  
✅ Restart app if sluggish  
✅ Keep number of records reasonable  

---

## APPENDIX A: KEYBOARD SHORTCUTS

| Action | Shortcut |
|--------|----------|
| Tab between fields | Tab |
| Submit form | Enter |
| Close dialog | Esc |
| Select all text | Ctrl + A |
| Copy | Ctrl + C |
| Paste | Ctrl + V |

---

## APPENDIX B: FILE FORMATS

### books.txt Format
```
BookID,BookName,Category,Price,Quantity
B001,Java Programming,Programming,2500.0,15
B002,Python Basics,Programming,2000.0,20
```

### users.txt Format
```
Username,Password,Role
admin,admin123,Manager
cashier,cash123,Cashier
```

---

## APPENDIX C: SAMPLE DATA

### Sample Books
```
B001,Java Programming,Programming,2500.00,15
B002,Python Basics,Programming,2000.00,20
B003,Data Structures,Computer Science,3000.00,10
B004,Web Development,Programming,2800.00,12
B005,Database Systems,Computer Science,3200.00,8
B006,The Great Gatsby,Fiction,1500.00,25
B007,To Kill a Mockingbird,Fiction,1800.00,18
B008,Business Management,Business,2200.00,14
```

### Sample Users
```
admin,admin123,Manager
cashier,cash123,Cashier
manager2,manager456,Manager
cashier2,cashier456,Cashier
```

---

## CONCLUSION

Thank you for using the City Bookshop Management System!

This manual should help you:
- ✅ Install and run the application
- ✅ Login and navigate the interface
- ✅ Use all features effectively
- ✅ Troubleshoot common issues
- ✅ Manage your bookshop efficiently

For best results:
- Read this manual thoroughly before use
- Practice with sample data first
- Backup data regularly
- Follow security best practices

**Happy Managing!**

---

**End of User Manual**
