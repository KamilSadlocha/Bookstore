package org.Bookstore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class Book {
    private String title;
    private String author;
    private int publicationYear;
    private double price;
    private int availableCopies;
    private Category category;

    public Book(String title, String author, int publicationYear, double price, int availableCopies, Category category) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.price = price;
        this.availableCopies = availableCopies;
        this.category = category;
    }

    public void sellCopy() {
        if (availableCopies > 0) {
            availableCopies--;
            System.out.println("Book sold. Remaining copies: " + availableCopies);
        } else {
            System.out.println("Book out of stock.");
        }
    }

    public void reserveCopy() {
        if (availableCopies > 0) {
            availableCopies--;
            System.out.println("Book reserved. Remaining copies: " + availableCopies);
        } else {
            System.out.println("Book out of stock for reservation.");
        }
    }
}
