package day1.part1;

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
            updateDialPosition(currentDirection, steps);

            if (dialPosition == 0) {
                password++;
            }
        }

        System.out.println(password);
    }

    private static void updateDialPosition(Direction direction, int steps) {
        if (direction == Direction.Left) {
            steps *= -1;
        }

        dialPosition = (dialPosition + steps) % 100;
    }
}
