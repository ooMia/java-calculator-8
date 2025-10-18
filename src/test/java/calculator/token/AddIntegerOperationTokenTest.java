package calculator.token;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AddIntegerOperationTokenTest {

    private AddIntegerOperationToken target;

    @BeforeEach
    void setUp() {
        this.target = new AddIntegerOperationToken();
    }

    @Test
    void testOperation() {
        var input = "//;\\n11;22;33";
        for (char c : input.toCharArray()) {
            target.reduce(c);
        }

        assertEquals(66, target.getFinalResult().intValue(), "커스텀 구분자로 구분된 양수가 제대로 더해짐");
    }
}
