package org.example.Question_1and2;
/**
 * The Main class represents the entry point of the program.
 */
public class Main {

    /**
     * The main method where the program execution starts.
     *
     * @param args Command-line arguments passed to the program.
     */
    public static void main(String[] args) {

        // Creating a Golden health insurance plan instance
        HealthInsurancePlan insurancePlan = new GoldenPlan();

        // Creating a new patient
        Patient patient = new Patient();

        // Setting the health insurance plan for the patient
        patient.setInsurancePlan(insurancePlan);

        // Computing payment amounts for the patient
        double[] payments = Billing.computePaymentAmount(patient, 1000.0);

    }
}
