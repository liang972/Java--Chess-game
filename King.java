package CSCI1933P2;

public class King extends Piece{

    public King(int row, int col, boolean isBlack) {
        // Set Parent class's row instance variable
        super.row = row;

        // Set Parent class's col instance variable
        super.col = col;

        // Boolean representing Piece object's color (white/black)
        super.isBlack = isBlack;

        // Setting the representation of the King object (based on the color)
        // View the Unicode table in the writeup for picking Piece char representations.
        if (isBlack){
            // Black King
            super.representation = '\u265A';
        }
        else{
            // White King
            super.representation = '\u2654';
        }
    }
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        // Ensure the move is inside the board and the starting position is valid with correct objects
        // The destination must be empty OR must contain a piece of the opposite color
            // Ensure King can only move one square in any direction
            return board.verifySourceAndDestination(this.row,this.col,endRow,endCol,isBlack) && board.verifyAdjacent(this.row,this.col,endRow,endCol);
    }

}
