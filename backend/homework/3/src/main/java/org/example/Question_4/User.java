package org.example.Question_4;

/**
 * The User class represents a user in a Hospital Management System.
 * It includes basic information about the user and their insurance details.
 */
public class User {

    /** The unique identifier for the user. */
    private long id;

    /** The first name of the user. */
    private String firstName;

    /** The last name of the user. */
    private String lastName;

    /** The gender of the user. */
    private String gender;

    /** The email address of the user. */
    private String email;

    /** A boolean indicating whether the user is insured. */
    private boolean insured;

    /** The health insurance plan associated with the user. */
    private HealthInsurancePlan insurancePlan;

    /**
     * Gets the unique identifier of the user.
     *
     * @return The unique identifier.
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the user.
     *
     * @param id The unique identifier to set.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets the first name of the user.
     *
     * @return The first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the user.
     *
     * @param firstName The first name to set.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the user.
     *
     * @return The last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the user.
     *
     * @param lastName The last name to set.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the gender of the user.
     *
     * @return The gender.
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the gender of the user.
     *
     * @param gender The gender to set.
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Gets the email address of the user.
     *
     * @return The email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the user.
     *
     * @param email The email address to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Checks if the user is insured.
     *
     * @return True if insured, false otherwise.
     */
    public boolean isInsured() {
        return insured;
    }

    /**
     * Sets whether the user is insured.
     *
     * @param insured A boolean indicating insurance status.
     */
    public void setInsured(boolean insured) {
        this.insured = insured;
    }

    /**
     * Gets the health insurance plan associated with the user.
     *
     * @return The health insurance plan.
     */
    public HealthInsurancePlan getInsurancePlan() {
        return insurancePlan;
    }

    /**
     * Sets the health insurance plan for the user.
     *
     * @param insurancePlan The health insurance plan to set.
     */
    public void setInsurancePlan(HealthInsurancePlan insurancePlan) {
        this.insurancePlan = insurancePlan;
    }
}
