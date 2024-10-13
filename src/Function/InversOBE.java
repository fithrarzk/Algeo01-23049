package Function;

import ADT_Matrix.Matrix;

public class InversOBE {
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
    }
}

