package CSCI1933P2;

public class Board {
    // Instance variables (add more if you need)
    private Piece[][] board;


    /**
     * Default Constructor
     */
    public Board() {
        // initialize the board to chessboard dimensions.
        board = new Piece[8][8];
    }

    // Accessor Methods

    /**
     * Gets the piece at a particular row and column of the board.
     * @param row       The row of the piece to be accessed.
     * @param col       The column of the piece to be accessed.
     * @return          The piece at the specified row and column of the board.
     */
    public Piece getPiece(int row, int col) {
        return board[row][col];
    }

    /**
     * Sets the piece at a particular row and column of the board.
     * @param row       The row to place the piece at.
     * @param col       The column to place the piece at.
     * @param piece     The piece to place at the specified row and column.
     */
    public void setPiece(int row, int col, Piece piece) {
        board [row][col] = piece;
    }

    // Movement helper functions

    /**
     * Verifies that the source and destination of a move are valid by performing the following checks:
     *  1. ALL rows and columns provided must be >= 0.
     *  2. ALL rows and columns provided must be < 8.
     *  3. The start position of the move must contain a piece.
     *  4. The piece at the starting position must be the correct color.
     *  5. The destination must be empty OR must contain a piece of the opposite color.
     * @param startRow  The starting row of the move.
     * @param startCol  The starting column of the move.
     * @param endRow    The ending row of the move.
     * @param endCol    The ending column of the move.
     * @param isBlack   The expected color of the starting piece.
     * @return True if the above conditions are met, false otherwise.
     */

    public boolean verifySourceAndDestination(int startRow, int startCol, int endRow, int endCol, boolean isBlack) {
        // Inverse check
        if (startRow < 0 || startCol< 0 || endRow < 0 || endCol < 0 ||
                startRow >=8 || startCol >=8 || endRow >=8 || endCol >=8 ||
                board[startRow][startCol] == null ||
                board[startRow][startCol].getIsBlack() != isBlack ||
                (board[endRow][endCol] != null && board[endRow][endCol].getIsBlack() == isBlack)) {
            return false;
        }
        // If all conditions are met, return true
        return true;
    }

    /**
     * Verifies that the source and destination of a move are adjacent squares (within 1 square of each other)
     * Example, Piece P is adjacent to the spots marked X:
     * OOOOO
     * OXXXO
     * OXPXO
     * OXXXO
     * OOOOO
     * @param startRow  The starting row of the move.
     * @param startCol  The starting column of the move.
     * @param endRow    The ending row of the move.
     * @param endCol    The ending column of the move.
     * @return True if the source and destination squares are adjacent, false otherwise.
     */
    public boolean verifyAdjacent(int startRow, int startCol, int endRow, int endCol) {
        // Calculate the distance of rows and columns between two positions
        int distRow = Math.abs(startRow - endRow);
        int distCol = Math.abs(startCol- endCol);
        // Check if the distance of rows and cols are both less than or equal to 1
        return (distRow == 0 && distCol == 1|| distRow == 1 && distCol == 0 || distRow == 1 && distCol == 1 || distRow == 0 && distCol == 0);

    }

    /**
     * Verifies that a source and destination are in the same row and that there are no pieces on squares
     * between the source and the destination.
     * @param startRow  The starting row of the move.
     * @param startCol  The starting column of the move.
     * @param endRow    The ending row of the move.
     * @param endCol    The ending column of the move.
     * @return True if source and destination are in same row with no pieces between them, false otherwise.
     */
    public boolean verifyHorizontal(int startRow, int startCol, int endRow, int endCol) {
        // If not moving horizontally, return false
        if (startRow != endRow) {
            return false;
        }

        // If start and end positions are the same, return true (no movement)
        if (startCol == endCol) {
            return true;
        }

        // Check for if it is no pieces between them
        for (int i = Math.min(startCol, endCol) + 1; i < Math.max(startCol, endCol); i++) {
            if (board[startRow][i] != null) {
                return false;
            }
        }

        return true;
    }


    /**
     * Verifies that a source and destination are in the same column and that there are no pieces on squares
     * between the source and the destination.
     * @param startRow  The starting row of the move.
     * @param startCol  The starting column of the move.
     * @param endRow    The ending row of the move.
     * @param endCol    The ending column of the move.
     * @return True if source and destination are in same column with no pieces between them, false otherwise.
     */


    public boolean verifyVertical(int startRow, int startCol, int endRow, int endCol) {
        // If not moving vertically, return false
        if (startCol != endCol) {
            return false;
        }

        // If start and end positions are the same, return true (no movement)
        if (startRow == endRow) {
            return true;
        }

        // Check for if it is no pieces between them
        for (int i = Math.min(startRow, endRow) + 1; i < Math.max(startRow, endRow); i++) {
            if (board[i][startCol] != null) {
                return false;
            }
        }

        return true;
    }

    /**
     * Verifies that a source and destination are on the same diagonal and that there are no pieces on squares
     * between the source and the destination.
     * @param startRow  The starting row of the move.
     * @param startCol  The starting column of the move.
     * @param endRow    The ending row of the move.
     * @param endCol    The ending column of the move.
     * @return True if source and destination are on the same diagonal with no pieces between them, false otherwise.
     */
    public boolean verifyDiagonal(int startRow, int startCol, int endRow, int endCol) {
        // Calculate the row and cols distance of two positions
        // Same rows and cols distance means diagonal
        int distRow = Math.abs(startRow - endRow);
        int distCol = Math.abs(startCol-endCol);

        //if the piece stay at the same position return true
        if(distRow ==0 && distCol ==0){
            return true;
        }
        // check if the move is with same the amount of rows and cols change to verify move diagonally
        if( distRow != distCol){
            return false;
        }else{
            // iterate through to check if no pieces between them
            for (int increment= 1; increment < distRow; increment++){
                if(startRow < endRow && startCol <endCol
                   && board[startRow + increment][startCol+increment] != null){
                    return false;
                } else if (startRow <endRow && startCol >endCol
                        && board [startRow + increment][startCol -increment] != null) {
                    return false;
                } else if (startRow > endRow && startCol < endCol
                        && board [startRow - increment][startCol +increment] != null) {
                    return false;
                } else if (startRow > endRow && startCol > endCol
                        && board[startRow -increment][startCol -increment] != null) {
                    return false;
                }
            }
            return true;
        }
    }

    // Game functionality methods

    /**
     * Moves the piece from startRow, startCol to endRow, endCol if it is legal to do so.
     * IMPORTANT: Make sure to update the internal position of the piece, and the starting position of the piece to null!
     * @param startRow  The starting row of the move.
     * @param startCol  The starting column of the move.
     * @param endRow    The ending row of the move.
     * @param endCol    The ending column of the move.
     * @return Whether the move was successfully completed or not. (Moves are not completed if they are not legal.)
     */
    public boolean movePiece(int startRow, int startCol, int endRow, int endCol) {
        // check if the start piece is not null and the move is legal
        if (board[startRow][startCol] != null && board[startRow][startCol].isMoveLegal(this,endRow,endCol) ){
            // set the end position with the piece on the start position
            board[endRow][endCol] = board[startRow][startCol];
            // update the piece to new position
            board[endRow][endCol].setPosition(endRow,endCol);
            // clear the start position to null
            board[startRow][startCol] = null;
            return true;
        }else{
            return false;
        }

    }

    /**
     * Returns true if there are fewer than TWO kings on the board.
     * @return If the game is in a game over state.
     */
    public boolean isGameOver() {
        //create an object to count the kings on the board
        int kingCount = 0;

        // iterate through the board to find the king
        for (int row = 0; row< board.length; row++){
            for (int col = 0; col <board[row].length; col++){
                Piece piece = this.getPiece(row,col);
                // check the piece if it is a king type instance
                if(piece instanceof King){
                    kingCount++;
                }
            }
        }
        return kingCount<2;

    }

    /**
     * Sets all indexes in the board to null
     */
    public void clear() {
        // Iterate through each position of the board and set them to null
        for (int i =0; i< board.length; i++){
            for (int j = 0; j < board[i].length; j++){
                board[i][j] = null;
            }
        }
    }


    public void display() {
        System.out.print("\t\t\t");
        for (int i = 0; i < 8; i++) {
            System.out.print(i + "\t\t");
        }
        System.out.print("\n");
        for (int i = 0; i < 8; i++) {
            System.out.print("\t" + i + "\t");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print("|\t\t");
                } else {
                    System.out.print("|\t" + board[i][j] + "\t");
                }
            }
            System.out.print("|\n");
        }
    }

    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append(" ");
        for(int i = 0; i < 8; i++){
            out.append(" ");
            out.append(i);
        }
        out.append('\n');
        for(int i = 0; i < board.length; i++) {
            out.append(i);
            out.append("|");
            for(int j = 0; j < board[0].length; j++) {
                out.append(board[i][j] == null ? "\u2001|" : board[i][j] + "|");
            }
            out.append("\n");
        }
        return out.toString();
    }
}
