package calculator;

public abstract class Token<T extends Number> {

    abstract Token<T> reduce(Token<T> token);

    abstract T value();

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Token token) {
            return this.value().equals(token.value());
        }
        return false;
    }

    @Override
    public String toString() {
        return String.valueOf(value());
    }
}
