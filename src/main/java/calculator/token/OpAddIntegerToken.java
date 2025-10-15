package calculator.token;

public final class OpAddIntegerToken extends Token<Number> {

    private int v;

    public OpAddIntegerToken() {
        this.v = 0;
    }

    public OpAddIntegerToken(int v) {
        this.v = v;
    }

    @Override
    public Number value() {
        // OperationToken들은 가시성을 확장하여 결과를 직접 참조할 수 있음
        return v;
    }

    @Override
    public OpAddIntegerToken reduce(Token<Number> operand) {
        // 연산을 정의하는 중요한 부분
        // 현재의 클래스는 컨셉에 맞게 operand를 정수로 취급하여 덧셈을 수행한다.

        // 현재 객체의 상태를 수정해서 그대로 반환할 수 있지만
        // 일단 불변 객체 + 일급 함수를 만드는 쪽으로 진행
        var v = this.value().intValue() + operand.value().intValue();
        return new OpAddIntegerToken(v);
    }

    // 실제 어떤 원리로 이게 경고가 되는걸까?
    // Type safety: Potential heap pollution via varargs parameter operandsJava(67109670)
    // public OpAddIntegerToken reduce(Token<Number>... operands) {

}
