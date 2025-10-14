package calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CalculatorTest {

    @Test
    void testAdd_1() {
        var calc = new Calculator();
        var res = calc.add("0");
        assertEquals(0, res);
    }

    @Test
    void testAdd_2() {
        var calc = new Calculator();
        var res = calc.add("1,2");
        assertEquals(3, res);
    }

    @Test
    void testAdd_3() {
        var calc = new Calculator();
        var res = calc.add("1,2,3");
        assertEquals(6, res);
    }

    @Test
    void testAdd_4() {
        var calc = new Calculator();
        var res = calc.add("1,2:3");
        assertEquals(6, res);
    }

    @Test
    void testAdd_5() {
        var calc = new Calculator();
        var res = calc.add("//;\\n1;2;3");
        assertEquals(6, res);
    }
}
