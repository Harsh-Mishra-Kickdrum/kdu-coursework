package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Library {
    private List<Book> books = new ArrayList<>();
    private List<Author> authors = new ArrayList<>();
    private List<Patron> patrons = new ArrayList<>(); // Patron is a borrower
    private List<Book> checkedOutBooks = new ArrayList<>(); // each Book can have multiple copies

    /**
     * For handling the condition of multiple books with same name we are using the Hash Map
     */
    private Map<String, Integer> bookCountMap = new HashMap<>();

    /**
     * Finds all available books in the library.
     *
     * @return List of available books.
     */
    public List<Book> findAllAvailableBooks() {
        return books.stream()
                .filter(book -> bookCountMap.getOrDefault(book.getTitle(), 0) > 0)
                .collect(Collectors.toList());
    }

    /**
     * Checks out a book to a patron if it is available and the patron has not reached the checkout limit.
     *
     * @param book   The book to be checked out.
     * @param patron The patron checking out the book.

     */
    public void checkOutBook(Book book, Patron patron){
        if (bookCountMap.getOrDefault(book.getTitle(), 0) == 0) {
            Logging.getmsg().warn("No available copies of Book '{}'.", book.getTitle());

        }

        if (patron.getCheckedOutBooks().size() >= patron.getCheckoutLimit()) {
            Logging.getmsg().warn("Patron '{}' has reached the checkout limit.", patron.getName());

        }

        bookCountMap.put(book.getTitle(), bookCountMap.get(book.getTitle()) - 1);

        book.setCheckOut(true);
        book.incrementCheckoutCount();
        patron.getCheckedOutBooks().add(book);
        checkedOutBooks.add(book);

        Logging.getmsg().info("Book '{}' checked out by Patron '{}'.", book.getTitle(), patron.getName());
    }

    /**
     * Finds books written by a specific author.
     *
     * @param author The author whose books are to be found.
     * @return List of books by the specified author.
     */
    public List<Book> findBooksByAuthor(Author author) {
        return author.getBooks();
    }

    /**
     * Finds overdue books in the library.
     *
     * @return List of overdue books.
     */
    public List<Book> findOverdueBooks() {
        return checkedOutBooks.stream()
                .filter(book -> book.getDueDate().isBefore(LocalDate.now()))
                .collect(Collectors.toList());
    }

    /**
     * Finds the top N popular books in the library based on checkout count.
     *
     * @param topN Number of popular books to retrieve.
     * @return List of top N popular books.
     */
    public List<Book> findPopularBooks(int topN) {
        return books.stream()
                .sorted(Comparator.comparingInt(book -> -book.getCheckoutCount()))
                .limit(topN)
                .collect(Collectors.toList());
    }

    /**
     * Groups books in the library by their genres.
     *
     * @return Map of genre name to list of books.
     */
    public Map<String, List<Book>> groupBooksByGenre() {
        return books.stream()
                .collect(Collectors.groupingBy(Book::getGenre));
    }


}
