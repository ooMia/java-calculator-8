package calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        this.calculator = new Calculator(new char[] {':', ','});
    }

    // - `"//;\\n"` -> 0
    // - `"//;\\n11"` -> 11
    // - `"//;\\n11"` -> 11
    // - `"//;\\n11;22;33"` -> 66
    // - 이외에 `-` `+` `.` `*` `/` `\\` `n` `\n` 등의 출력 가능한 모든 문자를 커스텀 구분자로 활용

    @Nested
    class UseDefaultDelimiter {
        @ParameterizedTest
        @ValueSource(strings = {"66", "33,33", "11:55", "11,22:33"})
        void testAddSuccess(String expression) {
            assertEquals(66, calculator.add(expression).intValue(), "기본 구분자");
        }

        @ParameterizedTest
        @ValueSource(strings = {"", "0", "0,0", "0:0"})
        void testAddSuccessResultZero(String expression) {
            assertEquals(0, calculator.add(expression).intValue(), "기본 구분자 - 0이 나오는 상황");
        }
    }



}
