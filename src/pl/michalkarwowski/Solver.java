package pl.michalkarwowski;

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
            s.rate = ratePlace(0, j, N, s.queens);
            situationList.add(s);
        }
        situationList.sort(Comparator.comparingInt(o -> o.rate));
        boolean isProcessingEnded = false;

        do {
            Situation s = situationList.get(situationList.size() - 1);
            BoardPlace lastQueen = s.getQueens().get(s.getQueens().size() - 1);
            if (lastQueen.getRow() != N - 1) {
                for (int i = 0; i < N; i++) {
                    if (canPlaceQueen(lastQueen.getRow() + 1, i, s.getQueens())) {
                        Situation newSituation = new Situation();
                        newSituation.addQueen(s.getQueens());
                        newSituation.addQueen(lastQueen.getRow() + 1, i);
                        newSituation.setRate(ratePlace(lastQueen.getRow() + 1, i, N, newSituation.getQueens()));
                        situationList.add(newSituation);
                        if (lastQueen.getRow() + 1 == N - 1) {
                            isProcessingEnded = true;
                            newSituation.setRate(0);
                            solution.add(newSituation);
                        }
                    }
                }
                situationList.remove(s);
                situationList.sort(Comparator.comparingInt(o -> o.rate));
            } else {
                isProcessingEnded = true;
            }
        } while (!isProcessingEnded && situationList.size() != 0);

        if (solution.size() > 0) {
            BoardPrinter.printBoard(solution.get(0));
            System.out.println(solution.get(0));
        } else {
            System.out.println("Brak rozwiązania");
        }

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


    private static int ratePlace(int w, int k, int N, List<BoardPlace> queens) {
        int freeInRow = 0;
        int sumFreePlaces = 0;
        int min = N;

        Board board = new Board(N);

        for (BoardPlace queen : queens) {
            board.placeQueen(queen.getRow(), queen.getColumn());
        }

        for (int i = w + 1; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (Math.abs(k - j) != i && j != k) {
                    if (board.get(i, j) != -1)
                        freeInRow++;
                }
            }
            if (freeInRow < min) {
                min = freeInRow;
            }
            sumFreePlaces += freeInRow;

            freeInRow = 0;
        }
        return -min + sumFreePlaces + 10 * queens.size();
    }
}
