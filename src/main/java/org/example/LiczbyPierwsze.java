package org.example;

public class LiczbyPierwsze {

    public static void main(String[] args) {

        int[] liczby = {2, 4, 5, 6, 7, 8, 11, 12, 13, 14, 17, 20, 22, 23, 24, 25, 27, 31};

        System.out.print("Liczbami pierwszymi sa: ");
        for (int i = 0; i < liczby.length; i++) {
            if (czyLiczbaJestPierwsza(liczby[i])) {
                System.out.print(liczby[i] + ", ");
            }
        }

    }

    public static boolean czyLiczbaJestPierwsza(int liczba) {

        if (liczba < 2) return false;
        if (liczba == 2 ||liczba == 3) return true;

        if (liczba % 2 == 0 || liczba % 3 == 0) return false;

        for (int i = 2; i < liczba; i++) {
            if (liczba % i == 0) return false;
        }
        return true;
    }
}
