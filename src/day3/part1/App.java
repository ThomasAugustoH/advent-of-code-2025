package day3.part1;

import utils.InputReader;

public class App {

    private static InputReader inputReader;

    public static void main(String[] args) {
        inputReader = new InputReader("src\\day3\\inputs\\input.txt");
        run();
    }

    private static void run() {
        int totalJoltage = 0;
        while (inputReader.hasNext()) {
            String line = inputReader.readLine();
            int joltage = findLargestJoltage(line);
            System.out.println(joltage);
            totalJoltage += joltage;
        }

        System.out.println(totalJoltage);

    }

    private static int findLargestJoltage(String line) {
        int leftBattery = 0;
        int rightBattery = 0;

        for (int i = 0; i < line.length(); i++) {
            char currentBattery = line.charAt(i);
            int batteryValue = Character.getNumericValue(currentBattery);

            if (batteryValue > leftBattery && i + 1 < line.length()) {
                leftBattery = batteryValue;
                rightBattery = 0;
                continue;
            }

            if ((batteryValue > rightBattery)) {
                rightBattery = batteryValue;
            }
        }

        return leftBattery * 10 + rightBattery;
    }
}
