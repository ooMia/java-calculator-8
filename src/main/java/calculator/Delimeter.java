package calculator;

public final class Delimeter {

    private final Character c;

    public Delimeter() {
        this.c = null;
    }

    public Delimeter(char c) {
        this.c = c;
    }

    // TODO 이걸 얘가 가지고 있어도 될까?
    // static 지양하라고 하긴 했는데, 그나마 얘가 가장 가까워보여서 일단 배치
    public static Delimeter extractCustomDelim(String line) {
        if (line.length() < 5) {
            return new Delimeter();
        }
        var part1 = line.substring(0, 2);
        var part2 = line.substring(3, 5);
        if ("//".equals(part1) && "\\n".equals(part2)) {
            return new Delimeter(line.charAt(2));
        }
        return new Delimeter();
    }

    public String toRegexFormat(Delimeter... args) {
        var sb = new StringBuilder(toExplicitRule(this.c));
        for (var arg : args) {
            sb.append(toExplicitRule(arg.c));
        }
        return String.format("[%s]", sb.toString());
    }

    private String toExplicitRule(Character c) {
        if (c == null) {
            return "";
        }
        return "\\" + c;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Delimeter delim) {
            return this.c == delim.c;
        }
        // TODO char 와 String 에 대해서도 equals 성공하기
        // if (obj instanceof String s) {
        //     return this.toString().equals(s);
        // }
        // if (obj instanceof Character c) {
        //     return String.valueOf(c).equals(this.toString());
        // }
        return false;
    }

    @Override
    public String toString() {
        return String.valueOf(this.c);
    }
}
