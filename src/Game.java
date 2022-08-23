import core.Globals;
import figures.Pawn;
import figures.Piece;

import java.io.IOException;
import java.util.Scanner;

public class Game {
    private boolean isWhite;
    private Piece[][] board = new Piece[8][8];

    public void start() throws IOException {
        String[][] field = Globals.standardField.clone();

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
                }
            }
        }

        mainLoop();
    }

    public void mainLoop() throws IOException {
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
            System.out.print("Введите ваш ход: ");
            String move = reader.nextLine();

            int row = Globals.letters.indexOf(move.charAt(0));
            int line = this.board.length - Integer.parseInt(move.substring(1, 2));
            int rowDest = Globals.letters.indexOf(move.charAt(3));
            int lineDest = this.board.length - Integer.parseInt(move.substring(4, 5));

            if (this.board[line][row] != null) {
                Piece piece = this.board[line][row];
                if (piece.move(rowDest, lineDest)) {
                    this.board[lineDest][rowDest] = this.board[line][row];
                    this.board[line][row] = null;
                }
            }


        }
    }
}
