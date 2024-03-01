import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeFrame extends JFrame implements ActionListener {
    private final int BOARD_SIZE = 3;
    private final JButton[][] buttons;
    private final TicTacToeGame game;
    private final JButton quitButton;

    public TicTacToeFrame() {
        setTitle("Tic Tac Toe");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(BOARD_SIZE + 1, BOARD_SIZE));

        buttons = new JButton[BOARD_SIZE][BOARD_SIZE];
        game = new TicTacToeGame();

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 40));
                buttons[i][j].addActionListener(this);
                add(buttons[i][j]);
            }
        }

        quitButton = new JButton("Quit");
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "Quit", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
        add(quitButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (e.getSource() == buttons[i][j]) {
                    if (game.makeMove(i, j)) {
                        buttons[i][j].setText(game.getCurrentPlayerSymbol());
                        if (game.checkForWin() || game.isBoardFull()) {
                            endGame();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid move! Try again.", "Invalid Move", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }

    private void endGame() {
        if (game.checkForWin()) {
            JOptionPane.showMessageDialog(null, "Player " + game.getCurrentPlayerSymbol() + " wins!", "Winner!", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "It's a tie!", "Tie!", JOptionPane.INFORMATION_MESSAGE);
        }
        int option = JOptionPane.showConfirmDialog(null, "Do you want to play again?", "Play Again", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            resetGame();
        } else {
            System.exit(0);
        }
    }

    private void resetGame() {
        game.reset();
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                buttons[i][j].setText("");
            }
        }
    }
}
