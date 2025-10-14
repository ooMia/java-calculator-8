package calculator;

import java.util.Arrays;

class Lexer {
    // private final Set<Character> defaultDelims;

    // 최초에 기본 구분자로 클래스 초기화
    // 하드코딩된 정규표현식들 defaultDelims 사용해서
    // 리팩토링하면 이것보단 깔끔해질듯

    // Lexer(char[] delims) {
    //     this.defaultDelims = new HashSet<>();
    //     for (var delim : delims) defaultDelims.add(delim);
    // }

    int[] convert(String s) {
        String regex = ",|:";
        while (true) {
            int iCustom = findCustomDelims(s);
            if (iCustom < 0) break;
            regex = buildRegexFormat(regex, s, iCustom);
            s = s.replaceFirst("//.\\\\n", ",");
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
        // 이런 식으로 추가되는 이유는
        // 정규표현식 규칙을 컴파일 할 때
        // 특수 문자들을 처리하기 위해서도 \를 사용하기 때문
        // 이러한 현상을 차단하고 싶으면, 정규표현식에서 스택 방식으로 전환하는 것이 나음
        return base + "|" + c;
    }

    int findCustomDelims(String s) {
        // 커스텀 구분자 접두사가 나타나기 전까지 스킵
        // 나타난 이후 위치 기록
        // 커스텀 접미사 후보 n이 나타나면 확인
        // 없으면 -1
        int i = 1;
        boolean found = false;
        for (; i < s.length(); ++i)
            if (s.charAt(i) == '/' && s.charAt(i - 1) == '/') {
                found = true;
                break;
            }
        if (found && i + 3 < s.length())
            if (s.charAt(i + 2) == '\\' && s.charAt(i + 3) == 'n')
                return i + 1;
        return -1;
    }
}
