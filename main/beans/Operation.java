package beans;

import java.util.Arrays;
import java.util.function.DoubleBinaryOperator;

public enum Operation {
    SUM(Double::sum),
    MIN(Math::min),
    MAX(Math::max),
    AVG((res, val) -> (res + val) / 2);

    private DoubleBinaryOperator op;

    Operation(DoubleBinaryOperator op) {
        this.op = op;
    }

    public double getResult(double[] numbers) {
        double result = Arrays.stream(numbers).reduce(op).getAsDouble();
        if (this == AVG) {
            result /= numbers.length;
        }
        return result;
    }
}

