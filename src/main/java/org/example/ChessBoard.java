package org.example;

import org.example.pieces.*;

import java.util.Arrays;

public class ChessBoard {

    private ChessPiece[] pieces = new ChessPiece[32];

    public ChessBoard() {
        ChessPiece[] whitePieces = new ChessPiece[]{
                new Rook(), new Knight(), new Bishop(), new Queen(), new King(), new Bishop(), new Knight(), new Rook(),
                new Pawn(), new Pawn(), new Pawn(), new Pawn(), new Pawn(), new Pawn(), new Pawn(), new Pawn()
        };
        int n = 0;
        for (ChessPiece piece : whitePieces) {
            piece.setColour(true);
            piece.setPosition(n);
            pieces[n] = piece;
            n++;
        }
        for (ChessPiece piece : whitePieces) {
            piece.updateLegalMoves(this);
        }

        ChessPiece[] blackPieces = new ChessPiece[]{
                new Rook(), new Knight(), new Bishop(), new Queen(), new King(), new Bishop(), new Knight(), new Rook(),
                new Pawn(), new Pawn(), new Pawn(), new Pawn(), new Pawn(), new Pawn(), new Pawn(), new Pawn()
        };
        n = 0;
        for (ChessPiece piece : blackPieces) {
            piece.setColour(false);
            piece.setPosition(63-n);
            pieces[n+16] = piece;
            n++;
        }
        for (ChessPiece piece : blackPieces) {
            piece.updateLegalMoves(this);
        }


        System.out.println("PIECES: " + Arrays.toString(pieces));
        System.out.println("TYPE: " + pieces[9]);
        System.out.println("TEST: " + pieces[9].getLegalMoves());
    }
    public ChessBoard(ChessPiece[] pieces) {
        this.pieces = pieces;
    }


    public ChessPiece[] getPieces() {
        return pieces;
    }

}
