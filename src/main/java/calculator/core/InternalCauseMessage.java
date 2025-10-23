package calculator.core;

enum InternalCauseMessage {

    NUMBER_SUM_OVERFLOW(),
    FAILED_PARSE_LONG(),
    BASE_DELIMITER_MORE_THAN_ONE(),
    CUSTOM_DELIMITER_RULE_BROKEN(),
    NO_MATCHED_RULE_FOUND,
    CUSTOM_DELIMITER_SINGLE_CHARACTER_ASSERTION,
    INVALID_INPUT("잘못된 사용자 입력입니다.");

    private final String message;

    InternalCauseMessage() {
        this.message = this.name();
    }

    InternalCauseMessage(String message) {
        this.message = message;
    }

    public IllegalArgumentException exception() {
        return new IllegalArgumentException(this.message);
    }

}
