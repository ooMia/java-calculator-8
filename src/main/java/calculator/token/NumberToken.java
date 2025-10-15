package calculator.token;


public final class NumberToken extends Token<Number> {
    private Number n;

    public NumberToken(Number n) {
        this.n = n;
    }

    @Override
    public Token<Number> reduce(Token<Number> token) {
        // 호출 주체에 따라 연산이 달라질 수 있도록 설계
        // 이 토큰 자체는 현재까지는 어떤 다른 토큰에 대한 능동적인 행동을 취할 수 없음
        throw new IllegalArgumentException();
    }

    @Override
    protected Number value() {
        return this.n;
    }
}
