package calculator;

public final class Delimeter {

    private final char c;

    public Delimeter(char c) {
        this.c = c;
    }

    public String toRegexFormat(Delimeter... args) {
        var sb = new StringBuilder(toExplicitRule(this.c));
        for (var arg : args) {
            sb.append(toExplicitRule(arg.c));
        }
        return String.format("[%s]", sb.toString());
    }

    private String toExplicitRule(char c) {
        return "\\" + c;
    }
}
