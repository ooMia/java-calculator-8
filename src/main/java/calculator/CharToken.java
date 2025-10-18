package calculator;

public interface CharToken {
    // 입력으로 char만 받음
    Token reduce(char input);
}
