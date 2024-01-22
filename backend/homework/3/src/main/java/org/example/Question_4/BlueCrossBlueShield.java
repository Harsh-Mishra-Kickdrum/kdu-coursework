package org.example.Question_4;

/**
 * The BlueCrossBlueShield class implements the InsuranceBrand interface
 * and provides a method to compute the monthly premium based on the health insurance plan, age, and smoking status.
 */
public class BlueCrossBlueShield implements InsuranceBrand {

    /**
     * Computes the monthly premium for a given health insurance plan, age, and smoking status.
     *
     * @param plan   The health insurance plan for which the premium is computed.
     * @param age    The age of the individual.
     * @param smokes A boolean indicating whether the individual smokes or not.
     * @return The computed monthly premium based on the specified parameters.
     */
    public double computeMonthlyPremium(HealthInsurancePlan plan, int age, boolean smokes) {

        double premium = 0;

        if (plan instanceof PlatinumPlan) {
            if (age > 55)
                premium += 200;
            if (smokes)
                premium += 100;
        } else if (plan instanceof GoldenPlan) {
            if (age > 55)
                premium += 150;
            if (smokes)
                premium += 90;
        } else if (plan instanceof SilverPlan) {
            if (age > 55)
                premium += 100;
            if (smokes)
                premium += 80;
        } else if (plan instanceof BronzePlan) {
            if (age > 55)
                premium += 50;
            if (smokes)
                premium += 70;
        }

        return premium;
    }

    @Override
    public double computeMonthlyPremium(HealthInsurancePlan plan, int age, Boolean smoking) {
        return 0;
    }
}
