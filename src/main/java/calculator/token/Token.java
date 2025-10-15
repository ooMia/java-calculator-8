package calculator.token;

public interface Token {
    Token reduce(Token token);

    boolean equals(Token token);
}
