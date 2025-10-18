package calculator.token;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import calculator.token.interfaces.CharToken;
import calculator.token.interfaces.Token;

public final class DelimiterToken implements CharToken {
    private final Set<Character> delimiters;
    private final StringBuilder sb = new StringBuilder();

    public DelimiterToken(Collection<Character> delimiters) {
        this.delimiters = Set.copyOf(delimiters);
    }

    @Override
    public Token reduce(char input) {
        if (delimiters.contains(input)) {
            sb.setLength(0);
            var num = Integer.parseInt(sb.toString());
            return new PositiveNumberToken(num);
        }
        sb.append(input);
        return evaluate();
    }

    public Token reduce(Token input) {
        if (input instanceof DelimiterToken delimiterToken) {
            var args = new HashSet<Character>();
            args.addAll(this.delimiters);
            args.addAll(delimiterToken.delimiters);
            return new DelimiterToken(args);
        }
        return evaluate();
    }

    private Token evaluate() {
        var status = sb.toString();
        if (isReadyToCustomize()) {
            var newDelimiter = status.charAt(2);
            if (delimiters.contains(newDelimiter)) {
                throw new IllegalArgumentException();
            }
            var delims = new HashSet<Character>();
            delims.addAll(delimiters);
            delims.add(newDelimiter);
            return new DelimiterToken(delims);
        }
        return this;
    }

    private boolean isReadyToCustomize() {
        var status = sb.toString();
        if (status.startsWith("//") && status.endsWith("\\n")) {
            if (status.length() != 5) {
                throw new IllegalArgumentException();
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DelimiterToken token) {
            return this.delimiters.equals(token.delimiters) && this.sb.toString().equals(token.sb.toString());
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        var a = Objects.hash(this.delimiters);
        var b = Objects.hash(this.sb.toString());
        return a + b;
    }
}
