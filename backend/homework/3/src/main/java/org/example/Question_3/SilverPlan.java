package org.example.Question_3;
/**
 * The SilverPlan class represents a health insurance plan with specific coverage.
 */
public abstract class SilverPlan extends HealthInsurancePlan {

    /**
     * Constructs a SilverPlan instance with a coverage of 0.7.
     */
    public SilverPlan() {
        setCoverage(0.7);
    }

    /**
     * Computes the monthly premium for a staff member based on the salary.
     *
     * @param salary The monthly salary of the staff member.
     * @return The computed monthly premium.
     */
    @Override
    public double computeMonthlyPremium(double salary) {
        return 0.06 * salary;
    }
}