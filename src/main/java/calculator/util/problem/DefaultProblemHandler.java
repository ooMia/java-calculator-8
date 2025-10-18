package calculator.util.problem;

public final class DefaultProblemHandler extends AbstractPrefixProblemHandler {
    public DefaultProblemHandler() {
        // use default constructor
        // defaultExceptionClass: IllegalArgumentException.class
        // exceptionMessagePrefix: "[ERROR] "
        super();
    }

    public DefaultProblemHandler(Class<? extends RuntimeException> defaultException, String messagePrefix) {
        super(defaultException, messagePrefix);
    }

    @Override
    protected void doOnFailure(RuntimeException e) {
        System.out.println(e.getMessage());
    }
}
