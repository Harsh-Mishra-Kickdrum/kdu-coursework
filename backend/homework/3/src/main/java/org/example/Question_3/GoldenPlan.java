package org.example.Question_3;
/**
 * The GoldenPlan class represents a health insurance plan with specific coverage.
 */
public abstract class GoldenPlan extends HealthInsurancePlan {

    /**
     * Constructs a GoldenPlan instance with a coverage of 0.8.
     */
    public GoldenPlan() {
        setCoverage(0.8);
    }

    /**
     * Computes the monthly premium for a staff member based on the salary.
     *
     * @param salary The monthly salary of the staff member.
     * @return The computed monthly premium.
     */
    @Override
    public double computeMonthlyPremium(double salary) {
        return 0.07 * salary;
    }
}
