package org.example;
public class Main {

    public static void main(String[] args) {
        BusinessService businessService = new BusinessService();

        try {
            // MethodA: Log INFO and handle exceptions
            businessService.methodA();
        } catch (Exception throwable) {
            // Handle exceptions if needed
        }

        try {
            // MethodB: Log DEBUG
            businessService.methodB();
        } catch (Exception throwable) {
            // Handle exceptions if needed
        }

        // MethodC: No annotations
        businessService.methodC();
    }
}
