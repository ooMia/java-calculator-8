package calculator.token;

import java.io.Serializable;

public interface Token {

    Serializable identity();

    Token reduce(Token operand);

}
