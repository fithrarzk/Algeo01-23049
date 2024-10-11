package ADT_Matrix;

//import java.io.BufferedReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.Scanner;
//import Function.*;

public class Matrix {
    public int row;
    public int col;
    public double[][] matrix  ;
    public double MARK = Double.NaN;

    // KONSTRUKTOR //
    // Membuat Sebuah Matriks kosong dari tipe double [][]
    public Matrix (double contents [][] , int rows, int cols) {
        this.matrix = contents;
        this.row = rows;
        this.col = cols;
    }

    public Matrix (int rows, int cols){
        int i, j;
        matrix = new double[rows][cols];
        this.row = rows;
        this.col = cols;
        for (i = 0; i < rows; i++){
            for (j = 0; j < cols ; j++){
                setElmt(i, j, MARK);
            }
        }
    }

    // SELEKTOR //
    // Untuk Mencari Panjang Baris
    public int getRowLength(){
        return this.row;
    }

    // Untuk Mencari Panjang Kolom
    public int getColLength(){
        return this.col;
    }

    // Untuk Mengambil Indeks Terakhir dari Baris 
    public int getLastRowIdx(){
        return this.row - 1;
    }

    // Untuk Mengambil Indeks Terakhir dari Kolom 
    public int getLastColIdx(){
        return this.col - 1;
    }

    // Untuk Menentukan Apakah Matriks Berukuran MxM atau Tidak
    public boolean isSquare(){
        return this.row == this.col;
    }

    // Untuk Mengambil Elemen  pada Suatu Indeks
    public double getElmt(int i, int j){
        return this.matrix[i][j];
    }

    // Untuk Memasukkan Elemen ke Suatu Indeks
    public void setElmt(int i, int j, double elmt){
        this.matrix[i][j] = elmt;
    }

    public void rowSwap(Matrix m, int rows1, int rows2){
		double temp;
		for (int i = 0; i < m.col; i++){
			temp = m.getElmt(rows1, i);
			m.setElmt(rows1, i, m.getElmt(rows2, i));
			m.setElmt(rows2, i, temp);
		}
    }


    public static Matrix gaussElimination(Matrix matrix) {
        int n = matrix.getRowLength();
        int m = matrix.getColLength();
        double[] X = new double[n];
    
        // Forward Elimination
        for (int i = 0; i < n; i++) {
            int pivotRow = i;
    
            // Find the first non-zero pivot
            if (i < matrix.col) {
                while (pivotRow < n && matrix.getElmt(pivotRow, i) == 0) {
                    pivotRow++;
                }
    
                // Skip if no pivot found
                if (pivotRow == n) {
                    continue;
                }
    
                // Normalize pivot row
                double pivotValue = matrix.getElmt(pivotRow, i);
                if (pivotValue != 1) {
                    for (int j = i; j < m; j++) {
                        matrix.setElmt(pivotRow, j, matrix.getElmt(pivotRow, j) / pivotValue);
                    }
                }
    
                // Swap pivot row with current row
                for (int j = i; j < m; j++) {
                    double temp = matrix.getElmt(i, j);
                    matrix.setElmt(i, j, matrix.getElmt(pivotRow, j));
                    matrix.setElmt(pivotRow, j, temp);
                }
    
                // Eliminate subsequent rows
                for (int j = i + 1; j < n; j++) {
                    double factor = matrix.getElmt(j, i);
                    for (int k = i; k < m; k++) {
                        matrix.setElmt(j, k, matrix.getElmt(j, k) - factor * matrix.getElmt(i, k));
                    }
                }
            }
        }
    
        // Move zero rows to the bottom
        for (int i = 0; i < n; i++) {
            boolean allZero = true;
            for (int j = 0; j < m - 1; j++) {
                if (matrix.getElmt(i, j) != 0) {
                    allZero = false;
                    break;
                }
            }
            if (allZero) {
                for (int k = i; k < n - 1; k++) {
                    matrix.rowSwap(matrix, k, k + 1);
                }
            }
        }
    
        // Normalize each row's pivot and perform further elimination
        for (int i = 0; i < n; i++) {
            int pivotCol = 0;
            while (pivotCol < m - 1 && matrix.getElmt(i, pivotCol) == 0) {
                pivotCol++;
            }
    
            if (pivotCol < m - 1) {
                double pivotValue = matrix.getElmt(i, pivotCol);
                if (pivotValue != 1) {
                    for (int k = pivotCol; k < m; k++) {
                        matrix.setElmt(i, k, matrix.getElmt(i, k) / pivotValue);
                    }
                }
            }
        }
    
        return matrix;
    }
    
    public static Matrix gaussJordanElimination(Matrix A) {
        int n = A.getRowLength();
        int m = A.getColLength();
    
        // Forward Elimination with Pivot
        for (int i = 0; i < n; i++) {
            int pivotRow = i;
    
            // Find pivot row
            if (i < A.col) {
                while (pivotRow < n && A.getElmt(pivotRow, i) == 0) {
                    pivotRow++;
                }
    
                // Skip if no pivot is found
                if (pivotRow == n) {
                    continue;
                }
    
                // Swap rows to bring pivot row to the current position
                A.rowSwap(A, i, pivotRow);
    
                // Normalize pivot to 1
                double pivot = A.getElmt(i, i);
                for (int j = i; j < m; j++) {
                    A.setElmt(i, j, A.getElmt(i, j) / pivot);
                }
    
                // Eliminate other rows
                for (int j = 0; j < n; j++) {
                    if (j != i) {
                        double factor = A.getElmt(j, i);
                        for (int k = i; k < m; k++) {
                            A.setElmt(j, k, A.getElmt(j, k) - factor * A.getElmt(i, k));
                        }
                    }
                }
            }
        }
    
        // Move zero rows to the bottom
        for (int i = 0; i < n; i++) {
            boolean allZero = true;
            for (int j = 0; j < m - 1; j++) {
                if (A.getElmt(i, j) != 0) {
                    allZero = false;
                    break;
                }
            }
            if (allZero) {
                for (int k = i; k < n - 1; k++) {
                    A.rowSwap(A, k, k + 1);
                }
            }
        }
    
        // Ensure pivot normalization if not already done
        for (int i = 0; i < n; i++) {
            int pivotCol = 0;
            while (pivotCol < m - 1 && A.getElmt(i, pivotCol) == 0) {
                pivotCol++;
            }
    
            if (pivotCol < m - 1) {
                double pivotValue = A.getElmt(i, pivotCol);
                if (pivotValue != 1) {
                    for (int k = pivotCol; k < m; k++) {
                        A.setElmt(i, k, A.getElmt(i, k) / pivotValue);
                    }
                }
            }
        }
    
        return A;
    }

}
