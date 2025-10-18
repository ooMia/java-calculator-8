# 1주차 컨셉

- 앞으로 반복해서 사용할 수 있는 무기를 갈고 닦자
- 재사용 가능한 패턴을 학습하기
- 객체 나열 - 그룹화 - 추상화 - 공통 제약 - 테스트화 - 기능 구현
- (필요하면 추가하기)

## 문제 풀이 방식

1. 매일 적어도 한 번, 문제를 `origin/develop`으로 초기화하여 다시 해결한다.
2. 5시간의 시간 제한을 둔다.
3. 해결 이후, `develop`에 회고 PR을 남긴다.
   - git-flow 설정 `oomia` - `develop` - `trial`(feature)
4. PR과 코드 내용을 바탕으로 현재 코드의 개선 방향성을 모색한다.
5. 각 회차 성공 시, 새로운 제약을 추가한다.

## 풀이 기록

### [Trial/first](https://github.com/ooMia/java-calculator-8/pull/1)

- 테스트 정리
  - `@BeforeEach`, `@Nested`
  - `@ParameterizedTest`, `@xxxSource`

### [Trial/second (실패)](https://github.com/ooMia/java-calculator-8/pull/2)

- 요구사항에서 프로그램 제약을 추출하고, 이를 활용하여 문제를 작게 분리한다.
- 적절한 시작점을 잡고, 테스트를 기반으로 규모 키워나가기

### [Trial/third](https://github.com/ooMia/java-calculator-8/pull/3)

- 요구사항을 정리하면서 개발 방향성과 프로그램 제약 모두 깔끔하게 정리되어야 한다.
- enum형 클래스가 `Cause` 인터페이스를 구현하면, `ProblemHandler` 인터페이스를 통해 오류를 쉽게 생성할 수 있다.
  - `Cause` 예외의 메시지 담당
  - `ProblemHandler` 예외의 클래스와 접두사 담당
