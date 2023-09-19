package tictactoe;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] board = new char[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }

        int[][] winningPatterns = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8},  // Rows
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8},  // Columns
                {0, 4, 8}, {2, 4, 6}  // Diagonals
        };

        int turn = 0;

        while (true) {
            printBoard(board);

            char player = (turn % 2 == 0) ? 'X' : 'O';

            System.out.println();
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if (row < 1 || row > 3 || col < 1 || col > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }
            row -= 1;
            col -= 1;

            if (board[row][col] != ' ') {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }

            board[row][col] = player;

            if (checkForWin(board, player, winningPatterns)) {
                printBoard(board);

                System.out.printf("%s Wins", player);
                break;
            }

            if (isDraw(board)) {
                printBoard(board);
                System.out.println("Draw");
                break;
            }
            board[row][col] = player;
            turn++;
        }
    }

    public static void printBoard(char[][] board) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public static boolean checkForWin(char[][] board, char player, int[][] winningPatterns) {
        for (int[] pattern : winningPatterns) {
            int firstCell = pattern[0];
            int secondCell = pattern[1];
            int thirdCell = pattern[2];

            char firstSymbol = board[firstCell / 3][firstCell % 3];
            char secondSymbol = board[secondCell / 3][secondCell % 3];
            char thirdSymbol = board[thirdCell / 3][thirdCell % 3];

            if (firstSymbol == player && secondSymbol == player && thirdSymbol == player) {
                return true;
            }
        }
        return false;
    }


    public static boolean isDraw(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false; // There are still empty cells
                }
            }
        }
        return true; // All cells are filled, it's a draw
    }
}
