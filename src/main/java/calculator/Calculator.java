package calculator;

import java.util.Collection;
import java.util.Set;
import java.util.function.IntBinaryOperator;

public final class Calculator {

    // 1. Lexer
    // - 기본 구분자를 모두 전달
    // - 커스텀 구분자 패턴을 기본 구분자로 치환

    // 2. Parser
    // - 기본 구분자를 모두 전달
    // - 문자열을 숫자 배열로 변환

    // 3. Evaluator
    // - 주어진 연산 방식에 따라 배열을 평가
    // - 하나의 결과로 합침

    private final Set<Integer> baseDelimiters;

    private final Lexer lexer;
    private final Parser parser;
    private final Evaluator evaluator;

    public Calculator(Collection<Integer> delimiters, CustomDelimiterRule rule) {
        this.baseDelimiters = Set.copyOf(delimiters);
        this.lexer = new Lexer(baseDelimiters, rule.prefix(), rule.suffix());
        this.parser = new Parser(baseDelimiters);
        this.evaluator = new Evaluator(sumPositiveOnlyOperation(), 0);
    }

    private static IntBinaryOperator sumPositiveOnlyOperation() {
        return (int res, int newValue) -> {
            if (newValue <= 0) {
                throw Cause.INVALID_INPUT.exception();
            }
            return res + newValue;
        };
    }

    public int sum(String line) {
        line = lexer.replace(line);
        var numbers = parser.parse(line);
        return evaluator.evaluate(numbers);
    }

}
