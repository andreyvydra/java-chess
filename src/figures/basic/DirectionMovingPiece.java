package figures.basic;

public class DirectionMovingPiece extends Piece {
    public int[][] directions;

    public DirectionMovingPiece(int[] coordinates, boolean isWhite, int[][] directions) {
        super(coordinates, isWhite);
        this.directions = directions;
    }

    @Override
    public boolean[][] getPossibleMoves(Piece[][] board) {
        boolean[][] possibleMoves = new boolean[board.length][board[0].length];
        for (int[] direction : this.directions) {
            for (int j = 1; j <= 8; j++) {
                int row = this.getRowCoordinate() + direction[0] * j;
                int col = this.getColCoordinate() + direction[1] * j;

                if (this.outOfBoardLimits(row, col)) {
                    break;
                }
                if (board[row][col] == null) {
                    possibleMoves[row][col] = true;
                } else if (board[row][col].getIsWhite() != this.getIsWhite()) {
                    possibleMoves[row][col] = true;
                    break;
                } else {
                    break;
                }
            }
        }
        return possibleMoves;
    }
}
