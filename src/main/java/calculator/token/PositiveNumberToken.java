package calculator.token;

import calculator.token.interfaces.IntegerToken;

public record PositiveNumberToken(int value) implements IntegerToken {
    public PositiveNumberToken {
        if (value < 1) {
            throw new IllegalArgumentException();
        }
    }
}
