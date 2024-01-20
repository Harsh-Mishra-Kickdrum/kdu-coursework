package org.example;

import java.time.LocalDate;

/**
 * Class for creating the book with all its attribute
 */
public class Book {

    private String title;
    private String isbn;
    private boolean checkOut;

    private LocalDate dueDate;  // New property  For knowing the due date of the book
    private String genre; // New property For knowing the genre of the book
    private int checkoutCount;  // New property to track the number of checkouts
    //constructor
    public Book(String title, String isbn, boolean checkOut, LocalDate dueDate, String genre) {
        this.title = title;
        this.isbn = isbn;
        this.checkOut = checkOut;
        this.dueDate = dueDate;
        this.genre = genre;
        Logging.getmsg().info("Created a book successfully !" );
    }


    //getters and setters


    public int getCheckoutCount() {
        return checkoutCount;
    }

    /**
     * This increments the book count ,it is for tracking the no of times the book is being issued
     */
    public void incrementCheckoutCount() {
        this.checkoutCount = checkoutCount + 1 ;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public boolean isCheckOut() {
        return checkOut;
    }

    public void setCheckOut(boolean checkOut) {
        this.checkOut = checkOut;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
