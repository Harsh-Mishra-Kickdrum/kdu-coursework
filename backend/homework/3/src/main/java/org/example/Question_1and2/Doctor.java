package org.example.Question_1and2;

/**
 * The Doctor class represents a doctor in the Hospital Management System,
 * extending the Staff class.
 */
public class Doctor extends Staff {
    private long doctorId;
    private String specialization;

    /**
     * Get the doctor ID.
     *
     * @return The doctor ID.
     */
    public long getDoctorId() {
        return doctorId;
    }

    /**
     * Set the doctor ID.
     *
     * @param doctorId The doctor ID to set.
     */
    public void setDoctorId(long doctorId) {
        this.doctorId = doctorId;
    }

    /**
     * Get the specialization of the doctor.
     *
     * @return The specialization.
     */
    public String getSpecialization() {
        return specialization;
    }

    /**
     * Set the specialization of the doctor.
     *
     * @param specialization The specialization to set.
     */
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

}
