package calculator.token;

public final class OpAddToken extends Token<Integer> {

    private final int v;

    public OpAddToken() {
        this.v = 0;
    }

    public OpAddToken(int v) {
        this.v = v;
    }


    @Override
    Integer value() {
        return v;
    }

    @Override
    Token<Integer> reduce(Token<Integer> operand) {
        // 현재 객체의 상태를 수정해서 그대로 반환할 수 있지만
        // 일단 불변 객체 + 일급 함수를 만드는 쪽으로 진행
        var v = this.value() + operand.value();
        return new OpAddToken(v);
    }
}
