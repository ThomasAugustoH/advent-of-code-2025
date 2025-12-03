package day2.part1;

import utils.InputReader;

public class App {

    private static InputReader inputReader;

    public static void main(String[] args) {
        inputReader = new InputReader("src\\day2\\inputs\\input.txt");
        run();
    }

    private static void run() {
        String line = inputReader.readLine();
        String[] idList = line.split(",");
        long invalidIds = 0;

        for (String ids : idList) {
            String[] idsLimiter = ids.split("-");
            long lowLimiter =   Long.parseLong(idsLimiter[0]);
            long highLimiter = Long.parseLong(idsLimiter[1]);

            invalidIds += verifyIds(lowLimiter, highLimiter);
        }

        System.out.println(invalidIds);
    }

    private static long verifyIds(long lowLimiter, long highLimiter) {
        long invalidIds = 0;

        for (long i = lowLimiter; i <= highLimiter; i++) {
            String id = String.valueOf(i);
            String halfId = id.substring(0, id.length() / 2);

            if (halfId.concat(halfId).equals(id)) {
                invalidIds+= i;
            }
        }

        return invalidIds;
    }
}
