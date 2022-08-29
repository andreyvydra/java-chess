package figures.basic;

public class SpecificMovingPiece extends Piece {
    public int[][] moves;

    public SpecificMovingPiece(int[] coordinates, boolean isWhite, int[][] moves) {
        super(coordinates, isWhite);
        this.moves = moves;
    }

    @Override
    public boolean[][] getPossibleMoves(Piece[][] board) {
        boolean[][] possibleMoves = new boolean[board.length][board[0].length];
        for (int[] direction : this.moves) {
            int row = this.coordinates[0] + direction[0];
            int col = this.coordinates[1] + direction[1];
            if (this.outOfBoardLimits(row, col)) {
                continue;
            }

            if (board[row][col] == null) {
                possibleMoves[row][col] = true;
            } else if (board[row][col].getIsWhite() != this.getIsWhite()) {
                possibleMoves[row][col] = true;
            }
        }

        return possibleMoves;
    }
}