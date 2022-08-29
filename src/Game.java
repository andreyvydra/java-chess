import core.Globals;
import figures.*;
import figures.basic.Piece;

import java.util.*;

public class Game {
    private boolean isWhite = true;
    private Piece[][] board;

    public void start() {
        Scanner reader = new Scanner(System.in);

        while (true) {
            this.buildBoard();
            this.mainLoop();
            System.out.println("Рестарт игры - r\nЗавершить игру - любая");
            String line = reader.nextLine();
            if (Objects.equals(line, "r")) {
                this.isWhite = true;
                continue;
            }
            break;
        }

    }

    public void buildBoard() {
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

    public void mainLoop() {
        Scanner reader = new Scanner(System.in);

        while (true) {
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
            String move = reader.nextLine();

            int col = Globals.letters.indexOf(move.charAt(0));
            int row = this.board.length - Integer.parseInt(move.substring(1, 2));
            int colDest = Globals.letters.indexOf(move.charAt(3));
            int rowDest = this.board.length - Integer.parseInt(move.substring(4, 5));


            if (this.board[row][col] != null && this.board[row][col].getIsWhite() == this.isWhite) {
                Piece piece = this.board[row][col];
                if (piece.move(rowDest, colDest, this.board)) {
                    this.isWhite = !this.isWhite;
                }
            }
            String res = checkMate();
            if (!Objects.equals(res, "Draw")) {
                System.out.println(res);
                break;
            }

        }
    }

    public String checkMate() {
        if (this.isMateForKing(true)) {
            return "Black Wins!";
        }

        if (this.isMateForKing(false)) {
            return "White Wins!";
        }

        return "Draw";
    }

    public boolean isMateForKing(boolean isWhite) {
        King king = this.getKings()[isWhite ? 1 : 0];
        boolean[][] kingMoves = king.getPossibleMoves(this.board);
        boolean[][] attackedCells = this.getAttackedCells(!isWhite);
        int[] startKingPosition = king.coordinates;

        if (!attackedCells[king.coordinates[0]][king.coordinates[1]]) {
            return false;
        }

        for (int i = 0; i < kingMoves.length; i++) {
            for (int j = 0; j < kingMoves[0].length; j++) {
                if (!kingMoves[i][j]) {
                    continue;
                }

                int[] curKingCoordinates = king.coordinates;
                king.coordinates = new int[]{i, j};
                this.board[i][j] = king;
                this.board[curKingCoordinates[0]][curKingCoordinates[1]] = null;
                attackedCells = this.getAttackedCells(!isWhite);

                if (!attackedCells[i][j]) {
                    this.board[startKingPosition[0]][startKingPosition[1]] = king;
                    this.board[i][j] = null;
                    return false;
                }
            }
        }

        return true;
    }

    public boolean[][] getAttackedCells(boolean isWhite) {
        boolean[][] attackedCells = new boolean[this.board.length][this.board[0].length];
        for (Piece[] row: this.board) {
            for (Piece piece: row) {
                if (piece != null && piece.getIsWhite() == isWhite) {
                    boolean[][] possibleMoves = piece.getPossibleMoves(this.board);
                    if (piece instanceof Pawn) { // Pawn может атаковать только клетки, которые она съедает
                        boolean[][] pawnMoves = ((Pawn) piece).getPredictableEatMoves(this.board);
                        for (int i = 0; i < possibleMoves.length; i++) {
                            System.arraycopy(pawnMoves[i], 0, possibleMoves[i], 0, possibleMoves[0].length);
                        }
                    }
                    for (int i = 0; i < possibleMoves.length; i++) {
                        for (int j = 0; j < possibleMoves[0].length; j++) {
                            if (possibleMoves[i][j]) {
                                attackedCells[i][j] = true;
                            }
                        }
                    }

                }
            }
        }
        return attackedCells;
    }

    public King[] getKings() {
        King whiteKing = new King(new int[]{}, true);
        King blackKing = new King(new int[]{}, false);
        for (Piece[] line : this.board) {
            for (Piece piece : line) {
                if (piece instanceof King) {
                    if (piece.getIsWhite()) {
                        whiteKing = (King) piece;
                    } else {
                        blackKing = (King) piece;
                    }
                }
            }
        }
        return new King[]{blackKing, whiteKing};
    }
}
