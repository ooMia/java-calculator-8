package calculator;

import java.util.Optional;
import java.util.Set;

public final class Lexer {

    private final Set<Integer> baseDelimiters;
    private final String prefix;
    private final String suffix;

    public Lexer(Set<Integer> baseDelimiters, String prefix, String suffix) {
        this.baseDelimiters = Set.copyOf(baseDelimiters);
        this.prefix = prefix;
        this.suffix = suffix;
    }

    /**
     * find pattern and replace with base delimiter
     * 
     * @param line expression with custom delimiters
     * @return numbers concatenated by base delimiters
     */
    public String replace(String line) {
        var baseDelimiter = this.getBaseDelimiter();
        while (true) {
            var pattern = findPattern(line).orElse(null);
            if (pattern == null) {
                break;
            }
            int customDelimiterCode = extractDelimiter(pattern);
            var customDelimiter = Character.toString(customDelimiterCode);

            // 1. remove `suffix + delimiter + prefix` pattern
            // 2. replace `delimiter` to base delimeter
            line = line.replace(pattern, "");
            line = line.replace(customDelimiter, baseDelimiter);
        }
        return line;
    }

    Optional<String> findPattern(String line) {
        try {
            int iStart = line.indexOf(this.prefix);
            int iEnd = line.indexOf(this.suffix) + this.suffix.length();
            return Optional.of(line.substring(iStart, iEnd));
        } catch (IndexOutOfBoundsException e) {
            return Optional.empty();
        }
    }

    int extractDelimiter(String pattern) {
        int iStart = this.prefix.length();
        int iEnd = pattern.length() - this.suffix.length();

        var candidates = pattern.substring(iStart, iEnd).codePoints().toArray();
        if (candidates.length != 1) {
            throw Cause.INVALID_INPUT.exception();
        }

        var delimiter = candidates[0];
        if (this.baseDelimiters.contains(delimiter)) {
            throw Cause.INVALID_INPUT.exception();
        }
        return delimiter;
    }

    private String getBaseDelimiter() {
        var codePoint = baseDelimiters.iterator().next();
        return Character.toString(codePoint);
    }

}
