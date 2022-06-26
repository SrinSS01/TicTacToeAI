package me.srin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TicTacToe {
    Player currentPlayer;
    Player player1;
    Player player2;
    State state = State.PLAYING;
    int blankCells = 9;
    static final Random RANDOM = new Random();
    static final char[] cells = new char[9];
    static ArrayList<Integer> emptyCells;
    public TicTacToe(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        currentPlayer = player1;
        Arrays.fill(cells, ' ');
        emptyCells = new ArrayList<>(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8));
    }

    public void play() {
        System.out.println(this);
        do {
            int index = currentPlayer.play();
            cells[index] = currentPlayer.getPlayer();
            blankCells--;
            System.out.println(this);
//            int winner = checkWinner(index);
            if (checkWinner(index)) {
                state = State.GAME_OVER;
                System.out.printf("Player %c wins!\n", currentPlayer.getPlayer());
            } else if (blankCells == 0) {
                state = State.GAME_OVER;
                System.out.println("Draw!");
            } else {
                currentPlayer = currentPlayer == player1 ? player2 : player1;
            }
        } while (state == State.PLAYING);
    }

    @Override
    public String toString() {
        return String.format(
                "+---+---+---+\n| %c | %c | %c |\n|---+---+---|\n| %c | %c | %c |\n|---+---+---|\n| %c | %c | %c |\n+---+---+---+",
                cells[0], cells[1], cells[2],
                cells[3], cells[4], cells[5],
                cells[6], cells[7], cells[8]
        );
    }
    private static boolean checkWinner(int index) {
        int row = index / 3;
        int col = index % 3;
        if (row == col) {
            int count = 0;
            for (int i = 0; i < 3; i++) {
                if (cells[i * 3 + i] == ' ' || cells[i * 3 + i] != cells[index]) {
                    break;
                }
                count++;
            }
            if (count == 3) return true;
        }
        if (row == 2 - col) {
            int count = 0;
            for (int i = 0; i < 3; i++) {
                if (cells[i * 3 + 2 - i] == ' ' || cells[i * 3 + 2 - i] != cells[index]) {
                    break;
                }
                count++;
            }
            if (count == 3) return true;
        }
        int count = 0;
        for (int i = 0; i < 3; i++) {
            if (cells[row * 3 + i] == ' ' || cells[row * 3 + i] != cells[index]) {
                break;
            }
            count++;
        }
        if (count == 3) return true;
        count = 0;
        for (int i = 0; i < 3; i++) {
            if (cells[i * 3 + col] == ' ' || cells[i * 3 + col] != cells[index]) {
                break;
            }
            count++;
        }
        return count == 3;
    }

    static public class Player {
        char player;
        Main.Mode mode;

        public Player(char player, Main.Mode mode) {
            this.player = player;
            this.mode = mode;
        }

        public int play() {
            int x = 0, y = 0;
            switch (mode) {
                case EASY -> {
                    System.out.println("Computer is playing (easy)...");
                    return emptyCells.remove(RANDOM.nextInt(emptyCells.size()));
                }
                case MEDIUM -> {
                    int bestScore = -10;
                    System.out.println("Computer is playing (medium)...");
                    for (int i = 0; i < 9; i++) {
                        if (cells[i] == ' ') {
                            cells[i] = player;
//                            System.out.printf(
//                                    "+---+---+---+\n| %c | %c | %c |\n|---+---+---|\n| %c | %c | %c |\n|---+---+---|\n| %c | %c | %c |\n+---+---+---+\n",
//                                    cells[0], cells[1], cells[2],
//                                    cells[3], cells[4], cells[5],
//                                    cells[6], cells[7], cells[8]
//                            );
//                            System.out.println("-------------------------------------------------");
                            int score = minimax(0, i, false);
//                            System.out.println("-------------------------------------------------");
                            cells[i] = ' ';
//                            System.out.printf(
//                                    "+---+---+---+\n| %c | %c | %c |\n|---+---+---|\n| %c | %c | %c |\n|---+---+---|\n| %c | %c | %c |\n+---+---+---+\n",
//                                    cells[0], cells[1], cells[2],
//                                    cells[3], cells[4], cells[5],
//                                    cells[6], cells[7], cells[8]
//                            );
                            if (score > bestScore) {
                                bestScore = score;
                                x = i % 3;
                                y = i / 3;
                            }
                        }
                    }
                }
                case USER -> {
                    do {
                        System.out.print("Enter position: ");
                        x = Main.SC.nextInt() - 1;
                        y = Main.SC.nextInt() - 1;
                        Main.SC.nextLine();
                        if (x < 0 || x > 2 || y < 0 || y > 2) {
                            System.out.println("Invalid position!");
                            continue;
                        } else if (cells[x + y * 3] != ' ') {
                            System.out.println("Position already taken!");
                            continue;
                        }
                        break;
                    } while (true);
                }
            }
            emptyCells.remove(Integer.valueOf(x + y * 3));
            return x + y * 3;
        }
        public int minimax(int depth, int index, boolean isMax) {
            if (checkWinner(index)) return cells[index] == 'X' ? 10 : -10;
            boolean isBlankCellPresent = false;
            for (char cell : cells) {
                if (cell == ' ') {
                    isBlankCellPresent = true;
                    break;
                }
            }
            if (!isBlankCellPresent) return 0;
            else if (isMax) {
                int best = -10;
                for (int i = 0; i < 9; i++) {
                    if (cells[i] == ' ') {
                        cells[i] = player;
//                        System.out.printf(
//                                "+---+---+---+\n| %c | %c | %c |\n|---+---+---|\n| %c | %c | %c |\n|---+---+---|\n| %c | %c | %c |\n+---+---+---+\n",
//                                cells[0], cells[1], cells[2],
//                                cells[3], cells[4], cells[5],
//                                cells[6], cells[7], cells[8]
//                        );
                        int score = minimax(depth + 1, i, false);
                        best = Math.max(best, score);
                        cells[i] = ' ';
//                        System.out.printf(
//                                "+---+---+---+\n| %c | %c | %c |\n|---+---+---|\n| %c | %c | %c |\n|---+---+---|\n| %c | %c | %c |\n+---+---+---+\n",
//                                cells[0], cells[1], cells[2],
//                                cells[3], cells[4], cells[5],
//                                cells[6], cells[7], cells[8]
//                        );
                    }
                }
                return best;
            } else {
                int best = 10;
                for (int i = 0; i < 9; i++) {
                    if (cells[i] == ' ') {
                        cells[i] = player == 'X' ? 'O' : 'X';
//                        System.out.printf(
//                                "+---+---+---+\n| %c | %c | %c |\n|---+---+---|\n| %c | %c | %c |\n|---+---+---|\n| %c | %c | %c |\n+---+---+---+\n",
//                                cells[0], cells[1], cells[2],
//                                cells[3], cells[4], cells[5],
//                                cells[6], cells[7], cells[8]
//                        );
                        int score = minimax(depth + 1, i, true);
                        best = Math.min(best, score);
                        cells[i] = ' ';
//                        System.out.printf(
//                                "+---+---+---+\n| %c | %c | %c |\n|---+---+---|\n| %c | %c | %c |\n|---+---+---|\n| %c | %c | %c |\n+---+---+---+\n",
//                                cells[0], cells[1], cells[2],
//                                cells[3], cells[4], cells[5],
//                                cells[6], cells[7], cells[8]
//                        );
                    }
                }
                return best;
            }
        }

        public char getPlayer() {
            return player;
        }
    }
    enum State {
        PLAYING, GAME_OVER
    }
}
