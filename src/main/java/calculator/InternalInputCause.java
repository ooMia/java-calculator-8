package calculator;

import calculator.util.problem.DefaultProblemHandler;
import calculator.util.problem.InputCause;
import calculator.util.problem.ProblemHandler;

// 위치한 package 내부에서만 활용 가능한 예외 원인 목록입니다
// enum 안에 필요한 예외 상황들을 정리해서 사용하세요.
enum InternalInputCause implements InputCause {

    INVALID_INPUT("INVALID_INPUT"), FALLBACK("FALLBACK");

    private static ProblemHandler handler = new DefaultProblemHandler();
    private final String message;

    InternalInputCause(String message) {
        this.message = message;
    }

    @Override
    public IllegalArgumentException exception() {
        return handler.exception(this.message);
    }
}
