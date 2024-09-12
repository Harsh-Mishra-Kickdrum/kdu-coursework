package org.example.Question_3;

/**
 * The Doctor class represents a doctor in a Hospital Management System.
 * It includes details such as doctor ID and specialization.
 */
public class Doctor {

    private long doctorId;
    private String specialization;

    /**
     * Getter method for retrieving the doctor ID.
     *
     * @return The doctor ID.
     */
    public long getDoctorId() {
        return doctorId;
    }

    /**
     * Setter method for setting the doctor ID.
     *
     * @param doctorId The doctor ID to set.
     */
    public void setDoctorId(long doctorId) {
        this.doctorId = doctorId;
    }

    /**
     * Getter method for retrieving the specialization.
     *
     * @return The specialization.
     */
    public String getSpecialization() {
        return specialization;
    }

    /**
     * Setter method for setting the specialization.
     *
     * @param specialization The specialization to set.
     */
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}

