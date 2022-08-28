package figures;

import java.util.Arrays;

public class Piece {
    private final boolean isWhite;
    boolean isFirstMove;
    public int[] coordinates;
    int[][] directions;

    String letterPiece;

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

        int[] destPoint = {rowDest, colDest};

        // Проверка на отсутствие вхождения клетки в возможные ходы
        if (Arrays.stream(this.getPossibleMoves(board)).noneMatch(x -> Arrays.equals(x, destPoint))) {
            return false;
        }

        this.isFirstMove = false;
        board[rowDest][colDest] = this; // Смена фигур на поле
        board[this.coordinates[0]][this.coordinates[1]] = null;
        this.coordinates[0] = rowDest;
        this.coordinates[1] = colDest;
        return true;
    }

    public int[][] getPossibleMoves(Piece[][] board) {
        int[][] possibleMoves = new int[24][2];
        byte counter = 0;
        for (int[] direction : this.directions) {
            for (int j = 1; j <= 8; j++) {
                int row = this.coordinates[0] + direction[0] * j;
                int col = this.coordinates[1] + direction[1] * j;

                if (this.outOfBoardLimits(row, col)) {
                    break;
                }
                if (board[row][col] == null) {
                    possibleMoves[counter][0] = row;
                    possibleMoves[counter][1] = col;
                } else if (board[row][col].getIsWhite() != this.getIsWhite()) {
                    possibleMoves[counter][0] = row;
                    possibleMoves[counter][1] = col;
                    counter++;
                    break;
                } else {
                    break;
                }
                counter++;
            }
        }
        return possibleMoves;
    }

    public boolean outOfBoardLimits(int row, int col) {
        return (0 > row || row > 7) || (0 > col || col > 7);
    }


}
