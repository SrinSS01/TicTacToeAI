package me.srin;

import java.util.Scanner;

import static java.lang.System.in;

public class Main {
    public static final Scanner SC = new Scanner(in);
    public static void main(String[] args) {
        do {
            System.out.print("Enter a command: ");
            String command = SC.nextLine().toUpperCase();
            if (command.equals("EXIT")) break;
            if (!command.matches("START\\s+(EASY|MEDIUM|HARD|USER)\\s+(EASY|MEDIUM|HARD|USER)")) continue;
            String[] params = command.split("\\s+");
            TicTacToe.Player player1 = new TicTacToe.Player('X', Mode.valueOf(params[1]));
            TicTacToe.Player player2 = new TicTacToe.Player('O', Mode.valueOf(params[2]));
            TicTacToe game = new TicTacToe(player1, player2);
            game.play();
        } while (true);
//        TicTacToe.Player player1 = new TicTacToe.Player('X', Main.Mode.MEDIUM);
//        TicTacToe.Player player2 = new TicTacToe.Player('O', Main.Mode.USER);
//        var ticTacToe = new TicTacToe(player1, player2) {
//            void refill() {
//                cells[0] = 'X';
//                cells[1] = 'O';
//                cells[2] = 'O';
//                cells[4] = 'O';
//                cells[7] = 'X';
//                cells[8] = 'X';
//                blankCells = 3;
//            }
//        };
//        ticTacToe.refill();
//        ticTacToe.play();
    }
    enum Mode {
        EASY, MEDIUM, USER
    }
}
