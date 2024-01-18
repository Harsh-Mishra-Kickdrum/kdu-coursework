package org.example.Question_1and2;

/**
 * The User class represents a generic user in the Hospital Management System.
 */
public class User {

    private long id;
    private String firstName;
    private String lastName;
    private String gender;
    private String email;

    /**
     * Get the user ID.
     *
     * @return The user ID.
     */
    public long getId() {
        return id;
    }

    /**
     * Set the user ID.
     *
     * @param id The user ID to set.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Get the first name of the user.
     *
     * @return The first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set the first name of the user.
     *
     * @param firstName The first name to set.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get the last name of the user.
     *
     * @return The last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set the last name of the user.
     *
     * @param lastName The last name to set.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get the gender of the user.
     *
     * @return The gender.
     */
    public String getGender() {
        return gender;
    }

    /**
     * Set the gender of the user.
     *
     * @param gender The gender to set.
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Get the email of the user.
     *
     * @return The email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the email of the user.
     *
     * @param email The email to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }
}


