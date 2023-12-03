package org.example;

import java.util.List;

public interface ChessPiece {
    int getValue();
    void setPosition(int i);
    boolean getColour();
    void setColour(boolean colour);
    List<Integer> getLegalMoves();
    void updateLegalMoves(ChessBoard board);
    int getPosition();
}
