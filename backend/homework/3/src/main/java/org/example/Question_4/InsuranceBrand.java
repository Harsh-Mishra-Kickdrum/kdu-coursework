package org.example.Question_4;

public interface InsuranceBrand {
    // Method to compute the monthly premium based on the health insurance plan, age, and smoking status
    public double computeMonthlyPremium(HealthInsurancePlan plan, int age, Boolean smoking);
}
