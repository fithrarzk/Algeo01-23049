package Function;
import ADT_Matrix.*;
//import ADT_Matrix.MatrixInput;
//import ADT_Matrix.MatrixOutput;

public class Determinan {
    //Mencari determinan menggunakan ekspansi kofaktor
    public static double determinanKofaktor(Matrix m) {
        int row = m.getRowEff();
        int col = m.getColEff();
        double det = 0;

        // Kasus bukan matriks persegi
        if (!Matrix.isSquare(m)) {
            System.out.println("Bukan Matrix Persegi Sehingga Nilai Determinan tidak Dapat Ditentukan");
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

    // Mencari Determinan dengan Reduksi Baris
    public static double determinanReduksi(Matrix m){
        if (Matrix.isIdentity(m)){
            return 1;
        }
        if (!Matrix.isSquare(m)) {
            System.out.println("Bukan Matrix Persegi Sehingga Nilai Determinan tidak Dapat Ditentukan");
            return m.MARK;
        }
        
        int i,j;
        int p = 0; // menyimpan jumlah pertukaran baris
        int n = m.getRowEff();

        // menukar baris untuk memindahkan elemen pertama yang tidak nol ke posisi diagonal
        for (int rowidx = 0; rowidx < n-1; rowidx++){
            if (Matrix.FirstElementNot0Coll(m, rowidx, 0) != rowidx){
                Matrix.swapRow(m, Matrix.FirstElementNot0Coll(m, rowidx, 0), rowidx);
                p++;
            }
        }
        // Membuat matriks segitiga atas dengan membuat nilai dibawah diagonal utama menjadi 0
        for (i = 1; i < n; i++){
            for (j = 0; j < i; j++){
                // Jika elemen di posisi (i, j) tidak nol, maka perlu mengeliminasi nilai tersebut
                if (m.getElmt(i,j) != 0){
                    if (m.getElmt(i-1,j)==0){
                        double faktor = (double) m.getElmt(i,j)/ m.getElmt(Matrix.FirstElementNot0Coll(m, 0, j),j);
                        for (int l = 0; l < n; l++)
                        {   
                            double subs = m.getElmt(Matrix.FirstElementNot0Coll(m, 0, j),l) * faktor;
                            m.setElmt(i,l, m.getElmt(i,l)-subs);
                        }
                    }
                    else{
                        double faktor = (double) m.getElmt(i,j)/m.getElmt(i-1,j);
                        for (int l = 0; l < n; l++){
                            double subs = m.getElmt(i-1,l) * faktor;
                            m.setElmt(i,l, m.getElmt(i,l)-subs);
                        }
                    }
                }
            }
        }

        double det = 1;
        for (int q = 0; q < n; q++){
            det *= m.getElmt(q, q);
        }

        det *= Math.pow((-1), p);

        if (det == -0) {
            det = 0;
        }
        return det;
        
    }
}
