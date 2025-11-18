public class Student {
    private int rollNumber;
    private String studentName;
    private int[] marks; 

    public Student(int rollNumber, String studentName, int[] marks) throws InvalidMarksException {
        this.rollNumber = rollNumber;
        this.studentName = studentName;
        if (marks == null || marks.length != 3) {
            throw new IllegalArgumentException("Marks array must contain exactly 3 values.");
        }
        this.marks = marks.clone();
        validateMarks(); // may throw InvalidMarksException
    }

    // Validate each mark 
    public void validateMarks() throws InvalidMarksException {
        for (int i = 0; i < marks.length; i++) {
            if (marks[i] < 0 || marks[i] > 100) {
                throw new InvalidMarksException("Invalid marks for subject " + (i + 1) + ": " + marks[i]);
            }
        }
    }

    // Calculate average
    public double calculateAverage() {
        double sum = 0;
        for (int m : marks)
            sum += m;
        return sum / marks.length;
    }

    // Determine pass or fail
    public String getResultStatus() {
        for (int m : marks) {
            if (m < 35)
                return "Fail";
        }
        return "Pass";
    }

    // Display student result
    public void displayResult() {
        System.out.println("Roll Number: " + rollNumber);
        System.out.println("Student Name: " + studentName);
        System.out.print("Marks: ");
        for (int m : marks)
            System.out.print(m + " ");
        System.out.println();
        System.out.println("Average: " + calculateAverage());
        System.out.println("Result: " + getResultStatus());
    }

    public int getRollNumber() {
        return rollNumber;
    }
}
