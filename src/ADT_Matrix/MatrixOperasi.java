package ADT_Matrix;

public class MatrixOperasi {
    public static boolean isIdxMatrixValid (int i, int j){ //index valid untuk semua matrix
        return (i >= 0 && j>= 0);
    }
    public static int getRowEff (double[][] matrix){ //panjang baris
        return matrix.length;
    }
    public static int getColEff (double[][] matrix){ //panjang kolom
        return matrix[0].length;
    }
    public static int getLastIdxRow (double[][] matrix){ //index baris
        return getRowEff(matrix) - 1;
    }
    public static int getLastIdxCol (double[][] matrix){ //index kolom
        return getColEff(matrix) - 1;
    }
    public static boolean isIdxEff (double[][] matrix, int i, int j){ //index valid untuk suatu matrix
        return (isIdxMatrixValid(i, j) && i<=getRowEff(matrix) && j <= getColEff(matrix));
    }
    public static double getElmtDiagonal (double[][] matrix, int i){ //ngambil elemen yang ada pada bidang diagonal (harus nxn)
        return matrix [i][i];
    }
    public static double getElmt (double[][] matrix, int i, int j){ //ngambil elemen matrix
        return matrix [i][j];
    }
    public static int getIdxRow (double[][] matrix, double x){ //cari baris untuk nilai x
        int row = 0;
        for (int i=0; i<getRowEff(matrix); i++){
            for (int j=0; j<getColEff(matrix); j++){
                if (x == matrix [i][j]){
                    row = i;
                }
            }
        }
        return row;
    }
    public static int getIdxCol (double[][] matrix, double x){ //cari kolom untuk nilai x
        int col = 0;
        for (int i=0; i<getRowEff(matrix); i++){
            for (int j=0; j<getColEff(matrix); j++){
                if (x == matrix [i][j]){
                    col = j;
                }
            }
        }
        return col;
    }
    public static void setElmt (double[][] matrix, int i, int j, double x){ //mengubah nilai matrix
        matrix [i][j] = x;
    }
    public static void copyMatrix (double[][] matrixIn, double[][] matrixOut){ //copy matrix
        int baris = getRowEff(matrixIn);
        int kolom = getColEff(matrixIn);
        for (int i=0; i<baris; i++){
            for (int j=0; j<kolom; j++){
                matrixOut [i][j] = matrixIn [i][j];
            }
        }
    }
    public static double[][] addMatrix (double[][] matrixA, double[][] matrixB){ //penjumlahan matrix
        double [][] matrix = new double [getRowEff(matrixA)][getRowEff(matrixA)];
        for (int i=0; i<getRowEff(matrixA); i++){
            for (int j=0; j<getColEff(matrixA); j++){
                matrix [i][j] = matrixA [i][j] + matrixB [i][j];
            }
        }
        return matrix;
    }
    public static double[][] substractMatrix (double[][] matrixA, double[][] matrixB){ //pengurangan matrix
        double [][] matrix = new double [getRowEff(matrixA)][getRowEff(matrixA)];
        for (int i=0; i<getRowEff(matrixA); i++){
            for (int j=0; j<getColEff(matrixA); j++){
                matrix [i][j] = matrixA [i][j] - matrixB [i][j];
            }
        }
        return matrix;
    }
    public static double[][] multiplyMatrix (double[][] matrixA, double[][] matrixB){ //perkalian matrix
        double [][] matrix = new double [getRowEff(matrixA)][getRowEff(matrixA)];
        for (int i=0; i<getRowEff(matrixA); i++){
            for (int j=0; j<getColEff(matrixA); j++){
                matrix [i][j] = matrixA [i][j] * matrixB [i][j];
            }
        }
        return matrix;        
    }
    public static double[][] multiplyConst (double[][] matrixA, int x){ //perkalian dengan konstanta matrix
        double [][] matrix = new double [getRowEff(matrixA)][getRowEff(matrixA)];
        for (int i=0; i<getRowEff(matrixA); i++){
            for (int j=0; j<getColEff(matrixA); j++){
                matrix [i][j] = matrixA [i][j] * x;
            }
        }
        return matrix;               
    }
    public static boolean isMatrixEqual (double[][] matrixA, double[][] matrixB){ //cek matrix equal
        boolean status = true;
        if (getColEff(matrixB) != getColEff(matrixA) || getRowEff(matrixB) != getRowEff(matrixA)){
            status = false;
        }
        else {
            for (int i=0; i<getRowEff(matrixB); i++){
                for (int j=0; j<getColEff(matrixB); j++){
                    if (matrixA [i][j] != matrixB [i][j]){
                        status = false;
                    }
                }
            }
        }
        return status;
    }
    public static boolean isMatrixNotEqual (double[][] matrixA, double[][] matrixB){ //cek matrix tidak equal
        return (!isMatrixEqual(matrixA, matrixB));
    }
    public static boolean isMatrixSizeEqual (double[][] matrixA, double[][] matrixB){ //cek ukuran matrix equal
        return (getColEff(matrixB) == getColEff(matrixA) && getRowEff(matrixB) == getRowEff(matrixA));
    }
    public static int countElmt (double[][] matrix){ //hitung jumlah elemen
        int count = 0;
        for (int i=0; i<getRowEff(matrix); i++){
            for (int j=0; j<getColEff(matrix); j++){
                count += 1;
            }
        }
        return count;
    }
    public static boolean isSquare (double[][] matrix){ //cek matrix nxn
        return (getColEff(matrix) == getRowEff(matrix));
    }
    public static boolean isSymmetric (double[][] matrix){ //cek matrix simetris (m[i][j] = m[j][i])
        boolean status = true;
        if (!isSquare(matrix)){
            status = false;
        }
        else {
            for (int i = 0; i<getRowEff(matrix); i++){
                for (int j = 0; j<getColEff(matrix); j++){
                    if (!(matrix [i][j] == matrix [j][i])){
                        status = false;
                    }
                }
            }
        }
        return status;
    }
    public static boolean isIdentity (double[][] matrix){ //cek matrix identitas
        boolean status = true;
        if (!isSymmetric(matrix)){
            status = false;
        }
        else {
            for (int i=0; i<getRowEff(matrix); i++){
                for (int j=0; j<getColEff(matrix); j++){
                    if (getElmtDiagonal(matrix, i) != 1){
                        status = false;
                    }
                    else if (matrix[i][j] != 0 && (i != j)){
                        status = false;
                    }
                }

            }
        }
        return status;
    }
    public static boolean isSparse (double[][] matrix){ //cek matrix sparse (maksimal elemen tidak 0 adalah 5%)
        int notZeroCount = 0;
        for (int i=0; i<getRowEff(matrix); i++){
            for (int j=0; j<getColEff(matrix); j++){
                if (matrix [i][j] != 0){
                    notZeroCount += 1;
                }
            }
        }
        return ((notZeroCount/100) <= (5/100));       
    }
    public static double[][] negation (double[][] matrix){ //membentuk negasi dari matrix (kali -1)
        return multiplyConst (matrix, (-1));
    }


    //** OPERASI TINGKAT LANJUT **\\
    public static boolean cekSegitiga (double[][] matrix){ //cek apabila matrix berbentuk segitiga
        boolean status = true;
        boolean statusBawah = true;
        boolean statusAtas = true;
        if (!isSquare(matrix)){
            status = false;
        }
        else {
            for (int i=0; i<getRowEff(matrix); i++){ //kalau segitiga bawah
                int j = 0;
                while (i != j){
                    if (matrix [i][j] != 0){
                        statusBawah = false;
                    j++;
                }
                }
            }
            for (int i=0; i<getRowEff(matrix); i++){ //kalau segitiga atas
                int j = getLastIdxCol(matrix);
                while (i != j){
                    if (matrix [i][j] != 0){
                        statusAtas = false;
                    j--;
                }
                }
            }
        }
        if (statusAtas || statusBawah){
            status = true;
        }
        return status;
    }
    public static void rowSwap(double[][]m, int rows1, int rows2){ //tuker baris
		double temp;
		for (int i = 0; i < getColEff(m); i++){
			temp = getElmt(m,rows1, i);
			setElmt(m, rows1, i, getElmt(m, rows2, i));
			setElmt(m, rows2, i, temp);
		}
    }        
    public static double[][] subMatrix (double[][] matrix, int baris, int kolom){ //mencari submatrix
        double[][] submatrix = new double [getLastIdxRow(matrix)-1][getLastIdxCol(matrix)-1];
        for (int i=0; i<getRowEff(submatrix); i++){
            for (int j=0; j<getColEff(submatrix); j++){
                if (i < baris && j < kolom){
                    submatrix [i][j] = matrix [i][j];
                }
                if (i < baris && j >= kolom){
                    submatrix [i][j] = matrix [i][j+1];
                }
                if (i >= baris && j < kolom){
                    submatrix [i][j] = matrix [i+1][j];
                }
                if (i >= baris && j >= kolom){
                    submatrix [i][j] = matrix [i+1][j+1];
                }                
            }
        }
        return submatrix;
    }

        public static double[][] matrixTranspose (double[][] matrix){ //transpose matrix
            double[][] newMatrix = new double[getRowEff(matrix)-1][getColEff(matrix)-1];
            for (int i=0; i<getRowEff(matrix); i++){
                for (int j=0; j<getColEff(matrix); j++){
                    newMatrix [i][j] = matrix [j][i];
                }
            }
            return newMatrix;
        }
        public static double[][] createIdentity (int x){ //buat matrix identitas
            double[][] identity = new double [x-1][x-1];
            for (int i=0; i<x; i++){
                for (int j=0; j<x; j++){
                    if (i == j){
                        identity [i][j] = 1;
                    }
                    else {
                        identity [i][j] = 0;
                    }
                }
            }
            return identity;
        }

        public static double[][] gaussElimination(double[][] matrix) { //gauss elimnasi
            int n = getRowEff(matrix);
            int m = getColEff(matrix);
        
            // Forward Elimination
            for (int i = 0; i < n; i++) {
                int pivotRow = i;
        
                // Find the first non-zero pivot
                if (i < m) {
                    while (pivotRow < n && getElmt(matrix, pivotRow, i) == 0) {
                        pivotRow++;
                    }
        
                    // Skip if no pivot found
                    if (pivotRow == n) {
                        continue;
                    }
        
                    // Normalize pivot row
                    double pivotValue = getElmt(matrix, pivotRow, i);
                    if (pivotValue != 1) {
                        for (int j = i; j < m; j++) {
                            setElmt(matrix, pivotRow, j, getElmt(matrix, pivotRow, j) / pivotValue);
                        }
                    }
        
                    // Swap pivot row with current row
                    for (int j = i; j < m; j++) {
                        double temp = getElmt(matrix, i, j);
                        setElmt(matrix, i, j, getElmt(matrix, pivotRow, j));
                        setElmt(matrix, pivotRow, j, temp);
                    }
        
                    // Eliminate subsequent rows
                    for (int j = i + 1; j < n; j++) {
                        double factor = getElmt(matrix, j, i);
                        for (int k = i; k < m; k++) {
                            setElmt(matrix, j, k, getElmt(matrix, j, k) - factor * getElmt(matrix, i, k));
                        }
                    }
                }
            }
        
            // Move zero rows to the bottom
            for (int i = 0; i < n; i++) {
                boolean allZero = true;
                for (int j = 0; j < m - 1; j++) {
                    if (getElmt(matrix, i, j) != 0) {
                        allZero = false;
                        break;
                    }
                }
                if (allZero) {
                    for (int k = i; k < n - 1; k++) {
                        rowSwap(matrix, k, k + 1);
                    }
                }
            }
        
            // Normalize each row's pivot and perform further elimination
            for (int i = 0; i < n; i++) {
                int pivotCol = 0;
                while (pivotCol < m - 1 && getElmt(matrix, i, pivotCol) == 0) {
                    pivotCol++;
                }
        
                if (pivotCol < m - 1) {
                    double pivotValue = getElmt(matrix, i, pivotCol);
                    if (pivotValue != 1) {
                        for (int k = pivotCol; k < m; k++) {
                            setElmt(matrix, i, k, getElmt(matrix, i, k) / pivotValue);
                        }
                    }
                }
            }
        
            return matrix;
        }
        
        public static double[][] gaussJordanElimination(double[][] A) { //gauss jordan eliminasi
            int n = getRowEff(A);
            int m = getColEff(A);
        
            // Forward Elimination with Pivot
            for (int i = 0; i < n; i++) {
                int pivotRow = i;
        
                // Find pivot row
                if (i < getColEff(A)) {
                    while (pivotRow < n && getElmt(A, pivotRow, i) == 0) {
                        pivotRow++;
                    }
        
                    // Skip if no pivot is found
                    if (pivotRow == n) {
                        continue;
                    }
        
                    // Swap rows to bring pivot row to the current position
                    rowSwap(A, i, pivotRow);
        
                    // Normalize pivot to 1
                    double pivot = getElmt(A, i, i);
                    for (int j = i; j < m; j++) {
                        setElmt(A, i, j, getElmt(A, i, j) / pivot);
                    }
        
                    // Eliminate other rows
                    for (int j = 0; j < n; j++) {
                        if (j != i) {
                            double factor = getElmt(A, j, i);
                            for (int k = i; k < m; k++) {
                                setElmt(A, j, k, getElmt(A, j, k) - factor * getElmt(A, i, k));
                            }
                        }
                    }
                }
            }
        
            // Move zero rows to the bottom
            for (int i = 0; i < n; i++) {
                boolean allZero = true;
                for (int j = 0; j < m - 1; j++) {
                    if (getElmt(A, i, j) != 0) {
                        allZero = false;
                        break;
                    }
                }
                if (allZero) {
                    for (int k = i; k < n - 1; k++) {
                        rowSwap(A, k, k + 1);
                    }
                }
            }
        
            // Ensure pivot normalization if not already done
            for (int i = 0; i < n; i++) {
                int pivotCol = 0;
                while (pivotCol < m - 1 && getElmt(A, i, pivotCol) == 0) {
                    pivotCol++;
                }
        
                if (pivotCol < m - 1) {
                    double pivotValue = getElmt(A, i, pivotCol);
                    if (pivotValue != 1) {
                        for (int k = pivotCol; k < m; k++) {
                            setElmt(A, i, k, getElmt(A, i, k) / pivotValue);
                        }
                    }
                }
            }
        
            return A;
        }
    
    }
    

