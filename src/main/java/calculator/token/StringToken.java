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
    public String identity() {
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Token token) {
            return this.identity().equals(token.identity());
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return identity().hashCode();
    }

    @Override
    public String toString() {
        return identity();
    }

}
