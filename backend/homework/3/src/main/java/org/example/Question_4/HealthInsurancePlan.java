package org.example.Question_4;

public abstract class HealthInsurancePlan {
    private double coverage;
    private InsuranceBrand offeredBy;

    // Abstract method for computing the monthly premium
    public abstract double computeMonthlyPremium(double salary, int age, boolean smokes);

    // Getter method for coverage
    public double getCoverage() {
        return coverage;
    }

    // Setter method for coverage
    public void setCoverage(double coverage) {
        this.coverage = coverage;
    }

    // Getter method for the insurance brand offering the plan
    public InsuranceBrand getOfferedBy() {
        return offeredBy;
    }

    // Setter method for the insurance brand offering the plan
    public void setOfferedBy(InsuranceBrand offeredBy) {
        this.offeredBy = offeredBy;
    }
}
