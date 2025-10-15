package calculator;

public final class UnsignedInteger extends Number {

    public static UnsignedInteger parseInt(String s) {
        int value = Integer.parseInt(s);
        return new UnsignedInteger(value);
    }

    // 불변 객체가 같은 값을 가지고 있는 경우, 생성하지 않고 참조하면 메모리 효율을 높일 수 있지 않을까?
    // (가령, String pool처럼)
    // JVM에게 힌트를 줄 수는 없을까?
    private final Integer value;

    public UnsignedInteger(int value) {
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
