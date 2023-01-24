package figures;
import figures.basic.SpecificMovingPiece;

public class King extends SpecificMovingPiece {

    // King should say is he attacked
    public King(int[] coordinates, boolean isWhite) {
        super(coordinates, isWhite, new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1},
                {1, 1}, {-1, 1}, {1, -1}, {-1, -1}});
        this.setLetterPiece("K");
    }
}
