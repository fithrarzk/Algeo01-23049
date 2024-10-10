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
}
