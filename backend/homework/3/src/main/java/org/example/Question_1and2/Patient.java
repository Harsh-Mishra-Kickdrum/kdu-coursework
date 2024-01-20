package org.example.Question_1and2;
/**
 * The Patient class represents a patient in the Hospital Management System,
 * extending the User class.
 */
class Patient extends User {
    private long patientId;
    private boolean insured;
    private HealthInsurancePlan insurancePlan;

    /**
     * Get the patient ID.
     *
     * @return The patient ID.
     */
    public long getPatientId() {
        return patientId;
    }

    /**
     * Set the patient ID.
     *
     * @param patientId The patient ID to set.
     */
    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }

    /**
     * Check if the patient is insured.
     *
     * @return True if insured, false otherwise.
     */
    public boolean isInsured() {
        return insured;
    }

    /**
     * Set the insured status of the patient.
     *
     * @param insured The insured status to set.
     */
    public void setInsured(boolean insured) {
        this.insured = insured;
    }

    /**
     * Get the health insurance plan of the patient.
     *
     * @return The health insurance plan.
     */
    public HealthInsurancePlan getInsurancePlan() {
        return insurancePlan;
    }

    /**
     * Set the health insurance plan of the patient.
     *
     * @param insurancePlan The health insurance plan to set.
     */
    public void setInsurancePlan(HealthInsurancePlan insurancePlan) {
        this.insurancePlan = insurancePlan;
    }
}
