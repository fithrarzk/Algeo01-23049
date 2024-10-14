package ADT_Matrix;

import Function.Determinan;

public class MatrixOperasi {
    public int row;
    public int col;
    public double[][] matrix;
    public double MARK = Double.NaN;

    
    public MatrixOperasi(double contents[][], int rows, int cols) {
        this.matrix = contents;
        this.row = rows;
        this.col = cols;
    }
    

    public MatrixOperasi(int rows, int cols){
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


    public void setElmt(int i, int j, double elmt){
        this.matrix[i][j] = elmt;
    }

    public int getColEff(){
        return this.col;
    }

    public int getRowEff(){
        return this.row;
    }


    public double getElmt(int i, int j){
        return this.matrix[i][j];
    }



    public static MatrixOperasi swapRow (MatrixOperasi m, int row1, int row2) {
        for (int j = 0; j < m.getColEff(); j++) {
            double temp = m.getElmt(row1,j);
            m.setElmt(row1, j, m.getElmt(row2,j));
            m.setElmt(row2, j, temp);
        }
        return m;
    }

    public static MatrixOperasi addRow (MatrixOperasi m, double[] newRow){
        MatrixOperasi mTemp;
        double[][] matrix;
        int i, j;

        matrix = new double[m.row + 1][m.col];
        for(i = 0; i < m.row; i++){
            for(j = 0; j < m.col; j++){
                matrix[i][j] = m.matrix[i][j];
            }
        }
        for(i = 0; i < m.col; i++){
            matrix[m.row][i] = newRow[i];
        }
        mTemp = new MatrixOperasi(matrix, m.row+1, m.col);
        return mTemp;
    }

    public static boolean isSquare (MatrixOperasi m){ //cek matrix nxn
        return m.getColEff() == m.getRowEff();
    }

    public static MatrixOperasi createIdentity(int n) {
        MatrixOperasi identityMatrix = new MatrixOperasi(n, n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    identityMatrix.setElmt(i, j, 1.0);
                } else {
                    identityMatrix.setElmt(i, j, 0.0);
                }
            }
        }
    
        return identityMatrix;
    }
    
    public static boolean isIdentity(MatrixOperasi m){
        if(isSquare(m)){
            for (int i=0;i<m.getRowEff();i++){
                for (int j=0;j<m.getColEff();j++){
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

    public static int FirstElementNot0Coll(MatrixOperasi m, int startrowIdx, int colIdx) {
        // Mengecek idx pertama saat elemen tidak bernilai 0 pada colIdx 
        int idx = startrowIdx;
        for (int rowIdx = startrowIdx; rowIdx < m.getRowEff(); rowIdx++){
            if (m.getElmt(rowIdx, colIdx) != 0){
                idx = rowIdx;
                break;
            }
        }
        return idx;
    }


    public static int solutionType(MatrixOperasi matrix) {
        int i, j;
        int n, m;
        int augmentedColumns;
        boolean hasZeroRowAugmented = false; // Penanda untuk solusi tak hingga
        boolean inconsistent = false; // Penanda untuk tidak ada solusi
        boolean hasAllDiagonalsOne = true; // Penanda solusi tunggal
        
        n = matrix.getRowEff();
        m = matrix.getColEff();
        augmentedColumns = m - 1;  // Kolom augmented (tanpa kolom hasil persamaan)
        
        if (m-1>n){
            return 2;
        }
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
        }else if (m-1>n && !inconsistent ||hasZeroRowAugmented){
            return 2; // Solusi tak hingga
        } else if (hasAllDiagonalsOne) {
            return 1;  // Solusi tunggal
        }
        
        return -1; 
    }
    
    public static void solveManySolution(MatrixOperasi matrix) {
        int nEff = matrix.getColEff() - 1;
        boolean[] visited = new boolean[nEff];
        char[] parametric = new char[nEff];
        int cur = 17;
        
        for (int i = 0; i < nEff; i++) {
            visited[i] = false;
        }
        for (int i = 0; i < matrix.getRowEff(); i++) {
            for (int j = i; j < nEff; j++) {
                if (matrix.getElmt(i, j) == 1) {
                visited[j] = true;
                StringBuilder temp = new StringBuilder();
                
                if (Math.abs(matrix.getElmt(i, matrix.getColEff() - 1)) > 1e-8) {
                    temp.append(String.format("%.4f", (matrix.getElmt(i, matrix.getColEff() - 1))));
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

    public static boolean KolomNol(MatrixOperasi m, int idxCol) {
        for (int i = 0; i < m.getRowEff(); i++) {
            if (m.getElmt(i,idxCol) != 0) {
                return false;
            }
        }
        return true;
    }

    public static String[] parametrik(MatrixOperasi m) {
        String[] solusi = new String[m.getColEff() - 1];
        String[] par = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        int i, j, a, b;
        int x = 0;
    
        for (j = 0; j < m.getColEff() - 1; j++) {
            StringBuilder result = new StringBuilder();
            if (KolomNol(m, j)) {
                solusi[x] = par[x];
                x++;
            } else {
                // cek apakah elemen itu leading one
                for (i = m.getRowEff() - 1; i >= 0; i--) {
                    if (m.getElmt(i,j) != 0) {
                        for (b = 0; b < m.getColEff() - 1; b++) {
                            if (m.getElmt(i,b) != 0) {
                                break;
                            }
                        } // b adalah indeks leading one
                        if (j != b) { // kalau dia bukan leading one
                            solusi[x] = par[x];
                            x++;
                        } else { // kalau dia leading one
                            if (m.getElmt(i,m.getColEff()-1) != 0) {
                                result.append(String.format("%.3f", m.getElmt(i,m.getColEff()-1)));
                            }
                            for (a = b + 1; a < m.getColEff() - 1; a++) {
                                if (m.getElmt(i,a) > 0) {
                                    result.append(" - ").append(String.format("%.3f", m.getElmt(i,a))).append("x").append(a + 1);
                                } else if (m.getElmt(i,a) < 0 && m.getElmt(i,m.getColEff()-1) != 0) {
                                    result.append(" + ").append(String.format("%.3f", (-1 * m.getElmt(i,a)))).append("x").append(a + 1);
                                } else if (m.getElmt(i,a) < 0 && m.getElmt(i,m.getColEff()-1) == 0) {
                                    result.append("").append(String.format("%.3f", (-1 * m.getElmt(i,a)))).append("x").append(a + 1);
                                }
                            }
                            solusi[x] = result.toString();
                            x++;
                        }
                        break;
                    }
                }
            }
        }
        for (int y = 0; y < (m.getColEff()) - 1; y++) {
            System.out.printf("x%d = ", y+1);
            System.out.println(solusi[y]);
        }
        
        for (int w = 0; w < x; w++) {
            // Cek apakah solusi mengandung variabel lain
            String currentSolution = solusi[w];
            for (int e = 0; e < x; e++) {
                // Ganti variabel lain yang merupakan parameter
                if (currentSolution.contains("x" + (e + 1)) && w != e) {
                    currentSolution = currentSolution.replace("x" + (e + 1), par[e]); // Mengganti x dengan parameter
                }
            }
            solusi[w] = currentSolution; // Update solusi dengan substitusi
        }
        return solusi;
    }

    public static void backSubstitution(MatrixOperasi matrix, double[] X) {
        int i, j;
        int n, m;
        
        n = matrix.getRowEff();
        m = matrix.getColEff();
        
        for (i = n - 1; i >= 0; i--) {
            X[i] = matrix.getElmt(i, m - 1);
            for (j = i + 1; j < n; j++) {
                X[i] -= matrix.getElmt(i, j) * X[j];
            }
            X[i] /= matrix.getElmt(i, i);
        }
    }
    

    // ELIMINASI GEDE-GEDE


    public static MatrixOperasi gaussElimination(MatrixOperasi matrix) {
        int n = matrix.getRowEff();
        int m = matrix.getColEff();
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
                    swapRow(matrix, k, k + 1);
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

    public static MatrixOperasi gaussJordanElimination(MatrixOperasi A) {
        int n = A.getRowEff();
        int m = A.getColEff();
    
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
                swapRow(A, i, pivotRow);
    
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
                    swapRow(A, k, k + 1);
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


    public static double detKofaktorRC(MatrixOperasi m, int row, int col) {
        int n = m.getRowEff();
        MatrixOperasi temp = new MatrixOperasi(n-1, n-1); 
        
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
    public static MatrixOperasi matriksKofaktor (MatrixOperasi m){
        int n = m.getRowEff();
        MatrixOperasi mKofaktor;

		mKofaktor = new MatrixOperasi(n,n);
		
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
    public static MatrixOperasi Adjoin (MatrixOperasi m){
        MatrixOperasi mAdjoin;
        int i, j;
        
        m = MatrixOperasi.matriksKofaktor(m);
        mAdjoin = new MatrixOperasi(m.getRowEff(), m.getColEff());

        for (i = 0; i < m.row; i++){
            for (j = 0; j < m.getColEff(); j++){
                mAdjoin.setElmt(i, j, m.getElmt(j, i));
            }
        }
        return mAdjoin;
    }

    public static MatrixOperasi multiplyMatrix(MatrixOperasi m1, MatrixOperasi m2){
        int i, j, k;
        double temp;
        MatrixOperasi MMultiply;

        MMultiply = new MatrixOperasi(m1.row, m2.col);
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