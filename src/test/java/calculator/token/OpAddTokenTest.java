package calculator.token;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OpAddTokenTest {

    private OpAddToken adder;

    @BeforeEach
    void setUp() {
        adder = new OpAddToken();
    }

    // 1. adder integerToken == integerToken
    // 2. adder adder == integerToken
    // 3. adder adder == adder

    @Test
    void test_adderReduceIntegerToken_equalsIntegerToken() {
        var seven = 7;
        var sevenToken1 = new IntegerToken(seven);
        var sevenToken2 = new IntegerToken(seven);

        // 7 = 0+ 7
        assertEquals(sevenToken2, adder.reduce(sevenToken1));
    }

    @Test
    void test_adderReduceAdder_equalsIntegerToken() {
        var seven = 7;
        var sevenToken1 = new OpAddToken(seven);
        var sevenToken2 = new IntegerToken(seven);

        // 7 = 0+ 7
        assertEquals(sevenToken2, adder.reduce(sevenToken1));
    }

    @Test
    void test_adderReduceAdder_equalsAdder() {
        var seven = 7;
        var sevenToken1 = new OpAddToken(seven);
        var sevenToken2 = new OpAddToken(seven);

        // 7 = 0+ 7
        assertEquals(sevenToken2, adder.reduce(sevenToken1));
    }
}
