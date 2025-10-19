package calculator.token;

public interface Token {
    
    Token reduce(Token operand);

}
