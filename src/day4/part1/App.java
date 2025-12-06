package day4.part1;

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
        System.out.println(countAccessibleRolls());
    }

    private static Grid createAccessibleNearbyRollsMap() {
        Grid accessibleMap = new Grid();

        for (int i = 0; i < map.getAmountOfLines(); i++) {
            String newLine = "";
            for (int j = 0; j < map.getLineSize(i); j++) {
                char mapItem = map.readAt(i, j);
                if (mapItem == '@' && isRollAccessible(i, j)) {
                    mapItem = 'x';
                }
                newLine += mapItem;
            }
            accessibleMap.addLine(newLine);
        }
        System.out.println(accessibleMap);
        return accessibleMap;
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

    private static int countAccessibleRolls() {
        Grid accessibleMap = createAccessibleNearbyRollsMap();
        int accessibleRolls = 0;

        for (int i = 0; i < accessibleMap.getAmountOfLines(); i++) {
            for (int j = 0; j < accessibleMap.getLineSize(i); j++) {
                if (accessibleMap.readAt(i, j) == 'x') {
                    accessibleRolls++;
                }
            }
        }

        return accessibleRolls;
    }
}
