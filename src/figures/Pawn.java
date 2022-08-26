package figures;


import java.util.Arrays;


public class Pawn extends Piece {
    int longMoveY = 2;

    public Pawn(int[] coordinates, boolean isWhite) {
        super(coordinates, isWhite);
        this.letterPiece = "P";
    }

    public int[][] getPossibleMoves(Piece[][] board) {
        int[][] straightMoves = this.getStraightMoves(board);
        int[][] eatMoves = this.getEatMoves(board);

        // Конкатинация двух массивов
        int[][] allMoves = Arrays.copyOf(straightMoves, straightMoves.length + eatMoves.length);
        System.arraycopy(eatMoves, 0, allMoves, straightMoves.length, eatMoves.length);
        return allMoves;
    }

    public int[][] getStraightMoves(Piece[][] board) {
        int[][] moves = new int[2][2]; // Только 2 хода вперёд
        int coefficientColor = this.getColorCoefficient();

        // Проверка на отсутствие фигуры и первого хода
        int lineDest = this.coordinates[0] + this.longMoveY * coefficientColor;
        int rowDest = this.coordinates[1];
        Piece piece = board[lineDest][rowDest];
        if (this.isFirstMove && piece == null) {
            moves[0][0] = lineDest;
            moves[0][1] = rowDest;
        }

        lineDest = this.coordinates[0] + coefficientColor; // rowDest остается таким же
        piece = board[lineDest][rowDest];
        if (piece == null) {
            moves[1][0] = lineDest;
            moves[1][1] = rowDest;
        }

        return moves;
    }

    public int[][] getEatMoves(Piece[][] board) {
        int[][] moves = new int[2][2]; // Только 2 хода, чтобы съесть
        int coefficientColor = this.getColorCoefficient();

        int line = this.coordinates[0];
        int row = this.coordinates[1];

        int lineDest = line + coefficientColor;
        int rowDest = row + coefficientColor;
        if (lineDest >= 0 && lineDest <= 7 && rowDest >= 0 && rowDest <= 7) {
            Piece piece = board[lineDest][rowDest];
            if (piece != null && piece.getIsWhite() != this.getIsWhite()) {
                moves[0][0] = lineDest;
                moves[0][1] = rowDest;
            }
        }

        rowDest = row - coefficientColor;
        if (lineDest >= 0 && lineDest <= 7 && rowDest >= 0 && rowDest <= 7) {
            Piece piece = board[lineDest][rowDest];
            if (piece != null && piece.getIsWhite() != this.getIsWhite()) {
                moves[1][0] = lineDest;
                moves[1][1] = rowDest;
            }
        }
        return moves;
    }

    public int getColorCoefficient() {
        int coefficientColor;
        if (this.getIsWhite()) {
            coefficientColor = -1;
        } else {
            coefficientColor = 1;
        }
        return coefficientColor;
    }
}