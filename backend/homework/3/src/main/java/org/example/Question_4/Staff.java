package org.example.Question_4;

/**
 * The Staff class represents a staff member in a Hospital Management System.
 * It extends the User class, inheriting general user attributes.
 */
public class Staff extends User {

    /** The unique identifier for the staff member. */
    private long staffId;

    /** The number of years of experience of the staff member. */
    private int yearsOfExperience;

    /** A brief description or role of the staff member. */
    private String description;

    /** The salary of the staff member. */
    private double salary;

    /**
     * Gets the staff member's unique identifier.
     *
     * @return The staff member's unique identifier.
     */
    public long getStaffId() {
        return staffId;
    }

    /**
     * Sets the staff member's unique identifier.
     *
     * @param staffId The unique identifier to set.
     */
    public void setStaffId(long staffId) {
        this.staffId = staffId;
    }

    /**
     * Gets the number of years of experience of the staff member.
     *
     * @return The number of years of experience.
     */
    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    /**
     * Sets the number of years of experience of the staff member.
     *
     * @param yearsOfExperience The number of years of experience to set.
     */
    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    /**
     * Gets a brief description or role of the staff member.
     *
     * @return The description or role of the staff member.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets a brief description or role of the staff member.
     *
     * @param description The description or role to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the salary of the staff member.
     *
     * @return The salary of the staff member.
     */
    public double getSalary() {
        return salary;
    }

    /**
     * Sets the salary of the staff member.
     *
     * @param salary The salary to set.
     */
    public void setSalary(double salary) {
        this.salary = salary;
    }
}
