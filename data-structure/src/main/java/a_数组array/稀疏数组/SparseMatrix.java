package a_数组array.稀疏数组;

import lombok.extern.slf4j.Slf4j;

/**
 * 数据结构-稀疏矩阵
 * 原理：计算次数
 *
 * 10	10	2
 * 1	2	1
 * 2	3	2
 * 10行 10列 共2个数据。
 * 第一个数据位于 (2，1)点。
 * 第二个数据位于 (3，2)点.
 *
 *
 * @author zijian Wang
 */
@Slf4j(topic = "c.SparseMatrix")
public class SparseMatrix {
    public static void main(String[] args) {

        int[][] matrix = new int[10][10];

        matrix[1][2] = 1;
        matrix[2][3] = 2;
        log.info("原始矩阵:");
        printMatrix(matrix);

        log.info("转为稀疏矩阵：");
        //To Sparse Matrix
        int[][] sparseMatrix = toSparseMatrix(matrix);

        log.info("在还原为矩阵：");
        //To array
        toMatrix(sparseMatrix);
    }

    public static int[][] toSparseMatrix(int[][] matrix) {

        int rowLength = matrix.length;
        int colLength = matrix[0].length;
        //数值不为零的矩阵计数
        int countNotZero = getCountNotZero(matrix);
        // init 稀疏矩阵
        int[][] spareMatrix = new int[countNotZero + 1][3];
        //标题 ： row 、col、sum
        spareMatrix[0][0] = rowLength;
        spareMatrix[0][1] = colLength;
        spareMatrix[0][2] = countNotZero;

        //稀疏矩阵的设置值
        int rowBegin = 1;//row count index
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] != 0) {
                    spareMatrix[rowBegin][0] = i;
                    spareMatrix[rowBegin][1] = j;
                    spareMatrix[rowBegin][2] = matrix[i][j];
                    rowBegin++;
                }
            }
        }
        printMatrix(spareMatrix);
        return spareMatrix;
    }

    public static int[][] toMatrix(int[][] spareMatrix) {
        //read header
        int rowLength = spareMatrix[0][0];
        int colLength = spareMatrix[0][1];
        int[][] matrix = new int[rowLength][colLength];
        for (int i = 1; i < spareMatrix.length; i++) {
            for (int j = 0; j < spareMatrix[i].length; j++) {
                matrix[spareMatrix[i][0]][spareMatrix[i][1]] = spareMatrix[i][2];
            }
        }
        printMatrix(matrix);
        return matrix;
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int col : row) {
                System.out.print(col + "\t");
            }
            System.out.println();
        }
    }

    private static int getCountNotZero(int[][] matrix) {
        int count = 0;
        for (int[] row : matrix) {
            for (int col : row) {
                if (col != 0) {
                    count++;
                }
            }
        }
        return count;
    }
}
