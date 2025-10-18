package calculator.util.problem;

public interface InputCause extends GeneralCause {
    @Override
    IllegalArgumentException exception();
}
