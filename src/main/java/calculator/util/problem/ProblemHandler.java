package calculator.util.problem;

import java.util.function.Supplier;

public interface ProblemHandler {
    <T> T tryUntilSucceed(Supplier<T> supplier);

    <T extends RuntimeException> T exception(String message);
}
