package calculator;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

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

    @Test
    void testConvert_기본_구분자_4() {
        var lexer = new Lexer(Constant.기본_구분자_배열);
        var input = "05:06,07";
        var res = lexer.convert(input);
        assertArrayEquals(new int[]{5, 6, 7}, res);
    }

    @Test
    void testConvert_기본_구분자_5() {
        var lexer = new Lexer(Constant.기본_구분자_배열);
        var input = "-1,-2,-3";
        assertThrowsExactly(IllegalArgumentException.class, () -> lexer.convert(input));
    }

    @Test
    void testConvert_커스텀_구분자_1() {
        var lexer = new Lexer(Constant.기본_구분자_배열);
        var input = "//;\\n1;2;3";
        var res = lexer.convert(input);
        assertArrayEquals(new int[]{1, 2, 3}, res);
    }

    @Test
    void testFindCustomDelims_커스텀_구분자_1() {
        var lexer = new Lexer(Constant.기본_구분자_배열);
        var input = "//;\\n1;2;3";
        var res = lexer.findCustomDelims(input);
        assertEquals(2, res);
    }

    @Test
    void testBuildRegexFormat_커스텀_구분자_1() {
        var lexer = new Lexer(Constant.기본_구분자_배열);
        var input = "//;\\n1;2;3";
        var res = lexer.buildRegexFormat(",|:", input, 2);
        assertEquals(",|:|;", res);
    }

    // @Test
    // void regexUsage_커스텀_구분자_1() {
    //     var input = "//;\\n1;2;3";
    //     String res = input.replaceAll("//.\\\\n", "");
    //     assertEquals("1;2;3", res);
    // }

    @Test
    void testConvert_커스텀_구분자_2() {
        var lexer = new Lexer(Constant.기본_구분자_배열);
        var input = "//;\\n1;2,3//n\\n1n2;3";
        var res = lexer.convert(input);
        assertArrayEquals(new int[]{1, 2, 3, 1, 2, 3}, res);
    }

    @Test
    void testConvert_커스텀_구분자_3() {
        var lexer = new Lexer(Constant.기본_구분자_배열);
        var input = "//-\\n1-2-3";
        var res = lexer.convert(input);
        assertArrayEquals(new int[]{1, 2, 3}, res);
    }

    @Test
    void testConvert_커스텀_구분자_4() {
        var lexer = new Lexer(Constant.기본_구분자_배열);
        var input = "//-\\n-1,-2,-3";
        var res = lexer.convert(input);
        assertArrayEquals(new int[]{1, 2, 3}, res);
    }

    @Test
    // @Disabled("OUT_OF_SCOPE: 정규표현식의 특수 역할 문자를 모두 하나씩 처리하지 말고, 정규표현식 구조에서 벗어나야 함")
    void testConvert_커스텀_구분자_5() {
        var lexer = new Lexer(Constant.기본_구분자_배열);
        var input = "//+\\n1+2+3";
        var res = lexer.convert(input);
        assertArrayEquals(new int[]{1, 2, 3}, res);
    }

    @Test
    // @Disabled("OUT_OF_SCOPE: 정규표현식의 특수 역할 문자를 모두 하나씩 처리하지 말고, 정규표현식 구조에서 벗어나야 함")
    void testConvert_커스텀_구분자_6() {
        var lexer = new Lexer(Constant.기본_구분자_배열);
        var input = "//*\\n1*2*3";
        var res = lexer.convert(input);
        assertArrayEquals(new int[]{1, 2, 3}, res);
    }

    @Test
    void testConvert_복합_구분자_1() {
        var lexer = new Lexer(Constant.기본_구분자_배열);
        var input = "//\\\\n1:2\\3//n\\n1n2\\3";
        var res = lexer.convert(input);
        assertArrayEquals(new int[]{1, 2, 3, 1, 2, 3}, res);
    }

    @Test
    void testFindCustomDelims_복합_구분자_1() {
        var lexer = new Lexer(Constant.기본_구분자_배열);
        var input = "//\\\\n1;2;3";
        var res = lexer.findCustomDelims(input);
        assertEquals(2, res);
        assertEquals('\\', input.charAt(res));
    }

    @Test
    void testBuildRegexFormat_복합_구분자_1() {
        var lexer = new Lexer(Constant.기본_구분자_배열);
        var input = "//\\\\n1:2\\3//n\\n1n2\\3";
        var res = lexer.buildRegexFormat(",|:", input, 2);
        assertEquals(",|:|\\\\", res);
    }

    // @Test
    // void regexUsage_복합_구분자_1() {
    //     var input = "1\\2\\3";
    //     var res = input.split(",|:|\\\\");
    //     assertArrayEquals(new String[]{"1", "2", "3"}, res);
    // }

    @Test
    void testConvert_복합_구분자_2() {
        var lexer = new Lexer(Constant.기본_구분자_배열);
        var input = "1,2///\\n3//\\\\n1\\2/3";
        var res = lexer.convert(input);
        assertArrayEquals(new int[]{1, 2, 3, 1, 2, 3}, res);
    }

}
