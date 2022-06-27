package me.srin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CheckWinnerTest {
    /**
     * 0 1 2 <br>
     * 3 4 5 <br>
     * 6 7 8 <br>
    * */
    /*
      +---+---+---+
      | X | O | O |
      +---+---+---+
      |   | O |   |
      +---+---+---+
      |   | X | X |
      +---+---+---+
    */
    private static int checkWinner(int index, int blankCells, char... cells) {
        int row = index / 3;
        int col = index % 3;
        int result = (cells[index] == 'X'? 2: -2) + (blankCells == 0? 0: 1);
        if (row == col) {
            int count = 0;
            for (int i = 0; i < 3; i++) {
                if (cells[i * 3 + i] == ' ' || cells[i * 3 + i] != cells[index]) {
                    break;
                }
                count++;
            }
            if (count == 3) return result;
        }
        if (row == 2 - col) {
            int count = 0;
            for (int i = 0; i < 3; i++) {
                if (cells[i * 3 + 2 - i] == ' ' || cells[i * 3 + 2 - i] != cells[index]) {
                    break;
                }
                count++;
            }
            if (count == 3) return result;
        }
        int count = 0;
        for (int i = 0; i < 3; i++) {
            if (cells[row * 3 + i] == ' ' || cells[row * 3 + i] != cells[index]) {
                break;
            }
            count++;
        }
        if (count == 3) return result;
        count = 0;
        for (int i = 0; i < 3; i++) {
            if (cells[i * 3 + col] == ' ' || cells[i * 3 + col] != cells[index]) {
                break;
            }
            count++;
        }
        return count == 3? result: 0;
    }

    @Test
    void checkWinnerTest() {
        Assertions.assertEquals(-1, checkWinner(
            6, 2,
                'x', 'o', 'o',
                      ' ', 'o', ' ',
                      'o', 'x', 'x'
        ));
        Assertions.assertEquals(-2, checkWinner(
            6, 0,
                'x', 'o', 'o',
                      'o', 'o', 'x',
                      'o', 'x', 'x'
        ));
    }
}
