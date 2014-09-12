enum Figure {
    KING('K'), QUEEN('Q'), ROOK('R'), BISHOP('B'), KNIGHT('N');

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
}
