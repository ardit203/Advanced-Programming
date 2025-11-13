package Exercises._04_Reading_From_SI_and_Comparator_Comparable.Weather;

public class TempTest {
    static void main() {
        DailyTemperatures dailyTemperatures = new DailyTemperatures();

        dailyTemperatures.readTemperatures(System.in);
        System.out.println("=== Daily temperatures in Celsius (C) ===");
        dailyTemperatures.writeDailyStats(System.out, 'C');
        System.out.println("=== Daily temperatures in Fahrenheit (F) ===");
        dailyTemperatures.writeDailyStats(System.out, 'F');
        System.out.println("=== Daily temperatures in Celsius (C) ===");
        dailyTemperatures.writeDailyStats(System.out, 'C');
    }
}
