package ADT_Matrix;
//import java.io.BufferedReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.*;
//import Function.Determinan;

public class MatrixOutput {
    //Menampilkan matriks pada layar
    public static void printMatrix(Matrix matrix) {
        int i, j;
        for (i = 0; i < matrix.row; i++) {
            for (j = 0; j < matrix.col; j++) {
                System.out.printf("%.4f ",matrix.getElmt(i, j));
            }
            System.out.println();
        }
    }
}
