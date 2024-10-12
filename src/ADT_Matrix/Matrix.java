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

    // Swap baris dan hasilnya disimpan pada matrix baru
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

    public static Matrix gaussElimination(Matrix matrix) {
        int n = matrix.getRowLength();
        int m = matrix.getColLength();
        // double[] X = new double[n];
    
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
                    swap_row(matrix, k, k + 1);
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

    public static int SolutionTypeAlternative(Matrix matrix) {
        int i, j;
        int n, m;
        int augmentedColumns;
        boolean hasZeroRowAugmented = false; // Penanda untuk solusi tak hingga
        boolean inconsistent = false; // Penanda untuk tidak ada solusi
        boolean hasAllDiagonalsOne = true; // Penanda solusi tunggal
    
        n = matrix.getRowLength();
        m = matrix.getColLength();
        augmentedColumns = m - 1;  // Kolom augmented (tanpa kolom hasil persamaan)
    
        for (i = 0; i < n; i++) {
            boolean isZeroRowMain = true; // Asumsi awal bahwa baris utama bernilai 0 semua
    
            for (j = 0; j < augmentedColumns; j++) {
                if (matrix.getElmt(i, j) != 0) {
                    isZeroRowMain = false;  
                }
            }
    
            // Cek diagonal utama
            if (i < augmentedColumns && matrix.getElmt(i, i) != 1) {
                hasAllDiagonalsOne = false;  
            }
    
            // Jika baris utama nol dan elemen augmented juga nol
            if (isZeroRowMain && matrix.getElmt(i, augmentedColumns) == 0) {
                hasZeroRowAugmented = true; 
            }
    
            // Jika baris utama nol tapi elemen augmented tidak nol
            else if (isZeroRowMain && matrix.getElmt(i, augmentedColumns) != 0) {
                inconsistent = true; 
            }
        }
    
        // Tentukan jenis solusi berdasarkan kondisi yang telah diperiksa
        if (inconsistent) {
            return 0;  // Tidak ada solusi
        } else if (hasZeroRowAugmented) {
            return 2;  // Solusi tak hingga
        } else if (hasAllDiagonalsOne) {
            return 1;  // Solusi tunggal
        }
    
        return -1; 
    }

    public static void solveManySolution(Matrix matrix) {
        int nEff = matrix.getColLength() - 1;
        boolean[] visited = new boolean[nEff];
        char[] parametric = new char[nEff];
        int cur = 17;

        for (int i = 0; i < nEff; i++) {
            visited[i] = false;
        }
        for (int i = 0; i < matrix.getRowLength(); i++) {
            for (int j = i; j < nEff; j++) {
                if (matrix.getElmt(i, j) == 1) {
                visited[j] = true;
                StringBuilder temp = new StringBuilder();

                if (Math.abs(matrix.getElmt(i, matrix.getColLength() - 1)) > 1e-8) {
                    temp.append(String.format("%.4f", (matrix.getElmt(i, matrix.getColLength() - 1))));
                }

                for (int k = j + 1; k < nEff; k++) {
                    if (Math.abs(matrix.getElmt(i, k)) > 1e-8) {
                        if (!visited[k]) {
                            visited[k] = true;
                            parametric[k] = (char) ('a' + cur % 26);
                            System.out.printf("X%d = %c%n", k + 1, parametric[k]);
                            cur = (cur + 1) % 26;
                        }

                        if (matrix.getElmt(i, k) > 0) {
                            if (temp.length() == 0) {
                                temp.append(String.format("%.4f", Math.abs(matrix.getElmt(i, k))));
                            } else {
                                if (Math.abs(matrix.getElmt(i, k)) == 1) {
                                    temp.append(String.format(" - ", Math.abs(matrix.getElmt(i, k))));
                                }
                                else {
                                    temp.append(String.format(" - %.4f", Math.abs(matrix.getElmt(i, k))));
                                }
                            }
                        } else {
                            if (temp.length() == 0) {
                                temp.append(String.format("%.4f", Math.abs(matrix.getElmt(i, k))));
                            } else {
                                if (Math.abs(matrix.getElmt(i, k)) == 1) {
                                    temp.append(String.format(" + ", Math.abs(matrix.getElmt(i, k))));
                                }
                                else {
                                temp.append(String.format(" + %.4f", Math.abs(matrix.getElmt(i, k))));
                                }
                            }
                        }
                        temp.append(parametric[k]);
                    }
                }
                System.out.printf("X%d = %s%n", j + 1, temp.toString());
                break;
            } 
            else {
                if (!visited[j]) {
                    visited[j] = true;
                    parametric[j] = (char) ('a' + cur % 26);
                    System.out.printf("X%d = %c%n", j + i, parametric[j]);
                    cur = (cur + 1) % 26;
                    }
                }
            }
        }
    }

    // Subtitusi dari baris paling bawah
    public static void backSubstitution(Matrix matrix, double[] X) {
        int i, j;
        int n, m;
        
        n = matrix.getRowLength();
        m = matrix.getColLength();

        for (i = n - 1; i >= 0; i--) {
            X[i] = matrix.getElmt(i, m - 1); // elemen augmented
            for (j = i + 1; j < n; j++) {
                X[i] -= matrix.getElmt(i, j) * X[j];
            }
            X[i] /= matrix.getElmt(i, i);
        }
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
                swap_row(A, i, pivotRow);
    
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
                    swap_row(A, k, k + 1);
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

    // Fungsi untuk membuat matriks identitas ukuran n x n
    public static Matrix createIdentity(int n) {
        Matrix identityMatrix = new Matrix(n, n); // Inisialisasi matrix ukuran n x n
    
        // Mengisi elemen diagonal utama dengan 1, dan elemen lainnya dengan 0
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    identityMatrix.setElmt(i, j, 1.0); // Set diagonal utama menjadi 1
                } else {
                    identityMatrix.setElmt(i, j, 0.0); // Set elemen non-diagonal menjadi 0
                }
            }
        }
    
        return identityMatrix;
    }
    

}
