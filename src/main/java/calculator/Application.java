package calculator;

import java.util.NoSuchElementException;
import calculator.token.AddIntegerOperationToken;
import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String line = readLine();
        var res = process(line);
        System.out.printf("결과 : %d", res);
    }

    private static String readLine() {
        try {
            return Console.readLine();
        } catch (NoSuchElementException e) {
            return "";
        }
    }

    private static int process(String line) {
        if (line.isEmpty()) {
            return 0;
        }
        var operator = new AddIntegerOperationToken();
        try {
            for (char c : line.toCharArray()) {
                operator.reduce(c);
            }
            return operator.getFinalResult().intValue();
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }
}
