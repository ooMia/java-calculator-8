package calculator.token;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class CharTokenTest {
    @Test
    void testReduce_1() {
        var t1 = fromInt('a');
        var t2 = fromInt('b');
        var actual = t1.reduce(t2);
        assertEquals("ab", actual.toString(), "문자끼리 만나면 문자열로 확장된다.");
    }

    @Test
    void testReduce_2() {
        var t1 = fromInt('a');
        var t2 = fromString("가b");
        var actual = t1.reduce(t2);
        assertEquals("a가b", actual.toString(), "문자끼리 만나면 문자열로 확장된다.");
    }

    private CharToken fromInt(int c) {
        return new CharToken(c);
    }

    private StringToken fromString(String str) {
        return new StringToken(str);
    }
}
