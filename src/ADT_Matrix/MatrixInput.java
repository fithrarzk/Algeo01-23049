package ADT_Matrix;
//import java.util.*;
//import java.io.*;
//import java.nio.file.Paths;
import java.util.Scanner;

public class MatrixInput {
    public static Scanner input = new Scanner(System.in);

    public static double[][] readMatrixKeyboard(){
        int i,j;
        double [][] matrix;
        int rows, cols;

        // Input Jumlah Baris dan Kolom
        System.out.print("Masukkan jumlah baris: ");
        rows = input.nextInt();
        System.out.print("Masukkan jumlah kolom: ");
        cols = input.nextInt();
        
        // Membuat Matriks 
        matrix = new double[rows][cols];
        int expectedElements = rows * cols;
        System.out.println("Masukkan " + expectedElements + " elemen matriks: ");
        for(i = 0; i < rows; i++){
            for(j = 0; j < cols; j++){
                matrix[i][j] = input.nextDouble();
            }
        }
        return matrix;
    }

    public static double[][] readMatrixSPL(){
        int i,j;
        double [][] matrix1, matrix2, matrix;
        int rows, cols;

        // Input Jumlah Baris dan Kolom
        System.out.print("Masukkan jumlah baris: ");
        rows = input.nextInt();
        System.out.print("Masukkan jumlah kolom: ");
        cols = input.nextInt();
        
        // Membuat Matriks 1
        matrix1 = new double[rows][cols];
        int expectedElements = rows * cols;
        System.out.println("Masukkan " + expectedElements + " elemen matriks utama: ");
        for(i = 0; i < rows; i++){
            for(j = 0; j < cols; j++){
                matrix1[i][j] = input.nextDouble();
            }
        }

        // Membuat Matriks 2 dengan ukuran nx1
        matrix2 = new double[rows][1];
        System.out.println("Masukkan elemen matriks hasil: ");
        for(i = 0; i < rows; i++){
            for(j = 0; j < 1; j++){
                matrix2 [i][j] = input.nextDouble();
            }
        }

        // Membuat Matriks Augmented dengan Menggabungkan Matrix A dan B
        matrix = new double [rows][cols + 1];
        for (i = 0; i < rows; i++){
            System.arraycopy(matrix1[i], 0, matrix[i], 0, cols);
            System.arraycopy(matrix2[i], 0, matrix[i], cols, 1);
        }
        
        return matrix;
    }
}
