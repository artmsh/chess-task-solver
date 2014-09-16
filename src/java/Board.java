import java.util.List;

public class Board {
    private int width, height;
    private int[][] grid;
    private int[] verticals, horizontals, diagonals1, diagonals2;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;

        grid = new int[height][width];
        verticals = new int[width];
        horizontals = new int[height];
        diagonals1 = new int[width + height - 1];
        diagonals2 = new int[width + height - 1];
    }

    public boolean fieldIsNotUnderAttack(int row, int column) {
        return grid[row][column] == 0;
    }

    public boolean fieldIsNotOccupiedByFigure(int row, int column) {
        return grid[row][column] != -1;
    }

    public boolean fieldsIsNotOccupiedByFigure(int row, int column, List<int[]> offsets) {
        return offsets.stream().allMatch(offset -> {
            if (row + offset[0] < height && row + offset[0] >= 0 &&
                    column + offset[1] < width && column + offset[1] >= 0) {
                return fieldIsNotOccupiedByFigure(row + offset[0], column + offset[1]);
            } else {
                return true;
            }
        });
    }

    public boolean isVerticalFree(int column) {
        return verticals[column] == 0;
    }

    public boolean isHorizontalFree(int row) {
        return horizontals[row] == 0;
    }

    public boolean isDiagonalsFree(int row, int column) {
        int startRow1 = row - Math.min(row, column);
        int startColumn1 = column - Math.min(row, column);

        int startRow2 = row + Math.min(grid.length - row - 1, column);
        int startColumn2 = column - Math.min(grid.length - row - 1, column);

        return diagonals1[startColumn1 == 0 ? startRow1 : grid.length - 1 + startColumn1] == 0 &&
               diagonals2[startColumn2 == 0 ? startRow2 : grid.length - 1 + startColumn2] == 0;
    }

    public void figureOccupy(int row, int column) {
        grid[row][column] = -1;

        diagonalsOccupyOrFree(row, column, 1);
        straightsOccupyOrFree(row, column, 1);
    }

    public void figureFree(int row, int column) {
        grid[row][column] = 0;

        diagonalsOccupyOrFree(row, column, -1);
        straightsOccupyOrFree(row, column, -1);
    }

    public void straightsOccupyOrFree(int row, int column, int occupyOrFree) {
        verticals[column] += occupyOrFree;
        horizontals[row] += occupyOrFree;
    }

    public void diagonalsOccupyOrFree(int row, int column, int occupyOrFree) {
        int startRow1 = row - Math.min(row, column);
        int startColumn1 = column - Math.min(row, column);

        int startRow2 = row + Math.min(grid.length - row - 1, column);
        int startColumn2 = column - Math.min(grid.length - row - 1, column);

        diagonals1[startColumn1 == 0 ? startRow1 : grid.length - 1 + startColumn1] += occupyOrFree;
        diagonals2[startColumn2 == 0 ? startRow2 : grid.length - 1 + startColumn2] += occupyOrFree;
    }

    private void straightsOperation(int row, int column, int d) {
        for (int r = 0; r < grid.length; r++) {
            grid[r][column] += d;
        }

        for (int col = 0; col < grid[row].length; col++) {
            grid[row][col] += d;
        }
    }

    public void attackStraights(int row, int column) {
        straightsOperation(row, column, 1);
    }

    public void unattackStraights(int row, int column) {
        straightsOperation(row, column, -1);
    }

    private void diagonalsOperation(int row, int column, int d) {
        int startRow1 = row - Math.min(row, column);
        int startColumn1 = column - Math.min(row, column);

        int startRow2 = row + Math.min(grid.length - row - 1, column);
        int startColumn2 = column - Math.min(grid.length - row - 1, column);

        for (int i = 0; i <= Math.min(height - startRow1 - 1, width - startColumn1 - 1); i++) {
            grid[startRow1 + i][startColumn1 + i] += d;
        }

        for (int i = 0; i <= Math.min(startRow2, width - startColumn2 - 1); i++) {
            grid[startRow2 - i][startColumn2 + i] += d;
        }
    }

    public void attackDiagonals(int row, int column) {
        diagonalsOperation(row, column, 1);
    }

    public void unattackDiagonals(int row, int column) {
        diagonalsOperation(row, column, -1);
    }

    private void offsetsOperation(int row, int column, List<int[]> offsets, int d) {
        offsets.forEach(offset -> {
            if (row + offset[0] < height && row + offset[0] >= 0 &&
                    column + offset[1] < width && column + offset[1] >= 0) {
                grid[row + offset[0]][column + offset[1]] += d;
            }
        });
    }

    public void attackOffsets(int row, int column, List<int[]> offsets) {
        offsetsOperation(row, column, offsets, 1);
    }

    public void unattackOffsets(int row, int column, List<int[]> offsets) {
        offsetsOperation(row, column, offsets, -1);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
