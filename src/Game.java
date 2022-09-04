import core.Globals;
import figures.*;
import figures.basic.Piece;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;

public class Game {
    private boolean isWhite = true;
    private Piece[][] board;
    private Frame mainframe;
    public int colStart;
    public int rowStart;
    public int colDest;
    public int rowDest;
    public boolean move = false;

    public Piece[][] getBoard() {
        return board;
    }

    public void start() {
        Scanner reader = new Scanner(System.in);

        while (true) {
            this.buildBoard();
            this.mainframe = new Frame(this);
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
            try {

//                System.out.println("\b\b\b\b\b");
//                System.out.println(Globals.outsideBoard);
//                for (int i = 0; i < this.board.length; i++) {
//                    System.out.print(this.board.length - i + " ");
//                    for (Piece piece : this.board[i]) {
//                        if (piece != null) {
//                            System.out.print("| " + piece + " ");
//                        } else {
//                            System.out.print("|    ");
//                        }
//                    }
//                    System.out.println("|");
//                    if (i != this.board.length - 1) {
//                        System.out.println(Globals.insideBoard);
//                    }
//                }
//                System.out.println(Globals.outsideBoard);
//                System.out.println(Globals.lettersUnderBoard);
//                System.out.print("Введите ваш ход (пример: e2 e4): ");
//                String move = reader.nextLine().strip();
//
//                if (Objects.equals(move.toLowerCase(), "00") || Objects.equals(move.toLowerCase(), "oo")) {
//                    if (shortCastle()) {
//                        this.isWhite = !this.isWhite;
//                    }
//                    continue;
//                } else if (Objects.equals(move.toLowerCase(), "000") || Objects.equals(move.toLowerCase(), "ooo")) {
//                    if (longCastle()) {
//                        this.isWhite = !this.isWhite;
//                    }
//                    continue;
//                }
                System.out.println(this.move);
                if (!this.move) {
                    continue;
                }

                int col = this.colStart;
                int row = this.rowStart;
                int colDest = this.colDest;
                int rowDest = this.rowDest;

                Piece piece = this.board[row][col];
                if ((piece != null && piece.getIsWhite() == this.isWhite) &&
                        !this.isKingAttacked(true) && !this.isKingAttacked(false)) {
                    System.out.println(4);
                    if (piece.move(rowDest, colDest, this.board)) {
                        this.isWhite = !this.isWhite;
                    }
                } else if ((this.isKingAttacked(true) || this.isKingAttacked(false)) &&
                        piece != null && piece.getIsWhite() == this.isWhite) {
                    if (this.escapeCheck(piece, rowDest, colDest)) {
                        this.isWhite = !this.isWhite;
                    }
                }

                String res = checkMate();
                if (!Objects.equals(res, "Draw")) {
                    System.out.println(res);
                    break;
                }
                this.mainframe.createField(this);
                this.mainframe.pack();
                this.colStart = 0;
                this.colDest = 0;
                this.rowStart = 0;
                this.rowDest = 0;
                this.move = false;

            } catch (Exception exc) {
                System.out.println(Globals.incorrectInput);
            }
        }
    }

    public boolean shortCastle() {
        int kingRow = this.isWhite ? 7 : 0;
        if (this.board[kingRow][5] == null && this.board[kingRow][6] == null) {
            Piece rook = this.board[kingRow][7];
            Piece king = this.board[kingRow][4];
            if (rook instanceof Rook && king instanceof King) {
                if (rook.isFirstMove && king.isFirstMove) {
                    rook.coordinates = new int[]{kingRow, 5};
                    king.coordinates = new int[]{kingRow, 6};
                    this.board[kingRow][4] = null;
                    this.board[kingRow][7] = null;
                    this.board[kingRow][5] = rook;
                    this.board[kingRow][6] = king;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean longCastle() {
        int kingRow = this.isWhite ? 7 : 0;
        if (this.board[kingRow][1] == null && this.board[kingRow][2] == null && this.board[kingRow][3] == null) {
            Piece rook = this.board[kingRow][0];
            Piece king = this.board[kingRow][4];
            if (rook instanceof Rook && king instanceof King) {
                if (rook.isFirstMove && king.isFirstMove) {
                    rook.coordinates = new int[]{kingRow, 5};
                    king.coordinates = new int[]{kingRow, 6};
                    this.board[kingRow][0] = null;
                    this.board[kingRow][4] = null;
                    this.board[kingRow][2] = king;
                    this.board[kingRow][3] = rook;
                    return true;
                }
            }
        }
        return false;
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

    public boolean isKingAttacked(boolean isWhite) {
        King king = this.getKings()[isWhite ? 1 : 0];
        boolean[][] attackedCells = this.getAttackedCells(!isWhite);
        return attackedCells[king.coordinates[0]][king.coordinates[1]];
    }

    public boolean escapeCheck(Piece piece, int rowDest, int colDest) {
        if (!piece.getPossibleMoves(this.board)[rowDest][colDest]) {
            return false;
        }

        int[] startPos = piece.coordinates;
        this.board[startPos[0]][startPos[1]] = null;
        this.board[rowDest][colDest] = piece;
        piece.coordinates = new int[]{rowDest, colDest};
        if (this.isKingAttacked(piece.getIsWhite())) {
            this.board[rowDest][colDest] = null;
            this.board[startPos[0]][startPos[1]] = piece;
            piece.coordinates = startPos;
            return false;
        }
        return true;
    }

    public boolean isMateForKing(boolean isWhite) {
        King king = this.getKings()[isWhite ? 1 : 0];
        boolean[][] kingMoves = king.getPossibleMoves(this.board);
        boolean[][] attackedCells;
        int[] startKingPosition = king.coordinates;

        if (!this.isKingAttacked(isWhite)) {
            return false;
        }
        for (Piece[] pieces : this.board) {
            for (Piece piece : pieces) {
                if (piece != null) {
                    int[] startPos = piece.coordinates;
                    boolean[][] possibleMoves = piece.getPossibleMoves(this.board);
                    for (int i = 0; i < possibleMoves.length; i++) {
                        for (int j = 0; j < possibleMoves.length; j++) {
                            if (possibleMoves[i][j]) {
                                this.board[startPos[0]][startPos[1]] = null;
                                Piece curPiece = this.board[i][j];
                                if (!this.getAttackedCells(!isWhite)[king.coordinates[0]][king.coordinates[1]]) {
                                    this.board[startPos[0]][startPos[1]] = piece;
                                    this.board[i][j] = curPiece;
                                    return false;
                                }
                                this.board[startPos[0]][startPos[1]] = piece;
                                this.board[i][j] = curPiece;
                            }
                        }
                    }
                }
            }
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
        for (Piece[] row : this.board) {
            for (Piece piece : row) {
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
