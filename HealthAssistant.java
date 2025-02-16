import java.util.Scanner;

public class HealthAssistant {

    // BMI categories
    private static final String UNDERWEIGHT = "Underweight: BMI is less than 18.5";
    private static final String NORMAL = "Normal weight: BMI is between 18.5 and 24.9";
    private static final String OVERWEIGHT = "Overweight: BMI is between 25 and 29.9";
    private static final String OBESE = "Obese: BMI is 30 or more";

    // Method to calculate BMI
    public static String bmiCalculator(double weight, double height) {
        double bmi = weight / (height * height);
        if (bmi < 18.5) {
            return UNDERWEIGHT + " (BMI: " + bmi + ")";
        } else if (bmi >= 18.5 && bmi < 24.9) {
            return NORMAL + " (BMI: " + bmi + ")";
        } else if (bmi >= 25 && bmi < 29.9) {
            return OVERWEIGHT + " (BMI: " + bmi + ")";
        } else {
            return OBESE + " (BMI: " + bmi + ")";
        }
    }

    // Method to give health advice
    public static String healthAdvice() {
        return "It's important to maintain a balanced diet, exercise regularly, and stay hydrated.";
    }

    // Method to provide basic information based on query
    public static String provideInfo(String query) {
        query = query.toLowerCase();
        if (query.contains("bmi")) {
            return "The Body Mass Index (BMI) is used to assess if you are underweight, normal, overweight, or obese.";
        } else if (query.contains("diet")) {
            return "A balanced diet includes a variety of foods: fruits, vegetables, whole grains, protein, and healthy fats.";
        } else if (query.contains("exercise")) {
            return "Aim for at least 30 minutes of moderate-intensity exercise most days of the week.";
        } else if (query.contains("hydration")) {
            return "It's recommended to drink about 2-3 liters of water per day depending on your activity level.";
        } else {
            return "I can help with basic health advice. Please ask about BMI, diet, exercise, or hydration.";
        }
    }

    // Main method to start the conversation
    public static void startConversation() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I am your AI-powered Health Assistant. How can I assist you today?");

        while (true) {
            String userInput = scanner.nextLine().toLowerCase();

            if (userInput.contains("exit")) {
                System.out.println("Goodbye! Stay healthy!");
                break;
            } else if (userInput.contains("bmi")) {
                System.out.print("Enter your weight in kg: ");
                if (scanner.hasNextDouble()) {
                    double weight = scanner.nextDouble();
                    System.out.print("Enter your height in meters: ");
                    if (scanner.hasNextDouble()) {
                        double height = scanner.nextDouble();
                        scanner.nextLine();  // consume the newline character
                        System.out.println(bmiCalculator(weight, height));
                    } else {
                        System.out.println("Invalid input for height. Please enter a valid number.");
                        scanner.nextLine(); // Clear the buffer
                    }
                } else {
                    System.out.println("Invalid input for weight. Please enter a valid number.");
                    scanner.nextLine(); // Clear the buffer
                }
            } else if (userInput.contains("advice")) {
                System.out.println(healthAdvice());
            } else {
                System.out.println(provideInfo(userInput));
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        startConversation();
    }
}

