package ds.arrays_and_strings.book;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 1.7 Rotate Matrix: Given an image represented by an NxN matrix, where each pixel in the image is 4
 * bytes, write a method to rotate the image by 90 degrees. Can you do this in place?
 * Hints: #51, #100
 *
 * #51.   1.7 Try thinking about it layer by layer. Can you rotate a specific layer?
 * #100.  1.7 Rotating a specific layer would just mean swapping the values in four arrays. If you were
 *            asked to swap the values in two arrays, could you do this? Can you then extend it to four
 *            arrays?
 *
 */
public class RotateMatrix {

    /*
    * NXN Matrix
    * o = O(N^2)
    * */
    public String doRotate90Degrees(String[][] matrix){
        // Check if NxN matrix is valid
        if(null == matrix || matrix.length == 0 || !isValidNXNMatrix(matrix)){
            return "Invalid NXN matrix";
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.print("\n");
        }
        System.out.print("\n\n");
        int matrixLength = matrix.length;
        for(int i=0; i< matrixLength/2;i++){
            for(int j=i; j<matrixLength-i-1;j++){
                String temp = matrix[i][j];
                matrix[i][j] = matrix[matrixLength -1 -j][i];
                matrix[matrixLength -1 -j][i] = matrix[matrixLength - 1-i][matrixLength - 1 - j];
                matrix[matrixLength-1-i][matrixLength - 1 - j] = matrix[j][matrixLength - 1 - i];
                matrix[j][matrixLength - 1 - i] = temp;
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.print("\n");
        }
        return null;
    }

    private boolean isValidNXNMatrix(String[][] matrix) {
        for (String[] strings : matrix) {
            if (matrix.length != strings.length) {
                return false;
            }
        }
        return true;
    }

    public static void main(String... args){
        RotateMatrix rotateMatrix = new RotateMatrix();
        String[][] matrix = {
                {"A1", "A2", "A3","A4"},
                {"B1", "B2", "B3", "B4"},
                {"C1", "C2", "C3", "C4"},
                {"D1", "D2", "D3", "D4"}};
        System.out.println(rotateMatrix.doRotate90Degrees(matrix));
    }
    /**
     * 1.7 Rotate Matrix: Given an image represented by an NxN matrix, where each pixel in the image is 4
     * bytes, write a method to rotate the image by 90 degrees. Can you do this in place?
     * SOLUTION
     * Because we're rotating the matrix by 90 degrees, the easiest way to do this is to implement the rotation in
     * layers. We perform a circular rotation on each layer, moving the top edge to the right edge, the right edge
     * to the bottom edge, the bottom edge to the left edge, and the left edge to the top edge.
     * How do we perform this four-way edge swap? One option is to copy the top edge to an array, and then
     * move the left to the top, the bottom to the left, and so on. This requires O(N) memory, which is actually
     * un necessa ry.
     * A better way to do this is to implement the swap index by index. In this case, we do the following:
     * 1 for i = 0 to n
     * 2 temp = top[i]j
     * 3 top[i] = left[i]
     * 4 left[i] = bottom[i]
     * 5 bottom[i] = right[i]
     * 6 right[i] = temp
     * We perform such a swap on each layer, starting from the outermost layer and working our way inwards.
     * (Alternatively, we could start from the inner layer and work outwards.)
     * The code for this algorithm is below.
     * 1 boolean rotate(int[][] matrix) {
     * 2 if (matrix.length == 0 I I matrix. length != matrix[0].length) return false;
     * 3 int n = matrix. length;
     * 4 for (int layer = 0; layer < n / 2; layer++) {
     * 5 int first = layer;
     * 6 int last = n - 1 - layer;
     * 7 for(int i = first; i < last; i++) {
     * 8 int offset = i-first;
     * 9
     * 1El
     * 11
     * 12
     * 13
     * 14
     * 15
     * 16
     * 17
     * 18
     * 19
     * 20
     * 21
     * 22 }
     * 23 }
     * int top = matrix[first][i]; II save top
     * II left -> top
     * matrix[first][i] matrix[last-offset] [first];
     * II bottom -> left
     * matrix[last-offset] [first] matrix[last][last - offset];
     * II right -> bottom
     * matrix[last][last - offset] matrix[i][last];
     * II top -> right
     * matrix[i][last] top; II right <- saved top
     * 24 return true;
     * 25 }
     * This algorithm is O( N2 ), which is the best we can do since any algorithm must touch all N2 elements.
    * */
}
