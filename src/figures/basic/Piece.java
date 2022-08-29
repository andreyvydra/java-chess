package figures.basic;

public abstract class Piece implements PieceInterface {
    private final boolean isWhite;
    public boolean isFirstMove;
    public int[] coordinates;
    public String letterPiece;

    public Piece (int[] coordinates, boolean isWhite) {
        this.coordinates = coordinates;
        this.isWhite = isWhite;
        this.isFirstMove = true;
    }

    @Override
    public String toString() {
        String color;
        if (this.getIsWhite()) {
            color = "w";
        } else {
            color = "b";
        }
        return color + this.letterPiece;
    }

    public boolean getIsWhite() {
        return this.isWhite;
    }

    public boolean move(int rowDest, int colDest, Piece[][] board) {

        if (this.outOfBoardLimits(rowDest, colDest)) {
            return false;
        }

        // Проверка на отсутствие вхождения клетки в возможные ходы
        if (!this.getPossibleMoves(board)[rowDest][colDest]) {
            return false;
        }

        this.isFirstMove = false;
        board[rowDest][colDest] = this; // Смена фигур на поле
        board[this.coordinates[0]][this.coordinates[1]] = null;
        this.coordinates[0] = rowDest;
        this.coordinates[1] = colDest;
        return true;
    }

    public abstract boolean[][] getPossibleMoves(Piece[][] board);

    public boolean outOfBoardLimits(int row, int col) {
        return (0 > row || row > 7) || (0 > col || col > 7);
    }


}
