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
        long totalIds = calculateTotalIds();
        System.out.println(totalIds);
    }

    private static void findFreshIngredients() {
        while (inputReader.hasNext()) {
            String lineRead = inputReader.readLine();
            if (lineRead.equals("")) {
                break;
            }
            createNewBlock(lineRead);
        }
    }

    private static void createNewBlock(String line) {
        IdBlock newIdBlock = new IdBlock(line);
        long lowId = newIdBlock.getLowId();
        long highId = newIdBlock.getHighId();
        boolean isNewIdRange = true;

        for (IdBlock idBlock : freshIngredientsId) {
            if (idBlock.isIdInRange(lowId)) {
                if (!idBlock.isIdInRange(highId)) {
                    removeNextIdBlock(idBlock, highId);
                }
                isNewIdRange = false;
                break;
            }
            if (idBlock.isIdInRange(highId)) {
                if (!idBlock.isIdInRange(lowId)) {
                    removePreviousIdBlock(idBlock, lowId);
                }
                isNewIdRange = false;
                break;
            }
        }

        if (isNewIdRange) {
            addNewBlock(newIdBlock);
        }
    }

    private static void removeNextIdBlock(IdBlock blockToExpand, long highId) {
        Iterator<IdBlock> idBlocksIterator = freshIngredientsId.iterator();
        while (idBlocksIterator.hasNext()) {
            IdBlock blockToCompare = idBlocksIterator.next();
            if (blockToCompare.isIdInRange(highId)) {
                blockToExpand.setHighId(blockToCompare.getHighId());
                idBlocksIterator.remove();
                return;
            }

            if (blockToCompare.getLowId() >= blockToExpand.getHighId() && blockToCompare.getHighId() <= highId) {
                Iterator<IdBlock> blocksInRange = freshIngredientsId.iterator();
                while (blocksInRange.hasNext()) {
                    IdBlock blockToDelete = blocksInRange.next();
                    if (blockToDelete == blockToExpand) {
                        continue;
                    }
                    if (blockToDelete.getHighId() > highId) {
                        break;
                    }
                    blocksInRange.remove();
                }
                blockToExpand.setHighId(highId);
                return;
            }
        }
        blockToExpand.setHighId(highId);
    }

    private static void removePreviousIdBlock(IdBlock blockToExpand, long lowId) {
        Iterator<IdBlock> idBlocksIterator = freshIngredientsId.iterator();
        while (idBlocksIterator.hasNext()) {
            IdBlock blockToCompare = idBlocksIterator.next();
            if (blockToExpand.equals(blockToCompare)) {
                continue;
            }
            if (blockToCompare.isIdInRange(lowId)) {
                blockToExpand.setLowId(blockToCompare.getLowId());
                idBlocksIterator.remove();
                return;
            }
            if (blockToCompare.getHighId() <= blockToExpand.getLowId() && blockToCompare.getLowId() >= lowId) {
                Iterator<IdBlock> blocksInRange = freshIngredientsId.iterator();
                while (blocksInRange.hasNext()) {
                    IdBlock blockToDelete = blocksInRange.next();
                    if (blockToDelete == blockToExpand) {
                        continue;
                    }
                    if (blockToDelete.getLowId() > lowId) {
                        break;
                    }
                    blocksInRange.remove();
                }
                blockToExpand.setLowId(lowId);
                return;
            }
        }
        blockToExpand.setLowId(lowId);
    }

    private static void addNewBlock(IdBlock newIdBlock) {
        Iterator<IdBlock> idBlocksIterator = freshIngredientsId.iterator();
        int index = 0;
        while (idBlocksIterator.hasNext()) {
            IdBlock idBlock = idBlocksIterator.next();
            if (idBlock.getLowId() > newIdBlock.getHighId()) {
                break;
            }
            index++;
        }
        freshIngredientsId.add(index, newIdBlock);
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
