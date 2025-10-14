package calculator;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Lexer {
    private final Set<Character> defaultDelims;

    // 최초에 기본 구분자로 클래스 초기화
    Lexer(char[] delims) {
        this.defaultDelims = new HashSet<>();
        for (var delim : delims) defaultDelims.add(delim);
    }

    int[] convert(String s) {
        String regex = ",|:";
        while (true) {
            int iCustom = findCustomDelims(s);
            if (iCustom < 0) break;
            regex = buildRegexFormat(regex, s, iCustom);
            s = s.replaceFirst("//.\\\\n", ",");
        }
        String[] parsed = s.split(regex);
        return Arrays.stream(parsed).filter(e -> e != null && !"".equals(e)).mapToInt(Integer::parseInt).toArray();
    }

    String buildRegexFormat(String base, String s, int iCustom) {
        if (iCustom < 0) return base;
        char c = s.charAt(iCustom);
        if (c == '\\') return base + "|" + c + c;
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
