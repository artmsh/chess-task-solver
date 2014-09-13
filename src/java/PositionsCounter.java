import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PositionsCounter {
    private Set<BoardPosition> positions = new HashSet<>();

    private void backtrack(int[][] board, BoardPosition currentPosition, Map<Figure, Integer> figures) {
        boolean figuresExists = false;

        for (Figure figure : figures.keySet()) {
            if (figures.get(figure) > 0) {
                figuresExists = true;

                for (int row = 0; row < board.length; row++) {
                    for (int column = 0; column < board[0].length; column++) {
                        if (board[row][column] == 0 && figure.isPossibleToPlace(board, row, column)) {
                            figure.place(board, row, column);
                            currentPosition.pushFigure(new BoardPosition.FigurePosition(figure, row, column));
                            figures.put(figure, figures.get(figure) - 1);

                            backtrack(board, currentPosition, figures);

                            figures.put(figure, figures.get(figure) + 1);
                            currentPosition.popFigure();
                            figure.unplace(board, row, column);
                        }
                    }
                }
            }
        }

        if (!figuresExists) {
            positions.add(new BoardPosition(currentPosition));
        }
    }

    public Set<BoardPosition> countAllPositions(int width, int height, Map<Figure, Integer> figures) {
        int[][] board = new int[height][width];

        backtrack(board, new BoardPosition(width, height), figures);

        return positions;
    }

}
