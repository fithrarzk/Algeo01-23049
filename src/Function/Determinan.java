package Function;
import ADT_Matrix.Matrix;
//import ADT_Matrix.MatrixInput;
//import ADT_Matrix.MatrixOutput;

public class Determinan {
    //Mencari determinan menggunakan ekspansi kofaktor
    public static double determinanKofaktor(Matrix m) {
        int row = m.getRowLength();
        int col = m.getColLength();
        double det = 0;

        // Kasus bukan matriks persegi
        if (!m.isSquare()) {
            return m.MARK;
        }

        // Kasus dasar untuk matriks 1x1
        if (row == 1) {
            return m.getElmt(0, 0);
        }

        // Kasus matriks berukuran mxm dengan m>=2
        for (int i = 0; i < col; i++) {
            Matrix temp = new Matrix(row - 1, col - 1); // Buat matriks baru untuk kofaktor

            // Buat matriks kofaktor
            for (int j = 1; j < row; j++) {
                for (int k = 0; k < col; k++) {
                    if (k < i) {
                        temp.setElmt(j - 1, k, m.getElmt(j, k));
                    } 
                    if (k > i) {
                        temp.setElmt(j - 1, k - 1, m.getElmt(j, k));
                    }
                }
            }

            //MatrixOutput.printMatrix(temp);

            // Hitung determinan menggunakan ekspansi kofaktor
            det += Math.pow(-1, i) * m.getElmt(0, i) * determinanKofaktor(temp);
            //System.out.println("det=" + det);
        }
        

        return det;
    }
}
