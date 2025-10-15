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

- 2일차: 반드시 `Token` 인터페이스를 활용하여 구현하시오.

## 라이브러리

- `camp.nextstep.edu.missionutils`에서 제공하는 Console API를 사용하여 구현해야 한다.
  - 사용자가 입력하는 값은 `camp.nextstep.edu.missionutils.Console`의 `readLine()`을 활용한다.

---

# 코딩 가이드

## 기본 원칙

- 요구사항으로부터 프로그램의 핵심 객체들을 나열한다.
- 유사한 객체들을 묶어 그룹화하고, 소속 객체들의 동작을 **추상화**한다. (클래스 설계)
  - 소속된 객체의 공통 제약을 정리한다.
  - 구체적인 구현 형태는 적지 않는다.
- 구현 결과를 테스트로 표현한다. 테스트 통과를 기준으로 커밋한다.
- 문제를 작게 쪼개는 연습을 해야 한다.
- 올바른 순서대로 흐름을 진행하는 것을 반복하여 속도를 향상시키는 방향으로 훈련한다.
- 주석을 적극적으로 활용한다. 불필요한 주석은 마무리 단계에서 삭제하면 된다.

## 체크리스트

### 우테코 PR 체크리스트

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

### 좋은 객체 격언

- 클래스는 계약이고, 객체는 생명체이다.
- 클래스 작성 요령
  1. 추상화한다.
  2. 계약을 생각한다.
  3. 인터페이스를 구현한다.
  4. 모든 public 메서드는 오버라이딩 되어야 한다.
- 각 객체마다 적절한 내부 상태를 갖는 것에 대한 중요성
  - 비슷하지만 구분되어야 한다. 마치 인간처럼.
- 좋은 객체는 서로 유사하지만, 그 사이 변하지 않는 자신만의 고유함이 있다.
  - 좋은 객체는 고유하기 위해 반드시 무언가를 캡슐화해야 한다.
- 어떤 동작이 필요할 때마다 클래스를 정의하는 식으로는 절차 지향에서 벗어날 수 없다.
  - OOP의 가치 중 하나는 범위 분해를 통해 커다랗고 추상적인 책임을 작은 단위로 분리하여 다룰 수 있다는 것이다.
- 클래스의 이름은 그것이 무엇인지를 말해야 한다.
  - 클래스의 이름이 “-er”로 끝난다면, 그것은 다양한 객체를 생성하는 것이 의미가 없을 가능성이 높다.
- 좋은 객체는 완전하거나 또는 불완전한 클래스에서 온다.
  - 슈뢰딩거의 클래스가 생성하는 객체는 신뢰할 수 없다.

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
