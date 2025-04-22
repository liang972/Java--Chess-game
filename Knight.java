package CSCI1933P2;

public class Knight extends Piece {
    public Knight(int row, int col, boolean isBlack) {
        // Set Parent class's row instance variable
        super.row = row;

        // Set Parent class's col instance variable
        super.col = col;

        // Boolean representing Piece object's color (white/black)
        super.isBlack = isBlack;

        // Setting the representation of the Knight object (based on the color)
        // View the Unicode table in the writeup for picking Piece char representations.
        if (isBlack) {
            // Black Knight
            super.representation = '\u265E';
        } else {
            // White Knight
            super.representation = '\u2658';
        }
    }

    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        // Ensure the move is inside the board and the starting position is valid with correct objects
        // The destination must be empty OR must contain a piece of the opposite color
        if (board.verifySourceAndDestination(this.row, this.col, endRow, endCol, isBlack)) {
            // Ensure Knight moves in an L-shape (2 squares in one direction, 1 in the other)
            if ((endRow == this.row + 2 || endRow == this.row - 2) && (endCol == this.col + 1 || endCol == this.col - 1)) {
                return true;
            }
            if ((endRow == this.row + 1 || endRow == this.row - 1) && (endCol == this.col + 2 || endCol == this.col - 2)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}
