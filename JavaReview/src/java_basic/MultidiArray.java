package java_basic;

import java.util.Scanner;

/**
 * tic-tac-toe game
 * 3 * 3 square matrix
 * 放置X / O，三个连成一条线就胜利，有点像五子棋
 */
//        i:
//        0 1 2
//        j:
//        0 1 2
//        1 0 0
//        0 0 1
//        0 1 0
//        0,0 0,1 0,2
//        1,0 1,1 1,2
//        2,0 2,1 2,2
//    test:
//        1 0 0 0 0 1 0 1 0
//    should be o won
//        1 0 0
//        0 0 1
//        0 1 0
//
//        1 0 0 1
//        0 0 1 0
//        0 1 0 0
public class MultidiArray {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        final int SIZE = 3;
        int[][] board = new int[SIZE][SIZE];
        int numX = 0;
        int numO = 0;
        boolean gotResult = false;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = in.nextInt();
            }
        }

        // 检查行
        if (!gotResult) {
            for (int i = 0; i < board.length; i++) {
                // Resetting Counts
                numX = 0;
                numO = 0;
                for (int j = 0; j < board[i].length; j++) {
                    if (j == board[i].length - 1) continue;
                    if (board[i][j] == board[i][j + 1]) {
                        numX = board[i][j] == 1 ? numX + 1 : numX;
                        numO = board[i][j] == 0 ? numO + 1 : numO;
                    }
                    // if current col/row got more than required number then win
                    if (numX == SIZE || numO == SIZE) {
                        gotResult = true;
                        break;
                    }
                }
            }
        }

        // 检查列
        if (!gotResult) {
            for (int i = 0; i < board.length; i++) {
                // Resetting Counts
                numX = 0;
                numO = 0;
                for (int j = 0; j < board[i].length; j++) {
                    if (board[j][i] == 1) {
                        numX++;
                    } else {
                        numO++;
                    }
                }
                // if current col/row got more than required number then win
                if (numX == SIZE || numO == SIZE) {
                    gotResult = true;
                    break;
                }
            }
        }

        // 检查diagonal
        if (!gotResult) {
            numX = 0;
            numO = 0;
            for (int i = 0; i < board.length; i++) {
                if (board[i][i] == 1) {
                    numX++;
                } else {
                    numO++;
                }
            }
            if (numX == SIZE || numO == SIZE) {
                gotResult = true;
            }
        }

        // 检查斜diagonal
        if (!gotResult) {
            numX = 0;
            numO = 0;
            for (int i = 0; i < board.length; i++) {
                if (board[i][board.length - i - 1] == 1) {
                    numX++;
                } else {
                    numO++;
                }
            }
            if (numX == SIZE || numO == SIZE) {
                gotResult = true;
            }
        }

        if (gotResult) {
            if (numX == SIZE) {
                System.out.println("x won!");
            }
            if (numO == SIZE) {
                System.out.println("o won!");
            }
        }

    }

}
