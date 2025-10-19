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

## 학습자 요구사항

- `Token` 인터페이스의 `Token reduce(Token operand)`를 활용하여 구현하시오.
- `Cause` enum을 활용하여 구현하시오.
- `codePoints()`를 기준으로 문자를 정의하여 처리한다.

## 라이브러리

- `camp.nextstep.edu.missionutils`에서 제공하는 Console API를 사용하여 구현해야 한다.
  - 사용자가 입력하는 값은 `camp.nextstep.edu.missionutils.Console`의 `readLine()`을 활용한다.

---

# 개인 코딩 체크리스트 정리

`객체/기능 나열 → 공통 제약 → 입/출력 나열 → 그룹화/추상화 → 테스트화`

## 1. 요구사항 분석 단계

1. 핵심 객체/기능 (`README.md`)
2. 프로그램 제약 (`Cause.java`)
3. 가능한 입력 및 출력 케이스 (`ApplicationTest.java`)

- 요구사항이 애매하게 느껴진다면
   1. 추상화한다. 추상화하기 복잡하면 **구현 편의성**을 우선한다.
   2. `오컴의 면도날`: 분석 이후, 주어진 문제는 작고 단순해져야 한다.
- 유사한 객체들을 묶어 그룹화하고, 소속 객체들의 동작을 추상화한다. (클래스 설계)
  - 소속된 객체의 공통 제약을 정리한다.
  - 구체적인 구현 형태는 적지 않는다.
- 가능한 입력의 형태를 사전에 정의한다. 이후 개발 중 추가하고 싶으면 `TODO`로 남기고 나중에 구현한다.

## 2. 설계 및 구조화 단계

- 설계 청사진을 README에는 **서술 형태로**, 코드에는 **인터페이스 & 테스트**로 남긴다.
- 전체를 한 번에 그릴 수는 없다. 문제를 작게 쪼개는 연습을 한다.
- 예외는 enum으로 정의하여 일관되게 처리한다.
- **PM**과 **개발자**, 2개의 자아를 번갈아가며 사용한다.

## 3. 구현 및 테스트 단계

- 모든 기능을 `given, when, then` 형식의 **검증 가능한 형태**로 정의한다.
- 구현 결과를 테스트로 표현한다. 테스트 통과를 기준으로 커밋한다.
- 동일한 유형의 테스트는 인자화를 통해 깔끔히 다듬는다.
- try-catch 블록을 안전장치로 활용한다.

## 4. 반복 개선 및 회고 단계

- **반복적 학습 → 테스트 → 회고**의 순환 구조를 유지한다.
- 올바른 순서대로 흐름을 반복하면 속도는 자연스럽게 향상된다.
- 결과론적 후회보다 **계획-실천 일관성**을 중시한다.
- 반복 사용 가능한 패턴과 재사용 가능한 무기를 학습하고 다듬는다.
- 객체지향적 접근과 시간 제약 사이에서 **제한된 시간 안에서의 균형감**을 유지하는 연습을 한다.

## 5. 코드 스타일 및 기본 원칙

- 한 메서드에 오직 한 단계의 들여쓰기(indent)만 허용했는가?
- else 예약어를 쓰지 않았는가?
- 코드 한 줄에 점(.)을 하나만 허용했는가?
- 클래스 필드 3개 내외로 적절히 유지
- 메소드 파라미터 수 3개 이하로 유지하기
- 메소드가 한 가지 일만 담당하도록 구현했는가?
- 주석을 적극적으로 활용한다. 불필요한 주석은 마무리 단계에서 삭제하면 된다.

## 6. 객체지향 설계 원칙 (OOP Design)

- 상속을 강제하는 식의 다형성 확보보다, 단순한 연산 수준(operate)에서는 **함수형 인터페이스** 활용이 낫다.
- 클래스 작성 요령: **추상화 -> 계약 -> 인터페이스 -> public 오버라이딩**
- 클래스는 계약이고, 객체는 생명체이다.
- 모든 원시값과 문자열을 포장했는가?
- 콜렉션에 대해 일급 콜렉션을 적용했는가?
- DTO를 제외한 핵심 로직을 구현하는 도메인 객체에서는 getter/setter를 쓰지 않는다.
- 각 객체마다 적절한 내부 상태를 가져야한다. 즉, 비슷하지만 구분되어야 한다. 마치 인간처럼.
- 클래스의 이름은 그것 자체를 드러내야지, 그 동작을 표현해선 안 된다. 동작은 인터페이스로 표현한다.
- 어떤 동작이 필요할 때마다 클래스를 정의하는 식으로는 절차지향에서 벗어날 수 없다.
  - OOP의 가치는 **책임 분해**를 통해 큰 문제를 작은 단위로 다루는 데 있다.
  - 특정한 동작이 구분될 필요가 있다면, 함수형 인터페이스를 고려해본다.
- 좋은 객체는 완전하거나 불완전한 클래스에서 온다.

---

# 프로젝트 초기화

## .vscode/settings.json

```json
{
  "java.format.settings.url": "https://gist.githubusercontent.com/ooMia/1a47bdf9ef00c3466d1f506aa99f4acb/raw/4ea31711e74e617607d9c27a47a78c60b6229a19/woowa-style.xml",
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
