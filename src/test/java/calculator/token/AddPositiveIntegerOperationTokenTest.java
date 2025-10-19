package calculator.token;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AddPositiveIntegerOperationTokenTest {

    private AddPositiveIntegerOperationToken operator;

    @BeforeEach
    void setUp() {
        this.operator = new AddPositiveIntegerOperationToken();
    }

    @Test
    void testOperation() {
        var input = new IntegerToken(1);
        var actual = operator.operation(0, input);
        assertEquals(1, actual, "0 + 1 = 1");
    }

    @Test
    void testReducePositiveSuccess() {
        var input = new IntegerToken(1);
        operator.reduce(input);
        assertEquals(1, operator.getResult(), "0 + 1 = 1");
    }

    @Test
    void testVerify() {
        var input = new IntegerToken(0);
        assertThrows(IllegalArgumentException.class, () -> operator.vadlidate(input), "양수가 아니면 실패");
    }

    @Test
    void testReduceNegativeFail() {
        var input = new IntegerToken(0);
        assertThrows(IllegalArgumentException.class, () -> operator.reduce(input), "양수가 아니면 실패");
    }
}
