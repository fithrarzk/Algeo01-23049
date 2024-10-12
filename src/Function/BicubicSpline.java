package Function;
import Function.Invers;
import ADT_Matrix.MatrixOperasi;

public class BicubicSpline {
    public static void RowBicubic(double[][] matrix, int x, int y, int count, int f) { // 
        double p, q; // p = x^i q = y^j
        double val = 1.0;

        for (int i=0; i<4; i++){
            for (int j=0; j<4; j++){
                if (f == 1){ //f (x,y)
                    p = Math.pow(x%2, i);
                    q = Math.pow(y%2, j);
                    if (x%2 == 0 && i == 0){ //membuat 0^0 = 1
                        p = 1;
                    }
                    if (y%2 == 0 && j == 0){ //membuat 0^0 = 1
                        q = 1;
                    }
                    val = p*q;
                }
        
                if (f == 2){ //fx (x,y)
                    p = Math.pow(x%2, Math.abs(j-1));
                    q = Math.pow (y%2, i);
                    if (x%2 == 0 && j == 1){
                        p = 1;
                    }
                    if (y%2 == 0 && i == 0){
                        q = 1;
                    }
                    val = j*p*q;
                }
        
                if (f == 3){ //fy (x,y)
                    p = Math.pow(x%2, j);
                    q = Math.pow (y%2, Math.abs(i-1));
                    if (x%2 == 0 && j == 0){
                        p = 1;
                    }
                    if (y%2 == 0 && i == 1){
                        q = 1;
                    }
                    val = i*p*q;            
                }
        
                if (f == 4){ // fxy (x,y)
                    p = Math.pow(x%2, Math.abs(j-1));
                    q = Math.pow (y%2, Math.abs(i-1));
                    if (x%2 == 0 && j == 1){
                        p = 1;
                    }
                    if (y%2 == 0 && i == 1){
                        q = 1;
                    }
                    val = i*j*p*q;             
                }
                matrix [x][count] = val;
                count++;
            }
        }
    }

    public static double[][] createBicubic(){ //membuat bicubic
        double[][] matrixB = new double [16][16];
        int y = 0, f = 0;
        for (int x=0; x<16; x++){
            int count = 0;
            if (x%4==0 && x!=0){
                y = 0;
            }
            else if (x%2==0 && x!=0){
                y = 1;
            }
            RowBicubic(matrixB, x, y, count, f);
            if ((x+1)%4 == 0){
                f++;
            }
        }
        return matrixB;
    }

    public static double[][] valueBicubicA (double[][] matrix){ //mencari nilai semua nilai a 
        double [][] matrixBic = createBicubic();
        double [][] matrixA = MatrixOperasi.multiplyMatrix(Invers.inversOBE(matrixBic), matrix);
        return matrixA;
    }

    public static double hasilBicubic (double [][] matrix, int a, int b){ //menghitung hasil f(a,b)
        double hasil = 0.0;
        double valA = 1.0, valB = 1.0;
        double[][] matrixA = valueBicubicA(matrix);
        int k = 0;
        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                valA = Math.pow(a, i);
                valB = Math.pow(b, j);
                if (a == 0 && i == 0){
                    valA = 1;
                if (b == 0 && j == 0){
                    valB = 1;
                }
                }
                hasil += matrixA[k][0] * valA * valB;
                k++;
            }
        }
        return hasil;
    }
}
