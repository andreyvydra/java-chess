package figures;

public class Bishop extends Piece {
    public Bishop(int[] coordinates, boolean isWhite) {
        super(coordinates, isWhite);
        this.directions = new int[][]{{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
        this.letterPiece = "B";
    }
}
