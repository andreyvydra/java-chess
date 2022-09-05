import figures.basic.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Frame extends JFrame {

    private JButton[][] board = new JButton[8][8];
    Container contents = getContentPane();
    Game game;

    public Frame(Game game) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.game = game;
        this.contents.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(720, 720));
        this.setResizable(false);
        this.setVisible(true);
        this.setTitle("Java chess");
        this.setAlwaysOnTop(false);
        this.createField(game);
        this.pack();
    }

    public void createField(Game game) {
        this.contents.removeAll();
        this.board = new JButton[8][8];
        JPanel boardPanel = new JPanel(new GridLayout(8, 8));
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = game.getBoard()[i][j];
                this.board[i][j] = setButton(new int[]{i, j}, piece);
                this.board[i][j].addActionListener(this::actionPerformed);
                boardPanel.add(this.board[i][j]);
            }
        }
        this.contents.add(boardPanel);
    }

    private JButton setButton(int[] coordinate, Piece piece) {
        JButton b = new JButton();

        backgroundSetter(coordinate, b);

        if (piece != null) {
            ImageIcon image = new ImageIcon(piece + ".png");
            b.setIcon(image);
        } else {
            b.setIcon(new ImageIcon());
        }

        formatButton(b);

        return b;
    }

    private void backgroundSetter(int[] coordinate, JButton b) {
        if (coordinate[0] % 2 == 1) {
            if (coordinate[1] % 2 == 0) {
                b.setBackground(new Color(139, 69, 19));
            } else {
                b.setBackground(new Color(242, 200, 170));
            }
        } else {
            if (coordinate[1] % 2 == 1) {
                b.setBackground(new Color(139, 69, 19));
            } else {
                b.setBackground(new Color(242, 200, 170));
            }
        }
    }

    private void formatButton(JButton b) {
        b.setSize(80, 80);
        b.setBorderPainted(false);
        b.setVisible(true);
    }


    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (this.board[i][j].equals(source)) {
                    if (!this.game.isPieceSelected() && this.game.getBoard()[i][j] != null) {
                        this.game.colStart = j;
                        this.game.rowStart = i;
                        this.game.setPieceSelected(true);
                    } else if (this.game.isPieceSelected()){
                        this.game.colDest = j;
                        this.game.rowDest = i;
                        this.game.setPointSelected(true);
                    }
                }
            }
        }
    }
}

