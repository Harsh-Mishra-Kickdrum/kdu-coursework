package question_4;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SetDemo class demonstrates the use of TreeSets with different comparators.
 */
public class SetDemo {

    private static final Logger logger = LoggerFactory.getLogger(SetDemo.class);

    /**
     * Demonstrates the usage of TreeSet with different comparators.
     *
     * @param comparator The comparator for sorting. If null, natural ordering is used.
     * @return Set of books.
     */
    public static Set<Book> treeSetDemo(Comparator<Book> comparator) {
        Book book1 = new Book("Harry Potter", "J.K.Rowling", 1997);
        Book book2 = new Book("Harry Potter", "J.K.Rowling", 1997);
        Book book3 = new Book("Walden", "Henry David Thoreau", 1854);
        Book book4 = new Book("Effective Java", "Joshua Bloch", 2008);
        Book book5 = new Book("The Last Lecture", "Randy Pausch", 2008);

        Set<Book> books = new TreeSet<>(comparator);
        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        books.add(book5);

        return books;
    }
}
