package calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import calculator.token.DelimiterToken;

class DelimiterTokenTest {

    private DelimiterToken target;

    @BeforeEach
    void setUp() {
        target = new DelimiterToken(List.of(',', ':'));
    }

    @Test
    void testSuccessReduceCustomDelimiter() {
        var initialize = "//;\\";
        for (char c : initialize.toCharArray()) {
            target.reduce(c);
        }

        var input = 'n';
        var actual = target.reduce(input);
        var expected = new DelimiterToken(List.of(',', ':', ';'));

        assertEquals(expected, actual, "커스텀 구분자 정상 식별");
        assertEquals(expected.hashCode(), actual.hashCode(), "equals 재정의 > hashCode 확인");
    }

    // TODO refactor 3 tests parameterize
    @Test
    void testFailCustomDelimiterWith2Bytes() {
        var initialize = "//;1\\";
        for (char c : initialize.toCharArray()) {
            target.reduce(c);
        }

        var input = 'n';
        assertThrows(IllegalArgumentException.class, () -> target.reduce(input), "커스텀 구분자가 1 byte가 아니라면 오류 발생");
    }

    @Test
    void testFailCustomDelimiterWithComma() {
        var initialize = "//";
        for (char c : initialize.toCharArray()) {
            target.reduce(c);
        }

        var input = ',';
        assertThrows(IllegalArgumentException.class, () -> target.reduce(input), "커스텀 구분자로 ',' 사용하면 오류");
    }

    @Test
    void testFailCustomDelimiterWithCollon() {
        var initialize = "//";
        for (char c : initialize.toCharArray()) {
            target.reduce(c);
        }

        var input = ':';
        assertThrows(IllegalArgumentException.class, () -> target.reduce(input), "커스텀 구분자로 ':' 사용하면 오류");
    }

    @Test
    void testSuccessExpandSameToken() {
        var input = new DelimiterToken(List.of(';'));
        var actual = target.reduce(input);
        var expected = new DelimiterToken(List.of(',', ':', ';'));
        assertEquals(expected, actual, "동일 토큰 간 확장 기능");
    }
}
