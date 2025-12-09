package day6.part2;

import java.util.ArrayList;
import java.util.Stack;

import utils.InputReader;

public class App {

    private static InputReader inputReader;
    private static ArrayList<String> lines;
    private static ArrayList<Stack<String>> problems;

    public static void main(String[] args) {
        inputReader = new InputReader("src\\day6\\inputs\\input.txt");
        run();
    }

    private static void run() {
        lines = new ArrayList<>();
        problems = new ArrayList<>();
        populateData();
        createProblems();
        long total = calculateAllProblems();
        System.out.println(total);
    }

    private static void populateData() {
        while (inputReader.hasNext()) {
            String line = inputReader.readLine();
            lines.add(line + " ");
        }
    }

    private static void createProblems() {
        int lineLength = lines.get(0).length();
        boolean createNewProblem = true;
        Stack<String> problem = null;
        char operation = ' ';

        for (int i = 0; i < lineLength; i++) {
            boolean stopProblem = true;
            int number = 0;

            if (createNewProblem) {
                problem = new Stack<>();
                createNewProblem = false;
                operation = ' ';
            }

            for (int j = 0; j < lines.size(); j++) {
                char foundChar = lines.get(j).charAt(i);
                if (foundChar != ' ') {
                    stopProblem = false;
                    if (Character.isDigit(foundChar)) {
                        number *= 10;
                        number += Character.getNumericValue(foundChar);
                    } else if (operation == ' ') {
                        operation = foundChar;
                    }
                }
            }
            if (number != 0) {
                problem.push(String.valueOf(number));
            }
            if (stopProblem) {
                problem.push(String.valueOf(operation));
                problems.add(problem);
                createNewProblem = true;
            }
        }
    }

    private static long calculateAllProblems() {
        long total = 0;
        for (Stack<String> stack : problems) {
            total += calculateProblem(stack);
        }
        return total;
    }

    private static long calculateProblem(Stack<String> problem) {
        String operation = problem.pop();
        long result = 0;
        if (operation.equals("*")) {
            result = 1;
        }
        while (!problem.isEmpty()) {
            long number = Long.valueOf(problem.pop());
            if (operation.equals("+")) {
                result += number;
            } else {
                result *= number;
            }
        }

        return result;
    }
}
