package org.example.Question_4;

public abstract class BronzePlan extends HealthInsurancePlan {

    // Constructor sets the coverage to 70%
    public BronzePlan() {
        setCoverage(0.7);
    }

    // Override computeMonthlyPremium method
    @Override
    public double computeMonthlyPremium(double salary, int age, boolean smokes) {
        // Calculate premium based on the formula
        return 0.05 * salary + getOfferedBy().computeMonthlyPremium(this, age, smokes);
    }
}
