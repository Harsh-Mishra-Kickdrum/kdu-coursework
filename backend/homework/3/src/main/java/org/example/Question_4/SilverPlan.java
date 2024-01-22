package org.example.Question_4;

public abstract class SilverPlan extends HealthInsurancePlan {
    // Set coverage to 0.7 for SilverPlan
    public SilverPlan() {

        setCoverage(0.7);
    }
    @Override

    public double computeMonthlyPremium(double salary,int age, boolean smokes) {
        return 0.06*salary+getOfferedBy().computeMonthlyPremium(this, age, smokes);
    }
}
