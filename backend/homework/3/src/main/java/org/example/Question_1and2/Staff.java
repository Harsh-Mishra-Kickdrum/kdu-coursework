package org.example.Question_1and2;

/**
 * The Staff class represents a staff member in the Hospital Management System,
 * extending the User class.
 */
public class Staff extends User {
    private long staffId;
    private int yearsOfExperience;
    private String description;
    private double salary;

    /**
     * Get the staff ID.
     *
     * @return The staff ID.
     */
    public long getStaffId() {
        return staffId;
    }

    /**
     * Set the staff ID.
     *
     * @param staffId The staff ID to set.
     */
    public void setStaffId(long staffId) {
        this.staffId = staffId;
    }

    /**
     * Get the years of experience of the staff.
     *
     * @return The years of experience.
     */
    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    /**
     * Set the years of experience of the staff.
     *
     * @param yearsOfExperience The years of experience to set.
     */
    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    /**
     * Get the description of the staff.
     *
     * @return The description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description of the staff.
     *
     * @param description The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the salary of the staff.
     *
     * @return The salary.
     */
    public double getSalary() {
        return salary;
    }

    /**
     * Set the salary of the staff.
     *
     * @param salary The salary to set.
     */
    public void setSalary(double salary) {
        this.salary = salary;
    }
}

