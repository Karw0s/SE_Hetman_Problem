package pl.michalkarwowski;

import java.util.Comparator;
import java.util.List;

public class BoardPrinter {

    public static void printBoard(Situation s) {
        String blackPlace = " # ";
        String whitePlace = "   ";
        String currentPlace;
        boolean isBlack;
        List<BoardPlace> queens = s.getQueens();
        queens.sort(Comparator.comparingInt(BoardPlace::getRow).reversed());
        for (int i = 0; i < s.getQueens().size(); i++) {
            if (i % 2 == 0) {
                currentPlace = blackPlace;
                isBlack = true;
            } else {
                currentPlace = whitePlace;
                isBlack = false;
            }

            for (int j = 0; j < s.getQueens().size(); j++) {
                if (queens.get(i).getColumn() == j)
                    System.out.print(" Q ");
                else
                    System.out.print(currentPlace);

                if (isBlack)
                    currentPlace = whitePlace;
                else
                    currentPlace = blackPlace;
                isBlack = !isBlack;
            }
            System.out.println(" " + (s.getQueens().size() - i));
        }

        for (int i = 1; i <= s.getQueens().size(); i++){
            if (i>99)
                System.out.print(i);
            else if (i>9)
                System.out.print(" "+i);
            else
                System.out.print(" "+i+" ");
        }
        System.out.println();
    }

}
