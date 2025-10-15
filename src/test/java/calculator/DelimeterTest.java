package calculator;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class DelimeterTest {
    @Test
    void testRejectNonPrintableChars() {
        var c = (char) 0;
        assertThrows(IllegalArgumentException.class, () -> new Delimeter(c));
    }

    @Test
    void testToRegexFormat_1() {
        var delim1 = new Delimeter(',');
        var delim2 = new Delimeter(':');

        var rule = delim1.toRegexFormat(delim2);
        assertEquals("[\\,\\:]", rule);

        var input = "1,2:3";
        assertArrayEquals(new String[] {"1", "2", "3",}, input.split(rule));
    }

    @Test
    void testToRegexFormat_2() {
        var delim1 = new Delimeter('\\');
        var delim2 = new Delimeter('*');

        var rule = delim1.toRegexFormat(delim2);
        var input = "1\\2*3";
        assertArrayEquals(new String[] {"1", "2", "3",}, input.split(rule));
    }

    @Test
    void testExtractCustomDelim() {
        String input = "//\\\\n";
        Delimeter customDelim = Delimeter.extractCustomDelim(input);
        assertEquals(new Delimeter('\\'), customDelim);
        // assertEquals("\\", customDelim);
        // assertEquals('\\', customDelim);
    }
}
