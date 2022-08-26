package figures;

public class Rook extends Piece {
    public Rook(int[] coordinates, boolean isWhite) {
        super(coordinates, isWhite);
        this.directions = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        this.letterPiece = "R";
    }


}
