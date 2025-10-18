package calculator.token;

import calculator.token.interfaces.CharToken;
import calculator.token.interfaces.IntegerToken;
import calculator.token.interfaces.Token;

abstract class AbstractOperationToken implements CharToken {

    // has internal state `result`
    private Number result;
    private DelimiterToken delimiterToken;

    protected AbstractOperationToken(Number initialValue, DelimiterToken delimiterToken) {
        this.result = initialValue;
        this.delimiterToken = delimiterToken;
    }

    protected abstract Number operation(Number currentResult, Token token);

    public final Number getFinalResult() {
        flush();
        return this.result;
    }

    @Override
    public final Token reduce(char input) {
        var token = delimiterToken.reduce(input);
        return reduce(token);
    }

    private Token reduce(Token input) {
        if (input instanceof DelimiterToken newDelimiter) {
            this.delimiterToken = newDelimiter;
        }
        if (input instanceof IntegerToken intToken) {
            this.result = operation(this.result, intToken);
        }
        return this;
    }

    private void flush() {
        var emptyToken = new EmptyToken();
        var token = delimiterToken.reduce(emptyToken);
        reduce(token);
    }
}
