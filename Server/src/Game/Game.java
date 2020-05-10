package Game;

import Game.*;
public class Game {
    private Board board;
    private Player player1;
    private Player player2;
    private boolean gameWon = false;
    int[][] gameMatrix;

    public Game(Board board, Player player1, Player player2) {
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
        gameMatrix = new int[board.getColumnsNumber()][board.getColumnsNumber()];
    }

    public void submitMove(int x, int y) {
        gameMatrix[x][y] = 1;
    }
    /*
    * 11 12 13 14 15
    * 21 22 23 24 25
    * 31 32 33 34 35
    * 41 42 43 44 45
    * 51 52 53 54 55
    * */
    private boolean DPWin(int i) {
        int rowCount = 0;
        for(int x = i; x< this.board.getColumnsNumber(); x++)
        {
            if(this.gameMatrix[x][x] == 1) {
                rowCount ++ ;
            }
        }

        return rowCount == 5;
    }

    private boolean DSWIN(int i) {
        int rowCount = 0;
        for(int x = i; x< this.board.getColumnsNumber(); x++)
        {
            if(this.gameMatrix[x][this.board.getColumnsNumber()-x+1] == 1) {
                rowCount ++ ;
            }
        }

        return rowCount == 5;
    }

    private boolean VertWIN(int i, int j) {
        int rowCount = 0;
        for(int x = i; x < this.board.getColumnsNumber(); x++) {
            if(this.gameMatrix[x][j] == 1)
                rowCount++;
        }
        return rowCount == 5;
    }

    private boolean HorizWIN(int i, int j) {
        int rowCount = 0;
        for(int x = j; x < this.board.getColumnsNumber(); x++) {
            if(this.gameMatrix[i][x] == 1)
                rowCount++;
        }
        return rowCount == 5;
    }

    private boolean isGameWon() {
        for(int i = 0; i< this.board.getColumnsNumber(); i++) {
            for(int j=0; j< this.board.getColumnsNumber(); j++) {
                if(this.gameMatrix[i][j] == 1) {
                    if(this.DPWin(i) || this.DSWIN(i) || this.HorizWIN(i, j) || this.VertWIN(i, j))
                        System.out.println("Game Won!");
                        return true;
                }
            }
        }
        return false;
    }


}
