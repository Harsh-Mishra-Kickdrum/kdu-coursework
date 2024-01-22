package org.example.Question_4;

public class PlatinumPlan extends HealthInsurancePlan {

    public PlatinumPlan() {

        // Set coverage to 0.9 for PlatinumPlan
        setCoverage(0.9);
    }
    @Override
    public double computeMonthlyPremium(double salary,int age, boolean smokes) {
        return 0.08*salary+getOfferedBy().computeMonthlyPremium(this, age, smokes);
    }
}
