package calculator;

public final class UnsignedInteger extends Number {
    // 불변 객체가 같은 값을 가지고 있는 경우, 생성하지 않고 참조하면 메모리 효율을 높일 수 있지 않을까?
    // (가령, String pool처럼)
    // JVM에게 힌트를 줄 수는 없을까?
    private final Integer value;

    public UnsignedInteger(int value) {
        // 양수 조건을 보고 만들긴 했는데
        // 원래 uint는 0도 포함하니까 0을 거를 수가 없다
        // 돌아간다면 그냥 Integer에 조건문으로 필터링할듯
        if (value < 0) {
            throw new IllegalArgumentException();
        }
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Number number) {
            return this.intValue() == number.intValue();
        }
        return super.equals(obj);
    }

    public boolean equals(Number obj) {
        return this.equals(obj);
    }

    @Override
    public int intValue() {
        return value.intValue();
    }

    @Override
    public long longValue() {
        return value.longValue();
    }

    @Override
    public float floatValue() {
        return value.floatValue();
    }

    @Override
    public double doubleValue() {
        return value.doubleValue();
    }
}
