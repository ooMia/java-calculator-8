package calculator.util.problem;

import java.util.function.Supplier;

public abstract class AbstractPrefixProblemHandler implements ProblemHandler {
    private final Class<? extends RuntimeException> defaultExceptionClass;
    private final String exceptionMessagePrefix;

    protected AbstractPrefixProblemHandler() {
        this.defaultExceptionClass = IllegalArgumentException.class;
        this.exceptionMessagePrefix = "[ERROR] ";
    }

    protected AbstractPrefixProblemHandler(Class<? extends RuntimeException> defaultException, String messagePrefix) {
        this.defaultExceptionClass = defaultException;
        this.exceptionMessagePrefix = messagePrefix;
    }

    @Override
    public final RuntimeException exception(String message) {
        return instantiate(message);
    }

    @Override
    public final <T> T tryUntilSucceed(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (RuntimeException e) {
                throwIfNotDefaultExceptionClass(e);
                doOnFailure(e);
            }
        }
    }

    // tryUntilSucceed catch 내부 콜백 정의 
    protected abstract void doOnFailure(RuntimeException e);

    private void throwIfNotDefaultExceptionClass(RuntimeException origin) throws RuntimeException {
        if (defaultExceptionClass.isInstance(origin)) {
            return;
        }
        var cause = origin.getCause();
        if (cause != null && defaultExceptionClass.isInstance(cause)) {
            return;
        }
        throw exception(origin.getMessage());
    }

    private RuntimeException instantiate(String message) {
        try {
            var msg = String.format("%s%s", exceptionMessagePrefix, message).trim();
            return defaultExceptionClass.getConstructor(String.class).newInstance(msg);
        } catch (Exception e) {
            return new RuntimeException("InternalProblem::instantiate");
        }
    }
}
