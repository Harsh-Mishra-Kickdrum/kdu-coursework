package org.example;
import java.util.*;

public class MainApp{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        int capacity;
        System.out.println("Pls Enter the capacity of the Student Repository;");
        capacity = sc.nextInt();
        studentRepository studentRepo = new studentRepository(capacity);

        do{
            System.out.println("Please choose the option according to your choice:");
            System.out.println("1. Add Students");
            System.out.println("2. Retrieve Students with ID");
            System.out.println("3. Retrieve Students with Name");
            System.out.println("4. Update Students");
            System.out.println("0. Exit");
            System.out.print("Enter your Choice: ");
            choice =  sc.nextInt();


            switch(choice){
                case 1:
                    // Adding Students
                    System.out.println("Enter student details:");
                    System.out.print("ID: ");
                    int id = sc.nextInt();
                    System.out.println();
                    System.out.print("Name: ");
                    String name = sc.next();
                    System.out.println();
                    System.out.print("Age: ");
                    int age = sc.nextInt();
                    System.out.println();
                    System.out.print("Grade: ");
                    String grade = sc.next();

                    Student newStudent = new Student(id, name, age, grade);
                    studentRepo.addStudent(newStudent);
                    System.out.println("Student added successfully!");
                    Main.getmsg().info("Added Successfully ! ");
                    break;

                case 2:
                    // Retrieving Students with ID
                    System.out.print("Enter student ID: ");
                    int retrieveId = sc.nextInt();
                    Student retrieveById = studentRepo.getStudentById(retrieveId);
                    if (retrieveById != null) {
                        System.out.println("Student found: " + retrieveById.getName());
                        Main.getmsg().info("Student Found Successfully ! ");
                    } else {
                        System.out.println("Student not found with ID: " + retrieveId);
                        Main.getmsg().warn("Not Found ! ");
                    }
                    break;

                case 3:
                    // Retrieving  Students with Name
                    System.out.print("Enter student Name: ");
                    String retrieveName = sc.next();
                    Student retrieveByName = studentRepo.getStudentByName(retrieveName);
                    if (retrieveByName != null) {
                        System.out.println("Student found: " + retrieveByName.getId() + ", " + retrieveByName.getName());
                        Main.getmsg().info("Found Successfully ! ");
                    } else {
                        System.out.println("Student not found with Name: " + retrieveName);
                        Main.getmsg().warn("Not Found ! ");
                    }
                    break;

                case 4:
                    // Updating Students
                    System.out.print("Enter student ID to update: ");
                    int updateId = sc.nextInt();
                    Student existingStudent = studentRepo.getStudentById(updateId);
                    if (existingStudent != null) {
                        System.out.println("Enter new details for student ID " + updateId + ":");
                        System.out.print("New Name: ");
                        String newName = sc.next();
                        System.out.println();
                        System.out.print("New Age: ");
                        int newAge = sc.nextInt();
                        System.out.println();
                        System.out.print("New Grade: ");
                        String newGrade = sc.next();

                        // Creating  a new student object with updated details
                        Student updatedStudent = new Student(updateId, newName, newAge, newGrade);

                        // Updating the student in the repository
                        studentRepo.updateStudent(updatedStudent);
                        System.out.println("Student updated successfully!");
                        Main.getmsg().info("Updated Successfully ! ");

                    } else {
                        System.out.println("Student not found with ID: " + updateId);
                        Main.getmsg().warn("Not Found ! ");

                    }
                    break;

                case 0:
                    System.out.println("Exiting the application. Thanks !");
                    Main.getmsg().info("Exiting the program ! ");
                    break;

                default:
                    System.out.println("Invalid choice !  Please enter a valid option.");
                    Main.getmsg().warn("Invalid Choice ! Please Enter a valid option. ");
            }

        }while(choice!=0);
    }

}