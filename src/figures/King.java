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
            int line = this.coordinates[0] + this.directions[i][0];
            int row = this.coordinates[1] + this.directions[i][1];
            if (!this.checkBoardLimits(row, line)) {
                continue;
            }

            if (board[line][row] == null) {
                possibleMoves[i][0] = line;
                possibleMoves[i][1] = row;
            } else if (board[line][row].getIsWhite() != this.getIsWhite()) {
                possibleMoves[i][0] = line;
                possibleMoves[i][1] = row;
            }
        }

        return possibleMoves;
    }
}
