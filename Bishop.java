package CSCI1933P2;

public class Bishop extends Piece {
    /**
     * Constructor.
     * @param row   The current row of the Bishop.
     * @param col   The current column of the Bishop.
     * @param isBlack   The color of the Bishop.
     */
    public Bishop(int row, int col, boolean isBlack) {
        // Set Parent class's row instance variable
        super.row = row;

        // Set Parent class's col instance variable
        super.col = col;

        // Boolean representing Piece object's color (white/black)
        super.isBlack = isBlack;

        // Setting the representation of the Bishop object (based on the color)
        // View the Unicode table in the writeup for picking Piece char representations.
        if (isBlack) {
            // Black Bishop
            super.representation = '\u265D';
        } else {
            // White Bishop
            super.representation = '\u2657';

        }
    }

    public boolean isMoveLegal(Board board, int endRow, int endCol){
        // Ensure the move is inside the board and the starting position is valid with correct objects
        // The destination must be empty OR must contain a piece of the opposite color
        if (board.verifySourceAndDestination(this.row,this.col,endRow,endCol,isBlack) == true){
            // Ensure it moves only diagonally
            return (board.verifyDiagonal(this.row, this.col,endRow,endCol));

        }else{
            return false;
        }
    }
}
