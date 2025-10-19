package calculator;

import java.util.function.IntBinaryOperator;

public final class Evaluator {

    private final IntBinaryOperator operator;
    private final int initialValue;

    public Evaluator(IntBinaryOperator operator, int initialValue) {
        this.operator = operator;
        this.initialValue = initialValue;
    }

    /**
     * apply cumulative operation for given numbers
     * 
     * @param operands values to use as an argument of cumulative operation
     * @return final value after iteration
     */
    int evaluate(int[] operands) {
        int res = this.initialValue;
        for (var operand : operands) {
            res = operator.applyAsInt(res, operand);
        }
        return res;
    }

}
