package org.example.question_1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.example.question_1.exceptions.InvalidDataException;

/**
 * Main class demonstrating the usage of Student class to calculate GPA and handle exception
 */

public class Main extends Throwable{

   private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws InvalidDataException {

        try{
            StudentUtil student  =  new StudentUtil();
            int[]  studentIds1 = {1001,1002};
            char[][] studentGrades1 = {{'A','A','A','B'}};
            double[] gpas  = student.calculateGPA(studentIds1,studentGrades1);
        }catch( MissingGradeException ex){
            logger.info("IllegalArgumentException caught : {}",ex.getStudentId());
        }

        try{
            StudentUtil student = new StudentUtil();
            int[] studentIds2 = {1001};
            char[][] studentGrades2 = {{'A',' ','B'}};
            double[] gpas = student.calculateGPA(studentIds2,studentGrades2);
        }catch(MissingGradeException exception){
            String formattedMessage = String.format("Missing grade for student: %d",exception.getStudentId());
             throw new InvalidDataException(formattedMessage,exception);
        }

    }
}
