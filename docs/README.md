### 기본 체크리스트

- [x] Constant 초안 작성 (README 줄 번호 주석)
- [ ] 명사 <> 동사 관계를 찾아 객체지향적으로 구조 설계하기
- [ ] model.in / out
- [ ] ErrorCode enum
- [ ] model.in 제약 반영
- [ ] view.Message
- [ ] 설계 내용에 따라 도메인 로직 구현
- [ ] 단위 테스트로 로직 검증
- [ ] model.in 변환 테스트
- [ ] model.out 변환 테스트
- [ ] InputView 구현
- [ ] OutputView 구현

# 설계하기

// 입력한 문자열에서
// 숫자를 추출하여
// 더하는
// 계산기

// 구분자
// 쉼표 ,
// 콜론 :
public static final String[] 기본_구분자_배열 = new String[]{",", ":"};

// 구분자를 기준으로 분리한 각 숫자의 합을 반환
// "" => 0, "1,2" => 3, "1,2,3" => 6, "1,2:3" => 6

// 앞의 기본 구분자
// 쉼표 콜론 외에
// 커스텀 구분자를 지정할 수 있다.

// 문자열 앞 부분의 // 와 \n 사이에 위치하는 문자를 커스텀 구분자로 사용한다
public static final String 커스텀_구분자_접두사 = "//", 커스텀_구분자_접미사 = "\\n";

// "//;\n1;2;3"
// 커스텀 구분자는 세미콜론(;)이며
// 결과 값은 6이 반환되어야 한다.

// 사용자가 잘못된 값을 입력할 경우 `IllegalArgumentException`을 발생시킨 후 애플리케이션은 종료되어야 한다.
public static final Class<IllegalArgumentException> INPUT_EXCEPTION = IllegalArgumentException.class;
