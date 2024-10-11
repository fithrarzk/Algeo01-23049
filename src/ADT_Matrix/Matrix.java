package ADT_Matrix;

//import java.io.BufferedReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.Scanner;
import Function.*;

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

    // Mengecek Matrix persegi atau bukan
    public static boolean isSquare (Matrix m) {
        return (m.getRowLength() == m.getRowLength());
    }


    // Mengecek apakah suatu matrix merupakan matrix identitas atau bukan
    public static boolean isIdentity(Matrix m){
        if(isSquare(m)){
            for (int i=0;i<m.getRowLength();i++){
                for (int j=0;j<m.getColLength();j++){
                    if (i==j){
                        if (m.getElmt(i,j) !=1){
                            return false;
                        }
                    } else {
                        if (m.getElmt(i,j) != 0){
                            return false;
                        }
                    }
                }
            }
            return true;
        }
        return false;
    } 

    public static int FirstElementNot0Coll(Matrix m, int startrowIdx, int colIdx) {
        // Mengecek idx pertama saat elemen tidak bernilai 0 pada colIdx 
        int idx = startrowIdx;
        for (int rowIdx = startrowIdx; rowIdx < m.getRowLength(); rowIdx++){
            if (m.getElmt(rowIdx, colIdx) != 0){
                idx = rowIdx;
                break;
            }
        }
        return idx;
    }

    // Swap baris
    public static Matrix swap_row (Matrix m, int row1, int row2) {
        for (int j = 0; j < m.getColLength(); j++) {
            double temp = m.getElmt(row1,j);
            m.setElmt(row1, j, m.getElmt(row2,j));
            m.setElmt(row2, j, temp);
        }
        return m;
    }

    // Mencari determinan dari matriks kofaktor yang baris nya selain row dan kolom nya selain col
    public static double detKofaktorRC(Matrix m, int row, int col) {
        int n = m.getRowLength();
        Matrix temp = new Matrix(n-1, n-1); 
        
        int tempRow = 0; //  baris pada matriks temp
        for (int i = 0; i < n; i++) {
            if (i == row) {
                continue; 
            }
    
            int tempCol = 0; //  kolom pada matriks temp
            for (int j = 0; j < n; j++) {
                if (j == col) {
                    continue; 
                }
    
                // Memasukkan elemen ke matriks minor
                temp.setElmt(tempRow, tempCol, m.getElmt(i, j));
                tempCol++;
            }
            tempRow++; 
        }
    
        // Mengembalikan determinan dari matriks minor
        return Determinan.determinanKofaktor(temp);
    }
    
    // Matriks Kofaktor
    public static Matrix matriksKofaktor (Matrix m){
        int n = m.getRowLength();
        Matrix mKofaktor;

		mKofaktor = new Matrix(n,n);
		
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++){
				mKofaktor.setElmt(i, j, detKofaktorRC(m,i,j));
				if ((i+j) % 2 == 1 && mKofaktor.getElmt(i,j) != 0)
					mKofaktor.setElmt(i, j, (mKofaktor.getElmt(i,j) * -1));
			}
		}

		return mKofaktor;
    }

    // Matriks adjoin dari matrix kofaktor
    public static Matrix Adjoin (Matrix m){
        Matrix mAdjoin;
        int i, j;
        
        m = Matrix.matriksKofaktor(m);
        mAdjoin = new Matrix(m.row, m.col);

        for (i = 0; i < m.row; i++){
            for (j = 0; j < m.col; j++){
                mAdjoin.setElmt(i, j, m.getElmt(j, i));
            }
        }

        return mAdjoin;
    }

    // Mengalikan 2 Matrix
    public static Matrix multiplyMatrix(Matrix m1, Matrix m2){
        int i, j, k;
        double temp;
        Matrix MMultiply;

        MMultiply = new Matrix(m1.row, m2.col);
        for (i = 0; i < MMultiply.row; i++) {
            for (j = 0; j < MMultiply.col; j++) {
                temp = 0;
                for(k = 0; k < m1.col; k++) {
                    temp = temp + (m1.getElmt(i, k) * m2.getElmt(k, j));
                }
                MMultiply.setElmt(i, j, temp);
            }
        }
        return MMultiply;
    }

}
