package calculator;

import calculator.util.ExceptionUtil;

public final class Constant {
    public static final String ERROR_PREFIX = "[ERROR]";
    static {
        ExceptionUtil.setErrorPrefix(ERROR_PREFIX);
    }

    private Constant() {
    }
}
