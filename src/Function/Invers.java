package Function;

import ADT_Matrix.MatrixOperasi;
import ADT_Matrix.MatrixOutput;

public class Invers {
    public static MatrixOperasi inversAdjoin (MatrixOperasi m){
		int i, j;
		// mencari matriks adjoin
		MatrixOperasi mAdjoin = MatrixOperasi.Adjoin(m);
		double det = Determinan.determinanKofaktor(m);
		if (det==0){
            return null;
        }
        else{
            for (i = 0; i < m.getRowEff(); i++){
                // membagi matriks adjoin dengan determinan m
                for (j = 0; j < m.getColEff(); j++){
                    mAdjoin.setElmt(i, j, (mAdjoin.getElmt(i, j)/det));
                }
            }
            return mAdjoin;
        }
	}

    public static void inversOBE(MatrixOperasi m) {
        int n = m.getRowEff(); // Assuming it's a square matrix
        MatrixOperasi identity = MatrixOperasi.createIdentity(n);

        // Perform the Gauss-Jordan elimination to get the inverse
        for (int i = 0; i < n; i++) {
            double pivot = m.getElmt(i, i); // Ambil elemen diagonal utama
    
            // Jika pivot 0, ini berarti matriks singular, tidak bisa dibalik
            if (pivot == 0) {
                System.out.println("Matrix singular, tidak memiliki invers.");
                return; // Handle zero pivot (not invertible)
            }
            
            // Eliminasi elemen di atas dan di bawah pivot (kolom ke-i)
            for (int j = 0; j < n; j++) {
                m.setElmt(i, j, m.getElmt(i, j) / pivot); 
                identity.setElmt(i, j, identity.getElmt(i, j) / pivot); 
            }

            // Eliminasi elemen di atas dan di bawah pivot (kolom ke-i)
            for (int k = 0; k < n; k++) {
                if (k != i) {
                    double factor = m.getElmt(k, i);
                    for (int j = 0; j < n; j++) {
                        m.setElmt(k, j, m.getElmt(k, j) - factor * m.getElmt(i, j)); 
                        identity.setElmt(k, j, identity.getElmt(k, j) - factor * identity.getElmt(i, j)); 
                    }
                }
            }
        }
        MatrixOutput.printMatrix(identity);
    }
}

