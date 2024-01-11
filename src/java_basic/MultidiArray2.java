package java_basic;

import java.util.Scanner;

/**
 * tic-tac-toe game refactoring
 */
public class MultidiArray2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        final int SIZE = 3;
        int[][] board = new int[SIZE][SIZE];

        // 填充棋盘
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = in.nextInt();
            }
        }

        // 检查行、列和对角线
        boolean xWon = checkWin(board, 1, SIZE);
        boolean oWon = checkWin(board, 0, SIZE);

        if (xWon) {
            System.out.println("x won!");
        } else if (oWon) {
            System.out.println("o won!");
        } else {
            System.out.println("No winner");
        }
    }

    private static boolean checkWin(int[][] board, int player, int size) {
        for (int i = 0; i < size; i++) {
            if (checkRow(board, i, player, size) || checkCol(board, i, player, size)) {
                return true;
            }
        }
        return checkDiagonal(board, player, size) || checkAntiDiagonal(board, player, size);
    }

    private static boolean checkRow(int[][] board, int row, int player, int size) {
        for (int j = 0; j < size; j++) {
            if (board[row][j] != player) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkCol(int[][] board, int col, int player, int size) {
        for (int i = 0; i < size; i++) {
            if (board[i][col] != player) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkDiagonal(int[][] board, int player, int size) {
        for (int i = 0; i < size; i++) {
            if (board[i][i] != player) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkAntiDiagonal(int[][] board, int player, int size) {
        for (int i = 0; i < size; i++) {
            if (board[i][size - i - 1] != player) {
                return false;
            }
        }
        return true;
    }
}
