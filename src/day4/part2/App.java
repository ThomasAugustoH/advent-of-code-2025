package day4.part2;

import utils.Grid;
import utils.InputReader;

public class App {

    private static InputReader inputReader;
    private static Grid map;

    public static void main(String[] args) {
        inputReader = new InputReader("src\\day4\\inputs\\input.txt");
        map = new Grid(inputReader);
        run();
    }

    private static void run() {
        boolean canMapUpdate = true;
        int totalRollsRemoved = 0;
        while (canMapUpdate) {
            canMapUpdate = updateAccessibleRolls();
            totalRollsRemoved += removeAccessibleRolls();
        }
        System.out.println(totalRollsRemoved);
    }

    private static boolean updateAccessibleRolls() {
        boolean hasChanged = false;
        for (int i = 0; i < map.getAmountOfLines(); i++) {
            String newLine = "";
            for (int j = 0; j < map.getLineSize(i); j++) {
                char mapItem = map.readAt(i, j);
                if (mapItem == '@' && isRollAccessible(i, j)) {
                    mapItem = 'x';
                    hasChanged = true;
                }
                newLine += mapItem;
            }
            map.updateLine(newLine, i);
        }
        //System.out.println(map);
        return hasChanged;
    }

    private static int removeAccessibleRolls() {
        int removedRolls = 0;
        for (int i = 0; i < map.getAmountOfLines(); i++) {
            String newLine = "";
            for (int j = 0; j < map.getLineSize(i); j++) {
                char mapItem = map.readAt(i, j);
                if (mapItem == 'x') {
                    mapItem = '.';
                    removedRolls++;
                }
                newLine += mapItem;
            }
            map.updateLine(newLine, i);
        }
        //System.out.println(map);
        return removedRolls;
    }


    private static boolean isRollAccessible(int line, int column) {
        int nearbyRolls = 0;

        String[] itemsBlock = map.readBlock(line - 1, column - 1, 3);
        for (int i = 0; i < itemsBlock.length; i++) {
            String lineToRead = itemsBlock[i];
            for (int j = 0; j < 3; j++) {
                if (lineToRead.charAt(j) == '@') {
                    nearbyRolls++;
                }
                if (nearbyRolls > 4) {
                    return false;
                }
            }
        }
        return true;
    }
}
