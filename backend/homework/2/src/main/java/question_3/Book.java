package question_3;

/**
 * Represents a Book with various attributes.
 */
public class Book {
    private String title;
    private String author;
    private int publicationYear;
    private double averageRating;
    private int ratingsCount;
    private String imageUrl;

    /**
     * Getter for the author of the book.
     * @return The author of the book.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Setter for the author of the book.
     * @param author The author to set.
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Getter for the average rating of the book.
     * @return The average rating of the book.
     */
    public double getAverageRating() {
        return averageRating;
    }

    /**
     * Setter for the average rating of the book.
     * @param averageRating The average rating to set.
     */
    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    /**
     * Getter for the ratings count of the book.
     * @return The ratings count of the book.
     */
    public int getRatingsCount() {
        return ratingsCount;
    }

    /**
     * Setter for the ratings count of the book.
     * @param ratingsCount The ratings count to set.
     */
    public void setRatingsCount(int ratingsCount) {
        this.ratingsCount = ratingsCount;
    }

    /**
     * Getter for the title of the book.
     * @return The title of the book.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter for the title of the book.
     * @param title The title to set.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter for the publication year of the book.
     * @return The publication year of the book.
     */
    public int getPublicationYear() {
        return publicationYear;
    }

    /**
     * Setter for the publication year of the book.
     * @param publicationYear The publication year to set.
     */
    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    /**
     * Getter for the image URL of the book.
     * @return The image URL of the book.
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Setter for the image URL of the book.
     * @param imageUrl The image URL to set.
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
