import core.Globals;
import figures.*;
import figures.basic.Piece;

import java.util.*;

public class Game {
    private boolean isWhite = true;
    private Board board;
    private int colStart;
    private int rowStart;
    private int colDest;
    private int rowDest;


    public Game() {
        this.board = new Board();
    }

    public Board getBoard() {
        return this.board;
    }

    public int getColStart() {
        return colStart;
    }

    public int getRowStart() {
        return rowDest;
    }

    public int getColDest() {
        return rowDest;
    }

    public int getRowDest() {
        return rowDest;
    }

    public void setColStart(int colStart) {
        this.colStart = colStart;
    }

    public void setRowStart(int rowStart) {
        this.rowStart = rowStart;
    }

    public void setColDest(int colDest) {
        this.colDest = colDest;
    }

    public void setRowDest(int rowDest) {
        this.rowDest = rowDest;
    }

    public void start(Object e) {
        this.isWhite = true;
        this.mainLoop();
    }

    public void mainLoop() {
        Scanner reader = new Scanner(System.in);

        while (true) {
            try {
                this.board.display();

                String move = reader.nextLine().strip();

                if (this.castling(move) && !this.isKingAttacked(this.isWhite)) {
                    continue;
                }

                this.processInputAndMove(move);

                String res = this.checkMate();
                if (!Objects.equals(res, "Draw")) {
                    System.out.println(res);
                    break;
                }

            } catch (Exception exc) {
                System.out.println(exc);
            }
        }
    }

    public void processInputAndMove(String move) {
        String[] twoPoints = move.split(" ");
        String startPoint = twoPoints[0];
        String finishPoint = twoPoints[1];

        this.colStart = Globals.letters.indexOf(startPoint.charAt(0));
        this.rowStart = 8 - Integer.valueOf(startPoint.substring(1));
        this.colDest = Globals.letters.indexOf(finishPoint.charAt(0));
        this.rowDest = 8 - Integer.valueOf(finishPoint.substring(1));

        Piece piece = this.board.getPiece(this.rowStart, this.colStart);
        if ((piece != null && piece.isWhite() == this.isWhite) &&
                !this.isKingAttacked(true) && !this.isKingAttacked(false)) {
            if (!this.checkKingAttackedAfterMove(piece, this.rowDest, this.colDest)) {
                if (piece.move(this.rowDest, this.colDest, this.board.getBoard())) {
                    this.isWhite = !this.isWhite;
                }
            }
        } else if ((this.isKingAttacked(true) || this.isKingAttacked(false)) &&
                piece != null && piece.isWhite() == this.isWhite) {
            if (this.escapeCheck(piece, this.rowDest, this.colDest)) {
                this.board.movePiece(piece, this.rowDest, this.colDest);
                this.isWhite = !this.isWhite;
            }
        }
    }

    public boolean castling(String move) {
        return (this.checkLongCastling(move) && this.longCastle()) ||
                (this.checkShortCastling(move) && this.shortCastle());
    }

    public boolean checkShortCastling(String move) {
        return move.equalsIgnoreCase("00") || move.equalsIgnoreCase("oo");
    }

    public boolean checkLongCastling(String move) {
        return move.equalsIgnoreCase("000") || move.equalsIgnoreCase("ooo");
    }

    public boolean shortCastle() {
        int kingRow = this.isWhite ? 7 : 0;
        if (this.board.getPiece(kingRow, 5) == null && this.board.getPiece(kingRow, 6) == null) {
            Piece rook = this.board.getPiece(kingRow, 7);
            Piece king = this.board.getPiece(kingRow, 4);
            if (rook instanceof Rook && king instanceof King) {
                if (rook.isFirstMove() && king.isFirstMove() && !this.checkKingAttackedAfterMove(king, kingRow, 6)) {
                    this.board.movePiece(rook, kingRow, 5);
                    this.board.movePiece(king, kingRow, 6);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean longCastle() {
        int kingRow = this.isWhite ? 7 : 0;
        if (this.board.getPiece(kingRow, 1) == null &&
                this.board.getPiece(kingRow, 2) == null &&
                this.board.getPiece(kingRow, 3) == null) {
            Piece rook = this.board.getPiece(kingRow, 0);
            Piece king = this.board.getPiece(kingRow, 4);
            if (rook instanceof Rook && king instanceof King) {
                if (rook.isFirstMove() && king.isFirstMove() && !this.checkKingAttackedAfterMove(king, kingRow, 2)) {
                    this.board.movePiece(king, kingRow, 2);
                    this.board.movePiece(rook, kingRow, 3);
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
        King king = this.board.getKing(isWhite);
        boolean[][] attackedCells = this.getAttackedCells(!isWhite);
        return attackedCells[king.getRowCoordinate()][king.getColCoordinate()];
    }

    public boolean escapeCheck(Piece piece, int rowDest, int colDest) {
        if (!piece.getPossibleMoves(this.board.getBoard())[rowDest][colDest]) {
            return false;
        }

        return !this.checkKingAttackedAfterMove(piece, rowDest, colDest);
    }

    public boolean isMateForKing(boolean isWhite) {

        // Логика по проверке на защиту другими фигурами, yes
        King king = this.board.getKing(isWhite);

        if (!this.isKingAttacked(isWhite)) {
            return false;
        }
        if (checkPieceDefenseForKing(isWhite)) {
            return false;
        }

        // Проверка ходов короля, если он может уйти, то уходит
        return !checkKingRetreat(king);
    }

    public boolean checkPieceDefenseForKing(boolean isWhite) {
        for (Piece[] pieces : this.board.getBoard()) {
            for (Piece piece : pieces) {
                if (piece != null && piece.isWhite() == isWhite) {
                    boolean[][] possibleMoves = piece.getPossibleMoves(this.board.getBoard());
                    for (int row = 0; row < possibleMoves.length; row++) {
                        for (int col = 0; col < possibleMoves.length; col++) {
                            if (possibleMoves[row][col]) {
                                if (!this.checkKingAttackedAfterMove(piece, row, col)) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean checkKingRetreat(King king) {
        boolean[][] kingMoves = king.getPossibleMoves(this.board.getBoard());
        for (int row = 0; row < kingMoves.length; row++) {
            for (int col = 0; col < kingMoves[0].length; col++) {
                if (!kingMoves[row][col]) {
                    continue;
                }

                if (!this.checkKingAttackedAfterMove(king, row, col)) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean checkKingAttackedAfterMove(Piece piece, int row, int col) {
        Piece curPiece = this.board.getPiece(row, col);
        int colPiece = piece.getColCoordinate();
        int rowPiece = piece.getRowCoordinate();
        this.board.setPiece(piece, row, col);

        if (!this.isKingAttacked(piece.isWhite())) {
            System.out.println(piece.getColCoordinate());
            this.board.setPiece(piece, rowPiece, colPiece);
            this.board.setPiece(curPiece, row, col);
            return false;
        }

        this.board.setPiece(piece, rowPiece, colPiece);
        this.board.setPiece(curPiece, row, col);
        return true;
    }

    public boolean[][] getAttackedCells(boolean isWhite) {
        boolean[][] attackedCells = new boolean[this.board.getBoard().length][this.board.getBoard()[0].length];
        for (Piece[] row : this.board.getBoard()) {
            for (Piece piece : row) {
                if (piece != null && piece.isWhite() == isWhite) {
                    boolean[][] possibleMoves = piece.getPossibleMoves(this.board.getBoard());
                    if (piece instanceof Pawn) { // Pawn может атаковать только клетки, которые она съедает
                        boolean[][] pawnMoves = ((Pawn) piece).getPredictableEatMoves(this.board.getBoard());
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
}
