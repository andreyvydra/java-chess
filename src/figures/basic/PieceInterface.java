package figures.basic;

public interface PieceInterface {
    boolean move(int rowDest, int colDest, Piece[][] board);
    boolean[][] getPossibleMoves(Piece[][] board);
}
