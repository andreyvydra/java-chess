package figures;

public class Queen extends Piece {
    public Queen(int[] coordinates, boolean isWhite) {
        super(coordinates, isWhite);
        this.directions = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1},
                {1, 1}, {-1, 1}, {1, -1}, {-1, -1}};
        this.letterPiece = "Q";
    }
}
