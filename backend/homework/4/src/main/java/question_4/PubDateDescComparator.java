package question_4;

import java.util.Comparator;

/**
 * Comparator for sorting books based on publication year in descending order.
 * If publication years are the same, titles are sorted in ascending order.
 */
public class PubDateDescComparator implements Comparator<Book> {

    /**
     * Compares two books based on publication year and title in descending order.
     *
     * @param book1 First book.
     * @param book2 Second book.
     * @return Comparison result.
     */
    public int compare(Book book1, Book book2) {
        int compareValue = Integer.compare(book2.getYear(), book1.getYear());

        if (compareValue == 0)
            return book1.getTitle().compareTo(book2.getTitle());

        return compareValue;
    }
}

