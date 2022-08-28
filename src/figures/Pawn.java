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
        int rowDest = this.coordinates[0] + this.longMoveY * coefficientColor;
        int colDest = this.coordinates[1];
        Piece piece = board[rowDest][colDest];
        if (this.isFirstMove && piece == null) {
            moves[0][0] = rowDest;
            moves[0][1] = colDest;
        }

        rowDest = this.coordinates[0] + coefficientColor; // rowDest остается таким же
        piece = board[rowDest][colDest];
        if (piece == null) {
            moves[1][0] = rowDest;
            moves[1][1] = colDest;
        }

        return moves;
    }

    public int[][] getEatMoves(Piece[][] board) {
        int[][] moves = new int[2][2]; // Только 2 хода, чтобы съесть
        int coefficientColor = this.getColorCoefficient();

        int row = this.coordinates[0];
        int col = this.coordinates[1];

        int rowDest = row + coefficientColor;
        int colDest = col + coefficientColor;
        if (rowDest >= 0 && rowDest <= 7 && colDest >= 0 && colDest <= 7) {
            Piece piece = board[rowDest][colDest];
            if (piece != null && piece.getIsWhite() != this.getIsWhite()) {
                moves[0][0] = rowDest;
                moves[0][1] = colDest;
            }
        }

        colDest = col - coefficientColor;
        if (rowDest >= 0 && rowDest <= 7 && colDest >= 0 && colDest <= 7) {
            Piece piece = board[rowDest][colDest];
            if (piece != null && piece.getIsWhite() != this.getIsWhite()) {
                moves[1][0] = rowDest;
                moves[1][1] = colDest;
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