package calculator.token;

public record CharToken(int codePoint) implements Token {

    @Override
    public Token reduce(Token operand) {

        if (operand instanceof CharToken ch) {
            return new StringToken(this.toString() + ch.toString());
        }
        if (operand instanceof StringToken st) {
            return new StringToken(this.toString() + st.toString());
        }

        throw Cause.FALLBACK.exception();
    }

    @Override
    public String toString() {
        return Character.toString(codePoint());
    }

    @Override
    public String identity() {
        return String.valueOf(this.codePoint);
    }

}
