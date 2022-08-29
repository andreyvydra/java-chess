package figures;

import figures.basic.DirectionMovingPiece;

public class Queen extends DirectionMovingPiece {
    public Queen(int[] coordinates, boolean isWhite) {
        super(coordinates, isWhite, new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1},
                {1, 1}, {-1, 1}, {1, -1}, {-1, -1}});
        this.letterPiece = "Q";
    }
}
