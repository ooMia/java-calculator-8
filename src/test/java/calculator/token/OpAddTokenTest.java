package calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class OpAddTokenTest {

    @Test
    void testReduce_1() {
        var token1 = new OpAddToken(1);
        var token2 = new IntegerToken(2);
        var token3 = new IntegerToken(3);

        assertEquals(token3, token1.reduce(token2));
    }

    @Test
    void testReduce_2() {
        var token1 = new OpAddToken(1);
        var token2 = new IntegerToken(2);
        var token3 = new OpAddToken(3);

        assertEquals(token3, token1.reduce(token2));
    }
}
