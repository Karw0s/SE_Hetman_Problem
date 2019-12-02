package pl.michalkarwowski;

import java.util.List;

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

    public int ocenmiejsce(int w, int k) {
        int free_w = 0;
        int min = 0;
        for (int i = w + 1; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (Math.abs(k - j) != i && j != k) {
                    if (boardPlate[i][j] != -1)
                        free_w++;
                }
            }
            if (free_w < min) {
                min = free_w;
            }

            free_w = 0;
        }
        return -min;
    }



    public boolean placeHetman(int w, int k) {
        if (canHetmanBePlaced(w, k)) {
            zablokujMiejsca(w, k);
            boardPlate[w][k] = 1;
            return true;
        }
        return false;
    }

    public boolean canHetmanBePlaced(int w, int k) {
        return boardPlate[w][k] != -1;
    }

    private void zablokujMiejsca(int w, int k) {
        for (int i = w; i < N; i++)
            for (int j = 0; j < N; j++) {
                if (Math.abs(k - j) == i || j == k) {
                      boardPlate[i][j] = -1;
                }
            }
    }
}
