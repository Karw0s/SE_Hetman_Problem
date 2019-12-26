package pl.michalkarwowski;

public class Board {

    private int[][] boardPlate;
    private int N;

    public Board(int n) {
        N = n;
        init();
    }

    public Board(int[][] boardPlate, int N) {
        this.boardPlate = boardPlate;
        this.N = N;
    }

    public int[][] getBoardPlate() {
        return boardPlate;
    }

    private void init() {
        boardPlate = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                boardPlate[i][j] = 0;
            }
        }
    }

    public int get(int w, int k) {
        return boardPlate[w][k];
    }

    public boolean placeQueen(int w, int k) {
        if (canPlaceQueen(w, k)) {
            lockPlace(w, k);
            boardPlate[w][k] = 1;
            return true;
        }
        return false;
    }

    public boolean canPlaceQueen(int w, int k) {
        return boardPlate[w][k] != -1;
    }

    private void lockPlace(int w, int k) {
        for (int i = w; i < N; i++)
            for (int j = 0; j < N; j++) {
                if (Math.abs(k - j) == i || j == k) {
                      boardPlate[i][j] = -1;
                }
            }
    }
}
