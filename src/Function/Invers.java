package Function;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ADT_Matrix.MatrixOperasi;
//import ADT_Matrix.MatrixOutput;
import ADT_Matrix.MatrixOutput;

public class Invers {

    public double matrix [][];

    public static MatrixOperasi inversAdjoin (MatrixOperasi m){
		int i, j;
		// mencari matriks adjoin
		MatrixOperasi mAdjoin = MatrixOperasi.Adjoin(m);
		double det = Determinan.determinanKofaktor(m);
		if (det==0){
            return null;
        }
        else{
            for (i = 0; i < m.getRowEff(); i++){
                // membagi matriks adjoin dengan determinan m
                for (j = 0; j < m.getColEff(); j++){
                    mAdjoin.setElmt(i, j, (mAdjoin.getElmt(i, j)/det));
                }
            }
            return mAdjoin;
        }
	}

    /*  
    public static void inversOBE(MatrixOperasi m) {
        int n = m.getRowEff(); // Assuming it's a square matrix
        MatrixOperasi identity = MatrixOperasi.createIdentity(n);

        // Perform the Gauss-Jordan elimination to get the inverse
        for (int i = 0; i < n; i++) {
            double pivot = m.getElmt(i, i); // Ambil elemen diagonal utama
    
            // Jika pivot 0, ini berarti matriks singular, tidak bisa dibalik
            if (pivot == 0) {
                System.out.println("Matrix singular, tidak memiliki invers.");
                return; // Handle zero pivot (not invertible)
            }
            
            // Eliminasi elemen di atas dan di bawah pivot (kolom ke-i)
            for (int j = 0; j < n; j++) {
                m.setElmt(i, j, m.getElmt(i, j) / pivot); 
                identity.setElmt(i, j, identity.getElmt(i, j) / pivot); 
            }

            // Eliminasi elemen di atas dan di bawah pivot (kolom ke-i)
            for (int k = 0; k < n; k++) {
                if (k != i) {
                    double factor = m.getElmt(k, i);
                    for (int j = 0; j < n; j++) {
                        m.setElmt(k, j, m.getElmt(k, j) - factor * m.getElmt(i, j)); 
                        identity.setElmt(k, j, identity.getElmt(k, j) - factor * identity.getElmt(i, j)); 
                    }
                }
            }
        }
        MatrixOutput.printMatrix(identity);
    }
    */

    public static MatrixOperasi inversIdentitas (MatrixOperasi m){
        int i, j, k;
        MatrixOperasi invers;

		// membuat matriks baru dengan ukuran kolom 2 kali lebih besar
		invers = new MatrixOperasi(m.getRowEff(), m.getColEff()*2);
		
		// mengisi matriks dengan matriks identitas dan matriks input
		for (i = 0; i < m.getRowEff(); i++){
			for (j = 0; j < m.getColEff(); j++){
				if (i == j){
					invers.setElmt(i, j+m.getColEff(), 1);
				}
				else{
					invers.setElmt(i, j+m.getColEff(), 0);
				}
				invers.setElmt(i, j, m.getElmt(i,j));
			}
		}
		// mengeloop sehingga mendapatkan invers
		for (i = 0; i < invers.getRowEff(); i++){
			for (j = 0; j < invers.getRowEff(); j++){
				if (i != j){
					k = (i + 1);
					if (invers.getElmt(i,i) == 0){
						while (k < invers.getRowEff()){
							if (invers.getElmt(k,i) != 0){
								// menukar baris
								invers.swapRow(invers, i, k);
							}
							k++;
						}
					}

					double sub = -1 * invers.getElmt(j, i) / invers.getElmt(i, i);
					for (k = i; k < invers.getColEff(); k++){
						invers.setElmt(j, k, (invers.getElmt(j,k)+sub*invers.getElmt(i,k)));
					}
				}
			}
		}

		for (i = 0; i < invers.getRowEff(); i++){
			double divisor = invers.getElmt(i,i);
			for (j = 0; j < invers.getColEff(); j++){
				if (invers.getElmt(i,j) != 0){
					invers.setElmt(i, j, invers.getElmt(i,j) / divisor);
				}
			}
		}

		for (i = 0; i < m.getRowEff(); i++){
			for (j = 0; j < m.getColEff(); j++){
				m.setElmt(i, j, (invers.getElmt(i, j+m.getColEff())));
			}
		}
		return m;
	}

	
	public static String[] handleInversCases(MatrixOperasi m1, int pil1) {
        List<String> resultToFile = new ArrayList<>();
    
        switch (pil1) {
            case 1:
                MatrixOperasi hasil = Invers.inversIdentitas(m1);
                if (Determinan.determinanReduksi(m1) != 0) { 
                    resultToFile.add("Matrix Invers (Metode Identitas):");
                    resultToFile.addAll(Arrays.asList(MatrixOutput.matrixToStringArray(hasil)));
                } else {
                    resultToFile.add("Invers tidak ada.");
                }
                break;

            case 2:
                MatrixOperasi inv = Invers.inversAdjoin(m1);
                if (inv == null) {
                    resultToFile.add("Matrix singular, tidak bisa dihitung inversenya.");
                } else {
                    resultToFile.add("Matrix Invers (Metode Adjoin):");
                    resultToFile.addAll(Arrays.asList(MatrixOutput.matrixToStringArray(inv)));
                }
                break;

            default:
                resultToFile.add("Pilihan tidak valid.");
                break;
        }
		return resultToFile.toArray(new String[0]);
    }
}

