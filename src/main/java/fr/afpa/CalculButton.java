package fr.afpa;



public class CalculButton {
    public static double calculate(String expression) {
        // Simple calculation logic for demonstration purposes
        // This assumes the expression is in the form of "1+2+3"
        String[] numbers = expression.split("\\+");
        double sum = 0;
        for (String number : numbers) {
            sum += Double.parseDouble(number);
        }
        return sum;
    }
}
