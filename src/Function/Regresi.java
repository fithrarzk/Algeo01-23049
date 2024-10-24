package Function;

import ADT_Matrix.*;
import java.util.ArrayList;
import java.util.List;

public class Regresi {
    public static String[] regresiLinearKeyboard(MatrixOperasi m) {
        List<String> resultToFile = new ArrayList<>();
        int i, j;
        double temp = 0;
        double result = 0;
        double sum = 0;
        MatrixOperasi mTemp;
        double[] x;

        x = new double[m.col - 1];

        // Input nilai-nilai xk yang ingin ditaksir
        resultToFile.add("Masukkan nilai x yang ingin ditaksir: ");
        for (i = 0; i < x.length; i++) {
            x[i] = MatrixInput.input.nextDouble();
        }

        // Membuat matriks baru semacam SPL
        mTemp = new MatrixOperasi(m.col, m.col + 1);

        for (i = 0; i < mTemp.row; i++) {
            for (j = 0; j < mTemp.col; j++) {
                if (i == 0 && j == 0) {
                    temp = m.row;
                } else if (i == 0 && j > 0) {
                    temp = MatrixOperasi.sumCol(m, j - 1);
                } else if (j == 0 && i > 0) {
                    temp = MatrixOperasi.sumCol(m, i - 1);
                } else if (i > 0 && j > 0) {
                    temp = MatrixOperasi.sumMultiplyCol(m, i - 1, j - 1);
                }
                mTemp.setElmt(i, j, temp);
            }
        }

        // Melakukan Eliminasi Gauss
        mTemp = MatrixOperasi.gaussElimination(mTemp);
        double[] m1 = new double[mTemp.getRowEff()];
        MatrixOperasi.backSubstitution(mTemp, m1);

        resultToFile.add("f(x) = ");
        for (i = 0; i < mTemp.row; i++) {
            if (i == 0) {
                result = m1[i];
                resultToFile.add(String.format("%.4f ", m1[i] > 0 ? m1[i] : -m1[i]));
            } else if (i > 0 && i < mTemp.row - 1) {
                result = m1[i] * x[i - 1];
                resultToFile.add(String.format("%s %.4fx%d ", m1[i] > 0 ? "+" : "-", Math.abs(m1[i]), i));
            } else if (i == mTemp.row - 1) {
                result = m1[i] * x[i - 1];
                resultToFile.add(String.format("%s %.4fx%d, ", m1[i] > 0 ? "+" : "-", Math.abs(m1[i]), i));
            }
            sum += result;
        }

        resultToFile.add(String.format("f(xk) = %.4f", sum));

        // Mengembalikan hasil dalam bentuk array string
        return resultToFile.toArray(new String[0]);
    }

    public static String[] regresiLinearFile(MatrixOperasi m) {
        List<String> resultToFile = new ArrayList<>();
        int i, j;
        double temp = 0;
        double result = 0;
        double sum = 0;
        MatrixOperasi mTemp;
        MatrixOperasi m1;
        double[] m2;
        double[] x;

        // Membuat Matriks dari File yang Diinput
        m1 = new MatrixOperasi(m.row - 1, m.col);
        for (i = 0; i < m.row - 1; i++) {
            for (j = 0; j < m.col; j++) {
                m1.setElmt(i, j, m.matrix[i][j]);
            }
        }

        m2 = new double[m.col - 1];
        for (i = 0; i < m2.length; i++) {
            m2[i] = m.getElmt(m.row - 1, i);
        }

        // Membuat matriks baru semacam SPL
        mTemp = new MatrixOperasi(m.col, m.col + 1);

        for (i = 0; i < mTemp.row; i++) {
            for (j = 0; j < mTemp.col; j++) {
                if (i == 0 && j == 0) {
                    temp = m1.row;
                } else if (i == 0 && j > 0) {
                    temp = MatrixOperasi.sumCol(m1, j - 1);
                } else if (j == 0 && i > 0) {
                    temp = MatrixOperasi.sumCol(m1, i - 1);
                } else if (i > 0 && j > 0) {
                    temp = MatrixOperasi.sumMultiplyCol(m1, i - 1, j - 1);
                }
                mTemp.setElmt(i, j, temp);
            }
        }

        // Melakukan Eliminasi Gauss
        mTemp = MatrixOperasi.gaussElimination(mTemp);
        x = new double[mTemp.getRowEff()];
        MatrixOperasi.backSubstitution(mTemp, x);

        resultToFile.add("f(x) = ");
        for (i = 0; i < mTemp.row; i++) {
            if (i == 0) {
                result = x[i];
                resultToFile.add(String.format("%.4f ", x[i] > 0 ? x[i] : -x[i]));
            } else if (i > 0 && i < mTemp.row - 1) {
                result = x[i] * m2[i - 1];
                resultToFile.add(String.format("%s %.4fx%d ", x[i] > 0 ? "+" : "-", Math.abs(x[i]), i));
            } else if (i == mTemp.row - 1) {
                result = x[i] * m2[i - 1];
                resultToFile.add(String.format("%s %.4fx%d, ", x[i] > 0 ? "+" : "-", Math.abs(x[i]), i));
            }
            sum += result;
        }
        resultToFile.add(String.format("f(xk) = %.4f", sum));

        // Mengembalikan hasil dalam bentuk array string
        return resultToFile.toArray(new String[0]);
    }

    public static String[] regresiLinearQuadraticKeyboard(MatrixOperasi m) {
        List<String> resultToFile = new ArrayList<>();
        int i, j;
        double temp = 0;
        double result = 0;
        double sum = 0;
        MatrixOperasi mTemp;
        double[] x;
        x = new double[m.col / 2];
        resultToFile.add("Masukkan nilai x yang ingin ditaksir: ");
        for (i = 0; i < x.length; i++) {
            x[i] = MatrixInput.input.nextDouble();
        }

        // Membuat matriks baru semacam SPL
        mTemp = new MatrixOperasi(2 * m.col - 1, 2 * m.col); // Menampung x, x^2 dan konstanta

        for (i = 0; i < mTemp.row; i++) {
            for (j = 0; j < mTemp.col; j++) {
                if (i == 0 && j == 0) {
                    temp = m.row;
                } else if (i == 0 && j > 0) {
                    temp = MatrixOperasi.sumCol(m, j - 1);
                } else if (j == 0 && i > 0) {
                    temp = MatrixOperasi.sumCol(m, i - 1);
                } else if (i > 0 && j > 0) {
                    temp = MatrixOperasi.sumMultiplyCol(m, i - 1, j - 1);
                }
                mTemp.setElmt(i, j, temp);
            }
        }

        // Melakukan Eliminasi Gauss
        mTemp = MatrixOperasi.gaussElimination(mTemp);
        double[] coefficients = new double[mTemp.getRowEff()];
        MatrixOperasi.backSubstitution(mTemp, coefficients);

        resultToFile.add("f(x) = ");
        for (i = 0; i < mTemp.row; i++) {
            if (i == 0) {
                result = coefficients[i];
                resultToFile.add(String.format("%.4f ", coefficients[i] > 0 ? coefficients[i] : -coefficients[i]));
            } else if (i > 0 && i < mTemp.row - 1) {
                result = coefficients[i] * x[(i - 1) % x.length];
                resultToFile.add(String.format("%s %.4fx%d ", coefficients[i] > 0 ? "+" : "-", Math.abs(coefficients[i]), i));
            } else if (i == mTemp.row - 1) {
                result = coefficients[i] * x[(i - 1) % x.length];
                resultToFile.add(String.format("%s %.4fx%d^2, ", coefficients[i] > 0 ? "+" : "-", Math.abs(coefficients[i]), i));
            }
            sum += result;
        }

        resultToFile.add(String.format("f(xk) = %.4f", sum));

        // Mengembalikan hasil dalam bentuk array string
        return resultToFile.toArray(new String[0]);
    }

    public static String[] regresiLinearQuadraticFile(MatrixOperasi m) {
        List<String> resultToFile = new ArrayList<>();
        int i, j;
        double temp = 0;
        double result = 0;
        double sum = 0;
        MatrixOperasi mTemp;
        MatrixOperasi m1;
        double[] m2;
        double[] x;

        // Membuat Matriks dari File yang Diinput
        m1 = new MatrixOperasi(m.row - 1, m.col);
        for (i = 0; i < m.row - 1; i++) {
            for (j = 0; j < m.col; j++) {
                m1.setElmt(i, j, m.matrix[i][j]);
            }
        }

        m2 = new double[m.col / 2];
        for (i = 0; i < m2.length; i++) {
            m2[i] = m.getElmt(m.row - 1, i);
        }

        // Membuat matriks baru semacam SPL
        mTemp = new MatrixOperasi(2 * m.col - 1, 2 * m.col); // Menampung x, x^2 dan konstanta

        for (i = 0; i < mTemp.row; i++) {
            for (j = 0; j < mTemp.col; j++) {
                if (i == 0 && j == 0) {
                    temp = m1.row;
                } else if (i == 0 && j > 0) {
                    temp = MatrixOperasi.sumCol(m1, j - 1);
                } else if (j == 0 && i > 0) {
                    temp = MatrixOperasi.sumCol(m1, i - 1);
                } else if (i > 0 && j > 0) {
                    temp = MatrixOperasi.sumMultiplyCol(m1, i - 1, j - 1);
                }
                mTemp.setElmt(i, j, temp);
            }
        }

        // Melakukan Eliminasi Gauss
        mTemp = MatrixOperasi.gaussElimination(mTemp);
        x = new double[mTemp.getRowEff()];
        MatrixOperasi.backSubstitution(mTemp, x);

        resultToFile.add("f(x) = ");
        for (i = 0; i < mTemp.row; i++) {
            if (i == 0) {
                result = x[i];
                resultToFile.add(String.format("%.4f ", x[i] > 0 ? x[i] : -x[i]));
            } else if (i > 0 && i < mTemp.row - 1) {
                result = x[i] * m2[i - 1];
                resultToFile.add(String.format("%s %.4fx%d ", x[i] > 0 ? "+" : "-", Math.abs(x[i]), i));
            } else if (i == mTemp.row - 1) {
                result = x[i] * m2[i - 1];
                resultToFile.add(String.format("%s %.4fx%d^2, ", x[i] > 0 ? "+" : "-", Math.abs(x[i]), i));
            }
            sum += result;
        }
        resultToFile.add(String.format("f(xk) = %.4f", sum));

        // Mengembalikan hasil dalam bentuk array string
        return resultToFile.toArray(new String[0]);
    }
}
