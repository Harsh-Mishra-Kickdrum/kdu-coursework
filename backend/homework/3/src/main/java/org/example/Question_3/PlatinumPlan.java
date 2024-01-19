package org.example.Question_3;

/**
 * The PlatinumPlan class represents a health insurance plan with specific coverage.
 */
public abstract class PlatinumPlan extends HealthInsurancePlan {

    /**
     * Constructs a PlatinumPlan instance with a coverage of 0.9.
     */
    public PlatinumPlan() {
        setCoverage(0.9);
    }

    /**
     * Computes the monthly premium for a staff member based on the salary.
     *
     * @param salary The monthly salary of the staff member.
     * @return The computed monthly premium.
     */
    @Override
    public double computeMonthlyPremium(double salary) {
        return 0.08 * salary;
    }
}
