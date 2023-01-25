import core.Globals;
import figures.*;
import figures.basic.Piece;

import java.util.Objects;

public class Board {

    private Piece[][] board;


    Board () {
        this.build();
    }

    public Piece[][] getBoard() {
        return board;
    }


    public Piece getPiece(int row, int col) {
        return this.getBoard()[row][col];
    }

    public King getKing(boolean isWhite) {
        for (Piece[] row : this.board) {
            for (Piece piece : row) {
                if (piece != null) {
                    if (piece instanceof King && piece.isWhite() == isWhite) {
                        return (King) piece;
                    }
                }
            }
        }
        return null;
    }

    public void movePiece(Piece piece, int row, int col) {
        piece.move(row, col, this.board); // Change position and update board
    }

    public void setPiece(Piece piece, int row, int col) {
        this.board[row][col] = piece;
        if (piece != null) {
            piece.setCoordinates(new int[]{row, col});
        }
    }

    public void build() {
        String[][] field = Globals.standardField.clone();
        this.board = new Piece[8][8];

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                if (field[i][j].equals("")) {
                    continue;
                }
                int[] coordinates = {i, j};
                char color = field[i][j].charAt(0);
                char piece = field[i][j].charAt(1);

                boolean isWhite = color == 'w';

                if (piece == 'P') {
                    Pawn pawn = new Pawn(coordinates, isWhite);
                    this.board[i][j] = pawn;
                } else if (piece == 'R') {
                    Rook rook = new Rook(coordinates, isWhite);
                    this.board[i][j] = rook;
                } else if (piece == 'B') {
                    Bishop bishop = new Bishop(coordinates, isWhite);
                    this.board[i][j] = bishop;
                } else if (piece == 'N') {
                    Knight knight = new Knight(coordinates, isWhite);
                    this.board[i][j] = knight;
                } else if (piece == 'Q') {
                    Queen queen = new Queen(coordinates, isWhite);
                    this.board[i][j] = queen;
                } else if (piece == 'K') {
                    King king = new King(coordinates, isWhite);
                    this.board[i][j] = king;
                }
            }
        }
    }

    public void display() {
        System.out.println("\b\b\b\b\b");
        System.out.println(Globals.outsideBoard);
        for (int i = 0; i < this.board.length; i++) {
            System.out.print(this.board.length - i + " ");
            for (Piece piece : this.board[i]) {
                if (piece != null) {
                    System.out.print("| " + piece + " ");
                } else {
                    System.out.print("|    ");
                }
            }
            System.out.println("|");
            if (i != this.board.length - 1) {
                System.out.println(Globals.insideBoard);
            }
        }
        System.out.println(Globals.outsideBoard);
        System.out.println(Globals.lettersUnderBoard);
        System.out.print("Введите ваш ход (пример: e2 e4): ");
    }
}
