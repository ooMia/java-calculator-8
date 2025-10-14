package calculator;

import java.util.Arrays;

class Lexer {

    int[] convert(String s) {
        String regex = ",|:"; // 쉼표 또는 콜론을 구분자로 정의한다는 뜻
        while (true) {
            // 커스텀 구분자에 대한 정의를 찾을 때마다 
            int iCustom = findCustomDelims(s);
            if (iCustom < 0) break;
            regex = buildRegexFormat(regex, s, iCustom); // 표현식에 추가하고 
            s = s.replaceFirst("//.\\\\n", ","); // 문자열에서 정의 내용 삭제
        }
        String[] parsed = s.split(regex);
        var res = Arrays.stream(parsed).filter(e -> e != null && !"".equals(e)).mapToInt(Integer::parseInt).toArray();
        validate(res);
        return res;
    }

    private void validate(int[] numbers) {
        for (int n : numbers)
            if (n < 0) throw new IllegalArgumentException("유효하지 않은 입력 값입니다.");
    }

    String buildRegexFormat(String base, String s, int iCustom) {
        if (iCustom < 0) return base;
        char c = s.charAt(iCustom);
        if (c == '\\') return base + "|" + '\\' + c;
        if (c == '+') return base + "|" + '\\' + c;
        if (c == '*') return base + "|" + '\\' + c;
        // 이런 식으로 분기가 추가되는 이유는
        // 정규표현식 규칙을 컴파일 할 때
        // 특수 문자들을 처리하기 위해서도 \를 사용하기 때문
        // 이러한 현상을 차단하고 싶으면, 정규표현식에서 스택 방식으로 전환하는 것이 나음
        return base + "|" + c;
    }

    int findCustomDelims(String s) {
        int i = 1;
        boolean found = false;
        for (; i < s.length(); ++i) // 커스텀 구분자 접두사가 나타나기 전까지 스킵
            if (s.charAt(i) == '/' && s.charAt(i - 1) == '/') {
                found = true;
                break;
            }
        if (!found) return -1;
        if (found && i + 3 < s.length()) // 구분자는 문자(char)라는 가정
            if (s.charAt(i + 2) == '\\' && s.charAt(i + 3) == 'n')
                return i + 1;
        throw new IllegalArgumentException("유효하지 않은 입력 값입니다."); // 커스텀 접두사만 존재하는 경우
    }
}
