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

    @Test
    void test_adderReduceNumberToken() {
        var sevenToken = new NumberToken(7);

        var afterAdd = adder.reduce(sevenToken); // 7 = 0+ 7

        assertEquals(sevenToken, afterAdd);
    }

    @Test
    void test_adderReduceNumberToken_2() {
        var one = new NumberToken(1);
        var two = new NumberToken(2);
        var three = new NumberToken(3);

        var afterAdd = adder.reduce(one).reduce(two).reduce(three);

        assertEquals(new NumberToken(6), afterAdd);
    }
}
