# java-calculator-precourse

- 세 개의 요구 사항을 만족하기 위해 노력한다.
- 기능을 구현하기 전에 기능 목록을 만들고, 기능 단위로 커밋 하는 방식으로 진행한다.
- 기능 요구 사항에 기재되지 않은 내용은 스스로 판단하여 구현한다.
- 제출은 일요일 오후 3시부터 가능하다.
- 과제를 수행하면서 느낀 점, 배운 점, 많은 시간을 투자한 부분 등 자유롭게 작성한다.

## 과제 진행 요구 사항

- 미션은 문자열 덧셈 계산기 저장소를 포크하고 클론하는 것으로 시작한다.
- 기능을 구현하기 전 README.md에 구현할 기능 목록을 정리해 추가한다.
- Git의 커밋 단위는 앞 단계에서 README.md에 정리한 기능 목록 단위로 추가한다.
- AngularJS Git Commit Message Conventions을 참고해 커밋 메시지를 작성한다.
- 자세한 과제 진행 방법은 프리코스 진행 가이드 문서를 참고한다.

## 기능 요구 사항

입력한 문자열에서 숫자를 추출하여 더하는 계산기를 구현한다.

- 쉼표(,) 또는 콜론(:)을 구분자로 가지는 문자열을 전달하는 경우 구분자를 기준으로 분리한 각 숫자의 합을 반환한다.
- 예: "" => 0, "1,2" => 3, "1,2,3" => 6, "1,2:3" => 6
- 앞의 기본 구분자(쉼표, 콜론) 외에 커스텀 구분자를 지정할 수 있다. 커스텀 구분자는 문자열 앞부분의 "//"와 "\n" 사이에 위치하는 문자를 커스텀 구분자로 사용한다.
  - 예를 들어 "//;\n1;2;3"과 같이 값을 입력할 경우 커스텀 구분자는 세미콜론(;)이며, 결과 값은 6이 반환되어야 한다.
- 사용자가 잘못된 값을 입력할 경우 `IllegalArgumentException`을 발생시킨 후 애플리케이션은 종료되어야 한다.

### 입출력 요구 사항

#### 입력

- 구분자와 양수로 구성된 문자열

#### 출력

- 덧셈 결과

```
결과 : 6
```

#### 실행 결과 예시

```
덧셈할 문자열을 입력해 주세요.
1,2:3
결과 : 6
```

# 프로그래밍 요구 사항

- JDK 21 버전에서 실행 가능해야 한다.
- 프로그램 실행의 시작점은 `Application`의 `main()`이다.
- build.gradle 파일은 변경할 수 없으며, 제공된 라이브러리 이외의 외부 라이브러리는 사용하지 않는다.
- 프로그램 종료 시 System.exit()를 호출하지 않는다.
- 프로그래밍 요구 사항에서 달리 명시하지 않는 한 파일, 패키지 등의 이름을 바꾸거나 이동하지 않는다.
- 자바 코드 컨벤션을 지키면서 프로그래밍한다.
- 기본적으로 Java Style Guide를 원칙으로 한다.

## 라이브러리

- `camp.nextstep.edu.missionutils`에서 제공하는 Console API를 사용하여 구현해야 한다.
  - 사용자가 입력하는 값은 `camp.nextstep.edu.missionutils.Console`의 `readLine()`을 활용한다.

---

# 코딩 가이드

- 이번 시도에는 최대한 각 클래스의 독립성을 살려서 구현해보자.
  - model.in에 static of(String) -> 파싱 구현
  - model.out에 static from -> 매핑 구현
  - WhatEverService 사용하기 (전역 참조 가능한 API)

## 기본 원칙

- service는 일반적으로 다음과 같은 흐름을 기대한다.
  - model.in::of -> (WhatEverService) -> model.out::from
- `Application::main`에 클래스 내부 static 메서드로 흐름을 정의한다.
- 개발 속도가 중요하다. 코드 내 한글 사용이 가능하다.
- 주석을 통해 참조(파일명, 줄 번호)를 명확히 한다.
- 요구사항에 명시된 상수/기준/조건 등은 `Constant`에 전역 변수로 정의
- 도메인 관련 예외는 `ErrorCode` enum으로 정의
- view.Message를 활용하여 출력 메시지를 관리한다.
- model.in, model.out에는 `record`를 활용하고, 데이터 형변환에 집중한다.

## 체크리스트

- [ ] 반드시 활용해야 하는 코드가 있는지 확인
- [ ] 명사 <> 동사 관계를 찾아 객체지향적으로 구조 설계하기
- [ ] Constant 초안 작성 (README 줄 번호 주석)
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

## 우테코 PR 체크리스트

- 자바 코드 컨벤션을 지키면서 프로그래밍했는가?
- 한 메서드에 오직 한 단계의 들여쓰기(indent)만 허용했는가?
- else 예약어를 쓰지 않았는가?
- 모든 원시값과 문자열을 포장했는가?
- 콜렉션에 대해 일급 콜렉션을 적용했는가?
- 3개 이상의 인스턴스 변수를 가진 클래스를 구현하지 않았는가?
  - 쉽지 않은 연습일 수 있다. 가능하면 인스턴스 변수의 수를 줄이기 위해 노력한다.
- getter/setter 없이 구현했는가?
  - 핵심 로직을 구현하는 도메인 객체에 getter/setter를 쓰지 않고 구현했는가?
  - 단, DTO는 허용한다.
- 메소드의 인자 수를 제한했는가?
  - 4개 이상의 인자는 허용하지 않는다.
  - 3개도 가능하면 줄이기 위해 노력해 본다.
- 코드 한 줄에 점(.)을 하나만 허용했는가?
  - 디미터(Demeter)의 법칙(“친구하고만 대화하라”)을 지켰는가?
  - 예를 들어 location.current.representation.substring(0, 1)와 같이 여러 개의 점(.)이 등장하면 리팩토링할 부분을 찾아본다.
- 메소드가 한가지 일만 담당하도록 구현했는가?
- 클래스를 작게 유지하기 위해 노력했는가?

---

# 프로젝트 초기화

## 프로젝트 구조

- (root)
  - `Application`
  - `Constant`
  - `ErrorCode` (enum)
  - `WhatEverService` (public; static)
- model (public)
  - in (record; static of)
  - out (record; static from)
- view
  - `Message` (package-private enum)
  - `InputView`
  - `OutputView`
- util
  - `ExceptionUtil`

## .vscode/settings.json

```json
{
  "java.format.settings.url": "https://gist.githubusercontent.com/ooMia/1a47bdf9ef00c3466d1f506aa99f4acb/raw/c7f0c56b4bd0a95dfa24de31655dac9cb9c65dc7/woowa-style.xml",
  "java.configuration.updateBuildConfiguration": "interactive",
  "[java]": {
    "editor.detectIndentation": false,
    "editor.renderWhitespace": "boundary",
    "editor.inlayHints.enabled": "on",
    "editor.parameterHints.enabled": true,
    "editor.suggest.snippetsPreventQuickSuggestions": false,
    "editor.suggest.showMethods": true,
    "editor.suggest.showFields": true
  },
  "java.saveActions.organizeImports": true,
  "java.codeGeneration.toString.codeStyle": "STRING_FORMAT",
  "java.completion.favoriteStaticMembers": [
    "org.assertj.core.api.Assertions.*",
    "org.junit.Assert.*",
    "org.junit.jupiter.api.Assertions.*"
  ]
}
```

## 참고

- `chmod +x docs/scaffold.sh && docs/scaffold.sh`
- `./gradlew wrapper --gradle-version=8.7 --distribution-type=bin`
- `java.clean.workspace`
- git 설정
  - .gitignore `/out/` `/bin/`
  - `git config --global commit.template ./docs/.gitmessage`
  - `https://gist.github.com/stephenparish/9941e89d80e2bc58a153`
