package org.example.question_1;

/**
 * Custom RuntimeException which indicates a missing grade with an related student ID.
 */
public class MissingGradeException extends Exception{

    private  int studentId;

    /**
     * Constructs a MissingGradeException with the related student's ID.
     *
     * @param studentId The ID of the student with the missing grade.
     */
    public MissingGradeException(int studentId) {
        this.studentId = studentId;
    }

    /**
     * Gets the student ID associated with the missing grade.
     *
     * @return The student ID.
     */
    public int getStudentId() {
        return studentId;
    }
}
