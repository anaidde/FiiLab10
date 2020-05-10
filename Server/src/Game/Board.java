package Game;

public class Board {
    private int columnsNumber = 15;

    public Board(int columnsNumber) {
        this.columnsNumber = columnsNumber;
    }

    public int getColumnsNumber() {
        return columnsNumber;
    }

    public void createBoard() {
        int[][] boardMatrix = new int[columnsNumber][columnsNumber];

    }

}
