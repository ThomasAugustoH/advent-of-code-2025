package day1.part2;

import utils.InputReader;

public class App {

    private static InputReader inputReader;
    private static int dialPosition = 50;

    private enum Direction {
        Left, Right;
    }

    public static void main(String[] args) {
        inputReader = new InputReader("src\\day1\\inputs\\input1.txt");
        run();
    }

    private static void run() {
        int password = 0;
        while (inputReader.hasNext()) {
            String line = inputReader.readLine();
            Direction currentDirection = Direction.Right;
            if (line.startsWith("L")) {
                currentDirection = Direction.Left;
            }
            int steps = Integer.parseInt(line.substring(1));
            int rotations = calculateRotations(currentDirection, steps);
            password += rotations;
        }

        System.out.println(password);
    }

    private static int calculateRotations(Direction direction, int steps) {
        if (direction == Direction.Left) {
            steps *= -1;
        }

        double rawPosition = dialPosition + steps;
        
        int completedRotations = (int) Math.floor(Math.abs(rawPosition / 100));
        int finalRotation = (int) rawPosition % 100;

        completedRotations = rawPosition <= 0 && dialPosition != 0? completedRotations += 1 : completedRotations;
        dialPosition = finalRotation < 0 ? finalRotation += 100: finalRotation;

        return completedRotations;
    }
}
