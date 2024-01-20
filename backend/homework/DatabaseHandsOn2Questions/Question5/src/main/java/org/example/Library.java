package org.example;


public class Library {
    public static void main(String[] args) {
        // Example usage of BookGenre enum
        BookGenre fictionGenre = BookGenre.FICTION;
        Logging.getmsg().info("Genre Name: {} " , fictionGenre.getGenreName());
        Logging.getmsg().info("Available Books Count: {} " , fictionGenre.getAvailableBooksCount());

        // Check out a book
        fictionGenre.checkoutBook();
        Logging.getmsg().info("After checkout, Available Books Count: {}  " , fictionGenre.getAvailableBooksCount());
    }
}
