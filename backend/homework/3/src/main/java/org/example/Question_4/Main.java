package org.example.Question_4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Logback.class);

    public static void main(String[] args) {
        User staff = new User();

        // Creating instances of InsuranceBrand and HealthInsurancePlan
        InsuranceBrand insuranceBrand = new BlueCrossBlueShield();
        HealthInsurancePlan insurancePlan = new PlatinumPlan();

        // Setting the insurance brand for the health insurance plan
        insurancePlan.setOfferedBy(insuranceBrand);

        // Setting the insurance plan for the staff
        staff.setInsurancePlan(insurancePlan);

        // Computing and logging the monthly premium
        double ans = insurancePlan.computeMonthlyPremium(5000, 56, true);
        logger.info("{}", ans);
    }
}
