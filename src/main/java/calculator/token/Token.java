package calculator.token;

// java.lang.Number를 상속받는 것이 최선이었을까?
abstract class Token<T extends Number> {

    abstract Token<T> reduce(Token<T> token);

    abstract T value();

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Token token) {
            return this.value().equals(token.value());
        }
        if (obj instanceof Number number) {
            return this.value().equals(number);
        }
        return super.equals(obj);
    }
}
