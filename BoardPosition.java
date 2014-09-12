import java.util.List;

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
    }

    private List<FigurePosition> figures;

    public BoardPosition(List<FigurePosition> figures) {
        this.figures = figures;
    }

    public List<FigurePosition> getFigures() {
        return figures;
    }
}
