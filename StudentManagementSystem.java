import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StudentManagementSystem {
    private Map<Integer, Student> students;
    private Scanner scanner;

    public StudentManagementSystem() {
        students = new HashMap<>();
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        StudentManagementSystem managementSystem = new StudentManagementSystem();
        managementSystem.run();
    }

    public void run() {
        int choice;
        do {
            System.out.println("\nStudent Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Exit");
            System.out.print("Enter your choice (1-5): ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    removeStudent();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    displayAllStudents();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 5);
    }

    private void addStudent() {
        System.out.println("\nAdding a New Student");
        System.out.print("Enter Roll Number: ");
        int rollNumber = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        if (students.containsKey(rollNumber)) {
            System.out.println("Student with Roll Number " + rollNumber + " already exists.");
            return;
        }

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Grade: ");
        String grade = scanner.next();

        // Additional data and input validation can be added here

        Student student = new Student(name, rollNumber, grade);
        students.put(rollNumber, student);
        System.out.println("Student added successfully!");
    }

    private void removeStudent() {
        System.out.println("\nRemoving a Student");
        System.out.print("Enter Roll Number: ");
        int rollNumber = scanner.nextInt();
        if (students.containsKey(rollNumber)) {
            Student removedStudent = students.remove(rollNumber);
            System.out.println("Removed student: " + removedStudent);
        } else {
            System.out.println("Student with Roll Number " + rollNumber + " not found.");
        }
    }

    private void searchStudent() {
        System.out.println("\nSearching for a Student");
        System.out.print("Enter Roll Number: ");
        int rollNumber = scanner.nextInt();
        if (students.containsKey(rollNumber)) {
            Student student = students.get(rollNumber);
            System.out.println("Found student: " + student);
        } else {
            System.out.println("Student with Roll Number " + rollNumber + " not found.");
        }
    }

    private void displayAllStudents() {
        System.out.println("\nAll Students");
        for (Student student : students.values()) {
            System.out.println(student);
        }
    }

    private static class Student {
        private String name;
        private int rollNumber;
        private String grade;

        public Student(String name, int rollNumber, String grade) {
            this.name = name;
            this.rollNumber = rollNumber;
            this.grade = grade;
        }

        @Override
        public String toString() {
            return "Student [Name: " + name + ", Roll Number: " + rollNumber + ", Grade: " + grade + "]";
        }
    }
}
