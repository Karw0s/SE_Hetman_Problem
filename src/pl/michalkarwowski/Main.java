package pl.michalkarwowski;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {


    public static void main(String[] args) throws IOException {
        int N = 0;
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));
        do {
            System.out.println("Podaj wartość -1 aby wyjść\nPodaj rozmiar szachownicy NxN");
            System.out.print("N = ");
            String input = reader.readLine();

            try {
                N = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Błędny rozmiar szachownicy.");
            }
            if (N > 0) {
                Solver o = new Solver();
                o.solve(N);
            } else if (N == 0 || N < -1) {
                System.out.println("Błędny rozmiar szachownicy.");
            }
        } while (N != -1);

    }
}
