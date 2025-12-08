package day6.part1;

import java.util.ArrayList;
import java.util.Stack;

import utils.InputReader;

public class App {

    private static InputReader inputReader;
    private static ArrayList<Stack<String>> problemsList;

    public static void main(String[] args) {
        inputReader = new InputReader("src\\day6\\inputs\\input.txt");
        run();
    }

    private static void run() {
        problemsList = new ArrayList<>();
        populateData();
        long total = calculateAllProblems();
        System.out.println(total);
    }

    private static void populateData() {
        while (inputReader.hasNext()) {
            String line = inputReader.readLine();
            String[] items = line.trim().split("(\s+)");
            int problemIndex = 0;
            for (String item : items) {
                Stack<String> problem;
                if (problemsList.size() <= problemIndex) {
                    problem = new Stack<>();
                    problemsList.add(problem);
                } else {
                    problem = problemsList.get(problemIndex);
                }
                problem.push(item);
                problemIndex++;
            }
        }
    }

    private static long calculateAllProblems() {
        long total = 0;
        for (Stack<String> stack : problemsList) {
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
