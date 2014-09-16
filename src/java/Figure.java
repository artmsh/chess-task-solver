import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

enum Figure {
    KING('K') {
        List<int[]> offsets = Arrays.asList(new int[]{0, 0}, new int[]{-1, 0},
                new int[]{1, 0}, new int[]{0, -1}, new int[]{0, 1}, new int[]{1, 1}, new int[]{1, -1},
                new int[]{-1, 1}, new int[]{-1, -1});

        @Override
        public boolean isPossibleToPlace(Board board, int row, int column) {
            return board.fieldsIsNotOccupiedByFigure(row, column, offsets);
        }

        @Override
        public void place(Board board, int row, int column) {
            board.attackOffsets(row, column, offsets);
            board.figureOccupy(row, column);
        }

        @Override
        public void unplace(Board board, int row, int column) {
            board.unattackOffsets(row, column, offsets);
            board.figureFree(row, column);
        }
    }, QUEEN('Q') {
        @Override
        public boolean isPossibleToPlace(Board board, int row, int column) {
            return board.isDiagonalsFree(row, column) && board.isHorizontalFree(row) && board.isVerticalFree(column);
        }

        @Override
        public void place(Board board, int row, int column) {
            board.attackDiagonals(row, column);
            board.attackStraights(row, column);
            board.figureOccupy(row, column);
        }

        @Override
        public void unplace(Board board, int row, int column) {
            board.unattackDiagonals(row, column);
            board.unattackStraights(row, column);
            board.figureFree(row, column);
        }
    }, ROOK('R') {
        @Override
        public boolean isPossibleToPlace(Board board, int row, int column) {
            return board.isHorizontalFree(row) && board.isVerticalFree(column);
        }

        @Override
        public void place(Board board, int row, int column) {
            board.attackStraights(row, column);
            board.figureOccupy(row, column);
        }

        @Override
        public void unplace(Board board, int row, int column) {
            board.unattackStraights(row, column);
            board.figureFree(row, column);
        }
    }, BISHOP('B') {
        @Override
        public boolean isPossibleToPlace(Board board, int row, int column) {
            return board.isDiagonalsFree(row, column);
        }

        @Override
        public void place(Board board, int row, int column) {
            board.attackDiagonals(row, column);
            board.figureOccupy(row, column);
        }

        @Override
        public void unplace(Board board, int row, int column) {
            board.unattackDiagonals(row, column);
            board.figureFree(row, column);
        }
    }, KNIGHT('N') {
        List<int[]> offsets = Arrays.asList(new int[] {0, 0}, new int[] {-1, 2}, new int[] {1, 2}, new int[] {2, -1},
                new int[] {2, 1}, new int[] {-2, 1}, new int[] {1, -2}, new int[] {-2, -1}, new int[] {-1, -2});

        @Override
        public boolean isPossibleToPlace(Board board, int row, int column) {
            return board.fieldsIsNotOccupiedByFigure(row, column, offsets);
        }

        @Override
        public void place(Board board, int row, int column) {
            board.attackOffsets(row, column, offsets);
            board.figureOccupy(row, column);
        }

        @Override
        public void unplace(Board board, int row, int column) {
            board.unattackOffsets(row, column, offsets);
            board.figureFree(row, column);
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

    public abstract boolean isPossibleToPlace(Board board, int row, int column);
    public abstract void place(Board board, int row, int column);
    public abstract void unplace(Board board, int row, int column);
}