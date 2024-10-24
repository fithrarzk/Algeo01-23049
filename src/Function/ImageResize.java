package Function;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class ImageResize {
    public static BufferedImage resizeImage(BufferedImage originalImage, double scaleX, double scaleY) {
        int newWidth = (int) (originalImage.getWidth() * scaleX);
        int newHeight = (int) (originalImage.getHeight() * scaleY);
        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
        for (int newY = 0; newY < newHeight; newY++) {
            for (int newX = 0; newX < newWidth; newX++) {
                double originalX = newX / scaleX;
                double originalY = newY / scaleY;

                // Ambil blok 4x4 untuk ketiga warna
                double[][][] pixelBlock = blok4x4(originalImage, originalX, originalY);

                // Interpolasi bicubic untuk ketiga warna
                int newPixelValue = bicubicInterpolationRGB(pixelBlock, originalX % 1, originalY % 1);

                // Setel piksel baru pada gambar hasil
                resizedImage.setRGB(newX, newY, newPixelValue);
            }
        }
        return resizedImage;
    }

    // Fungsi untuk mengambil blok 4x4 piksel di sekitar titik (x, y)
    public static double[][][] blok4x4(BufferedImage image, double x, double y) {
        double[][][] block = new double[3][4][4];  // 3 warna: R, G, B
        int startX = (int) Math.floor(x) - 1;
        int startY = (int) Math.floor(y) - 1;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int pixelX = Math.min(Math.max(startX + i, 0), image.getWidth() - 1);
                int pixelY = Math.min(Math.max(startY + j, 0), image.getHeight() - 1);

                int rgb = image.getRGB(pixelX, pixelY);
                block[0][i][j] = (rgb >> 16) & 0xFF;  // merah
                block[1][i][j] = (rgb >> 8) & 0xFF;   // hijau
                block[2][i][j] = rgb & 0xFF;          // biru
            }
        }
        return block;
    }

    // interpolasi bicubic merah hijau biru
    public static int bicubicInterpolationRGB(double[][][] pixels, double x, double y) {
        double[][] merah, hijau, biru;
        merah = pixels[0];
        hijau = pixels[1];
        biru = pixels[2];
        int red = (int) BicubicInterpolation.bicubicVal(merah, x, y);
        int green = (int) BicubicInterpolation.bicubicVal(hijau, x, y);
        int blue = (int) BicubicInterpolation.bicubicVal(biru, x, y);
        return (red << 16) | (green << 8) | blue;
    }

    public static void resizing(String file) {
        String path = "test/input/" + file;
        try {
            BufferedImage originalImage = ImageIO.read(new File(path));
            double scaleX = 1.5;
            double scaleY = 2.0;
            BufferedImage resizedImage = resizeImage(originalImage, scaleX, scaleY);
            ImageIO.write(resizedImage, "jpg", new File("test/output/" + file));
            System.out.println("Berhasil mengubah ukuran gambar!");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}