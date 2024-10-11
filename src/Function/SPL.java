package Function;
import java.io.BufferedReader;
import ADT_Matrix.*;
import java.io.InputStreamReader;

public class SPL {
    public static void gaussSPL (Matrix Mgauss) {
        BufferedReader inputFile = new BufferedReader(new InputStreamReader(System.in));
        //Melakukan eliminasi gauss 
        Mgauss = Matrix.gaussElimination(Mgauss);
        double X[] = new double[Mgauss.getRowLength()];
    }

    public static void cramerSPL (Matrix m){
        Matrix matMain;
        Matrix matRes;
        Matrix temp;
        
        matMain = new Matrix (m.getRowLength(), m.getColLength()-1);
        matRes = new Matrix (m.getRowLength(), 1);

        // Pengisian setiap matrix
        for (int i=0; i<m.getRowLength(); i++){
            for (int j=0; j<m.getColLength(); j++){
                if (j!=m.getColLength()-1){
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
        if (det == 0 || (m.getRowLength() != m.getColLength()-1)){
            System.out.println("SPL tidak dapat diselesaikan dengan metode cramer.");
        }
        else {
            for (int i=0; i<matMain.getRowLength(); i++){
                temp = new Matrix(matMain.matrix, matMain.getRowLength(), matMain.getColLength());
                for (int j=0; j<matMain.getColLength(); j++){
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


    public static void gaussJordanSPL (Matrix Mgajo) {
        //Melakukan eliminasi gauss jordan
        Mgajo = Matrix.gaussJordanElimination(Mgajo);
        int pil3;
        BufferedReader inputFile = new BufferedReader(new InputStreamReader(System.in));
    }
}
