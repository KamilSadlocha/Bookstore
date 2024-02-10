package org.Bookstore;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Setter
@Getter
class Bookstore {
    private List<Book> books;
    private List<Category> categories;
    private List<User> customers;
    private List<Order> orders;
    private int orderIdCounter;

    public Bookstore() {
        this.books = new ArrayList<>();
        this.categories = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.orders = new ArrayList<>();
        this.orderIdCounter = 1;
    }

    public void addCustomer(User user) {
        customers.add(user);
        System.out.println("Customer added to the system.");
    }

    public void addOrder(Order order) {
        order.setOrderId(orderIdCounter++);
        orders.add(order);
        System.out.println("Order placed successfully.");
    }

    public void addCategory(Category category) {
        categories.add(category);
        System.out.println("Category added to the system.");
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added to the inventory.");
    }

    public List<Order> getOrdersByUser(UUID customerId) {
        List<Order> userOrders = new ArrayList<>();
        for (Order order : orders) {
            if (order.getCustomerId().equals(customerId)) {
                userOrders.add(order);
            }
        }
        return userOrders;
    }

    public void editBook(Book book, double newPrice, int newAvailableCopies) {
        if (newPrice <= 0 || newAvailableCopies < 0) {
            System.out.println("Invalid values for price or available copies. Cannot update book details.");
            return;
        }

        book.setPrice(newPrice);
        book.setAvailableCopies(newAvailableCopies);
        System.out.println("Book details updated.");
    }

    public void sellBook(Book book, UUID customerId) {

        if (book.getAvailableCopies() > 0) {
            book.sellCopy();
            Order order = new Order(book, OrderType.BUY, customerId);
            addOrder(order);
        } else {
            System.out.println("Book out of stock.");
        }
    }

    public void reserveBook(Book book, UUID customerId) {

        if (book.getAvailableCopies() > 0) {
            book.reserveCopy();
            Order order = new Order(book, OrderType.RESERVE, customerId);
            addOrder(order);
        } else {
            System.out.println("Book out of stock for reservation.");
        }
    }

    public void removeBook(Book book) {
        books.remove(book);
        System.out.println("Book removed from the inventory.");
    }

    public Category findCategoryByName(String categoryName) {
        for (Category category : categories) {
            if (category.getName().equals(categoryName)) {
                return category;
            }
        }
        return null;
    }

}
