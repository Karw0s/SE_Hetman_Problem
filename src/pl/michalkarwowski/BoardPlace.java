package pl.michalkarwowski;

public class BoardPlace {
    private int row;
    private int column;

    public BoardPlace(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public String toString() {
        return "BoardPlace{" +
                "row=" + row +
                ", column=" + column +
                '}';
    }
}
