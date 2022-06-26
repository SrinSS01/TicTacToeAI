package me.srin;

import org.junit.jupiter.api.Test;

public class MiniMaxTest {
    @Test
    public void test() {
        TicTacToe.Player player1 = new TicTacToe.Player('X', Main.Mode.MEDIUM);
        TicTacToe.Player player2 = new TicTacToe.Player('X', Main.Mode.USER);
        var ticTacToe = new TicTacToe(player1, player2) {
            void refill() {
                cells[0] = 'X';
                cells[1] = 'O';
                cells[2] = 'O';
                cells[4] = 'O';
                cells[7] = 'X';
                cells[8] = 'X';
                blankCells = 3;
            }
        };
        ticTacToe.refill();
        ticTacToe.play();
    }
}
