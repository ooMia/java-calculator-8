package calculator;

import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        var inputString = Console.readLine();
        SumCalculator calculator = new Calculator();
        var resultNumber = calculator.sum(inputString);
        System.out.printf("결과 : %s%s", resultNumber, System.lineSeparator());
    }
}
