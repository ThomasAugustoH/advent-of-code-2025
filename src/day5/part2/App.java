package day5.part2;

import java.util.ArrayList;
import java.util.Iterator;

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
        mergeBlocks();
        long totalIds = calculateTotalIds();
        System.out.println(totalIds);
    }

    private static void findFreshIngredients() {
        while (inputReader.hasNext()) {
            String lineRead = inputReader.readLine();
            if (lineRead.equals("")) {
                break;
            }
            IdBlock newIdBlock = new IdBlock(lineRead);
            addNewBlock(newIdBlock);
        }
    }

    private static void addNewBlock(IdBlock newIdBlock) {
        Iterator<IdBlock> idBlocksIterator = freshIngredientsId.iterator();
        int index = 0;
        while (idBlocksIterator.hasNext()) {
            IdBlock idBlock = idBlocksIterator.next();
            if (idBlock.getLowId() > newIdBlock.getLowId()) {
                break;
            }
            index++;
        }
        freshIngredientsId.add(index, newIdBlock);
    }

    private static void mergeBlocks() {
        Iterator<IdBlock> idBlocksIterator = freshIngredientsId.iterator();
        IdBlock lowerIdBlock = idBlocksIterator.next();
        boolean setNewLowerId = false;
        IdBlock blockToRemove = null;
        while (idBlocksIterator.hasNext()) {
            if (setNewLowerId) {
                lowerIdBlock = blockToRemove;
                setNewLowerId = false;
                continue;
            } else {
                blockToRemove = idBlocksIterator.next();
            }

            if (lowerIdBlock.getHighId() > blockToRemove.getHighId()) {
                idBlocksIterator.remove();
            } else if (blockToRemove.isIdInRange(lowerIdBlock.getHighId())) {
                lowerIdBlock.setHighId(blockToRemove.getHighId());
                idBlocksIterator.remove();
            } else {
                setNewLowerId = true;
            }
        }
    }

    private static long calculateTotalIds() {
        long totalIds = 0;
        for (IdBlock idBlock : freshIngredientsId) {
            System.out.println(idBlock.getLowId() + " " + idBlock.getHighId() + ": " + idBlock.getCountOfIds());
            totalIds += idBlock.getCountOfIds();
        }

        return totalIds;
    }
}
