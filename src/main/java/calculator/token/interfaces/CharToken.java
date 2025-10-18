package calculator.token.interfaces;

public interface CharToken extends Token {
    Token reduce(char input);
}
