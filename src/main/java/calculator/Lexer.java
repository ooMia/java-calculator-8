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
        String[] parsed = s.split(",|:");
        return Arrays.stream(parsed).mapToInt(Integer::parseInt).toArray();
    }

    // > 커스텀 구분자는 문자열 앞부분의 "//"와 "\n" 사이에 위치하는 문자를 커스텀 구분자로 사용한다.
    // > `문자열`이 아닌 `문자`라고 명시했으므로, 구분자는 `char`로 표현할 수 있는 1 바이트 문자라고 판단

    // 1. 문자열을 받으면 토큰의 배열로 변형
    //    - 내부에 스택 보유
    //    - 최초에 기본 구분자로 클래스 초기화
    //    - 메서드에 문자열 넘기면 스택 생성
    //    - 이후 순차적으로 스택에 push
    //      - 구분자 만나면 스택이 빌 때까지 pop하고 전부 퉁쳐서 숫자 하나로 결과에 반영
    //      - 문자를 만날 때마다 등록된 구분자를 만나면 치환
    //      - 커스텀 접미사를 만나면,
    //        접두사를 만나기 전까지 stack에서 pop
    //        전부 StringBuilder에 넣고 커스텀 구분자로 퉁치기
    //        - 이후 해당 커스텀 구분자도 구분자에 등록
    //        <!-- - 그리고 커스텀 구분자는 현재 인덱스 이후의
    //          String.replace로 기본 구분자로 변환 후 로직 계속 진행 -->
    // 2. 제대로 동작하는 지 확인하려면 `1,21:13//n\n1n21n13` = 90 
}
