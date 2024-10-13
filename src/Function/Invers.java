package Function;

import ADT_Matrix.Matrix;
import ADT_Matrix.MatrixOutput;

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
            for (i = 0; i < m.getRowEff(); i++){
                // membagi matriks adjoin dengan determinan m
                for (j = 0; j < m.getColEff(); j++){
                    mAdjoin.setElmt(i, j, (mAdjoin.getElmt(i, j)/det));
                }
            }
            return mAdjoin;
        }
	}

    public static void inversOBE(Matrix m) {
        int n = m.getRowEff(); // Assuming it's a square matrix
        Matrix identity = Matrix.createIdentity(n);

        // Perform the Gauss-Jordan elimination to get the inverse
        for (int i = 0; i < n; i++) {
            double pivot = m.getElmt(i, i); // Get the pivot element
            if (pivot == 0) {
                System.out.println("Pivot element is zero, cannot compute inverse.");
                return; // Handle zero pivot (not invertible)
            }
            
            // Normalize the pivot row by dividing by the pivot
            for (int j = 0; j < n; j++) {
                m.setElmt(i, j, m.getElmt(i, j) / pivot); // Update matrix m
                identity.setElmt(i, j, identity.getElmt(i, j) / pivot); // Update identity matrix
            }

            // Eliminate the other rows
            for (int k = 0; k < n; k++) {
                if (k != i) {
                    double factor = m.getElmt(k, i); // Get the factor to eliminate
                    for (int j = 0; j < n; j++) {
                        m.setElmt(k, j, m.getElmt(k, j) - factor * m.getElmt(i, j)); // Update matrix m
                        identity.setElmt(k, j, identity.getElmt(k, j) - factor * identity.getElmt(i, j)); // Update identity matrix
                    }
                }
            }
        }
        MatrixOutput.printMatrix(identity);
    }
    
}

