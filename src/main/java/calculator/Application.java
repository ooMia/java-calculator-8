package calculator;

import java.util.List;
import calculator.token.NumberToken;
import calculator.token.OpAddIntegerToken;
import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {
        Delimeter[] delims = new Delimeter[] {new Delimeter(','), new Delimeter(':'),};

        System.out.println("덧셈할 문자열을 입력해 주세요.");
        var line = Console.readLine();

        Delimeter customDelim = Delimeter.extractCustomDelim(line);
        if (!customDelim.isEmpty()) {
            line = line.substring(5);
        }

        String splitRule = customDelim.toRegexFormat(delims);

        // 전제에 따르면 각 토큰은 양의 정수이어야 함
        List<String> stringTokens = List.of(line.split(splitRule));
        var integerTokens = stringTokens.stream().map(s -> new NumberToken<Integer>(Integer.parseInt(s))).toList();
        var operation = new OpAddIntegerToken();
        for (var token : integerTokens) {
            operation = operation.reduce(token);
        }
        System.out.printf("결과 : %s", operation.toString());
    }
}
