package tictactoe;

import java.util.*;

public class Main {
    public static int countOfX;
    public static int countOfO;

    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        /*
        System.out.print("Enter cells: ");

        String inputLine = scanner.nextLine();

        char[] charArr = inputLine.toCharArray();
        */
        char[][] ticTacToeArr = new char[3][3];

        // int k = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                ticTacToeArr[i][j] = ' ';
                // k++;
            }
        }

        showGame(ticTacToeArr);

        /*

        String gameResult = gameState(ticTacToeArr);

        System.out.println(gameResult);

         */

        do {
            System.out.print("Enter the coordinates: ");

            try {

                char[] input = scanner.nextLine().replaceAll(" ", "").toCharArray();


                int row = Integer.parseInt(Character.toString(input[0]));
                // System.out.println(row);
                int column = Integer.parseInt(Character.toString(input[1]));
                // System.out.println(column);

                if (row >= 1 && row <= 3 && column >= 1 && column <= 3) {
                    if (ticTacToeArr[row - 1][column - 1] == ' ') {
                        if (countOfX <= countOfO) {
                            ticTacToeArr[row - 1][column - 1] = 'X';
                        } else {
                            ticTacToeArr[row - 1][column - 1] = 'O';
                        }

                    } else {
                        System.out.println("This cell is occupied! Choose another one!");
                        continue;
                    }
                } else {
                    System.out.println("Coordinates should be from 1 to 3!");
                    continue;
                }
            }
            catch (Exception e) {
                System.out.println("You should enter numbers!");
                continue;
            }

            showGame(ticTacToeArr);

            String result = gameState(ticTacToeArr);

            if (!result.equals("Game not finished")) {
                System.out.println(result);
                break;
            }


        } while (true);



    }

    public static void showGame (char[][] array) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("---------");
    }

    public static String gameState(char[][] array) {
        String rowString = rowString(array);
        String columnString = columnString(array);
        String diagonalString = diagonalString(array);

        /*
        System.out.println("Row String: " + rowString);
        System.out.println("Column String: " + columnString);
        System.out.println("Diagonal String: " + diagonalString);
        */

        countOfX = countOfX(rowString);
        countOfO = countOfY(rowString);
        int countOf_ = 9 - countOfX - countOfO;

        /*
        System.out.println("X count: " + countOfX);
        System.out.println("O count: " + countOfO);
        System.out.println("_ count: " + countOf_);
        */

        int xWinsCount = countOfXWins(rowString, columnString, diagonalString);
        int oWinsCount = countOfOWins(rowString, columnString ,diagonalString);

        /*
        System.out.println("X Wins Count: " + xWinsCount);
        System.out.println("O Wins Count: " + oWinsCount);
        */

        int difOfCountsXO = Math.abs(countOfX - countOfO);
        int countsOfWinsXO = xWinsCount + oWinsCount;
        boolean impossible = difOfCountsXO > 1 || countsOfWinsXO > 1;
        boolean draw = !impossible && countsOfWinsXO == 0 && countOf_ == 0;
        boolean gameNotFinished = !impossible && countsOfWinsXO == 0 && countOf_ > 0;
        boolean xWins = !impossible && countsOfWinsXO == 1 && xWinsCount == 1 && oWinsCount == 0;
        boolean oWins = !impossible && countsOfWinsXO == 1 && xWinsCount == 0 && oWinsCount == 1;

        /*
        System.out.println("Different Of Turns: " + difOfCountsXO);
        System.out.println("Is it impossible: " + impossible);
        System.out.println("Is it Draw: " + draw);
        System.out.println("Is not it finished: " + gameNotFinished);
        System.out.println("X Wins: " + xWins);
        System.out.println("OWins: " + oWins);
        */

        if (impossible) {
            return "Impossible";
        } else if (draw) {
            return "Draw";
        } else if (gameNotFinished) {
            return "Game not finished";
        } else if (xWins) {
            return "X wins";
        } else if (oWins) {
            return "O wins";
        }
        return "";
    }

    public static int countOfX (String rowString) {
        int countOfX = 0;

        for (int i = 0; i < rowString.length(); i++) {
            if (rowString.charAt(i) == 'X') {
                countOfX += 1;
            }
        }

        return countOfX;
    }

    public static int countOfY (String rowString) {
        int countOfO = 0;

        for (int i = 0; i < rowString.length(); i++) {
            if (rowString.charAt(i) == 'O') {
                countOfO += 1;
            }
        }

        return countOfO;
    }

    public static int countOfXWins (String rowString, String columnString, String diagonalString) {
        int countOfXWins = 0;

        for (int i = 0; i < rowString.length(); i += 3) {
            if (rowString.charAt(i) == 'X'
                    && rowString.charAt(i+1) == 'X'
                    && rowString.charAt(i+2) == 'X') {
                countOfXWins += 1;
            }

            if (columnString.charAt(i) == 'X'
                    && columnString.charAt(i+1) == 'X'
                    && columnString.charAt(i+2) == 'X') {
                countOfXWins += 1;
            }
        }

        for (int j = 0; j < diagonalString.length(); j += 3) {
            if (diagonalString.charAt(j) == 'X'
                    && diagonalString.charAt(j+1) == 'X'
                    && diagonalString.charAt(j+2) == 'X') {
                countOfXWins += 1;
            }
        }

        return countOfXWins;
    }

    public static int countOfOWins (String rowString, String columnString, String diagonalString) {
        int countOfOWins = 0;

        for (int i = 0; i < rowString.length(); i += 3) {
            if (rowString.charAt(i) == 'O'
                    && rowString.charAt(i+1) == 'O'
                    && rowString.charAt(i+2) == 'O') {
                countOfOWins += 1;
            }

            if (columnString.charAt(i) == 'O'
                    && columnString.charAt(i+1) == 'O'
                    && columnString.charAt(i+2) == 'O') {
                countOfOWins += 1;
            }
        }

        for (int j = 0; j < diagonalString.length(); j += 3) {
            if (diagonalString.charAt(j) == 'O'
                    && diagonalString.charAt(j+1) == 'O'
                    && diagonalString.charAt(j+2) == 'O') {
                countOfOWins += 1;
            }
        }

        return countOfOWins;
    }

    public static String rowString(char[][] array) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result.append(array[i][j]);
            }
        }

        return result.toString();
    }

    public static String columnString(char[][] array) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result.append(array[j][i]);
            }
        }

        return result.toString();
    }

    public static String diagonalString(char[][] array) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            result.append(array[i][i]);
        }

        for (int j = 0; j < 3; j++) {
            result.append(array[j][2 - j]);
        }

        return result.toString();
    }
}
