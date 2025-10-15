package calculator.token;

public final class OpAddIntegerToken extends Token<Integer> {

    private int v;

    public OpAddIntegerToken() {
        this.v = 0;
    }

    public OpAddIntegerToken(int v) {
        this.v = v;
    }

    @Override
    protected Integer value() {
        return this.v;
    }

    @Override
    public Token<Integer> reduce(Token<Integer> operand) {
        // 현재 객체의 상태를 수정해서 그대로 반환할 수 있지만
        // 일단 불변 객체 + 일급 함수를 만드는 쪽으로 진행
        var v = this.value() + operand.value();
        return new OpAddIntegerToken(v);
    }
}
