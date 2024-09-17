package org.example.question_1.exceptions;

import org.example.question_1.MissingGradeException;

/**
 * Custom RuntimeException indicating invalid data with an associated student ID.
 */
public class InvalidDataException extends Exception {

    private  int affectedStudentId;
    /**
     * Constructs an InvalidDataException with a custom message and the cause (a MissingGradeException).
     *
     * @param message            Custom message describing the exception.
     * @param missingGradeCause The cause of the exception, a MissingGradeException.
     */
    public InvalidDataException(String message, MissingGradeException missingGradeCause){
       super();
       this.affectedStudentId = missingGradeCause.getStudentId();

    }


}
