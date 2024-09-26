package org.example;
import java.util.*;

public class studentRepository{

        private  Student[] students;
        private int size; //track the current number of students

        // Constructor
       public studentRepository(int capacity) {
            this.students = new Student[capacity];
            this.size = 0; // Initialize size to zero
        }

        //method to add student in the repo
       public void addStudent(Student student){
           if (size<students.length){
               students[size++] = student;
           }
           else{
              // System.out.println("Array is Full !");
               //throwing an exception instead of print statement
               throw new IllegalStateException("Array is Full!");
           }
       }

       //Method to retrieve students with id
      public Student getStudentById(int id){
          for (int i = 0; i < size; i++) {
              if (students[i] != null && students[i].getId() == id) {
                  return students[i];
              }
          }
          return null;  //student not find
      }

      //Method to retrieve students with name
     public Student getStudentByName(String name){
         for (int i = 0; i < size; i++) {
             if (students[i] != null && students[i].getName().equals(name)) {
                 return students[i];
             }
         }
         return null;
     }

     //method to update student
    public void updateStudent(Student updatedStudent) {
        // Implementation to update a student in the array
        for (int i = 0; i < size; i++) {
            if (students[i].getId() == updatedStudent.getId()) {
                students[i] = updatedStudent;
                break;
            }
        }
    }


}