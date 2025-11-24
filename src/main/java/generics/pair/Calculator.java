package generics.pair;

public class Calculator<T extends Number> {

    private boolean isNull(T a, T b) {
        return a == null || b == null;
    }

    public double sum(T a, T b) {
        if (isNull(a, b)) {
            return Double.NaN;
        }
        return a.doubleValue() + b.doubleValue();
    }

    public double subtract(T a, T b) {
        if (isNull(a, b)) {
            return Double.NaN;
        }
        return a.doubleValue() - b.doubleValue();
    }

    public double multiply(T a, T b) {
        if (isNull(a, b)) {
            return Double.NaN;
        }
        return a.doubleValue() * b.doubleValue();
    }

    public double divide(T a, T b) {
        if (isNull(a, b)) {
            return Double.NaN;
        }

        double denominator = b.doubleValue();

        if (denominator == 0.0) {
            return Double.NaN;
        }

        return a.doubleValue() / denominator;
    }
}