package calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import calculator.token.DelimiterToken;
import calculator.token.PositiveNumberToken;

class DelimiterTokenTest {

    private DelimiterToken standardTarget;
    private DelimiterToken customTarget;

    @BeforeEach
    void setUp() {
        standardTarget = new DelimiterToken(List.of(',', ':'));
        customTarget = new DelimiterToken(List.of(',', ':', ';'));
    }

    @Test
    void testSuccessReduceCustomDelimiter() {
        var initialize = "//;\\";
        for (char c : initialize.toCharArray()) {
            standardTarget.reduce(c);
        }

        var input = 'n';
        var actual = standardTarget.reduce(input);
        var expected = new DelimiterToken(List.of(',', ':', ';'));

        assertEquals(expected, actual, "커스텀 구분자 정상 식별");
        assertEquals(expected.hashCode(), actual.hashCode(), "equals 재정의 > hashCode 확인");
    }

    @Test
    void testSuccessReduceCustomDelimiter_2() {
        var initialize = "1;2;3";
        for (char c : initialize.toCharArray()) {
            customTarget.reduce(c);
        }

        var input = ';';
        var actual = customTarget.reduce(input);
        var expected = new PositiveNumberToken(3);

        assertEquals(expected, actual, "숫자 파싱 성공");
    }

    // TODO refactor 3 tests parameterize
    @Test
    void testFailCustomDelimiterWith2Bytes() {
        var initialize = "//;1";
        for (char c : initialize.toCharArray()) {
            standardTarget.reduce(c);
        }

        var input = '\\';
        assertThrows(IllegalArgumentException.class, () -> standardTarget.reduce(input), "커스텀 구분자가 1 byte가 아니라면 오류 발생");
    }

    @Test
    void testFailCustomDelimiterWithComma() {
        var initialize = "//";
        for (char c : initialize.toCharArray()) {
            standardTarget.reduce(c);
        }

        var input = ',';
        assertThrows(IllegalArgumentException.class, () -> standardTarget.reduce(input), "커스텀 구분자로 ',' 사용하면 오류");
    }

    @Test
    void testFailCustomDelimiterWithCollon() {
        var initialize = "//";
        for (char c : initialize.toCharArray()) {
            standardTarget.reduce(c);
        }

        var input = ':';
        assertThrows(IllegalArgumentException.class, () -> standardTarget.reduce(input), "커스텀 구분자로 ':' 사용하면 오류");
    }

    @Test
    void testSuccessExpandSameToken() {
        var input = new DelimiterToken(List.of(';'));
        var actual = standardTarget.reduce(input);
        var expected = new DelimiterToken(List.of(',', ':', ';'));
        assertEquals(expected, actual, "동일 토큰 간 확장 기능");
    }
}
