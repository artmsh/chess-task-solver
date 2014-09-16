import java.util.*;

public class ChessSolver {

    private static class Arguments {
        boolean printOnlyCombinationCount;
        int width, height;
        Map<Figure, Integer> figures;

        private Arguments(boolean printOnlyCombinationCount, int width, int height, Map<Figure, Integer> figures) {
            this.printOnlyCombinationCount = printOnlyCombinationCount;
            this.width = width;
            this.height = height;
            this.figures = figures;
        }
    }

    private static void showUsage() {
        System.out.println("Usage: java ChessSolver [-n] [board-width] [board-height] [list-of-figures]");
        System.exit(0);
    }

    private static Arguments parseArguments(String[] args) {
        boolean onlyCombinationCount = false;
        int argsStartIndex = 0;
        if (args.length > 0) {
            if (args[0].trim().equals("-n")) {
                onlyCombinationCount = true;
                argsStartIndex++;
            }

            if (args.length >= argsStartIndex + 3) {
                try {
                    Integer width = Integer.valueOf(args[argsStartIndex].trim());
                    Integer height = Integer.valueOf(args[argsStartIndex + 1].trim());

                    if (width <= 0 || height <= 0) {
                        throw new RuntimeException("Board width and height should be non-zero positive");
                    }

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

                    return new Arguments(onlyCombinationCount, width, height, figures);
                } catch (NumberFormatException e) {
                    throw new RuntimeException("Invalid format: " + e.getMessage());
                }
            } else showUsage();
        } else showUsage();

        return null;
    }

    public static void main(String[] args) {
        try {
            Arguments arguments = parseArguments(args);
            PositionsCounter positionsCounter = new PositionsCounter();
            Set<BoardPosition> positions = positionsCounter.countAllPositions(
                    arguments.width, arguments.height, arguments.figures);

            System.out.println(positions.size());
            if (!arguments.printOnlyCombinationCount) {
                for (BoardPosition position : positions) {
                    System.out.println(position);
                }
            }
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
        }
    }
}