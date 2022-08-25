package figures;

import java.util.Arrays;

public class Piece {
    int moveX;
    int moveY;
    private boolean isWhite = true; // White
    boolean isFirstMove;
    int[] coordinates = new int[2];

    Piece () {
        this.isFirstMove = true;
    }

    public Piece (int[] coordinates, boolean isWhite) {
        this.coordinates = coordinates;
        this.isWhite = isWhite;
        this.isFirstMove = true;
    }

    public boolean getIsWhite() {
        return this.isWhite;
    }

    public boolean move(int rowDest, int lineDest, Piece[][] board) {

        if (!this.checkBoardLimits(rowDest, lineDest)) {
            return false;
        }

        int[] destPoint = {lineDest, rowDest};

        // Проверка на отсутствие вхождения клетки в возможные ходы
        if (Arrays.stream(this.getPossibleMoves(board)).noneMatch(x -> Arrays.equals(x, destPoint))) {
            return false;
        }

        this.isFirstMove = false;
        board[lineDest][rowDest] = this; // Смена фигур на поле
        board[this.coordinates[0]][this.coordinates[1]] = null;
        this.coordinates[0] = lineDest;
        this.coordinates[1] = rowDest;
        return true;
    }

    public int[][] getPossibleMoves(Piece[][] board) {
        return new int[0][0];
    }

    public boolean checkBoardLimits(int row, int line) {
        return (0 <= row && row <= 7) && (0 <= line && line <= 7);
    }
}
