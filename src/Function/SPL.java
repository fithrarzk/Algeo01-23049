package Function;
import java.io.BufferedReader;
import ADT_Matrix.*;
import java.io.InputStreamReader;

public class SPL {

    public static void gaussSPL (Matrix matrix) {
        // BufferedReader inputFile = new BufferedReader(new InputStreamReader(System.in));
        //Melakukan eliminasi gauss 
        matrix = Matrix.gaussElimination(matrix);
        double x[] = new double[matrix.getRowEff()];
        //Menganalisis matriks hasil eliminasi gauss apakah memiliki solusi tidak ada, unik atau banyak
        int solutionType = Matrix.solutionType(matrix);
        //Matrix.backSubstitution(matrix, X);
        System.out.println("Hasil Matrix Setelah Gauss Elimination");
        MatrixOutput.printMatrix(matrix);
        System.out.println();
        if (solutionType == 0) {
            System.out.println("Solusi tidak ada.");
        } 
        else if (solutionType == 1) {
            Matrix.backSubstitution(matrix, x);
            System.out.println("Solusi tunggal:");
            for (int i = 0; i < matrix.getRowEff(); i++) {
                System.out.printf("X[%d] = %.4f%n", i + 1, x[i]);
            }

        } else {
                //Jika solusi berupa parametrik maka memanggil fungsi solusi parametrik
                System.out.println("Solusi banyak (parametrik): ");
                Matrix.parametrik(matrix);
        }
    }

    public static void gaussJordanSPL (Matrix Mgaussjordan) {
        // BufferedReader inputFile = new BufferedReader(new InputStreamReader(System.in));
        //Melakukan eliminasi gauss jordan
        Mgaussjordan = Matrix.gaussJordanElimination(Mgaussjordan);
        //Menganalisis matriks hasil eliminasi gauss apakah memiliki solusi tidak ada, unik atau banyak
        int solutionType = Matrix.solutionType(Mgaussjordan);
        //Matrix.backSubstitution(Mgauss, X);
        System.out.println("Hasil Matrix Setelah Gauss Jordan Elimination");
        MatrixOutput.printMatrix(Mgaussjordan);
        System.out.println();
        if (solutionType == 0) {
            System.out.println("Solusi tidak ada.");
        } 
        else if (solutionType == 1) {
            System.out.println("Solusi tunggal:");
            int n= Mgaussjordan.getColEff();
            for (int i = 0; i < Mgaussjordan.getRowEff(); i++) {
                System.out.printf("X[%d] = %.4f%n", i + 1, Mgaussjordan.getElmt(i, n-1));
            }

        } else {
                //Jika solusi berupa parametrik maka memanggil fungsi solusi parametrik
                System.out.println("Solusi banyak (parametrik):");
                Matrix.parametrik(Mgaussjordan);
        }
    }

    public static void cramerSPL (Matrix m){
        Matrix matMain;
        Matrix matRes;
        Matrix temp;
        
        matMain = new Matrix(m.getRowEff(), m.getColEff()-1);
        matRes = new Matrix(m.getRowEff(), 1);

        // Pengisian setiap matrix
        for (int i=0; i<m.getRowEff(); i++){
            for (int j=0; j<m.getColEff(); j++){
                if (j!=m.getColEff()-1){
                    matMain.setElmt(i, j, m.getElmt(i, j));
                }
                else {
                    matRes.setElmt(i, 0, m.getElmt(i, j));
                }
            }
        }

        double det = Determinan.determinanKofaktor(matMain);
        // System.out.printf("det == %.4f\n", (det));
        // Jika det = 0 atau Banyak persamaan != Banyak variabel, maka 
        // tidak bisa diselesaikan dengan cramer
        if (det == 0 || (m.getRowEff() != m.getColEff()-1)){
            System.out.println("SPL tidak dapat diselesaikan dengan metode cramer.");
        }
        else {
            for (int i=0; i<matMain.getRowEff(); i++){
                temp = new Matrix(matMain.matrix, matMain.getRowEff(), matMain.getColEff());
                for (int j=0; j<matMain.getColEff(); j++){
                    temp.setElmt(j, i, matRes.getElmt(j,0));
                }
                //MatrixOutput.printMatrix(temp);
                double solution = Determinan.determinanKofaktor(temp)/det;
                System.out.printf("X%d = %.4f\n", (i+1), (solution));
                for (int k = 0; k < matMain.row; k++){
                    temp.setElmt(k, i, m.getElmt(k, i));
                }
            }
        }
    }

    public static void inversSPL(Matrix m) {
        Matrix matMain, matRes;
        matMain = new Matrix (m.getRowEff(), m.getColEff()-1);
        matRes = new Matrix (m.getRowEff(), 1);
        
        if (!Matrix.isSquare(matMain)){
            System.out.println("Persamaan tidak dapat diselesaikan dengan metode invers SPL karena matrix koefisien bukan matrix persegi sehingga invers tidak dapat ditentukan.");
            return;
        }
        // Pengisian setiap matrix
        for (int i=0; i<m.getRowEff(); i++){
            for (int j=0; j<m.getColEff(); j++){
                if (j!=m.getColEff()-1){
                    matMain.setElmt(i, j, m.getElmt(i, j));
                }
                else {
                    matRes.setElmt(i, 0, m.getElmt(i, j));
                }
            }
        }
        
        Matrix matMainInvers=Invers.inversAdjoin(matMain);
        if (matMainInvers==null){
            System.out.println("SPL tidak dapat diselesaikan dengan matriks balikan karena nilai balikan matriks tidak terdefinisi.");
        }
        else {
            Matrix Result = new Matrix (m.getRowEff(), 1);
            Result = Matrix.multiplyMatrix(matMainInvers, matRes);
            //MatrixOutput.printMatrix(matMainInvers);

            for (int i=1; i<=m.getRowEff(); i++){
                System.out.printf("X%d = %.4f\n", (i), Result.getElmt(i-1,0));
            }
            //MatrixOutput.printMatrix(Result);
        }
    }
}
