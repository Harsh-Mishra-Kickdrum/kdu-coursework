package org.example;

public enum BookGenre {
    FICTION("Fiction", 10),
    MYSTERY("Mystery", 15),
    SCIENCE_FICTION("Science Fiction", 12),
    HISTORY("History", 20);

    private final String genreName;
    private int availableBooksCount;

    // Constructor to initialize genre name and available books count
    BookGenre(String genreName, int initialCount) {
        this.genreName = genreName;
        this.availableBooksCount = initialCount;
    }

    // Method to get the genre name
    public String getGenreName() {
        return genreName;
    }

    // Method to update the count of available books after checkout
    public void checkoutBook() {
        if (availableBooksCount > 0) {
            availableBooksCount--;
            Logging.getmsg().info("Checked out a book from genre: " + genreName +
                    ". Available books count: " + availableBooksCount);
        } else {
            Logging.getmsg().info("No books available for genre: " + genreName);
        }
    }

    // Method to get the count of available books for a specific genre
    public int getAvailableBooksCount() {
        return availableBooksCount;
    }
}
