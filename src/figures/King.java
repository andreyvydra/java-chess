package figures;

public class King extends Piece {
    public King(int[] coordinates, boolean isWhite) {
        super(coordinates, isWhite);
        this.directions = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1},
                {1, 1}, {-1, 1}, {1, -1}, {-1, -1}};
        this.letterPiece = "K";
    }

    @Override
    public int[][] getPossibleMoves(Piece[][] board) {
        int[][] possibleMoves = new int[8][2];
        for (int i = 0; i < this.directions.length; i++) {
            int row = this.coordinates[0] + this.directions[i][0];
            int col = this.coordinates[1] + this.directions[i][1];
            if (this.outOfBoardLimits(row, col)) {
                continue;
            }

            if (board[row][col] == null) {
                possibleMoves[i][0] = row;
                possibleMoves[i][1] = col;
            } else if (board[row][col].getIsWhite() != this.getIsWhite()) {
                possibleMoves[i][0] = row;
                possibleMoves[i][1] = col;
            }
        }

        return possibleMoves;
    }
}
