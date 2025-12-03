package day3.part2;

import utils.InputReader;

public class App {

    private static InputReader inputReader;
    private static int[] batteries;
    private static final int BATTERIES_NEEDED = 12;

    public static void main(String[] args) {
        inputReader = new InputReader("src\\day3\\inputs\\input.txt");
        run();
    }

    private static void run() {
        long totalJoltage = 0;
        batteries = new int[BATTERIES_NEEDED];
        while (inputReader.hasNext()) {
            String line = inputReader.readLine();
            long joltage = findLargestJoltage(line);
            totalJoltage += joltage;
        }

        System.out.println(totalJoltage);

    }

    private static long findLargestJoltage(String line) {
        resetLowerBatteries(-1);

        for (int i = 0; i < line.length(); i++) {
            char currentBattery = line.charAt(i);
            int batteryValue = Character.getNumericValue(currentBattery);

            for (int j = 0; j < BATTERIES_NEEDED; j++) {
                if (!isBatteryAtLimit(i, j, line.length()) && batteryValue > batteries[j]) {
                    batteries[j] = batteryValue;
                    resetLowerBatteries(j);
                    break;
                }
            }
        }

        return calculateTotalJoltage();
    }

    private static boolean isBatteryAtLimit(int currentBattery, int batteryIndex, int amountOfBatteries) {
        return currentBattery >= amountOfBatteries - (BATTERIES_NEEDED - (batteryIndex + 1));
    }

    private static void resetLowerBatteries(int batteryIndex) {
        for (int i = batteryIndex + 1; i < batteries.length; i++) {
            batteries[i] = 0;
        }
    }

    private static long calculateTotalJoltage() {
        long total = 0;
        for (int i = 0; i < batteries.length; i++) {
            total += batteries[i] * (long) Math.pow(10, BATTERIES_NEEDED - i - 1);
        }

        return total;
    }
}
