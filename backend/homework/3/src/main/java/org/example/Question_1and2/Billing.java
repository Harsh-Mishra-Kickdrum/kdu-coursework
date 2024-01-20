package org.example.Question_1and2;

/**
 * Represents the billing component for calculating payment amounts.
 */
public class Billing extends HealthInsurancePlan {

    /**
     * Computes the payment amounts for a patient based on the insurance plan and provided amount.
     *
     * @param patient The patient object.
     * @param amount  The amount billed before applying insurance.
     * @return A double array where the first element is the amount paid by the insurance company
     *         and the second element is the amount the patient has to pay.
     */
    public static double[] computePaymentAmount(Patient patient, double amount) {
        double[] payments = new double[2];

        HealthInsurancePlan patientInsurancePlan = patient.getInsurancePlan();

        if(patientInsurancePlan != null)
            payments = getAmount(amount, patientInsurancePlan);
        else {
            payments[1] = amount - 20;
            payments[0] = 0;
        }

        return payments;
    }

    /**
     * Calculates the insurance coverage amount and remaining amount based on the insurance plan.
     *
     * @param amount         The total billed amount.
     * @param patientPlan    The patient's insurance plan.
     * @return A double array where the first element is the insurance coverage amount
     *         and the second element is the remaining amount after applying discounts.
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
