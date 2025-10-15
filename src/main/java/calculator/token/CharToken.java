package calculator.token;

public record CharToken(char c) implements Token {

    @Override
    public Token reduce(Token token) {
        var a = SpecialCharToken.DOT_TOKEN;
        // SpecialCharToken.values()
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'reduce'");
    }

    @Override
    public boolean equals(Token token) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'equals'");
    }

}
