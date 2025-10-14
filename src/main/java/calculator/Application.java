package calculator;

import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {
        // 덧셈할 문자열을 입력해 주세요.
        // 1,2:3
        // 결과 : 6
        var calculator = new Calculator();
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        var line = Console.readLine();
        var res = calculator.add(line);
        System.out.println(String.format("결과 : %d", res));
    }
}
