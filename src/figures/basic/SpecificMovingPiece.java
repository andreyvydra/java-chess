package figures.basic;

public class SpecificMovingPiece extends Piece {
    private final int[][] moves;

    public SpecificMovingPiece(int[] coordinates, boolean isWhite, int[][] moves) {
        super(coordinates, isWhite);
        this.moves = moves;
    }

    @Override
    public boolean[][] getPossibleMoves(Piece[][] board) {
        boolean[][] possibleMoves = new boolean[board.length][board[0].length];
        for (int[] direction : this.moves) {
            int row = this.getRowCoordinate() + direction[0];
            int col = this.getColCoordinate() + direction[1];
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