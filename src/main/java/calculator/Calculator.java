package calculator;

import java.util.Set;
import java.util.function.BinaryOperator;

public final class Calculator extends AbstractLongCalculator {

    private static final Set<Integer> DEFAULT_BASE_DELIMITERS = Set.of((int) ',', (int) ':');
    private static final CustomDelimiterRule DEFAULT_CUSTOM_DELIMITER_RULE = new CustomDelimiterRule("//", "\\n");

    public Calculator() {
        super(DEFAULT_BASE_DELIMITERS, DEFAULT_CUSTOM_DELIMITER_RULE);
    }

    @Override
    protected BinaryOperator<Number> sumReducer() {
        return (Number res, Number newValue) -> {
            if (newValue.longValue() <= 0) {
                throw Cause.INVALID_INPUT.exception();
            }
            try {
                return Math.addExact(res.longValue(), newValue.longValue());
            } catch (ArithmeticException e) {
                throw Cause.NUMBER_SUM_OVERFLOW.exception();
            }
        };
    }
}
