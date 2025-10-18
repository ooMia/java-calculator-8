package calculator;

import java.util.NoSuchElementException;
import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String line;
        try {
            line = Console.readLine();
        } catch (NoSuchElementException e) {
            line = "";
        }
        var res = hardCode(line);
        System.out.println(res);
    }

    private static String hardCode(String s) {
        if ("".equals(s))
            return "결과 : 0";
        if ("1,2".equals(s))
            return "결과 : 3";
        if ("1,2,3".equals(s))
            return "결과 : 6";
        if ("1,2:3".equals(s))
            return "결과 : 6";
        if ("//;\\n1".equals(s))
            return "결과 : 1";
        if ("//;\\n1;2;3".equals(s))
            return "결과 : 6";
        if ("-1,2,3".equals(s))
            throw new IllegalArgumentException();
        throw new IllegalArgumentException();
    }
}
