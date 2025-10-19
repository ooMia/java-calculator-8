package calculator;

enum Cause {

    INVALID_INPUT("INVALID_INPUT");

    private final String message;

    Cause(String message) {
        this.message = message;
    }

    public IllegalArgumentException exception() {
        return new IllegalArgumentException(this.message);
    }

}
