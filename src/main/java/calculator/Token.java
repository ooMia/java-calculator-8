package calculator;

public interface Token {
    Token reduce(Token operand);
}
