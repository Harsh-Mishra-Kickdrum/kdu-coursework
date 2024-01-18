package org.example.Question_4;

public abstract class GoldenPlan extends HealthInsurancePlan {
    public GoldenPlan() {
        // Setting the coverage for the GoldenPlan
        setCoverage(0.8);
    }

    // Overriding the computeMonthlyPremium method
    @Override
    public double computeMonthlyPremium(double salary, int age, boolean smokes) {
        // Computing the monthly premium based on salary and additional calculations
        return 0.07 * salary + getOfferedBy().computeMonthlyPremium(this, age, smokes);
    }
}
