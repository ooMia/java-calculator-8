package calculator;

import java.util.ArrayList;
import java.util.Collection;

public class Calculator implements AddCalculator {

    private final Collection<Character> delimiters;

    // 기본 구분자 생성자 -> 목록 저장
    public Calculator(char[] delimiters) {
        var buffer = new ArrayList<Character>();
        for (var c : delimiters) {
            buffer.add(Character.valueOf(c));
        }
        this.delimiters = buffer;
    }

    @Override
    public Number add(String expression) {
        if (expression == null || expression.isEmpty() || expression.isBlank()) {
            return 0L;
        }

        // TODO temporal implementation
        long sum = 0;
        for (var s : expression.split("[:,]")) {
            sum += Long.parseLong(s);
        }
        return sum;


        // 1.
        // 기본 구분자 규칙에 따라, 유효 구분자 상태 갱신
        // var delimiter = new HashSet<>(this.delimiters);
        // 문자열 -> 숫자 토큰 배열 변환

        // 2.
        // var addOperationToken = xx;
        // 변환 결과를 순회하며, 정의된 연산 토큰 규칙에 따라 계산 (AddPositiveNumberToken)

        // return -1L; // TODO implement
    }
}


interface AddCalculator {
    Number add(String expression);
}
