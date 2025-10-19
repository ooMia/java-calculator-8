package calculator.token;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class StringTokenTest {
    @Test
    void testReduce_1() {
        var t1 = fromInt('가');
        var t2 = fromInt('나');
        var actual = t1.reduce(t2);
        assertEquals("가나", actual.toString(), "문자끼리 만나면 문자열로 확장된다.");
    }

    @Test
    void testReduce_2() {
        var t1 = fromInt('\\');
        var t2 = fromInt('나');
        var actual = t1.reduce(t2);
        assertEquals("\\나", actual.toString(), "문자끼리 만나면 문자열로 확장된다.");
    }

    @Test
    void testReduce_3() {
        var t1 = fromString("가나");
        var t2 = fromString("다");
        var actual = t1.reduce(t2);
        assertEquals("가나다", actual.toString(), "문자끼리 만나면 문자열로 확장된다.");
    }


    private CharToken fromInt(int c) {
        return new CharToken(c);
    }

    private StringToken fromString(String str) {
        return new StringToken(str);
    }
}
