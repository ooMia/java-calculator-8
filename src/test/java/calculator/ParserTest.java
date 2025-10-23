package calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParserTest {

    private Parser parser;

    @BeforeEach
    void setUp() {
        this.parser = new Parser(Set.of((int) ',', (int) ':'));
    }

    @Test
    void testParseSuccess() {
        var input = "11,22:33";
        var expected = new long[]{11, 22, 33};
        var actual = parser.parse(input);
        assertArrayEquals(expected, actual, "기본 구분자로 구분된 문자열의 분리");
    }

    @Test
    void testParseSuccessOnUnicode() {
        var parser = new Parser(Set.of((int) '가', Character.codePointAt("😀", 0)));
        var input = "11가22😀33";
        var expected = new long[]{11, 22, 33};
        var actual = parser.parse(input);
        assertArrayEquals(expected, actual, "유니코드로 주어진 기본 구분자에 대한 분리");
    }

    @Test
    void testParseFail() {
        var input = "11가x:33";
        assertThrows(IllegalArgumentException.class, () -> parser.parse(input), "x는 숫자가 아니다");
    }
}
