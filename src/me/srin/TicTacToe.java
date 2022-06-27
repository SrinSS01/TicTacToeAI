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
            int index = currentPlayer.play(blankCells);
            cells[index] = currentPlayer.getPlayer();
            blankCells--;
            System.out.println(this);
            int winner = checkWinner(index, blankCells);
            if (winner != 0) {
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
    private static int checkWinner(int index, int blankCells) {
        int row = index / 3;
        int col = index % 3;
        int result = 0;
        if (cells[index] == 'X') {
            result = blankCells != 0? 2: 1;
        } else if (cells[index] == 'O') {
            result = blankCells != 0? -1: -2;
        }
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

    static public class Player {
        char player;
        char opponent;
        Main.Mode mode;

        public Player(char player, Main.Mode mode) {
            this.player = player;
            this.opponent = player == 'X'? 'O': 'X';
            this.mode = mode;
        }

        public int play(int blankCells) {
            switch (mode) {
                case EASY -> {
                    System.out.println("Computer is playing (easy)...");
                    return emptyCells.remove(RANDOM.nextInt(emptyCells.size()));
                }
                case MEDIUM -> {
                    System.out.println("Computer is playing (medium)...");
                    int index = findBestMove(blankCells);
                    emptyCells.remove(Integer.valueOf(index));
                    return index;
                }
                case USER -> {
                    do {
                        System.out.print("Enter position: ");
                        int x = Main.SC.nextInt() - 1;
                        int y = Main.SC.nextInt() - 1;
                        Main.SC.nextLine();
                        if (x < 0 || x > 2 || y < 0 || y > 2) {
                            System.out.println("Invalid position!");
                            continue;
                        } else if (cells[x + y * 3] != ' ') {
                            System.out.println("Position already taken!");
                            continue;
                        }
                        emptyCells.remove(Integer.valueOf(x + y * 3));
                        return x + y * 3;
                    } while (true);
                }
                default -> { return 0; }
            }
        }

        public int findBestMove(int blankCells) {
            int bestScore = -Integer.MAX_VALUE;
            int move = 0;
            for (int i = 0; i < 9; i++) {
                if (cells[i] == ' ') {
                    cells[i] = player;
                    int score = minimax(0, i, false, blankCells - 1);
                    cells[i] = ' ';
                    if (score > bestScore) {
                        bestScore = score;
                        move = i;
                    }
                }
            }
            return move;
        }

        public int minimax(int depth, int index, boolean isMax, int blankCell) {
            int result = checkWinner(index, blankCell);
            char winner = result < 0? 'O': result > 0? 'X': 0;
            int resultAbsolute = Math.abs(result);
            if (winner == player) {
                return resultAbsolute;
            } else if (winner == opponent) {
                return -resultAbsolute;
            } else if (blankCell == 0) {
                return 0;
            }
            else if (isMax) {
                int best = -Integer.MAX_VALUE;
                for (int i = 0; i < 9; i++) {
                    if (cells[i] == ' ') {
                        cells[i] = player;
                        int score = minimax(depth + 1, i, false, blankCell - 1);
                        best = Math.max(best, score);
                        cells[i] = ' ';
                    }
                }
                return best;
            } else {
                int best = Integer.MAX_VALUE;
                for (int i = 0; i < 9; i++) {
                    if (cells[i] == ' ') {
                        cells[i] = opponent;
                        int score = minimax(depth + 1, i, true, blankCell - 1);
                        best = Math.min(best, score);
                        cells[i] = ' ';
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
