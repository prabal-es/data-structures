package ds.arrays_and_strings.book;

/**
 * 1.8 Zero Matrix: Write an algorithm such that if an element in an MxN matrix is 0, its entire row and
 * column are set to O.
 * Hints: # 17, #74, #102
 *
 * #17.    1.8 If you just cleared the rows and columns as you found Os, you'd likely wind up clearing
 *             the whole matrix. Try finding the cells with zeros first before making any changes to the
 *             matrix
 * #74.    1.8 Can you use 0 (N) additional space instead of 0 (N2 )? What information do you really
 *             need from the list of cells that are zero?
 * #102.   1.8 You probably need some data storage to maintain a list of the rows and columns that
 *             need to be zeroed. Can you reduce the additional space usage to a (1) by using the
 *             matrix itself for data storage?
 *
 */
public class ZeroMatrix {

    /*
    * NXM Matrix
    * o = O(N^2)
    * */
    public String doZeroMatrix(int[][] matrix){
        // Check if NxN matrix is valid
        if(null == matrix || matrix.length == 0 ){
            return "Invalid NXN matrix";
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.print("\n");
        }
        System.out.print("\n\n");
        boolean[] rowZerows = new boolean[matrix.length];
        boolean[] columnZeros = new boolean[matrix[0].length];
        for(int i=0; i< matrix.length;i++){
            for(int j=0; j<matrix[i].length;j++){
                if(matrix[i][j]==0){
                    rowZerows[i] = true;
                    columnZeros[j] = true;
                }
            }
        }
        for(int i = 0; i < rowZerows.length; i++){
            if(rowZerows[i]){
                //  Nullify row
                for(int j = 0; j< matrix[i].length; j++){
                    matrix[i][j] = 0;
                }
            }
        }
        for(int i = 0; i < columnZeros.length; i++){
            if(columnZeros[i]){
                //  Nullify row
                for(int j = 0; j< matrix[i].length; j++){
                    matrix[j][i] = 0;
                }
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



    public static void main(String... args){
        ZeroMatrix zeroMatrix = new ZeroMatrix();
        int[][] matrix = {
                {1, 2, 3,4},
                {5, 6, 7, 8},
                {9, 10, 0, 12},
                {13, 14, 15, 16}};
        System.out.println(zeroMatrix.doZeroMatrix(matrix));
    }
    /**
     * 1.8 Zero Matrix: Write an algorithm such that if an element in an MxN matrix is 0, its entire row and
     * column are set to 0.
     *
     * SOLUTION
     * At first glance, this problem seems easy: just iterate through the matrix and every time we see a cell with
     * value zero, set its row and column to 0. There's one problem with that solution though: when we come
     * across other cells in that row or column, we'll see the zeros and change their row and column to zero. Pretty
     * soon, our entire matrix will be set to zeros.
     * One way around this is to keep a second matrix which flags the zero locations. We would then do a second
     * pass through the matrix to set the zeros. This would ta ke 0 (MN) space.
     * Do we really need O(MN) space? No. Since we're going to set the entire row and column to zero, we don't
     * need to track that it was exactly ce 11 [2] [ 4] (row 2, column 4). We only need to know that row 2 has a
     * zero somewhere, and column 4 has a zero somewhere. We'll set the entire row and column to zero anyway,
     * so why would we care to keep track of the exact location of the zero?
     * The code below implements this algorithm. We use two arrays to keep track of all the rows with zeros and all
     * the columns with zeros. We then nullify rows and columns based on the values in these arrays.
     *
     * 1 void setZeros(int[][] matrix) {
     * 2 boolean[] row = new boolean[matrix.length];
     * 3 boolean[] column = new boolean[matrix[0].length];
     * 4
     * 5 II Store the row and column index with value 0
     * 6 for (int i = 0; i < matrix. length; i++) {
     * 7 for (int j = e; j < matrix[0].length;j++) {
     * 8 if (matrix[i][j] == 0) {
     * 9 row[i] = true;
     * 10 column[j] = true;
     * 11 }
     * 12 }
     * 13 }
     * 14 15 II Nullify rows
     * 16 for (int i = 8j i < row.lengthj i++) {
     * 17 if (row[i]) nullifyRow(matrix, i)j
     * 18 }
     * 19
     * 28 II Nullify columns
     * 21 for (int j = 8j j < column.lengthj j++) {
     * 22 if (column[j]) nullifyColumn(matrix, j)j
     * 23 }
     * 24 }
     * 25
     * 26 void nullifyRow(int[][] matrix, int row) {
     * 27 for (int j = aj j < matrix[a].lengthj j++) {
     * 28 matrix[row][j] = 8j
     * 29 }
     * 38 }
     * 31
     * 32 void nullifyColumn(int[][] matrix, int col) {
     * 33 for (int i = aj i < matrix.lengthj i++) {
     * 34 matrix[i][col] = aj
     * 35 }
     * 36 }
     * To make this somewhat more space efficient, we could use a bit vector instead of a boolean array. It would
     * still beO(N) space.
     * We can reduce the space to 0 (1) by using the first row as a replacement for the row array and the first
     * column as a replacement for the column array. This works as follows:
     * 1. Check if the first row and first column have any zeros, and set variables rowHasZero and
     * columnHasZero. (We'll nullify the first row and first column later, if necessary.)
     * 2. Iterate through the rest of the matrix, setting matrix[ i] [e] and matrix [e] [j] to zero whenever
     * there's a zero in mat rix [i] [j ].
     * 3. Iterate through rest of matrix, nullifying row i if there's a zero in matrix [i] [e].
     * 4. Iterate through rest of matrix, nullifying column j ifthere's a zero in mat rix [e] [j ].
     * 5. Nullify the first row and first column, if necessary (based on values from Step 1).
     *
     * This code is below:
     * 1 void setZeros(int[][] matrix) {
     * 2 boolean rowHasZero = falsej
     * 3 boolean colHasZero = falsej
     * 4
     * 5 II Check if first row has a zero
     * 6 for (int j = 8j j < matrix[a].lengthj j++) {
     * 7 if (matrix[a][j] == a) {
     * 8 rowHasZero = truej
     * 9 breakj
     * 18 }
     * 11 }
     * 12
     * 13 II Check if first column has a zero
     * 14 for (int i = aj i < matrix.lengthj i++) {
     * 15 if (matrix[i][8] == 8) {
     * 16 colHasZero = truej
     * 17 breakj
     * }
     * }
     * }
     * II Check for zeros in the rest of the array
     * for (int i = 1; i < matrix. length; i++) {
     * }
     * for (int j = 1; j < matrix[0).length;j++) {
     * if (matrix[i)[j) == a) {
     * matrix[i][0] = a;
     * matrix[0][j] = 0;
     * }
     * }
     * II Nullify rows based on values in first column
     * for (int i = 1; i < matrix. length; i++) {
     * if (matrix[i][0] == 0) {
     * nullifyRow(matrix, i);
     * }
     * }
     * II Nullify columns based on values in first row
     * for (int j = 1; j < matrix[0).length; j++) {
     * }
     * if (matrix[a][j) == a) {
     * nullifyColumn(matrix, j);
     * }
     * II Nullify first row
     * if (rowHasZero) {
     * nullifyRow(matrix, 0);
     * }
     * II Nullify first column
     * if (colHasZero) {
     * nullifyColumn(matrix, a);
     * }
     * This code has a lot of udo this for the rows, then the equivalent action for the column:' In an interview, you
     * could abbreviate this code by adding comments and TODOs that explain that the next chunk of code looks
     * the same as the earlier code, but using rows. This would allow you to focus on the most important parts of
     * the algorithm.
    * */
}
