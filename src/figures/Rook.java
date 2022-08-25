package figures;

public class Rook extends Piece {

    public Rook(int[] coordinates, boolean isWhite) {
        super(coordinates, isWhite);
        this.moveX = 1;
        this.moveY = 1;
    }

    @Override
    public String toString() {
        String color;
        if (this.getIsWhite()) {
            color = "w";
        } else {
            color = "b";
        }
        return color + "R";
    }

    public int[][] getPossibleMoves(Piece[][] board) {
        int[][] possibleMoves = new int[16][2];
        int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        short counter = 0;
        for (int[] direction : directions) {
            for (int j = 1; j < board.length; j++) {
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
}
