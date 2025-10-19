package calculator.token;

public final class AddPositiveIntegerOperationToken extends AbstractOperationToken implements Validator {

    protected AddPositiveIntegerOperationToken() {
        super(0);
    }

    @Override
    public Integer getResult() {
        return super.getResult().intValue();
    }

    @Override
    protected Integer operation(Number currentCumulativeValue, Token operand) {
        vadlidate(operand);
        if (operand instanceof IntegerToken(Integer value)) {
            return currentCumulativeValue.intValue() + value;
        }
        throw Cause.INVALID_INPUT.exception();
    }

    @Override
    protected Token reduceAfterOperationHook(Token operand) {
        return this;
    }

    @Override
    public void vadlidate(Token operand) throws IllegalArgumentException {
        if (operand instanceof NumberToken number && number.value().intValue() <= 0) {
            throw Cause.INVALID_INPUT.exception();
        }
    }

}
