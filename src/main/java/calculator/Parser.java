package calculator;

import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Stream;

final class Parser {

    private final Pattern pattern;

    public Parser(Set<Integer> baseDelimiters) {
        var sb = new StringBuilder();
        for (var code : baseDelimiters) {
            sb.append('\\').append(Character.toString(code));
        }
        var regex = String.format("[%s]", sb);
        this.pattern = Pattern.compile(regex);
    }

    /**
     * split joined long values by delimiters into array
     *
     * @param line long values concatenated by base delimiters
     * @return array of long values
     */
    public long[] parse(String line) {
        try {
            String[] tokens = pattern.split(line);
            return Stream.of(tokens)
                    .filter(s -> !s.isEmpty())
                    .mapToLong(Long::parseLong)
                    .toArray();
        } catch (NumberFormatException e) {
            throw Cause.FAILED_PARSE_LONG.exception();
        }
    }

}
