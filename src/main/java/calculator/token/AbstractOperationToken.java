package calculator.token;

public abstract class AbstractOperationToken implements OperationToken {

    private Number cumulatedResult;

    protected AbstractOperationToken(Number initialValue) {
        this.cumulatedResult = initialValue;
    }

    @Override
    public final Token reduce(Token operand) {
        this.cumulatedResult = operation(this.cumulatedResult, operand);
        return reduceAfterOperationHook(operand);
    }

    @Override
    public Number getResult() {
        return this.cumulatedResult;
    }

    /**
     * 새로운 토큰에 대한 누적 결과값 로직을 정의합니다.
     * 예를 들어, 두 정수를 더한다면, `currentCumulativeValue.intValue()`
     * 그리고 operand의 정수 값을 더하여 결과값으로 반환하면 됩니다.
     * 
     * @param currentCumulativeValue 누적되어 있는 현재 결과값
     * @param operand 추가적으로 들어오는 다음 토큰
     * @return 현재 결과를 대체할 다음 결과값
     */
    protected abstract Number operation(Number currentCumulativeValue, Token operand);

    protected abstract Token reduceAfterOperationHook(Token operand);

}
