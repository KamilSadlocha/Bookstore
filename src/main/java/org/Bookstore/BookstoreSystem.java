package org.Bookstore;

import static org.Bookstore.UserType.ADMIN;
import static org.Bookstore.UserType.CUSTOMER;

public class BookstoreSystem {


    private static Bookstore createSampleBookstore() {
        Bookstore bookstore = new Bookstore();

        User admin = new User("admin", ADMIN);
        User user1 = new User("Jan Mazurek", "Kraków, Krakowska 1", CUSTOMER);

        bookstore.addCustomer(user1);
        bookstore.addCustomer(admin);

        Category fantasyCategory = new Category("fantasy");
        Category novelCategory = new Category("novel");
        Category scienceCategory = new Category("science");

        bookstore.addCategory(fantasyCategory);
        bookstore.addCategory(novelCategory);
        bookstore.addCategory(scienceCategory);

        Book book1 = new Book("Witcher", "A. Sapkowski", 1994, 25.99, 5, fantasyCategory);
        Book book2 = new Book("LotR", "J.R.R. Tolkien", 1954, 39.99, 8, fantasyCategory);
        Book book3 = new Book("O psie, który jeździł koleja", "Roman Pisarski", 1967, 9.99, 3, novelCategory);
        Book book4 = new Book("Harry Potter and the Chamber of Secrets", "J.K. Rowling", 1998, 22.22, 6, fantasyCategory);
        Book book5 = new Book("Nauka. To lubię", "T.Rożek", 2023, 28.99, 10, novelCategory);

        bookstore.addBook(book1);
        bookstore.addBook(book2);
        bookstore.addBook(book3);
        bookstore.addBook(book4);
        bookstore.addBook(book5);

        return bookstore;
    }


    public static void main(String[] args) {

        Bookstore bookstore = createSampleBookstore();

        ConsoleBookstoreInterface consoleInterface = new ConsoleBookstoreInterface(bookstore);
        consoleInterface.start();
    }
}
