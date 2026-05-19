public class Main {
    public static void main(String[] args) {
        double[][] matrixB = {
            {1.5,  2.3,  3.7,  4.1},
            {5.0,  6.8,  7.2,  8.9},
            {9.4,  10.1, 11.6, 12.3},
            {13.0, 14.5, 15.8, 16.2},
            {17.7, 18.3, 19.9, 20.0}
        };
        MatrixProcessor processor = new MatrixProcessor();
        double[][] matrixC;
        try {
            matrixC = processor.transpose(matrixB);
        } catch (IllegalArgumentException e) {
            System.err.println("Transpose error: " + e.getMessage());
            return;
        }
        System.out.println("Matrix B:");
        printMatrix(matrixB);
        System.out.println("\nMatrix C = B^T:");
        printMatrix(matrixC);
        double result;
        try {
            result = processor.computeSum(matrixC);
        } catch (IllegalArgumentException e) {
            System.err.println("ComputeSum error: " + e.getMessage());
            return;
        }
        System.out.printf("%nResult = %.4f%n", result);
    }

    private static void printMatrix(double[][] matrix) {
        for (double[] row : matrix) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < row.length; j++) {
                sb.append(String.format("%7.2f", row[j]));
                if (j < row.length - 1) sb.append(" ");
            }
            System.out.println(sb);
        }
    }
}
