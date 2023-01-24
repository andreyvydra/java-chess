package figures.basic;

public abstract class Piece implements PieceInterface {
    private final boolean isWhite;
    private boolean isFirstMove;
    private int[] coordinates;
    private String letterPiece;


    public Piece (int[] coordinates, boolean isWhite) {
        this.coordinates = coordinates;
        this.isWhite = isWhite;
        this.isFirstMove = true;
    }

    public void setLetterPiece(String letterPiece) {
        this.letterPiece = letterPiece;
    }

    public void setFirstMove(boolean firstMove) {
        isFirstMove = firstMove;
    }

    public void setCoordinates(int[] coordinates) {
        this.coordinates = coordinates;
    }

    public int[] getCoordinates() {
        return coordinates;
    }

    public int getRowCoordinate() {
        return coordinates[0];
    }

    public int getColCoordinate() {
        return coordinates[1];
    }

    public boolean isFirstMove() {
        return isFirstMove;
    }

    public String getLetterPiece() {
        return letterPiece;
    }

    @Override
    public String toString() {
        String color;
        if (this.isWhite()) {
            color = "w";
        } else {
            color = "b";
        }
        return color + this.letterPiece;
    }

    public boolean isWhite() {
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
