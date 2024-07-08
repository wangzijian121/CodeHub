package a_数组array.稀疏数组;

import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.linear.FieldMatrix;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.util.BigReal;
import org.apache.commons.math3.util.BigRealField;

/**
 * 使用 Apache 的矩阵创建与转换工具
 * @author zijian Wang
 */
public class SparseMatrixWithApache {

    public static void main(String[] args) {
        int[][] matrix = new int[10][10];
        matrix[1][2] = 1;
        matrix[2][3] = 2;
        FieldMatrix fieldMatrix =MatrixUtils.createFieldMatrix(BigRealField.getInstance(),10,10);
        //初始化值
        fieldMatrix.setEntry(1,2,new BigReal(1));
        fieldMatrix.setEntry(2,3,new BigReal(2));
        printMatrix(fieldMatrix);

    }

    private static void printMatrix(FieldMatrix fieldMatrix) {
        FieldElement[][] fieldElements= fieldMatrix.getData();
        for (FieldElement[] fieldElement : fieldElements) {
            for (FieldElement element : fieldElement) {
                BigReal bigReal=(BigReal)element;
                System.out.print(bigReal.doubleValue()+"\t");
            }
            System.out.println();
        }
    }
}
