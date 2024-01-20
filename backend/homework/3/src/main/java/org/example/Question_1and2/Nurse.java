package org.example.Question_1and2;

/**
 * The Nurse class represents a nurse in the Hospital Management System,
 * extending the Staff class.
 */
public class Nurse extends Staff {
    private long nurseId;

    /**
     * Get the nurse ID.
     *
     * @return The nurse ID.
     */
    public long getNurseId() {
        return nurseId;
    }

    /**
     * Set the nurse ID.
     *
     * @param nurseId The nurse ID to set.
     */
    public void setNurseId(long nurseId) {
        this.nurseId = nurseId;
    }
}

