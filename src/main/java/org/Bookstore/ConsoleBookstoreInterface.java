package org.Bookstore;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ConsoleBookstoreInterface {
    private final Bookstore bookstore;
    private User currentUser;

    public ConsoleBookstoreInterface(Bookstore bookstore) {
        this.bookstore = bookstore;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to our Bookstore!");

            displayBooks();

            System.out.println("\n1. Go Shopping");
            System.out.println("2. Exit");
            System.out.println("0. Admin Panel");

            int roleChoice = scanner.nextInt();

            switch (roleChoice) {
                case 0:
                    authenticateAdmin(scanner);
                    adminMenu(scanner);
                    break;
                case 1:
                    createCustomer(scanner);
                    customerMenu(scanner);
                    break;
                case 2:
                    System.out.println("Exiting the program. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    // ADMIN

    private void authenticateAdmin(Scanner scanner) {
        System.out.print("Enter admin PIN: ");
        int adminPassword = scanner.nextInt();

        if (adminPassword == 111) {
            System.out.println("Admin authentication successful.");
        } else {
            System.out.println("Incorrect password. Exiting the program. Goodbye!");
            System.exit(0);
        }
    }

    private void adminMenu(Scanner scanner) {
        boolean logout = false;

        while (!logout) {
            try {
                displayAdminMenu();
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        addBook(scanner);
                        break;
                    case 2:
                        editBook(scanner);
                        break;
                    case 3:
                        removeBook(scanner);
                        break;
                    case 4:
                        addCategory(scanner);
                        break;
                    case 5:
                        generateReport();
                        break;
                    case 6:
                        System.out.println("Logging out. Returning to the main menu.");
                        logout = true;
                        break;
                    case 7:
                        System.out.println("Exiting the program. Goodbye!");
                        System.exit(0);

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
            }
        }
    }

    private void displayAdminMenu() {
        System.out.println("\nAdmin Menu:");
        System.out.println("1. Add a Book");
        System.out.println("2. Edit a Book");
        System.out.println("3. Remove a Book");
        System.out.println("4. Add a Category");
        System.out.println("5. Generate Report");
        System.out.println("6. Logout");
        System.out.println("7. Exit");
        System.out.print("Enter your choice: ");
    }


    private void addBook(Scanner scanner) {
        try {
            System.out.print("Enter the title: ");
            String title = scanner.next();
            title += scanner.nextLine();

            System.out.print("Enter the author: ");
            String author = scanner.next();
            author += scanner.nextLine();

            System.out.print("Enter the publication year: ");
            int publicationYear = scanner.nextInt();

            System.out.print("Enter the price [$$,¢¢]: ");
            double price = scanner.nextDouble();

            System.out.print("Enter the number of available copies: ");
            int availableCopies = scanner.nextInt();

            System.out.print("Enter the category name: ");
            String categoryName = scanner.next();
            categoryName += scanner.nextLine();

            Category category = bookstore.findCategoryByName(categoryName.toLowerCase());

            if (category == null) {
                System.out.println("Category not found. You must create category first. Book not added.");
                return;
            }

            Book newBook = new Book(title, author, publicationYear, price, availableCopies, category);
            bookstore.addBook(newBook);
            System.out.println("Book added successfully.");

        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter valid data.");
            scanner.nextLine();
        }
    }

    private void editBook(Scanner scanner) {
        displayBooks();
        try {
            System.out.print("Enter the number of the book you want to edit: ");
            int bookNumber = scanner.nextInt();

            if (bookNumber > 0 && bookNumber <= bookstore.getBooks().size()) {
                Book selectedBook = bookstore.getBooks().get(bookNumber - 1);

                System.out.print("Enter the new price [$$,¢¢]: ");
                double newPrice = scanner.nextDouble();

                System.out.print("Enter the new number of available copies: ");
                int newAvailableCopies = scanner.nextInt();

                bookstore.editBook(selectedBook, newPrice, newAvailableCopies);
            } else {
                System.out.println("Invalid book number. Please enter a valid number.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter valid data.");
            scanner.nextLine();
        }
    }

    private void removeBook(Scanner scanner) {
        displayBooks();
        try {
            System.out.print("Enter the number of the book you want to remove: ");
            int bookNumber = scanner.nextInt();

            if (bookNumber > 0 && bookNumber <= bookstore.getBooks().size()) {
                Book selectedBook = bookstore.getBooks().get(bookNumber - 1);
                bookstore.removeBook(selectedBook);
                System.out.println("Book removed successfully.");
            } else {
                System.out.println("Invalid book number. Please enter a valid number.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter valid data.");
            scanner.nextLine();
        }
    }

    private void addCategory(Scanner scanner) {
        System.out.print("Enter the category name: ");
        String categoryName = scanner.next();
        categoryName += scanner.nextLine();

        Category newCategory = new Category(categoryName.toLowerCase());
        bookstore.addCategory(newCategory);

        System.out.println("Category added successfully.");
    }

    private void generateReport() {
        System.out.println("Bookstore Report:");

        System.out.println("\nBooks: ");
        for (Book book : bookstore.getBooks()) {
            System.out.println("Title: " + book.getTitle() + ", Author: " + book.getAuthor() +
                    ", Price: " + book.getPrice() + ", Available Copies: " + book.getAvailableCopies());
        }

        System.out.println("\nCategories: ");
        for (Category category : bookstore.getCategories()) {
            System.out.println("Name: " + category.getName());
        }

        System.out.println("\nCustomers: ");
        for (User customer : bookstore.getCustomers()) {
            System.out.println("Name: " + customer.getUserName() + ", Type: " + customer.getUserType());
        }

        System.out.println("\nOrders: ");
        for (Order order : bookstore.getOrders()) {
            System.out.println("Title: " + order.getBook().getTitle() +
                    ", Author: " + order.getBook().getAuthor() +
                    ", Type: " + order.getOrderType() +
                    ", Customer ID: " + order.getCustomerId() +
                    ", Date: " + order.getDate());
        }
    }

    // CUSTOMER

    private void createCustomer(Scanner scanner) {
        System.out.print("Enter your name: ");
        String customerName = scanner.next();

        customerName += scanner.nextLine();
        System.out.print("Enter your address: ");
        String customerAddress = scanner.next();
        customerAddress = customerAddress + scanner.nextLine();


        currentUser = new User(customerName, customerAddress, UserType.CUSTOMER);
        bookstore.addCustomer(currentUser);
        System.out.println("WELCOME!");

    }

    private void customerMenu(Scanner scanner) {
        boolean logout = false;

        while (!logout) {
            try {
                displayCustomerMenu();
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        reserveOrBuyBook(scanner);
                        break;
                    case 2:
                        viewPurchases();
                        break;
                    case 3:
                        System.out.println("Logging out. Returning to the main menu.");
                        logout = true;
                        break;
                    case 4:
                        System.out.println("Exiting the program. Goodbye!");
                        System.exit(0);

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
            }
        }
    }

    private void displayCustomerMenu() {
        System.out.println("\nCustomer Menu:");
        System.out.println("1. Reserve/Buy a Book");
        System.out.println("2. View Purchases");
        System.out.println("3. Logout");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    private void viewPurchases() {
        System.out.println("\nYour Purchases:");

        List<Order> userOrders = bookstore.getOrdersByUser(currentUser.getUserId());

        if (userOrders.isEmpty()) {
            System.out.println("No purchases made.");
        } else {
            for (Order order : userOrders) {
                System.out.println("Title: " + order.getBook().getTitle() +
                        ", Author: " + order.getBook().getAuthor() +
                        ", Type: " + order.getOrderType() +
                        ", Date: " + order.getDate());
            }
        }
    }

    private void reserveOrBuyBook(Scanner scanner) {
        displayBooks();
        try {
            System.out.print("Enter the number of the book you want to reserve/buy: ");
            int bookNumber = scanner.nextInt();

            if (bookNumber > 0 && bookNumber <= bookstore.getBooks().size()) {
                Book selectedBook = bookstore.getBooks().get(bookNumber - 1);

                System.out.println("1. Buy");
                System.out.println("2. Reserve");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        bookstore.sellBook(selectedBook, currentUser.getUserId());
                        break;
                    case 2:
                        bookstore.reserveBook(selectedBook, currentUser.getUserId());
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } else {
                System.out.println("Invalid book number. Please enter a valid number.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.nextLine();
        }
    }

    // DISPLAY
    private void displayBooks() {
        System.out.println("\nAvailable Books:");
        if (bookstore.getBooks().isEmpty()) {
            System.out.println("No books available.");
        } else {
            for (int i = 0; i < bookstore.getBooks().size(); i++) {
                Book book = bookstore.getBooks().get(i);
                System.out.println((i + 1) + ". " + book.getTitle() + " by " + book.getAuthor() + " - Price: $" + book.getPrice());
            }
        }
    }

}
