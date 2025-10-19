package calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EvaluatorTest {

    private Evaluator evaluator;

    @BeforeEach
    void setUp() {
        this.evaluator = new Evaluator((res, newValue) -> res + newValue, 0);
    }

    @Test
    void testEvaluate() {
        var input = new int[] {1, 2, 3};
        var expected = 6;
        var actual = evaluator.evaluate(input);
        assertEquals(expected, actual, "6 = 1 + 2 + 3");
    }
    
}
