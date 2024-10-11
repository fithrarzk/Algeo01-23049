package Function;

import ADT_Matrix.Matrix;

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
            for (i = 0; i < m.row; i++){
                // membagi matriks adjoin dengan determinan m
                for (j = 0; j < m.col; j++){
                    mAdjoin.setElmt(i, j, (mAdjoin.getElmt(i, j)/det));
                }
            }
            return mAdjoin;
        }
	}

    public static Matrix inversOBE (Matrix m){
        
    }

}
