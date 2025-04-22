package CSCI1933P2;

public class Queen extends Piece{
    public Queen(int row, int col, boolean isBlack) {
        // Set Parent class's row instance variable
        super.row = row;

        // Set Parent class's col instance variable
        super.col = col;

        // Boolean representing Piece object's color (white/black)
        super.isBlack = isBlack;

        // Setting the representation of the Queen object (based on the color)
        // View the Unicode table in the writeup for picking Piece char representations.
        if (isBlack){
            // Black Queen
            super.representation = '\u265B';
        }
        else{
            // White Queen
            super.representation = '\u2655';
        }
    }

    public boolean isMoveLegal(Board board, int endRow, int endCol){
        // Ensure the move is inside the board and the starting position is valid with correct objects
        // The destination must be empty OR must contain a piece of the opposite color
        // Queen can move diagonally, horizontally and vertically.
        return  board.verifySourceAndDestination(this.row, this.col, endRow, endCol, isBlack) &&
                (board.verifyDiagonal(this.row, this.col, endRow,endCol)
                        || board.verifyHorizontal(this.row, this.col, endRow, endCol)
                        || board.verifyVertical(this.row, this.col, endRow, endCol)
                        || board.getPiece(endRow, endCol) == null);
    }
}
