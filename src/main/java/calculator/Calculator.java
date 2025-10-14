package calculator;

import java.util.Arrays;

public class Calculator {
    private final Lexer lex = new Lexer(Constant.기본_구분자_배열);

    public int add(String s) {
        int[] numbers = lex.convert(s);
        return add(numbers);
    }

    private int add(int[] numbers) {
        return Arrays.stream(numbers).sum();
    }
}
