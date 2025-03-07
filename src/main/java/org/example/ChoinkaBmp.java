package org.example;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class ChoinkaBmp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj sciezke do pliku");
        String path = scanner.nextLine();

        int H = 10;
        int W = (H * 2) - 1;
        int Pien = 5;
        int szerokoscPnia = 3;
        int height = H + Pien;
        int width = W;
        int rowSize = (width * 3 + 3) & (~3);
        int pictureSize = rowSize * height;
        int fileSize = 54 + pictureSize;

        try {
            FileOutputStream fos = new FileOutputStream(path);

            fos.write(new byte[]{
                    0x42, 0x4D,
                    (byte) (fileSize), (byte) (fileSize >> 8), (byte) (fileSize >> 16), (byte) (fileSize >> 24),
                    0x00, 0x00, 0x00, 0x00,
                    0x36, 0x00, 0x00, 0x00
            });

            fos.write(new byte[]{
                    0x28, 0x00, 0x00, 0x00,
                    (byte) width, 0x00, 0x00, 0x00,
                    (byte) height, 0x00, 0x00, 0x00,
                    0x01, 0x00,
                    0x18, 0x00,
                    0x00, 0x00, 0x00, 0x00,
                    (byte) (pictureSize), (byte) (pictureSize >> 8), (byte) (pictureSize >> 16), (byte) (pictureSize >> 24),
                    0x13, 0x0B, 0x00, 0x00,
                    0x13, 0x0B, 0x00, 0x00,
                    0x00, 0x00, 0x00, 0x00,
                    0x00, 0x00, 0x00, 0x00
            });

            int[][] choinka = new int[height][width];

            for (int i = 0; i < H; i++) {
                int liczbaGwiazdek = (i * 2) + 1;
                int liczbaSpacjiZLewej = (W - liczbaGwiazdek) / 2;

                for (int j = 0; j < liczbaGwiazdek; j++) {
                    choinka[i][liczbaSpacjiZLewej + j] = 1;
                }
            }

            int liczbaSpacjiPniaZLewej = (W - szerokoscPnia) / 2;
            for (int i = H; i < height; i++) {
                for (int j = 0; j < szerokoscPnia; j++) {
                    choinka[i][liczbaSpacjiPniaZLewej + j] = 2;
                }
            }

            for (int i = height - 1; i >= 0; i--) {
                for (int j = 0; j < width; j++) {
                    if (choinka[i][j] == 1) {
                        fos.write(new byte[]{0x00, (byte) 0xFF, 0x00});
                    } else if (choinka[i][j] == 2) {
                        fos.write(new byte[]{(byte) 139, 69, 19});
                    } else {
                        fos.write(new byte[]{(byte) 0xFF, (byte) 0xFF, (byte) 0xFF});
                    }
                }
                for (int j = 0; j < rowSize - width * 3; j++) {
                    fos.write(0x00);
                }
            }
            fos.close();
            System.out.println("Plik BMP zapisano pomyślnie");

        } catch (IOException e) {
            System.out.println("Błąd zapisu choinki, Świąt Nie Będzie :(");
        }
    }
}

//   /Users/sylwiabarteczko//Desktop/choinka.bmp