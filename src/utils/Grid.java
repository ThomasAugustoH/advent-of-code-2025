package utils;

import java.util.ArrayList;

public class Grid {

    private ArrayList<ArrayList<Character>> lines;

    public Grid() {
        lines = new ArrayList<>();
    }

    public Grid(InputReader inputReader) {
        lines = new ArrayList<>();
        createGridFromReader(inputReader);
    }

    public int getAmountOfLines() {
        return lines.size();
    }

    public int getLineSize(int line) {
        return lines.get(line).size();
    }

    private void createGridFromReader(InputReader inputReader) {
        while (inputReader.hasNext()) {
            addLine(inputReader.readLine());
        }
    }

    public void addLine(String line) {
        ArrayList<Character> newLine = new ArrayList<>();
        for (int i = 0; i < line.length(); i++) {
            newLine.add(line.charAt(i));
        }
        lines.add(newLine);
    }

    public void addLine(String line, int lineIndex) {
        ArrayList<Character> newLine = new ArrayList<>();
        for (int i = 0; i < line.length(); i++) {
            newLine.add(line.charAt(i));
        }
        lines.add(lineIndex, newLine);
    }

    public void updateLine(String line, int lineIndex) {
        lines.remove(lineIndex);
        addLine(line, lineIndex);
    }

    public char readAt(int line, int column) {
        ArrayList<Character> lineToRead = lines.get(line);
        char foundCharacter = lineToRead.get(column);

        return foundCharacter;
    }

    public String readSubstring(int line, int leftMostColumn, int length) {

        String subString = "";

        if (line < 0 || line >= lines.size()) {
            for (int i = 0; i < length; i++) {
                subString += " ";
            }
            return subString;
        }

        ArrayList<Character> lineToRead = lines.get(line);

        for (int i = 0; i < length; i++) {
            int column = i + leftMostColumn;
            if (column < 0 || column >= lineToRead.size()) {
                subString += " ";
            } else {
                subString += lineToRead.get(column);
            }
        }

        return subString;
    }

    public String[] readBlock(int upperMostLine, int leftMostColumn, int size) {
        String[] stringBlock = new String[size];
        int amountOfLines = size;

        for (int i = 0; i < amountOfLines; i++) {
            stringBlock[i] = readSubstring(upperMostLine + i, leftMostColumn, size);
        }

        return stringBlock;
    }

    public String toString() {
        String grid = "";
        int totalLines = lines.size();

        for (int i = 0; i < totalLines; i++) {
            ArrayList<Character> lineToRead = lines.get(i);
            int lineSize = lineToRead.size();
            for (int j = 0; j < lineSize; j++) {
                grid += " ";
                grid += readAt(i, j);
                grid += " ";
            }

            grid += "\n";
        }

        return grid;
    }
}
