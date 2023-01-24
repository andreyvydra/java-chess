package figures;


import figures.basic.Piece;


public class Pawn extends Piece {

    public Pawn(int[] coordinates, boolean isWhite) {
        super(coordinates, isWhite);
        this.setLetterPiece("P");
    }

    public boolean[][] getPossibleMoves(Piece[][] board) {
        boolean[][] straightMoves = this.getStraightMoves(board);
        boolean[][] eatMoves = this.getEatMoves(board);
        boolean[][] allMoves = new boolean[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                allMoves[i][j] = eatMoves[i][j] || straightMoves[i][j];
            }
        }

        return allMoves;
    }

    public boolean[][] getStraightMoves(Piece[][] board) {
        boolean[][] moves = new boolean[board.length][board[0].length]; // Только 2 хода вперёд
        int coefficientColor = this.getColorCoefficient();

        // Проверка на отсутствие фигуры и первого хода
        int longMoveY = 2;
        int rowDest = this.getRowCoordinate() + longMoveY * coefficientColor;
        int colDest = this.getColCoordinate();
        Piece piece = board[rowDest][colDest];
        Piece piece1 = board[rowDest - coefficientColor][colDest];
        if (this.isFirstMove() && piece == null && piece1 == null) {
            moves[rowDest][colDest] = true;
        }

        rowDest = this.getRowCoordinate() + coefficientColor; // colDest остается таким же
        piece = board[rowDest][colDest];
        if (piece == null) {
            moves[rowDest][colDest] = true;
        }

        return moves;
    }

    public boolean[][] getEatMoves(Piece[][] board) {
        boolean[][] moves = new boolean[board.length][board[0].length];
        int coefficientColor = this.getColorCoefficient();

        int row = this.getRowCoordinate();
        int col = this.getColCoordinate();

        int rowDest = row + coefficientColor;
        int colDest = col + coefficientColor;

        if (!this.outOfBoardLimits(rowDest, colDest)) {
            Piece piece = board[rowDest][colDest];
            if (piece != null && piece.isWhite() != this.isWhite()) {
               moves[rowDest][colDest] = true;
            }
        }

        colDest = col - coefficientColor;
        if (!this.outOfBoardLimits(rowDest, colDest)) {
            Piece piece = board[rowDest][colDest];
            if (piece != null && piece.isWhite() != this.isWhite()) {
                moves[rowDest][colDest] = true;
            }
        }
        return moves;
    }

    public boolean[][] getPredictableEatMoves(Piece[][] board) {
        boolean[][] moves = new boolean[board.length][board[0].length]; // Только 2 хода, чтобы съесть
        int coefficientColor = this.getColorCoefficient();

        int row = this.getRowCoordinate();
        int col = this.getColCoordinate();

        int rowDest = row + coefficientColor;
        int colDest = col + coefficientColor;

        if (!this.outOfBoardLimits(rowDest, colDest)) {
            Piece piece = board[rowDest][colDest];
            if (piece == null) {
                moves[rowDest][colDest] = true;
            }
        }

        colDest = col - coefficientColor;
        if (!this.outOfBoardLimits(rowDest, colDest)) {
            Piece piece = board[rowDest][colDest];
            if (piece == null) {
                moves[rowDest][colDest] = true;
            }
        }
        return moves;
    }

    public int getColorCoefficient() {
        int coefficientColor;
        if (this.isWhite()) {
            coefficientColor = -1;
        } else {
            coefficientColor = 1;
        }
        return coefficientColor;
    }
}