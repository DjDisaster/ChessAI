package org.example.pieces;

import org.example.ChessBoard;
import org.example.ChessPiece;

import java.util.ArrayList;
import java.util.List;

public class Pawn implements ChessPiece {
    private int position; // Position is 0-63
    private boolean colour; // true = white, false = black
    private List<Integer> legalMoves = new ArrayList<>();
    public List<Integer> getLegalMoves() {
        return legalMoves;
    }

    public void updateLegalMoves(ChessBoard board) {
        legalMoves.clear();
        int x = position % 8;
        int y = position / 8;

        // For white pawns
        if (colour) {
            // Normal move (one square forward)
            if (y < 7) {
                legalMoves.add((y + 1) * 8 + x);
            }
            // First move (two squares forward)
            if (y == 1) {
                legalMoves.add((y + 2) * 8 + x);
            }
        }
        // For black pawns
        else {
            // Normal move (one square forward)
            if (y > 0) {
                legalMoves.add((y - 1) * 8 + x);
            }
            // First move (two squares forward)
            if (y == 6) {
                legalMoves.add((y - 2) * 8 + x);
            }
        }
    }

    @Override
    public int getPosition() {
        return position;
    }

    public boolean getColour() {
        return colour;
    }
    public void setColour(boolean b) {
        colour = b;
    }
    @Override
    public void setPosition(int number) {
        this.position = number;
    }
    @Override
    public int getValue() {
        return 10;
    }

}
