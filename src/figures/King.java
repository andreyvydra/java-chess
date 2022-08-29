package figures;
import figures.basic.SpecificMovingPiece;

public class King extends SpecificMovingPiece {
    public King(int[] coordinates, boolean isWhite) {
        super(coordinates, isWhite, new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1},
                {1, 1}, {-1, 1}, {1, -1}, {-1, -1}});
        this.letterPiece = "K";
    }
}
