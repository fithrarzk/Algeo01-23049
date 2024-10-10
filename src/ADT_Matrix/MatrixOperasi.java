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

    //OPERASI TINGKAT LANJUT 
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

    public static double determinant (double[][] matrix){ //mencari determinan matrix
        double det = 1.0;
        if (!isSquare(matrix)){ //matrix tidak nxn
            System.out.println("Tidak ada determinan!");
        }
        else if (cekSegitiga(matrix)){ //jika matrix berbentuk segitiga (reduksi baris)
            for (int i=0; i<getRowEff(matrix); i++){
                det *= getElmtDiagonal(matrix, i);
            }
        }
        else { //menggunakan ekspansi kofaktor
            if (getColEff(matrix) == 1){
                det = matrix [0][0];
            }
            else if (getColEff(matrix) == 2){
                det = (matrix [0][0] * matrix [1][1]) - (matrix [1][0] * matrix [0][1]);
            }
            else {
                for (int i=0; i<getRowEff(matrix); i++){
                    for (int j=0; j<getColEff(matrix); j++){
                        det += determinant (subMatrix(matrix, i, j)) * Math.pow((-1), (i+j));
                    }
                }
            }
            }
            return det;
        }
        public static double[][] matrixTranspose (double[][] matrix){
            double[][] newMatrix = new double[getRowEff(matrix)-1][getColEff(matrix)-1];
            for (int i=0; i<getRowEff(matrix); i++){
                for (int j=0; j<getColEff(matrix); j++){
                    newMatrix [i][j] = matrix [j][i];
                }
            }
            return newMatrix;
        }
    }

