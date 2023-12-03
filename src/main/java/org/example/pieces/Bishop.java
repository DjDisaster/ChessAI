package org.example.pieces;

import org.example.ChessBoard;
import org.example.ChessPiece;

import java.util.List;

public class Bishop implements ChessPiece {
    private int position; // Position is 0-63
    private boolean colour; // true = white, false = black

    public boolean getColour() {
        return colour;
    }
    public void setColour(boolean b) {
        colour = b;
    }

    @Override
    public List<Integer> getLegalMoves() {
        return null;
    }

    @Override
    public void updateLegalMoves(ChessBoard board) {

    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public void setPosition(int number) {
        this.position = number;
    }

    @Override
    public int getValue() {
        return 75;

    }
}
