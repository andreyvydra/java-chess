package figures;

import figures.basic.SpecificMovingPiece;

public class Knight extends SpecificMovingPiece {
    public Knight(int[] coordinates, boolean isWhite) {
        super(coordinates, isWhite, new int[][]{{2, 1}, {-2, 1}, {2, -1}, {-2, -1},
                {1, 2}, {-1, 2}, {1, -2}, {-1, -2}});
        this.setLetterPiece("N");
    }

}
