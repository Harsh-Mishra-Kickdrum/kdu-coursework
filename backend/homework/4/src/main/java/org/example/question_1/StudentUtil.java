package org.example.question_1;

import org.example.question_1.exceptions.InvalidDataException;

/**
 * Represents a student with methods for GPA calculation and getting student by GPA.
 */
public class StudentUtil {
    /**
     * Calculates the GPA for each student based on the provided grades.
     *
     * @param studentIds    An array of student IDs.
     * @param studentGrades A 2D array representing the grades of each student.
     * @return An array of calculated GPAs for each student.
     * @throws MissingGradeException If a student has a missing grade.
     */
    public double[] calculateGPA(int[] studentIds, char[][] studentGrades) throws MissingGradeException {
        if (studentIds.length != studentGrades.length) {
           throw new IllegalArgumentException("studentIds & studentGrades are out-of-sync. studentIds.length: " + studentIds.length + ", studentGrades.length: " + studentGrades.length);
        }

        double[] gpaList = new double[studentGrades.length];

        for (int i = 0; i < studentGrades.length; i++) {
            double gpa = 0.0;

            for (int j = 0; j < studentGrades[i].length; j++) {
                if (studentGrades[i][j] == 'A') {
                    gpa += 4.0;
                } else if (studentGrades[i][j] == 'B') {
                    gpa += 3.0;
                } else if (studentGrades[i][j] == 'C') {
                    gpa += 2.0;
                } else if (studentGrades[i][j] == ' ') {
                     throw new MissingGradeException(studentIds[i]);
                }
            }

            gpaList[i] = gpa / studentGrades[i].length;
        }
        return gpaList;
    }

    /**
     * Filters students based on GPA within a specified range.
     *
     * @param lower         The lower bound of the GPA range.
     * @param higher        The higher bound of the GPA range.
     * @param studentIds    An array of student IDs.
     * @param studentGrades A 2D array representing the grades of each student.
     * @return An array of student IDs within the specified GPA range.
     */
    public int[] getStudentsByGPA(double lower, double higher, int[] studentIds, char[][] studentGrades) throws InvalidDataException {
        if (lower < 0 || higher < 0 || lower > higher) {
            return null;
        }

        double[] gpaList = new double[studentIds.length];

        try {
            gpaList = calculateGPA(studentIds, studentGrades);
        } catch (MissingGradeException e) {
             throw new InvalidDataException("Grades are missing", e);
        }

        int count = 0;
        for (double gpa : gpaList) {
            if (gpa >= lower && gpa <= higher) {
                count++;
            }
        }

        int[] result = new int[count];
        int index = 0;
        for (int i = 0; i < gpaList.length; i++) {
            if (gpaList[i] >= lower && gpaList[i] <= higher) {
                result[index++] = studentIds[i];
            }
        }
        return result;

    }
}
