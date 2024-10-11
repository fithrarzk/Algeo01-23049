package ADT_Matrix;
import Function.Determinan;
import Function.Invers;
//import Function.Determinan;
import Function.SPL;

public class test {
    public static void main(String[] args) {
        /* 
        // Test case 1: Membaca dan mencetak matriks
        System.out.println("Test Case 1: Input Matriks 3x3");
        
        // Membaca matriks dari input keyboard
        double[][] matrixData = MatrixInput.readMatrixKeyboard();
        
        // Membuat objek Matrix dari data yang dibaca
        Matrix matrix = new Matrix(matrixData, matrixData.length, matrixData[0].length);
        
        // Mencetak matriks yang telah dimasukkan
        System.out.println("Matriks yang dimasukkan:");
        MatrixOutput.printMatrix(matrix);
        
        // Test case 2: Menggunakan metode getRowLength dan getColLength
        System.out.println("\nTest Case 2: Menguji panjang baris dan kolom");
        System.out.println("Jumlah baris: " + matrix.getRowLength());
        System.out.println("Jumlah kolom: " + matrix.getColLength());
        
        // Test case 3: Menguji metode isSquare
        System.out.println("\nTest Case 3: Menguji apakah matriks persegi");
        if (matrix.isSquare()) {
            System.out.println("Matriks adalah persegi.");
        } else {
            System.out.println("Matriks bukan persegi.");
        }
        
        // Test case 4: Menguji setElmt dan getElmt
        //System.out.println("\nTest Case 4: Menguji setElmt dan getElmt");
        //matrix.setElmt(0, 0, 10.0); // Mengatur elemen (0,0) menjadi 10.0
        //MatrixOutput.printMatrix(matrix);
        //System.out.println("Elemen pada indeks (0,0) setelah diset: " + matrix.getElmt(0, 0));
        */
        // Test case 5: Menghitung determinan matriks (jika matriks persegi)
        double[][] mat = MatrixInput.readMatrixKeyboard2();
        Matrix m = new Matrix(mat, mat.length, mat[0].length);
        SPL.splInvers(m);
        /* 
        System.out.println("\nTest Case 5: Menghitung determinan matriks (ekspansi kofaktor)");
        if (matrix.isSquare()) {
            double determinant = Determinan.determinanKofaktor(matrix);
            System.out.println("Determinan matriks: " + determinant);
        } else {
            System.out.println("Tidak dapat menghitung determinan karena matriks bukan persegi.");
        }
        */
        /* 
        System.out.println("Test Case: Menyelesaikan SPL dengan metode Cramer");
        
        // Membaca matriks augmented (gabungan koefisien dan hasil)
        double[][] mat = MatrixInput.readMatrixKeyboard2();
        Matrix m = new Matrix(mat, mat.length, mat[0].length);

        // Menjalankan metode SPL Cramer
        SPL.splCramer(m);
        */
    }
}
