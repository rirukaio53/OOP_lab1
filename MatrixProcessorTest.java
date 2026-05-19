public class MatrixProcessorTest {

    private static final double DELTA = 1e-9;
    private MatrixProcessor processor;

    private static void assertEquals(double expected, double actual, double delta) {
        if (Math.abs(expected - actual) > delta)
            throw new AssertionError("expected: " + expected + ", actual: " + actual);
    }

    private static void assertEquals(int expected, int actual) {
        if (expected != actual)
            throw new AssertionError("expected: " + expected + ", actual: " + actual);
    }

    private static <T extends Throwable> void assertThrows(Class<T> type, Runnable action) {
        try {
            action.run();
            throw new AssertionError("Expected " + type.getSimpleName() + " but nothing was thrown");
        } catch (Throwable t) {
            if (!type.isInstance(t))
                throw new AssertionError("Expected " + type.getSimpleName() + " but got " + t.getClass().getSimpleName());
        }
    }

    private void setUp() {
        processor = new MatrixProcessor();
    }

    void testTransposeCorrect() {
        double[][] b = {{1.0, 2.0, 3.0}, {4.0, 5.0, 6.0}};
        double[][] c = processor.transpose(b);
        assertEquals(3, c.length);
        assertEquals(2, c[0].length);
        assertEquals(1.0, c[0][0], DELTA);
        assertEquals(4.0, c[0][1], DELTA);
        assertEquals(3.0, c[2][0], DELTA);
    }

    void testTransposeNullThrows() {
        assertThrows(IllegalArgumentException.class, () -> processor.transpose(null));
    }

    void testComputeSumCorrect() {
        double[][] c = {
            {1.0, 2.0, 3.0},
            {4.0, 1.0, 5.0},
            {7.0, 8.0, 9.0}
        };
        assertEquals(13.0, processor.computeSum(c), DELTA);
    }

    void testComputeSumNullThrows() {
        assertThrows(IllegalArgumentException.class, () -> processor.computeSum(null));
    }

    void testComputeSumAfterTranspose() {
        double[][] b = {{1.0, 4.0}, {2.0, 5.0}, {3.0, 6.0}};
        double[][] c = processor.transpose(b);
        assertEquals(7.0, processor.computeSum(c), DELTA);
    }

    public static void main(String[] args) throws Exception {
        String[][] tests = {
            {"testTransposeCorrect",       "transpose: correct 2x3 -> 3x2"},
            {"testTransposeNullThrows",    "transpose: null -> IllegalArgumentException"},
            {"testComputeSumCorrect",      "computeSum: max/min rows (3x3) = 13.0"},
            {"testComputeSumNullThrows",   "computeSum: null -> IllegalArgumentException"},
            {"testComputeSumAfterTranspose","computeSum after transpose: B(3x2) -> C(2x3) = 7.0"}
        };

        int passed = 0, failed = 0;

        for (String[] test : tests) {
            MatrixProcessorTest suite = new MatrixProcessorTest();
            suite.setUp();
            String label = test[1];
            try {
                MatrixProcessorTest.class.getDeclaredMethod(test[0]).invoke(suite);
                System.out.printf("  PASSED  %s%n", label);
                passed++;
            } catch (java.lang.reflect.InvocationTargetException ite) {
                System.out.printf("  FAILED  %s  ->  %s%n", label, ite.getCause().getMessage());
                failed++;
            }
        }

        System.out.printf("%n  %d/%d passed%n", passed, passed + failed);
        System.exit(failed > 0 ? 1 : 0);
    }
}
