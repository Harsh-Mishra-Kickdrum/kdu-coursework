package org.example.Question_1and2;

/**
 * Represents a health insurance plan with a coverage percentage.
 */
class HealthInsurancePlan {
    private double coverage;
    private HealthInsurancePlan insurancePlan;
    private InsuranceBrand offeredBy;

    /**
     * Gets the coverage percentage of the insurance plan.
     *
     * @return The coverage percentage.
     */
    public double getCoverage() {
        return coverage;
    }

    /**
     * Sets the coverage percentage of the insurance plan.
     *
     * @param coverage The coverage percentage to set.
     */
    public void setCoverage(double coverage) {
        this.coverage = coverage;
    }

    /**
     * Sets the patient's insurance plan.
     *
     * @param insurancePlan The insurance plan to set.
     */
    public void setInsurancePlan(HealthInsurancePlan insurancePlan) {
        this.insurancePlan = insurancePlan;
    }

    /**
     * Gets the company that offers the insurance plan.
     *
     * @return The company offering the insurance plan.
     */
    public InsuranceBrand getOfferedBy() {
        return offeredBy;
    }

    /**
     * Sets the company that offers the insurance plan.
     *
     * @param offeredBy The company offering the insurance plan.
     */
    public void setOfferedBy(InsuranceBrand offeredBy) {
        this.offeredBy = offeredBy;
    }

    /**
     * Gets the patient's insurance plan.
     *
     * @return The patient's insurance plan.
     */
    public HealthInsurancePlan getInsurancePlan() {
        return insurancePlan;
    }
}
