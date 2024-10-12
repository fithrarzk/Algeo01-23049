package Function;

import ADT_Matrix.Matrix;
//import ADT_Matrix.MatrixOutput;

public class Invers {
    public static Matrix inversAdjoin (Matrix m){
		int i, j;
		// mencari matriks adjoin
		Matrix mAdjoin = Matrix.Adjoin(m);
		double det = Determinan.determinanKofaktor(m);
		if (det==0){
            return null;
        }
        else{
            for (i = 0; i < m.row; i++){
                // membagi matriks adjoin dengan determinan m
                for (j = 0; j < m.col; j++){
                    mAdjoin.setElmt(i, j, (mAdjoin.getElmt(i, j)/det));
                }
            }
            return mAdjoin;
        }
	}

    public static Matrix inversOBE (Matrix matrix){
        int n = matrix.getRowLength();
        Matrix identity = Matrix.createIdentity(n);
        //MatrixOutput.printMatrix(identity);
        for (int i = 0; i < n; i++) {
            double pivot = matrix.getElmt(i, i); // Ambil elemen diagonal utama
    
            // Jika pivot 0, ini berarti matriks singular, tidak bisa dibalik
            if (pivot == 0) {
                return null;
            }
    
            // Membagi semua elemen pada baris i dengan pivot untuk membuat diagonal utama menjadi 1
            for (int j = 0; j < n; j++) {
                matrix.setElmt(i, j, matrix.getElmt(i, j) / pivot);
                identity.setElmt(i, j, identity.getElmt(i, j) / pivot);
            }
    
            // Eliminasi elemen di atas dan di bawah pivot (kolom ke-i)
            for (int k = 0; k < n; k++) {
                if (k != i) {
                    double factor = matrix.getElmt(k, i);
                    for (int j = 0; j < n; j++) {
                        matrix.setElmt(k, j, matrix.getElmt(k, j) - factor * matrix.getElmt(i, j));
                        identity.setElmt(k, j, identity.getElmt(k, j) - factor * identity.getElmt(i, j));
                    }
                }
            }
        }
        return identity;
    }
}
