import java.util.*;

public class ChessSolver {

    private static List<BoardPosition> listAllPositions(int width, int height, Map<Figure, Integer> figures) {
        return Collections.emptyList();
    }

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: java ChessSolver [-n] [board-width] [board-height] [list-of-figures]");
        } else {
            boolean onlyCombinationCount = false;
            int argsStartIndex = 0;
            if (args[0].trim().equals("-n")) {
                onlyCombinationCount = true;
                argsStartIndex++;
            }

            try {
                Integer width = Integer.valueOf(args[argsStartIndex].trim());
                Integer height = Integer.valueOf(args[argsStartIndex + 1].trim());

                Map<Figure, Integer> figures = new HashMap<>();
                for (int i = argsStartIndex + 2; i < args.length; i++) {
                    String arg = args[i].trim();
                    Figure figure = Figure.parseFigure(arg.charAt(0));
                    if (figure == null) {
                        System.err.println("Unrecognized figure: " + arg.charAt(0));
                    } else {
                        try {
                            figures.put(figure, Integer.parseInt(arg.substring(1).trim()));
                        } catch (NumberFormatException e) {
                            System.err.println("Unrecognized figure count: " + e.getMessage());
                        }
                    }
                }

                List<BoardPosition> boardPositions = listAllPositions(width, height, figures);
                System.out.println(boardPositions.size());
                for (BoardPosition position : boardPositions) {
                    char[][] field = new char[height][width];

                    for (BoardPosition.FigurePosition figurePosition : position.getFigures()) {
                        field[figurePosition.getRow()][figurePosition.getColumn()] =
                                figurePosition.getFigure().getShortName();
                    }

                    for (int row = 0; row < height; row++) {
                        for (int column = 0; column < width; column++) {
                            if (field[row][column] != '\0') {
                                System.out.print(field[row][column]);
                            } else {
                                System.out.print('.');
                            }
                        }
                        System.out.println();
                    }
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid format: " + e.getMessage());
            }
        }
    }
}