package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj sciezke do pliku");
        String filePath = scanner.nextLine();

        try {
            byte[] data = Files.readAllBytes(Paths.get(filePath));

            if (jpgFile(data)) {
                System.out.println("Jest to plik JPG");
            } else if (pngFile(data)) {
                System.out.println("Jest to plik PNG");
            } else {
                System.out.println("Plik nieznanego formatu");
            }
        } catch (IOException e) {
            System.out.println("Blad oczytu");
        }
    }

    public static boolean jpgFile(byte[] data) {

        return data[0] == (byte) 0xFF &&
                data[1] == (byte) 0xD8 &&
                data[2] == (byte) 0xFF;
    }

    public static boolean pngFile(byte[] data) {

        return data[0] == (byte) 0x89 &&
                data[1] == (byte) 0x50 &&
                data[2] == (byte) 0x4E &&
                data[3] == (byte) 0x47 &&
                data[4] == (byte) 0x0D &&
                data[5] == (byte) 0x0A &&
                data[6] == (byte) 0x1A &&
                data[7] == (byte) 0x0A;
    }

}


// /Users/sylwiabarteczko//Desktop/obrazek
// /Users/sylwiabarteczko//Desktop/transparentny obrazek