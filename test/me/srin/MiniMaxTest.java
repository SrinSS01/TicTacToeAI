package me.srin;

import org.junit.jupiter.api.Test;

public class MiniMaxTest {
    /*
      +---+---+---+
      | X | O | O |
      +---+---+---+
      | X | O |   |
      +---+---+---+
      |   | X | X |
      +---+---+---+
    */
    @Test
    public void testMinimaxO01() {
        TicTacToe.Player player = new TicTacToe.Player('O', Main.Mode.MEDIUM);
        TicTacToe.cells[0] = 'X';        TicTacToe.cells[1] = 'O';        TicTacToe.cells[2] = 'O';
        TicTacToe.cells[3] = 'X';        TicTacToe.cells[4] = 'O';        TicTacToe.cells[5] = ' ';
        TicTacToe.cells[6] = ' ';        TicTacToe.cells[7] = 'X';        TicTacToe.cells[8] = 'X';
        int move = player.findBestMove(2);
        System.out.printf("index = %d { row: %d, col: %d }\n", move, move / 3, move % 3);
    }
    /*
      +---+---+---+
      | X | O | O |
      +---+---+---+
      |   | O | X |
      +---+---+---+
      |   | X | X |
      +---+---+---+
    */
    @Test
    public void testMinimaxO21() {
        TicTacToe.Player player = new TicTacToe.Player('O', Main.Mode.MEDIUM);
        TicTacToe.cells[0] = 'X';        TicTacToe.cells[1] = 'O';        TicTacToe.cells[2] = 'O';
        TicTacToe.cells[3] = ' ';        TicTacToe.cells[4] = 'O';        TicTacToe.cells[5] = 'X';
        TicTacToe.cells[6] = ' ';        TicTacToe.cells[7] = 'X';        TicTacToe.cells[8] = 'X';
        int move = player.findBestMove(2);
        System.out.printf("index = %d { row: %d, col: %d }\n", move, move / 3, move % 3);
    }
    /*
      +---+---+---+
      | X | O | O |
      +---+---+---+
      |   | O |   |
      +---+---+---+
      |   | X | X |
      +---+---+---+
    */
    @Test
    public void testMinimaxX21() {
        TicTacToe.Player player = new TicTacToe.Player('X', Main.Mode.MEDIUM);
        TicTacToe.cells[0] = 'X';        TicTacToe.cells[1] = 'O';        TicTacToe.cells[2] = 'O';
        TicTacToe.cells[3] = ' ';        TicTacToe.cells[4] = 'O';        TicTacToe.cells[5] = ' ';
        TicTacToe.cells[6] = ' ';        TicTacToe.cells[7] = 'X';        TicTacToe.cells[8] = 'X';
        int move = player.findBestMove(3);
        System.out.printf("index = %d { row: %d, col: %d }\n", move, move / 3, move % 3);
    }
    /*
      +---+---+---+
      | X | O | X |
      +---+---+---+
      | O | O | X |
      +---+---+---+
      |   |   |   |
      +---+---+---+
    */
    @Test
    public void testMinimaxX22() {
        TicTacToe.Player player = new TicTacToe.Player('X', Main.Mode.MEDIUM);
        TicTacToe.cells[0] = 'X';        TicTacToe.cells[1] = 'O';        TicTacToe.cells[2] = 'X';
        TicTacToe.cells[3] = 'O';        TicTacToe.cells[4] = 'O';        TicTacToe.cells[5] = 'X';
        TicTacToe.cells[6] = ' ';        TicTacToe.cells[7] = ' ';        TicTacToe.cells[8] = ' ';
        int move = player.findBestMove(3);
        System.out.printf("index = %d { row: %d, col: %d }\n", move, move / 3, move % 3);
    }
}
