package ex01.ImagesToChar.src.java.edu.school21.printer.logic;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FilePrinter {

    private final char white_pixel;

    private final char black_pixel;
    private char[][] charImage;

    private int height;

    private int width;

    public FilePrinter(String white_pixel, String black_pixel) {
        this.white_pixel = white_pixel.toCharArray()[0];
        this.black_pixel = black_pixel.toCharArray()[0];
    }

    public void openFileAndPrint() {
        try {
            File imageFile = new File("target/resources/it.bmp");
            BufferedImage image = ImageIO.read(imageFile);

            width = image.getWidth();
            height = image.getHeight();

            charImage = new char[width][height];

            // Print the pixel values to the console
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int pixel = image.getRGB(x, y);
                    int intensity = (pixel >> 16) & 0xFF; // Since it's grayscale, all channels are the same

                    if (intensity == 0) {
                        charImage[x][y] = black_pixel;

                    } else if (intensity == 255) {
                        charImage[x][y] = white_pixel;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printCharArray() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                System.out.print(charImage[x][y]);
            }
            System.out.println();
        }

    }

}
