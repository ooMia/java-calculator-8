package calculator;

public interface Token {
    // 입력으로 Token만 받음
    Token reduce(Token input);
}
