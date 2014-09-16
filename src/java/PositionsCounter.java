import java.util.*;
import java.util.stream.Collectors;

public class PositionsCounter {
    private Set<BoardPosition> positions = new HashSet<>();

    private void backtrack(Board board, BoardPosition currentPosition, List<Figure> figures,
                           boolean sameFigure, int lastRow, int lastColumn) {
        if (!figures.isEmpty()) {
            Figure figure = figures.get(0);

            for (int row = sameFigure ? lastRow : 0; row < board.getHeight(); row++) {
                for (int column = sameFigure && row == lastRow ? lastColumn + 1 : 0; column < board.getWidth(); column++) {
                    if (board.fieldIsNotUnderAttack(row, column) && figure.isPossibleToPlace(board, row, column)) {
                        figure.place(board, row, column);
                        currentPosition.pushFigure(new BoardPosition.FigurePosition(figure, row, column));

                        backtrack(board, currentPosition, figures.subList(1, figures.size()),
                                figures.size() > 1 && figures.get(1) == figure, row, column);

                        currentPosition.popFigure();
                        figure.unplace(board, row, column);
                    }
                }
            }
        } else {
            positions.add(new BoardPosition(currentPosition));
        }
    }

    public Set<BoardPosition> countAllPositions(int width, int height, Map<Figure, Integer> figures) {
        Board board = new Board(width, height);

        List<Figure> figuresCollected = figures.entrySet()
                .stream().flatMap(e -> Collections.<Figure>nCopies(e.getValue(), e.getKey()).stream()).collect(Collectors.<Figure>toList());
        backtrack(board, new BoardPosition(width, height), figuresCollected, false, 0, 0);

        return positions;
    }

}
