package calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        var baseDelimiters = List.of((int) ',', (int) ':');
        var customDelimiterRule = new CustomDelimiterRule("//", "\\n");
        this.calculator = new Calculator(baseDelimiters, customDelimiterRule);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "//;\\n"})
    void testSumResultZero(String input) {
        assertEquals(0, calculator.sum(input), "0이 나오는 경우");
    }

    @ParameterizedTest
    @ValueSource(strings = {"//;\\n33", "11,22"})
    void testSumResultThirtyThree(String input) {
        assertEquals(33, calculator.sum(input), "33이 나오는 경우");
    }

    @ParameterizedTest
    @ValueSource(strings = {"11,22,33", "11,22:33", "//;\\n11;22;33"})
    void testSumResultSixtySix(String input) {
        assertEquals(66, calculator.sum(input), "66이 나오는 경우");
    }

    @ParameterizedTest
    @ValueSource(chars = {'-', '+', '.', '*', '/', '\\', 'n', '\n'})
    void testSumResultSuccessForVarietyCustomDelims(char delimiter) {
        var input = String.format("//%c\\n11%c22%c33", delimiter, delimiter, delimiter);
        assertEquals(66, calculator.sum(input), "66이 나오는 경우");
    }

    @ParameterizedTest
    @ValueSource(strings = {"////\\n11", "//\\n\\n11", "//11\\n11"})
    void testSumResultFailWhenCustomDelimiterNotSingleChar(String input) {
        assertThrows(IllegalArgumentException.class, () -> calculator.sum(input), "커스텀 구분자 1 문자 조건 위배");
    }

    @ParameterizedTest
    @ValueSource(strings = {"//", "\\n", "//11,22", "11,22\\n", "\\n11//", "11;22//;\\n33"})
    void testSumResultFailWhenCustomDelimiterRuleBroken(String input) {
        assertThrows(IllegalArgumentException.class, () -> calculator.sum(input), "커스텀 구분자 규칙 위배");
    }

    @ParameterizedTest
    @ValueSource(strings = {"//:\\n11", "//,\\n11"})
    void testSumResultFailWhenCustomDelimiterEqualsWithBase(String input) {
        assertThrows(IllegalArgumentException.class, () -> calculator.sum(input), "커스텀 구분자는 기본 구분자가 아니어야 한다");
    }

    @ParameterizedTest
    @ValueSource(strings = {"-11,22,33", "//;\\n11;-22;33", "0,22,33"})
    void testSumResultFailWhenContainsNonPositives(String input) {
        assertThrows(IllegalArgumentException.class, () -> calculator.sum(input), "모든 숫자는 양수여야 한다");
    }

    @ParameterizedTest
    @ValueSource(strings = {"//→\\n11→22→33", "//아\\n11아22아33"})
    void testSumResultSuccessSupportingCustomUnicodeDelimiter(String input) {
        assertEquals(66, calculator.sum(input), "유니코드 한 문자 커스텀 구분자 지원");
    }

    @ParameterizedTest
    @ValueSource(strings = {"//;\\n11:22;33", "//;\\n//n\\n11n22;33",})
    void testSumResultSuccessUsingCustomWithBase(String input) {
        // "11//;\\n22;33" 와 같은 케이스는 입력의 `앞부분`은 아니므로 제외
        assertEquals(66, calculator.sum(input), "구분자가 중복되어 함께 사용되는 경우");
    }
}
