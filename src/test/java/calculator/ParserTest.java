package calculator;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ParserTest {

    private Parser parser;

    @BeforeEach
    void setUp() {
        this.parser = new Parser(Set.of((int) '가', (int) ':'));
    }

    @Test
    void testParseSuccess() {
        var input = "11가22:33";
        var expected = new int[] {11, 22, 33};
        var actual = parser.parse(input);
        assertArrayEquals(expected, actual, "기본 구분자로 구분된 문자열의 분리");
    }

    @Test
    void testParseFail() {
        var input = "11가x:33";
        assertThrows(IllegalArgumentException.class, () -> parser.parse(input), "x는 숫자가 아니다");
    }
}
