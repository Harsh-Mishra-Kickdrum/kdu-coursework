package org.example;

import java.util.List;

public class Author {
    private String name;
    private List<Book> books;

    // Constructors
    public Author(String name, List<Book> books) {
        this.name = name;
        this.books = books;
        Logging.getmsg().info("Created a author successfully !" );
    }

    //getters and setters


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
