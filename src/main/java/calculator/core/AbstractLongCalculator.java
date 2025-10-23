package calculator.core;

import java.util.Arrays;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

/**
 * 1. Lexer : 커스텀 구분자 패턴을 기본 구분자로 치환
 * 2. Parser : 기본 구분자로 연결된 문자열을 숫자 배열로 변환
 * 3. Reducer : 두 수를 하나로 만드는 연산을 반복하여 결과 반환
 */
abstract class AbstractLongCalculator implements SumCalculator {

    private final CustomDelimiterLexer customDelimiterLexer;
    private final LongParser longParser;

    AbstractLongCalculator(Set<Integer> baseDelimiters, CustomDelimiterRule rule) {
        this.customDelimiterLexer = new CustomDelimiterLexer(baseDelimiters, rule.prefix(), rule.suffix());
        this.longParser = new LongParser(baseDelimiters);
    }

    private Stream<Number> parseNumbers(String line) {
        line = customDelimiterLexer.replaceDelimiter(line);
        var longArray = longParser.parse(line);
        return Arrays.stream(longArray).mapToObj(Number.class::cast);
    }

    @Override
    public final Number sum(String line) {
        var numberStream = parseNumbers(line);
        return numberStream.reduce(0L, sumReducer());
    }

    protected abstract BinaryOperator<Number> sumReducer();

}
