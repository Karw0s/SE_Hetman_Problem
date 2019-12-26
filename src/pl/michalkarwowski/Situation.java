package pl.michalkarwowski;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Situation {
    List<BoardPlace> queens = new ArrayList<>();
    int rate = 0;

    public void addQueen(int w, int k) {
        queens.add(new BoardPlace(w, k));
        queens.sort(Comparator.comparingInt(BoardPlace::getRow));
    }

    public void addQueen(List<BoardPlace> hetmany) {
        for (BoardPlace h : hetmany) {
            this.queens.add(new BoardPlace(h.getRow(), h.getColumn()));
        }
    }

    public List<BoardPlace> getQueens() {
        return queens;
    }

    public void setQueens(List<BoardPlace> queens) {
        this.queens = queens;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Situation{" +
                "queens=" + queens +
                ", ocena=" + rate +
                '}';
    }
}
