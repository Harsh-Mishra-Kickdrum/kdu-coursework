package org.example.Question_3;
/**
 * The User class represents a user in a system, including basic information and insurance details.
 */
public class User {

    private long id;
    private String firstName;
    private String lastName;
    private String gender;
    private String email;
    private boolean insured;
    private HealthInsurancePlan insurancePlan;

    /**
     * Getter method for retrieving the user ID.
     *
     * @return The user ID.
     */
    public long getId() {
        return id;
    }

    /**
     * Setter method for setting the user ID.
     *
     * @param id The user ID to set.
     */
    public void setId(long id) {
        this.id = id;
    }

    // Similar JavaDoc comments can be added for other getter and setter methods...

    /**
     * Getter method for retrieving the user's health insurance plan.
     *
     * @return The health insurance plan.
     */
    public HealthInsurancePlan getInsurancePlan() {
        return insurancePlan;
    }

    /**
     * Setter method for setting the user's health insurance plan.
     *
     * @param insurancePlan The health insurance plan to set.
     */
    public void setInsurancePlan(HealthInsurancePlan insurancePlan) {
        this.insurancePlan = insurancePlan;
    }
}
