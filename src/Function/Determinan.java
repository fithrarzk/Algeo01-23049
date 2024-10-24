package Function;
import java.util.ArrayList;
import java.util.List;

import ADT_Matrix.*;
//import ADT_Matrix.MatrixInput;
//import ADT_Matrix.MatrixOutput;

public class Determinan {
    //Mencari determinan menggunakan ekspansi kofaktor
    public static double determinanKofaktor(MatrixOperasi m) {
        int row = m.getRowEff();
        int col = m.getColEff();
        double det = 0;

        // Kasus bukan matriks persegi
        if (!MatrixOperasi.isSquare(m)) {
            //System.out.println("Bukan Matrix Persegi Sehingga Nilai Determinan tidak Dapat Ditentukan.");
            return m.MARK;
        }

        // Kasus dasar untuk matriks 1x1
        if (row == 1) {
            return m.getElmt(0, 0);
        }

        // Kasus matriks berukuran mxm dengan m>=2
        for (int i = 0; i < col; i++) {
            MatrixOperasi temp = new MatrixOperasi(row - 1, col - 1); // Buat matriks baru untuk kofaktor

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
    public static double determinanReduksi(MatrixOperasi m){
        if (MatrixOperasi.isIdentity(m)){
            return 1;
        }
        if (!MatrixOperasi.isSquare(m)) {
            //System.out.println("Bukan Matrix Persegi Sehingga Nilai Determinan tidak Dapat Ditentukan");
            return m.MARK;
        }
        
        int i,j;
        int p = 0; // menyimpan jumlah pertukaran baris
        int n = m.getRowEff();

        // menukar baris untuk memindahkan elemen pertama yang tidak nol ke posisi diagonal
        for (int rowidx = 0; rowidx < n-1; rowidx++){
            if (MatrixOperasi.FirstElementNot0Coll(m, rowidx, 0) != rowidx){
                MatrixOperasi.swapRow(m, MatrixOperasi.FirstElementNot0Coll(m, rowidx, 0), rowidx);
                p++;
            }
        }
        // Membuat matriks segitiga atas dengan membuat nilai dibawah diagonal utama menjadi 0
        for (i = 1; i < n; i++){
            for (j = 0; j < i; j++){
                // Jika elemen di posisi (i, j) tidak nol, maka perlu mengeliminasi nilai tersebut
                if (m.getElmt(i,j) != 0){
                    if (m.getElmt(i-1,j)==0){
                        double faktor = (double) m.getElmt(i,j)/ m.getElmt(MatrixOperasi.FirstElementNot0Coll(m, 0, j),j);
                        for (int l = 0; l < n; l++)
                        {   
                            double subs = m.getElmt(MatrixOperasi.FirstElementNot0Coll(m, 0, j),l) * faktor;
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

    public static String[] handleDeterminantCases(MatrixOperasi m1, int choice) {
        List<String> resultToFile = new ArrayList<>();
        switch (choice) {
            case 1:
                double det1 = determinanKofaktor(m1);
                if (Double.isNaN(det1)) {
                    resultToFile.add("Bukan Matrix Persegi Sehingga Nilai Determinan tidak Dapat Ditentukan.");
                } else {
                    resultToFile.add("Hasil Determinan (Metode Kofaktor): " + det1);
                }
                break;

            case 2:
                double det2 = determinanReduksi(m1);
                if (Double.isNaN(det2)) {
                    resultToFile.add("Bukan Matrix Persegi Sehingga Nilai Determinan tidak Dapat Ditentukan.");
                } else {
                    resultToFile.add("Hasil Determinan (Metode Reduksi): " + det2);
                }
                break;

            default:
                resultToFile.add("Pilihan tidak valid.");
                break;
        }
        // Convert the result list to a String array and return
        return resultToFile.toArray(new String[0]);
    }
}
