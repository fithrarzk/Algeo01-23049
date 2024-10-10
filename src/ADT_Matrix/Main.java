package ADT_Matrix;

import java.util.Scanner;

import ADT_Matrix.MatrixInput;
import Function.*;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int pilihan, pil1, pil2, pil3;

        // Menampilkan menu utama
        System.out.println("1. Sistem Persamaan Linier");
        System.out.println("2. Determinan");
        System.out.println("3. Matriks Balikan");
        System.out.println("4. Interpolasi Polinom");
        System.out.println("5. Interpolasi Bicubic Spline");
        System.out.println("6. Regresi Linier Berganda");
        System.out.println("7. Keluar");
        
        pilihan = input.nextInt();

        if (pilihan == 1) {
            // Menu untuk sistem persamaan linear
            System.out.println("1. Metode Eliminasi Gauss");
            System.out.println("2. Metode Eliminasi Gauss-Jordan");
            System.out.println("3. Metode Matriks Balikan");
            System.out.println("4. Kaidah Cramer");
            
            pil1 = input.nextInt();

            System.out.println("1. Masukan dari Keyboard");
            System.out.println("2. Masukan dari File");
            pil2 = input.nextInt();

            System.out.println("1. Masukan Matrix a dan b");
            System.out.println("2. Masukan Matrix Augmented");
            pil3 = input.nextInt();

            double[][] m;

            if (pil2 == 1) {
                if (pil3 == 1) {
                    m = MatrixInput.readMatrixSPL();
                } else {
                    m = MatrixInput.readMatrixKeyboard();
                }
            } else {
                // Tambahkan logika untuk input dari file jika diperlukan
                System.out.println("Fitur masukan dari file belum tersedia.");
                return;
            }

            Matrix m1 = new Matrix(m, m.length, m[0].length);

            switch (pil1) {
                case 1:
                    SPL.gaussSPL(m1);
                    break;
                case 2:
                    SPL.gaussJordanSPL(m1);
                    break;
                case 3:
                    // Tambahkan logika untuk metode matriks balikan jika diperlukan
                    System.out.println("Fitur matriks balikan belum tersedia.");
                    return;
                case 4:
                    // Tambahkan logika untuk kaidah Cramer jika diperlukan
                    System.out.println("Fitur kaidah Cramer belum tersedia.");
                    return;
                default:
                    System.out.println("Pilihan tidak valid.");
                    return;
            }

            MatrixOutput.printMatrix(m1);
        } else {
            System.out.println("Fitur lain belum tersedia.");
        }

        input.close();
    }
}

