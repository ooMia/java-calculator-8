package calculator.token;

import java.util.Map;

public enum SpecialCharToken implements Token {
    DOT_TOKEN('.'), SLASH_TOKEN('/'), REVERSE_SLASH_TOKEN('\\'), LOWER_ALPHABET_N('n');

    private final Token token;

    private SpecialCharToken(char c) {
        this.token = new CharToken(c);
    }

    public static SpecialCharToken of(char c) {
        var map = Map.of('.', SpecialCharToken.DOT_TOKEN, '/', SpecialCharToken.SLASH_TOKEN, '\\',
                                        SpecialCharToken.REVERSE_SLASH_TOKEN, 'n', SpecialCharToken.LOWER_ALPHABET_N);
        return map.getOrDefault(map, null);
    }

    @Override
    public boolean equals(Token token) {
        return this.token.equals(token);
    }

    @Override
    public Token reduce(Token token) {
        return this.token.reduce(token);
    }
}
