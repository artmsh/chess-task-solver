import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

enum Figure {
    KING('K') {
        @Override
        protected List<int[]> offsets(int[][] board, int row, int column) {
            return Arrays.asList(new int[]{0, 0}, new int[]{-1, 0}, new int[]{1, 0}, new int[]{0, -1},
                    new int[]{0, 1}, new int[]{1, 1}, new int[]{1, -1}, new int[]{-1, 1}, new int[]{-1, -1});
        }
    }, QUEEN('Q') {
        @Override
        protected List<int[]> offsets(int[][] board, int row, int column) {
            List<int[]> offsets = new ArrayList<>(super.generateDiagonals(board, row, column));
            offsets.addAll(super.generateStraights(board, row, column));

            return offsets;
        }
    }, ROOK('R') {
        @Override
        protected List<int[]> offsets(int[][] board, int row, int column) {
            return super.generateStraights(board, row, column);
        }
    }, BISHOP('B') {
        @Override
        protected List<int[]> offsets(int[][] board, int row, int column) {
            return super.generateDiagonals(board, row, column);
        }
    }, KNIGHT('N') {
        @Override
        protected List<int[]> offsets(int[][] board, int row, int column) {
            return Arrays.asList(new int[] {0, 0}, new int[] {-1, 2}, new int[] {1, 2}, new int[] {2, -1},
                    new int[] {2, 1}, new int[] {-2, 1}, new int[] {1, -2}, new int[] {-2, -1}, new int[] {-1, -2});
        }
    };

    public static Figure parseFigure(Character c) {
        for (Figure figure : Figure.values()) {
            if (figure.getShortName().equals(c)) {
                return figure;
            }
        }

        return null;
    }

    private Character shortName;

    Figure(Character shortName) {
        this.shortName = shortName;
    }

    public Character getShortName() {
        return shortName;
    }


    private List<int[]> generateStraights(int[][] board, int row, int column) {
        List<int[]> straights = new ArrayList<>();

        for (int col = 0; col < board[row].length; col++) {
            straights.add(new int[]{ 0, col - column });
        }

        for (int r = 0; r < board.length; r++) {
            straights.add(new int[]{ r - row, 0 });
        }

        return straights;
    }

    private List<int[]> generateDiagonals(int[][] board, int row, int column) {
        List<int[]> diagonals = new ArrayList<int[]>();

        int startRow1, startColumn1, startRow2, startColumn2;
        if (row < column) {
            startRow1 = 0;
            startColumn1 = column - row;
        } else {
            startRow1 = row - column;
            startColumn1 = 0;
        }

        if (board.length - row - 1 > column) {
            startRow2 = row + (board.length - row - 1 - column);
            startColumn2 = 0;
        } else {
            startRow2 = board.length - 1;
            startColumn2 = column - (board.length - row - 1);
        }

        for (int i = 0; i < Math.min(board.length - startRow1 - 1, board[startRow1].length - startColumn1 - 1); i++) {
            diagonals.add(new int[] { startRow1 + i - row, startColumn1 + i - column });
        }

        for (int i = 0; i < Math.min(startRow2, board[startRow2].length - startColumn2 - 1); i++) {
            diagonals.add(new int[] { startRow2 - i - row, startColumn2 + i - column });
        }

        return diagonals;
    }

    protected abstract List<int[]> offsets(int[][] board, int row, int column);

    public boolean isPossibleToPlace(int[][] board, int row, int column) {
        return offsets(board, row, column).stream().allMatch(offset -> {
                if (row + offset[0] < board.length && row + offset[0] >= 0 &&
                        column + offset[1] < board[row].length && column + offset[1] >= 0) {
                    return board[row + offset[0]][column + offset[1]] != -1;
                } else {
                    return true;
                }
        });
    }

    public void place(int[][] board, int row, int column) {
        offsets(board, row, column).stream().forEach(offset -> {
                if (row + offset[0] < board.length && row + offset[0] >= 0 &&
                    column + offset[1] < board[row].length && column + offset[1] >= 0) {
                    board[row + offset[0]][column + offset[1]]++;
                }
        });

        board[row][column] = -1;
    }

    public void unplace(int[][] board, int row, int column) {
        offsets(board, row, column).stream().forEach(offset -> {
            if (row + offset[0] < board.length && row + offset[0] >= 0 &&
                    column + offset[1] < board[row].length && column + offset[1] >= 0) {
                board[row + offset[0]][column + offset[1]]--;
            }
        });

        board[row][column] = 0;
    }
}