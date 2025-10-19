package calculator.token;

public final class StringToken implements Token {

    private final StringBuilder sb;

    public StringToken() {
        this.sb = new StringBuilder();
    }

    public StringToken(String initialState) {
        this.sb = new StringBuilder(initialState);
    }

    @Override
    public Token reduce(Token operand) {
        if (operand instanceof CharToken ch) {
            sb.append(ch.toString());
        }
        if (operand instanceof StringToken st) {
            sb.append(st.toString());
        }
        return this;
    }

    @Override
    public String toString() {
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof StringToken st) {
            return toString().equals(st.toString());
        }
        if (obj instanceof CharToken ch) {
            return toString().equals(ch.toString());
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

}
