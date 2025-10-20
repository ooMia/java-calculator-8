package calculator;

import java.util.Set;
import java.util.stream.Stream;

public final class Parser {

    private final String regex;

    public Parser(Set<Integer> baseDelimiters) {
        var sb = new StringBuilder();
        for (var code : baseDelimiters) {
            sb.append('\\').append(Character.toString(code));
        }
        this.regex = String.format("[%s]", sb);
    }

    /**
     * split joined integers by delimiters into array
     * 
     * @param line integers concatenated by base delimiters
     * @return array of integers
     */
    public int[] parse(String line) {
        try {
            String[] tokens = line.split(this.regex);
            return Stream.of(tokens).filter(s -> !s.isEmpty()).mapToInt(Integer::parseInt).toArray();
        } catch (NumberFormatException e) {
            throw Cause.INVALID_INPUT.exception();
        }
    }

}
