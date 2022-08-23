package figures;

public class Piece {
    int moveX;
    int moveY;
    boolean isWhite = true; // White
    boolean isFirstMove;
    int[] coordinates = new int[2];

    Piece () {
        this.isFirstMove = true;
    }

    public Piece (int[] coordinates, boolean isWhite) {
        this.coordinates = coordinates;
        this.isWhite = isWhite;
        this.isFirstMove = true;
    };

    public boolean move(int rowDest, int lineDest) {
        return false;
    }

}
