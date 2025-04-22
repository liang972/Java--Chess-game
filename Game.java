package CSCI1933P2;
import java.util.Scanner;

public class Game {
        public static void main(String[] args) {
            Board board = new Board(); // Instantiate the board
            Fen.load("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR", board);
            boolean isBlack = false; // White starts the game

            System.out.println("Welcome to Chess!");
            board.display(); // Board displays
            Scanner scanner = new Scanner(System.in);

            // Show which player's turn
            while (!board.isGameOver()) {
                if(!isBlack) {
                    System.out.println(board);
                    System.out.println("White's turn.");
                }else {
                    System.out.println(board);
                    System.out.println("Black's turn");
                }
                //check if a valid move is made
                boolean moveMade = false;

                while (!moveMade) {
                    // Get user input
                    System.out.print("Enter your move (format: startRow startCol endRow endCol): ");
                    int startRow = scanner.nextInt();
                    int startCol = scanner.nextInt();
                    int endRow = scanner.nextInt();
                    int endCol = scanner.nextInt();
                    Piece piece = board.getPiece(startRow, startCol);

                    // Validate and implement the move
                    if (piece != null && piece.getIsBlack() == isBlack && piece.isMoveLegal(board, endRow, endCol)) {
                        board.movePiece(startRow, startCol, endRow, endCol); // Move the piece
                        board.display();
                        //switch turns after valid move
                        moveMade = true;
                    } else {
                        System.out.println("Invalid move! Try again.");
                    }
                }

                // Check if the game is over and print the winner
                if (board.isGameOver()) {
                    if(isBlack){
                        System.out.println("Black wins! Game over.");
                    }else {
                        System.out.println("White wins! Game over.");
                    }
                    break;
                }
                isBlack = !isBlack;
            }

            scanner.close();
        }
    }

