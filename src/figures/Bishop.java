package figures;

import figures.basic.DirectionMovingPiece;

public class Bishop extends DirectionMovingPiece {
    public Bishop(int[] coordinates, boolean isWhite) {
        super(coordinates, isWhite, new int[][]{{1, 1}, {1, -1}, {-1, 1}, {-1, -1}});
        this.setLetterPiece("B");
    }
}
