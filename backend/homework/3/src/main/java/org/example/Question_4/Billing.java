package org.example.Question_4;

/**
 * The Billing class handles the computation of payment amounts for a patient based on their insurance plan.
 */
class Billing {

    /**
     * Computes the payment amounts for a patient based on their insurance plan and the total amount.
     *
     * @param patient The patient for whom the payment is computed.
     * @param amount  The total amount to be paid.
     * @return An array containing two elements - the first element is the insurance-covered amount, and the second element is the remaining amount to be paid.
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
     * Computes the insurance-covered amount and the remaining amount based on the patient's insurance plan.
     *
     * @param amount      The total amount to be paid.
     * @param patientPlan The patient's health insurance plan.
     * @return An array containing two elements - the first element is the insurance-covered amount, and the second element is the remaining amount to be paid.
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
