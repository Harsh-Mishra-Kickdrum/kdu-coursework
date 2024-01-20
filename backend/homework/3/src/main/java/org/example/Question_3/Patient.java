package org.example.Question_3;
/**
 * The Patient class represents a patient in a Hospital Management System.
 * It extends the User class and includes a unique patient ID.
 */
class Patient extends User {

    private long patientId;

    /**
     * Getter method for retrieving the patient ID.
     *
     * @return The patient ID.
     */
    public long getPatientId() {
        return patientId;
    }

    /**
     * Setter method for setting the patient ID.
     *
     * @param patientId The patient ID to set.
     */
    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }
}
