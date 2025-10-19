package calculator;

import java.util.List;
import camp.nextstep.edu.missionutils.Console;

public class Application {

    public static void main(String[] args) {

        var baseDelimiters = List.of((int) ',', (int) ':');
        var customDelimiterRule = new CustomDelimiterRule("//", "\\n");
        var calculator = new Calculator(baseDelimiters, customDelimiterRule);

        System.out.println("덧셈할 문자열을 입력해 주세요.");
        var line = Console.readLine();
        var res = calculator.sum(line);
        System.out.printf("결과 : %d%s", res, System.lineSeparator());

    }
}
