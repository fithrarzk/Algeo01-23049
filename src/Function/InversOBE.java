package Function;
import ADT_Matrix.MatrixOperasi;

public class InversOBE {
    public static double[][] inversIndetity (double[][] matrix){
        int n = MatrixOperasi.getRowEff(matrix)-1;
        double[][] identity = MatrixOperasi.createIdentity(n);
        for (int i=0; i<n; i++){
            double pivot = matrix[i][i]; //Di main nanti di cek matrix(0,0) nya 0 atau ngga
            for (int j=0; j<n; j++){
                matrix [i][j] /= pivot;
                identity [i][j] /= pivot;
            }
            for (int k=0; k<n; k++){
                if (k!=i){
                    double faktor = matrix [k][i];
                    for (int j=0; j<n; j++){
                        matrix [k][j] -= faktor * matrix [i][j];
                        identity [k][j] -= faktor * matrix [i][j];
                    }
                }
            }
        }
        return identity;
    }
}
