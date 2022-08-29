package figures;

import figures.basic.DirectionMovingPiece;

public class Rook extends DirectionMovingPiece {
    public Rook(int[] coordinates, boolean isWhite) {
        super(coordinates, isWhite, new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}});
        this.letterPiece = "R";
    }


}
