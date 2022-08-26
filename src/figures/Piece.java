package figures;

import java.util.Arrays;

public class Piece {
    private boolean isWhite;
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
        int[][] possibleMoves = new int[24][2];
        short counter = 0;
        for (int[] direction : this.directions) {
            for (int j = 1; j <= 8; j++) {
                int line = this.coordinates[0] + direction[0] * j;
                int row = this.coordinates[1] + direction[1] * j;

                if (!this.checkBoardLimits(row, line)) {
                    break;
                }
                if (board[line][row] == null) {
                    possibleMoves[counter][0] = line;
                    possibleMoves[counter][1] = row;
                } else if (board[line][row].getIsWhite() != this.getIsWhite()) {
                    possibleMoves[counter][0] = line;
                    possibleMoves[counter][1] = row;
                } else {
                    break;
                }
                counter++;
            }
        }
        return possibleMoves;
    }

    public boolean checkBoardLimits(int row, int line) {
        return (0 <= row && row <= 7) && (0 <= line && line <= 7);
    }


}
