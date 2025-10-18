package calculator.token;

import calculator.token.interfaces.Token;

public record PositiveNumberToken(int value) implements Token {
    public PositiveNumberToken {
        if (value < 1) {
            throw new IllegalArgumentException();
        }
    }
}
