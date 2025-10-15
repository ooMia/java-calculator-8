package calculator.token;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OpAddIntegerTokenTest {

    private OpAddIntegerToken adder;

    @BeforeEach
    void setUp() {
        adder = new OpAddIntegerToken();
    }

    // 1. adder integerToken == integerToken
    // 2. adder adder == integerToken
    // 3. adder adder == adder

    @Test
    void test_adderReduceIntegerToken_equalsIntegerToken() {
        var seven = 7;
        var sevenToken1 = new NumberToken<Integer>(seven);
        var sevenToken2 = new NumberToken<Integer>(seven);

        // 7 = 0+ 7
        assertEquals(sevenToken2, adder.reduce(sevenToken1));
    }

    @Test
    void test_adderReduceAdder_equalsIntegerToken() {
        var seven = 7;
        var sevenToken1 = new OpAddIntegerToken(seven);
        var sevenToken2 = new NumberToken<Integer>(seven);

        // 7 = 0+ 7
        assertEquals(sevenToken2, adder.reduce(sevenToken1));
    }

    @Test
    void test_adderReduceAdder_equalsAdder() {
        var seven = 7;
        var sevenToken1 = new OpAddIntegerToken(seven);
        var sevenToken2 = new OpAddIntegerToken(seven);

        // 7 = 0+ 7
        assertEquals(sevenToken2, adder.reduce(sevenToken1));
    }
}
