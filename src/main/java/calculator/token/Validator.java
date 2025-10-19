package calculator.token;

public interface Validator {
    
    /**
     * 특정한 토큰에 대한 유효성 검증 로직을 정의할 수 있는 인터페이스.
     * 유효하지 않은 토큰에 대해 `IllegalArgumentException` 예외를 발생시킨다.
     * 
     * @param operand 추가적으로 들어오는 다음 토큰
     */
    void vadlidate(Token operand) throws IllegalArgumentException;

}
