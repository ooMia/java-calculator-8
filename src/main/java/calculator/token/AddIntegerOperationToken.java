package calculator.token;

import java.util.List;
import calculator.token.interfaces.IntegerToken;
import calculator.token.interfaces.Token;

public final class AddIntegerOperationToken extends AbstractOperationToken {

    public AddIntegerOperationToken() {
        super(Long.valueOf(0L), new DelimiterToken(List.of(',', ':')));
    }

    @Override
    protected Long operation(Number currentResult, Token token) {
        if (token instanceof IntegerToken intToken) {
            return currentResult.longValue() + intToken.value();
        }
        return currentResult.longValue();
    }
}
