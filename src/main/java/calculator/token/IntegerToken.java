package calculator.token;

public record IntegerToken(Integer value) implements NumberToken {
    
    @Override
    public Token reduce(Token operand) {
        if (operand instanceof OperationToken operator) {
            return operator.reduce(this);
        }

        throw Cause.FALLBACK.exception();
    }

}
