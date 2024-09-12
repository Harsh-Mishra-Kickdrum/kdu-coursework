package org.example.Question_3;
/**
 * The Nurse class represents a nurse in a Hospital Management System.
 * It extends the Staff class and includes a unique nurse ID.
 */
public class Nurse extends Staff {

    private long nurseId;

    /**
     * Getter method for retrieving the nurse ID.
     *
     * @return The nurse ID.
     */
    public long getNurseId() {
        return nurseId;
    }

    /**
     * Setter method for setting the nurse ID.
     *
     * @param nurseId The nurse ID to set.
     */
    public void setNurseId(long nurseId) {
        this.nurseId = nurseId;
    }
}
