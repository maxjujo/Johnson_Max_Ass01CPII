public class TicTacToeGame {
    private final int BOARD_SIZE = 3;
    private final String[][] board;
    private String currentPlayerSymbol;
    private int moves;

    public TicTacToeGame() {
        board = new String[BOARD_SIZE][BOARD_SIZE];
        reset();
    }

    public void reset() {
        currentPlayerSymbol = "X";
        moves = 0;

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = "";
            }
        }
    }

    public boolean makeMove(int row, int col) {
        if (row < 0 || row >= BOARD_SIZE || col < 0 || col >= BOARD_SIZE || !board[row][col].equals("")) {
            return false;
        }

        board[row][col] = currentPlayerSymbol;
        moves++;
        currentPlayerSymbol = (currentPlayerSymbol.equals("X")) ? "O" : "X";
        return true;
    }

    public boolean checkForWin() {
        // Check rows and columns
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i][0].equals(board[i][1]) && board[i][0].equals(board[i][2]) && !board[i][0].equals("")) {
                return true; // Rows
            }
            if (board[0][i].equals(board[1][i]) && board[0][i].equals(board[2][i]) && !board[0][i].equals("")) {
                return true; // Columns
            }
        }
        // Check diagonals
        if (board[0][0].equals(board[1][1]) && board[0][0].equals(board[2][2]) && !board[0][0].equals("")) {
            return true; // Diagonal 1
        }
        if (board[0][2].equals(board[1][1]) && board[0][2].equals(board[2][0]) && !board[0][2].equals("")) {
            return true; // Diagonal 2
        }
        return false;
    }

    public boolean isBoardFull() {
        return moves >= BOARD_SIZE * BOARD_SIZE;
    }

    public String getCurrentPlayerSymbol() {
        return currentPlayerSymbol;
    }
}
