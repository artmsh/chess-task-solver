import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class PositionsCounterTest {
    private PositionsCounter positionsCounter;

    @Before
    public void setUp() {
        positionsCounter = new PositionsCounter();
    }

    @Test
    public void testCase1() {
        Map<Figure, Integer> figures = new HashMap<>();
        figures.put(Figure.ROOK, 1);
        figures.put(Figure.KING, 2);
        Set<BoardPosition> boardPositions = positionsCounter.countAllPositions(3, 3, figures);
        assertEquals(4, boardPositions.size());

        List<BoardPosition> expectedBoardPositions = new ArrayList<>();
        BoardPosition bp1 = new BoardPosition(3, 3);
        bp1.pushFigure(new BoardPosition.FigurePosition(Figure.KING, 0, 0));
        bp1.pushFigure(new BoardPosition.FigurePosition(Figure.KING, 0, 2));
        bp1.pushFigure(new BoardPosition.FigurePosition(Figure.ROOK, 2, 1));

        BoardPosition bp2 = new BoardPosition(3, 3);
        bp2.pushFigure(new BoardPosition.FigurePosition(Figure.KING, 0, 0));
        bp2.pushFigure(new BoardPosition.FigurePosition(Figure.KING, 2, 0));
        bp2.pushFigure(new BoardPosition.FigurePosition(Figure.ROOK, 1, 2));

        BoardPosition bp3 = new BoardPosition(3, 3);
        bp3.pushFigure(new BoardPosition.FigurePosition(Figure.KING, 0, 2));
        bp3.pushFigure(new BoardPosition.FigurePosition(Figure.KING, 2, 2));
        bp3.pushFigure(new BoardPosition.FigurePosition(Figure.ROOK, 1, 0));

        BoardPosition bp4 = new BoardPosition(3, 3);
        bp4.pushFigure(new BoardPosition.FigurePosition(Figure.KING, 2, 0));
        bp4.pushFigure(new BoardPosition.FigurePosition(Figure.KING, 2, 2));
        bp4.pushFigure(new BoardPosition.FigurePosition(Figure.ROOK, 0, 1));

        expectedBoardPositions.add(bp1);
        expectedBoardPositions.add(bp2);
        expectedBoardPositions.add(bp3);
        expectedBoardPositions.add(bp4);

        boardPositions.retainAll(expectedBoardPositions);
        assertEquals(4, boardPositions.size());
    }

    @Test
    public void testCase2() {
        Map<Figure, Integer> figures = new HashMap<>();
        figures.put(Figure.ROOK, 2);
        figures.put(Figure.KNIGHT, 4);
        Set<BoardPosition> boardPositions = positionsCounter.countAllPositions(4, 4, figures);
        assertEquals(8, boardPositions.size());
    }

    @Test
    public void testCase8Queens() {
        Map<Figure, Integer> figures = new HashMap<>();
        figures.put(Figure.QUEEN, 8);
        Set<BoardPosition> boardPositions = positionsCounter.countAllPositions(8, 8, figures);
        assertEquals(92, boardPositions.size());
    }
}
