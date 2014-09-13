import java.util.*;

public class BoardPosition {
    public static class FigurePosition {
        private Figure figure;
        private int row, column;

        public FigurePosition(Figure figure, int row, int column) {
            this.figure = figure;
            this.row = row;
            this.column = column;
        }

        public Figure getFigure() {
            return figure;
        }

        public int getRow() {
            return row;
        }

        public int getColumn() {
            return column;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            FigurePosition that = (FigurePosition) o;

            if (column != that.column) return false;
            if (row != that.row) return false;
            if (figure != that.figure) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = figure != null ? figure.hashCode() : 0;
            result = 31 * result + row;
            result = 31 * result + column;
            return result;
        }
    }

    private int width, height;
    private Stack<FigurePosition> figures = new Stack<>();

    public BoardPosition(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public BoardPosition(BoardPosition boardPosition) {
        this.width = boardPosition.width;
        this.height = boardPosition.height;
        this.figures.addAll(boardPosition.getFigures());
    }

    public void pushFigure(FigurePosition figurePosition) {
        figures.push(figurePosition);
    }

    public void popFigure() {
        figures.pop();
    }

    public Stack<FigurePosition> getFigures() {
        return figures;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BoardPosition that = (BoardPosition) o;

        return figures.size() == that.figures.size() &&
                figures.stream().allMatch(that.figures::contains);
    }

    @Override
    public int hashCode() {
        SortedSet<FigurePosition> positions =
                new TreeSet<>((p1, p2) -> (p1.row * width + p1.column) - (p2.row * width + p2.column));
        positions.addAll(figures);
        return positions.hashCode();
    }

    @Override
    public String toString() {
        char[][] field = new char[height][width];

        StringBuilder sb = new StringBuilder();

        for (BoardPosition.FigurePosition figurePosition : figures) {
            field[figurePosition.getRow()][figurePosition.getColumn()] =
                    figurePosition.getFigure().getShortName();
        }

        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                if (field[row][column] != '\0') {
                    sb.append(field[row][column]);
                } else {
                    sb.append('.');
                }
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
