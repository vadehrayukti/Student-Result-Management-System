import java.util.InputMismatchException;
import java.util.Scanner;

public class ResultManager {
    private static final int MAX_STUDENTS = 100;
    private Student[] students;
    private int count;
    private Scanner scanner;

    public ResultManager() {
        students = new Student[MAX_STUDENTS];
        count = 0;
        scanner = new Scanner(System.in);
    }

    // Add a student
    public void addStudent() {
        try {
            System.out.print("Enter Roll Number: ");
            int roll = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Enter Student Name: ");
            String name = scanner.nextLine().trim();

            int[] marks = new int[3];
            for (int i = 0; i < 3; i++) {
                System.out.print("Enter marks for subject " + (i + 1) + ": ");
                String line = scanner.nextLine().trim();
                try {
                    marks[i] = Integer.parseInt(line);
                } catch (NumberFormatException nfe) {
                    System.out.println("Error: Input mismatch for marks. Expected integer. Returning to main menu...");
                    return;
                }
            }

            // Create student (constructor validates marks and may throw InvalidMarksException)
            Student s = new Student(roll, name, marks);
            if (count < MAX_STUDENTS) {
                students[count++] = s;
                System.out.println("Student added successfully. Returning to main menu...");
            } else {
                System.out.println("Storage full. Cannot add more students.");
            }

        } catch (InvalidMarksException ime) {
            System.out.println("Error: " + ime.getMessage() + " Returning to main menu...");
        } catch (IllegalArgumentException iae) {
            System.out.println("Error: " + iae.getMessage() + " Returning to main menu...");
        } catch (Exception e) {
            // Catch any other unchecked exceptions 
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    // Show details for a specific roll number
    public void showStudentDetails() {
        try {
            System.out.print("Enter Roll Number to search: ");
            int roll = Integer.parseInt(scanner.nextLine().trim());
            boolean found = false;
            for (int i = 0; i < count; i++) {
                if (students[i].getRollNumber() == roll) {
                    students[i].displayResult();
                    found = true;
                    break;
                }
            }
            if (!found) System.out.println("Student with roll number " + roll + " not found.");
            System.out.println("Search completed.");
        } catch (NumberFormatException nfe) {
            System.out.println("Error: Invalid roll number input. Returning to main menu...");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    // Main menu loop
    public void mainMenu() {
        boolean exit = false;
        while (!exit) {
            try {
                System.out.println("=====Student Result Management System =====");
                System.out.println("1. Add Student");
                System.out.println("2. Show Student Details");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");

                String choiceLine = scanner.nextLine().trim();
                int choice = Integer.parseInt(choiceLine);

                switch (choice) {
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        showStudentDetails();
                        break;
                    case 3:
                        System.out.println("Exiting program. Thank you!");
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter 1-3.");
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Input mismatch. Please enter numeric choices only.");
            } catch (Exception e) {
                System.out.println("An unexpected error occurred in menu: " + e.getMessage());
            } finally {
                // finally used to show a short separator each loop iteration
                System.out.println();
            }
        }

        // Close scanner in finally-like manner here before program exits
        try {
            if (scanner != null) scanner.close();
        } finally {
            System.out.println("Scanner closed. Program terminated.");
        }
    }

    public static void main(String[] args) {
        ResultManager manager = new ResultManager();
        manager.mainMenu();
    }
}