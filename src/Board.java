import figures.Piece;

public class Board {
    private Piece[][] board;


    Board () {
        Piece[][] board = new Piece[8][8];
        this.setBoard(board);

    }

    public Piece[][] getBoard() {
        return board;
    }

    public void setBoard(Piece[][] board) {
        this.board = board;
    }
}
