package org.example.Question_3;

/**
 * The Billing class provides methods for computing payment amounts for patients.
 */
class Billing {

    /**
     * Computes payment amounts based on the patient's insurance plan.
     *
     * @param patient The patient for whom the payment is computed.
     * @param amount  The total amount before applying insurance.
     * @return An array containing insurance and patient payment amounts.
     */
    public static double[] computePaymentAmount(Patient patient, double amount) {
        double[] payments = new double[2];

        HealthInsurancePlan patientInsurancePlan = patient.getInsurancePlan();

        if (patientInsurancePlan != null)
            payments = getAmount(amount, patientInsurancePlan);
        else {
            payments[1] = amount - 20;
            payments[0] = 0;
        }

        return payments;
    }

    /**
     * Computes insurance and patient payment amounts based on the insurance plan.
     *
     * @param amount        The total amount before applying insurance.
     * @param patientPlan   The patient's insurance plan.
     * @return An array containing insurance and patient payment amounts.
     */
    public static double[] getAmount(double amount, HealthInsurancePlan patientPlan) {

        double insuranceCoverage = patientPlan.getCoverage();
        double insuranceAmount = amount * insuranceCoverage;
        double remainingAmount = amount - insuranceAmount;

        if (patientPlan instanceof PlatinumPlan)
            remainingAmount -= 50;
        else if (patientPlan instanceof GoldenPlan)
            remainingAmount -= 40;
        else if (patientPlan instanceof BronzePlan)
            remainingAmount -= 30;
        else if (patientPlan instanceof SilverPlan)
            remainingAmount -= 25;
        else
            remainingAmount -= 20;

        return new double[]{insuranceAmount, remainingAmount};
    }
}
