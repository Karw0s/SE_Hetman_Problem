package com.company;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Solver {

    private List<Situation> situationList = new ArrayList<>();
    private List<Situation> solution = new ArrayList<>();

    public void solve(int N) {
        for (int j = 0; j < N; j++) {
            Situation s = new Situation();
            s.queens.add(new BoardPlace(0, j));
            s.ocena = ocenmiejsce(0, j, N, s.queens);
            situationList.add(s);
        }
        situationList.sort(Comparator.comparingInt(o -> o.ocena));

        boolean rozwiazanie = false;

        do {
            Situation s = situationList.get(situationList.size() - 1);
            BoardPlace lastQueen = s.getQueens().get(s.getQueens().size() - 1);
            if (lastQueen.getRow() != N - 1) {
                for (int i = 0; i < N; i++) {
                    if (canPlaceQueen(lastQueen.getRow() + 1, i, s.getQueens())) {
                        Situation newSituation = new Situation();
                        newSituation.addQuenn(s.getQueens());
                        newSituation.addQueen(lastQueen.getRow() + 1, i);
                        newSituation.setOcena(ocenmiejsce(lastQueen.getRow() + 1, i, N, newSituation.getQueens()));
                        situationList.add(newSituation);
                        if (lastQueen.getRow() + 1 == N - 1) {
                            rozwiazanie = true;
                            solution.add(newSituation);
                        }
                    }
                }
                situationList.remove(s);

                situationList.sort(Comparator.comparingInt(o -> o.ocena));
            } else {
                rozwiazanie = true;
            }


        } while (!rozwiazanie && situationList.size() != 0);

//        System.out.println(situationList.get(situationList.size() - 1));
        if (solution.size() > 0)
            System.out.println(solution.get(0));
    }

    private boolean canPlaceQueen(int w, int k, List<BoardPlace> queens) {
        for (BoardPlace h : queens) {
            if (h.getColumn() == k)
                return false;
            if (Math.abs(k - h.getColumn()) == (w - h.getRow()))
                return false;
        }
        return true;
    }


    private static int ocenmiejsce(int w, int k, int N, List<BoardPlace> hetmany) {
        int free_w = 0;
        int min = N;

        Board board = new Board(N);

        int[] w_hetmany = new int[hetmany.size()];
        for (int i = 0; i < hetmany.size(); i++) {
            w_hetmany[i] = hetmany.get(i).getRow();
            board.placeHetman(hetmany.get(i).getRow(), hetmany.get(i).getColumn());
        }

        for (int i = w + 1; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (Math.abs(k - j) != i && j != k) {
                    if (board.get(i, j) != -1)
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
}
