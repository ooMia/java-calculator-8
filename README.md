# 1주차 컨셉

- 앞으로 반복해서 사용할 수 있는 무기를 갈고 닦자
- 재사용 가능한 패턴을 학습하기
- (필요하면 추가하기)

## 문제 풀이 방식

1. 매일 한 번씩 문제를 `origin/develop`으로 초기화하여 다시 해결한다.
2. 5시간의 시간 제한 내에 각 회차의 시도를 마무리한다.
3. 해결 이후, `develop`에 회고 PR을 남긴다.
   - git-flow 설정 `oomia` - `develop` - `trial`(feature)
4. PR과 코드 내용을 바탕으로 현재 코드의 개선 방향성을 모색한다.
   - Google Style Convention 점검
   - Linter 활용
   - AI를 활용한 코드 리뷰 등
5. 2회차부터는 이전 회차에서의 부족한 점을 개선하기 위한 시도를 한다.

## 풀이 기록

### [Trial/first #1](https://github.com/ooMia/java-calculator-8/pull/1)

#### 동일한 유형의 테스트들을 깔끔하게 관리하는 방법

> 조사는 했지만, 리팩토링 전에는 나열하듯 작성하고 순서만 신경써도 충분하다.

- `@BeforeEach`로 given 코드 중복 제거

  ```java
  private Lexer lexer;

  @BeforeEach
  void setUp() {
     lexer = new Lexer();
  }
  ```

- `@Nested` 클래스로 논리적 그룹화
- `@ParameterizedTest`, `@xxxSource`로 중복 제거
  ```java
  @ParameterizedTest
  @ValueSource(strings = {"a", "bb", "ccc"})
  ```
