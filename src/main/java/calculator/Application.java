package calculator;

import java.util.List;
import calculator.token.NumberToken;
import calculator.token.OpAddIntegerToken;
import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        var line = Console.readLine();
        Delimeter customDelim = Delimeter.extractCustomDelim(line);
        if (!customDelim.isEmpty()) {
            line = line.substring(5);
        }

        Delimeter[] delims = new Delimeter[] {new Delimeter(','), new Delimeter(':'),};
        String splitRule = customDelim.toRegexFormat(delims);
        var integerTokens = getUnsignedIntegerTokens(line, splitRule);

        var operation = new OpAddIntegerToken();
        for (var token : integerTokens) {
            operation = operation.reduce(token);
        }
        System.out.printf("결과 : %d", operation.value());
    }


    // 전제에 따르면 각 토큰은 양수이어야 함
    // 양의 실수(ex 3.3)는 배제한 상태로 진행
    private static List<NumberToken> getUnsignedIntegerTokens(String line, String splitRule) {
        List<String> stringTokens = List.of(line.split(splitRule));
        var integerTokens = stringTokens.stream().map((s) -> {
            var num = Integer.parseInt(s);
            if (num <= 0) {
                throw new IllegalArgumentException();
            }
            return new NumberToken(new UnsignedInteger(num));
        }).toList();
        return integerTokens;
    }
}
