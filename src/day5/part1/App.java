package day5.part1;

import java.util.ArrayList;

import day5.IdBlock;
import utils.InputReader;

public class App {

    private static InputReader inputReader;
    private static ArrayList<IdBlock> freshIngredientsId;

    public static void main(String[] args) {
        inputReader = new InputReader("src\\day5\\inputs\\input.txt");
        run();
    }

    private static void run() {
        freshIngredientsId = new ArrayList<>();
        findFreshIngredients();
        int ingredientsCount = calculateFreshIngredients();
        System.out.println(ingredientsCount);
    }

    private static void findFreshIngredients() {
        while (inputReader.hasNext()) {
            String lineRead = inputReader.readLine();
            if (lineRead.equals("")) {
                break;
            }
            IdBlock newIdBlock = new IdBlock(lineRead);
            freshIngredientsId.add(newIdBlock);
        }
    }

    private static int calculateFreshIngredients() {
        int freshIngredientsCount = 0;
        while (inputReader.hasNext()) {
            long id = Long.parseLong(inputReader.readLine());
            for (IdBlock idBlock : freshIngredientsId) {
                if (idBlock.isIdInRange(id)) {
                    freshIngredientsCount++;
                    break;
                }
            }

        }

        return freshIngredientsCount;
    }
}
