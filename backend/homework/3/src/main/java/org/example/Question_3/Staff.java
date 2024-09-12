package org.example.Question_3;

/**
 * The Staff class represents staff members in a Hospital Management System.
 * It extends the User class and includes specific details for staff members.
 */
public class Staff extends User {

    private long staffId;
    private int yearsOfExperience;
    private String description;
    private double salary;

    /**
     * Getter method for retrieving the staff ID.
     *
     * @return The staff ID.
     */
    public long getStaffId() {
        return staffId;
    }

    /**
     * Setter method for setting the staff ID.
     *
     * @param staffId The staff ID to set.
     */
    public void setStaffId(long staffId) {
        this.staffId = staffId;
    }

    /**
     * Getter method for retrieving the years of experience.
     *
     * @return The years of experience.
     */
    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    /**
     * Setter method for setting the years of experience.
     *
     * @param yearsOfExperience The years of experience to set.
     */
    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    /**
     * Getter method for retrieving the staff description.
     *
     * @return The staff description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter method for setting the staff description.
     *
     * @param description The staff description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter method for retrieving the staff salary.
     *
     * @return The staff salary.
     */
    public double getSalary() {
        return salary;
    }

    /**
     * Setter method for setting the staff salary.
     *
     * @param salary The staff salary to set.
     */
    public void setSalary(double salary) {
        this.salary = salary;
    }
}
