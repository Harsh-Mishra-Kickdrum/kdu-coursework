package org.example.Question_1;

import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * The StudentUtil class provides methods to calculate GPA and filter students based on GPA range.
 */
public class StudentUtil {

    private static final Logger LOGGER = Logger.getLogger(StudentUtil.class.getName());

    /**
     * Calculates the GPA for each student based on their grades.
     *
     * @param studentIdList   Array of student IDs.
     * @param studentsGrades  2D array representing grades of each student.
     * @return Array of GPAs for each student.
     */
    public static double[] calculateGPA(int[] studentIdList, char[][] studentsGrades) {
        if (studentIdList == null || studentsGrades == null || studentIdList.length != studentsGrades.length)
            return null;

        double[] gpas = new double[studentIdList.length];

        for (int i = 0; i < studentIdList.length; i++) {
            int totalScore = 0;
            int totalCourses = studentsGrades[i].length;

            for (char grade : studentsGrades[i]) {
                switch (grade) {
                    case 'A' -> totalScore += 4;
                    case 'B' -> totalScore += 3;
                    case 'C' -> totalScore += 2;
                }
            }

            gpas[i] = (double) totalScore / totalCourses;
        }
        return gpas;
    }

    /**
     * Filters students based on GPA range.
     *
     * @param lower           Lower bound of GPA range.
     * @param higher          Upper bound of GPA range.
     * @param studentIdList   Array of student IDs.
     * @param studentsGrades  2D array representing grades of each student.
     * @return Array of student IDs within the specified GPA range.
     */
    public static int[] getStudentsByGPA(double lower, double higher, int[] studentIdList, char[][] studentsGrades) {
        if (lower > higher || lower < 0 || higher < 0 || studentsGrades.length != studentIdList.length) {
            return null;
        }

        double[] gpaAvg = calculateGPA(studentIdList, studentsGrades);
        int count = 0;

        for (double val : gpaAvg) {
            if (val >= lower && val <= higher) {
                count++;
            }
        }

        int[] result = new int[count];
        int idx = 0;

        for (int i = 0; i < gpaAvg.length; i++) {
            if (gpaAvg[i] >= lower && gpaAvg[i] <= higher) {
                result[idx++] = studentIdList[i];
            }
        }
        return result;
    }


    /**
     * Main method to test the StudentUtil class.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        LOGGER.info("Enter the number of students (n): ");
        int n = scanner.nextInt();
        // Added an extra scanner.nextLine() to consume the newline character
        scanner.nextLine();

        int[] studentIdList = new int[n];

        LOGGER.info("Enter student IDs:");
        for (int i = 0; i < n; i++) {
            studentIdList[i] = scanner.nextInt();
        }

        // Added an extra scanner.nextLine() to consume the newline character
        scanner.nextLine();

        char[][] studentsGrades = new char[n][];

        LOGGER.info("Enter students' grades:");
        for (int i = 0; i < n; i++) {
            LOGGER.info("Student " + studentIdList[i] + ": ");
            String gradesInput = scanner.nextLine() ;
            studentsGrades[i] = gradesInput.toCharArray();
        }

        double[] gpas = calculateGPA(studentIdList, studentsGrades);
        LOGGER.info("GPAs: " + Arrays.toString(gpas));

        LOGGER.info("Enter the lower number: ");
        double lower = scanner.nextDouble();

        LOGGER.info("Enter the high number: ");
        double higher = scanner.nextDouble();

        int[] result = getStudentsByGPA(lower, higher, studentIdList, studentsGrades);
        LOGGER.info("Students with GPA between " + lower + " and " + higher + " is : " + Arrays.toString(result));
    }
}
