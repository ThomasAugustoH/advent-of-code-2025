package day2.part2;

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
            long lowLimiter = Long.parseLong(idsLimiter[0]);
            long highLimiter = Long.parseLong(idsLimiter[1]);

            invalidIds += verifyIds(lowLimiter, highLimiter);
        }

        System.out.println(invalidIds);
    }

    private static long verifyIds(long lowLimiter, long highLimiter) {
        long invalidIds = 0;

        for (long i = lowLimiter; i <= highLimiter; i++) {
            String id = String.valueOf(i);
            //System.out.println("id: " + id);
            int idLenght = id.length();

            for (int j = 1; j <= idLenght / 2; j++) {
                if (idLenght % j != 0) {
                    //System.out.println("Skipped");
                    continue;

                }
                String partialId = id.substring(0, j);
                String newId = buildNewId(partialId, idLenght / j);
                //System.out.println(newId);

                if (newId.equals(id)) {
                    invalidIds += i;
                    break;
                }

            }

        }

        return invalidIds;
    }

    private static String buildNewId(String pattern, int times) {
        String newId = "";
        for (int i = 0; i < times; i++) {
            newId += pattern;
        }

        return newId;
    }
}
