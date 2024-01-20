package org.example.Question_3;

/**
 * The HealthInsurancePlan class represents an abstract health insurance plan with coverage information.
 */
public abstract class HealthInsurancePlan {

    private double coverage;
    private InsuranceBrand offeredBy;

    /**
     * Abstract method to compute the monthly premium for a staff member based on the salary.
     *
     * @param salary The monthly salary of the staff member.
     * @return The computed monthly premium.
     */
    public abstract double computeMonthlyPremium(double salary);

    /**
     * Getter method for the coverage.
     *
     * @return The coverage of the health insurance plan.
     */
    public double getCoverage() {
        return coverage;
    }

    /**
     * Setter method for the coverage.
     *
     * @param coverage The coverage to set for the health insurance plan.
     */
    public void setCoverage(double coverage) {
        this.coverage = coverage;
    }

    /**
     * Getter method for the insurance brand.
     *
     * @return The insurance brand offering the health insurance plan.
     */
    public InsuranceBrand getOfferedBy() {
        return offeredBy;
    }

    /**
     * Setter method for the insurance brand.
     *
     * @param offeredBy The insurance brand to set for the health insurance plan.
     */
    public void setOfferedBy(InsuranceBrand offeredBy) {
        this.offeredBy = offeredBy;
    }
}
