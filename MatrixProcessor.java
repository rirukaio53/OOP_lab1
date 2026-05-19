public class MatrixProcessor {

    public double[][] transpose(double[][] b) {
        if (b == null || b.length == 0 || b[0].length == 0) {
            throw new IllegalArgumentException("Matrix must not be null or empty.");
        }
        int rows = b.length;
        int cols = b[0].length;
        double[][] c = new double[cols][rows];
        for (int i = 0; i < rows; i++) {
            if (b[i].length != cols) {
                throw new IllegalArgumentException("All rows must have the same length.");
            }
            for (int j = 0; j < cols; j++) {
                c[j][i] = b[i][j];
            }
        }
        return c;
    }

    public double computeSum(double[][] c) {
        if (c == null || c.length == 0 || c[0].length == 0) {
            throw new IllegalArgumentException("Matrix must not be null or empty.");
        }
        double total = 0.0;
        for (int i = 0; i < c.length; i++) {
            if (c[i] == null || c[i].length == 0) {
                throw new IllegalArgumentException("Row " + i + " is null or empty.");
            }
            total += (i % 2 == 0) ? findMax(c[i]) : findMin(c[i]);
        }
        return total;
    }

    private double findMax(double[] row) {
        double max = row[0];
        for (double v : row) {
            if (v > max) max = v;
        }
        return max;
    }

    private double findMin(double[] row) {
        double min = row[0];
        for (double v : row) {
            if (v < min) min = v;
        }
        return min;
    }
}
