package org.example;


import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

public class DrawBoard {
    private ChessBoard board;
    private JLabel[] labels = new JLabel[64];
    ChessPiece selectedPiece = null;
    private JFrame frame;
    public void createFrame() {
        frame = new JFrame("Chess Game");
        frame.setLayout(new GridLayout(8, 8));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.height, screenSize.height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    public DrawBoard(ChessBoard board) {
        this.board = board;
        createFrame();
        refreshBoard();
    }


    private void showLegalMoves(int position) {
        clearPreviousHighlightsAndListeners();

        ChessPiece piece = findPieceAtPosition(position);
        if (piece != null) {
            selectedPiece = piece;
            List<Integer> legalMoves = piece.getLegalMoves();
            for (int move : legalMoves) {
                labels[move].setBorder(BorderFactory.createLineBorder(Color.GREEN, 20));
                labels[move].addMouseListener(new MoveListener(move));
            }
        } else {
            selectedPiece = null;
        }
    }

    private void clearPreviousHighlightsAndListeners() {
        for (int i = 0; i < 64; i++) {
            labels[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            removeAllMouseListeners(labels[i]);
        }
    }

    private void removeAllMouseListeners(JLabel label) {
        for (MouseListener listener : label.getMouseListeners()) {
            label.removeMouseListener(listener);
        }
    }

    private class MoveListener extends MouseAdapter {
        private final int movePosition;

        public MoveListener(int movePosition) {
            this.movePosition = movePosition;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (selectedPiece != null) {
                selectedPiece.setPosition(movePosition);
                refreshBoard();
            }
        }
    }
    private ChessPiece findPieceAtPosition(int position) {
        ChessPiece[] pieces = board.getPieces();
        for (ChessPiece piece : pieces) {
            if (piece != null && piece.getPosition() == position) {
                return piece;
            }
        }
        return null;
    }

    private ImageIcon getPieceIcon(ChessPiece piece) {
        String name = piece.getClass().getSimpleName();
        String color = piece.getColour() ? "W" : "B";
        String filename = color + name + ".png";

        // Use getClassLoader().getResource to get the URL
        URL url = getClass().getClassLoader().getResource(filename);
        if (url != null) {
            ImageIcon icon = new ImageIcon(url);
            Image image = icon.getImage();
            Image newimg = image.getScaledInstance(
                    icon.getIconWidth() * 2,
                    icon.getIconHeight() * 2,
                    java.awt.Image.SCALE_SMOOTH
            );
            return new ImageIcon(newimg);
        }
        return null;
    }


    private void refreshBoard() {
        updateAllLegalMoves();
        frame.getContentPane().removeAll();
        buildBoard();

        ChessPiece[] pieces = board.getPieces();
        for (ChessPiece piece : pieces) {
            if (piece != null) {
                int pos = piece.getPosition();
                labels[pos].setIcon(getPieceIcon(piece));
            }
        }

        frame.revalidate();
        frame.repaint();
    }

    private void buildBoard() {

        for (int i = 0; i < 64; i++) {
            labels[i] = new JLabel("", SwingConstants.CENTER);
            labels[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            labels[i].setOpaque(true);
            labels[i].setBackground((i / 8 + i % 8) % 2 == 0 ? Color.WHITE : Color.LIGHT_GRAY);
            int finalI = i;

            labels[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    showLegalMoves(finalI);
                }
            });
            frame.add(labels[i]);
        }
    }

    private void updateAllLegalMoves() {
        for (ChessPiece piece : board.getPieces()) {
            if (piece != null) {
                piece.updateLegalMoves(board);
            }
        }
    }



}
