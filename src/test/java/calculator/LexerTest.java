package calculator;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class LexerTest {
    @Test
    void testConvert_기본_구분자_1() {
        var lexer = new Lexer(Constant.기본_구분자_배열);
        var input = "1,2";
        var res = lexer.convert(input);
        assertArrayEquals(new int[]{1, 2}, res);
    }

    @Test
    void testConvert_기본_구분자_2() {
        var lexer = new Lexer(Constant.기본_구분자_배열);
        var input = "3:4";
        var res = lexer.convert(input);
        assertArrayEquals(new int[]{3, 4}, res);
    }

    @Test
    void testConvert_기본_구분자_3() {
        var lexer = new Lexer(Constant.기본_구분자_배열);
        var input = "5:6,7";
        var res = lexer.convert(input);
        assertArrayEquals(new int[]{5, 6, 7}, res);
    }
}
