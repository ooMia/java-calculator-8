package calculator.core;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

final class CustomDelimiterLexer {

    private final Set<Integer> baseDelimiters;
    private final String prefix;
    private final String suffix;

    public CustomDelimiterLexer(Set<Integer> baseDelimiters, String prefix, String suffix) {
        this.baseDelimiters = Collections.unmodifiableSet(baseDelimiters);
        if (baseDelimiters.isEmpty()) {
            throw InternalCauseMessage.BASE_DELIMITER_MORE_THAN_ONE.exception();
        }
        this.prefix = prefix;
        this.suffix = suffix;
    }

    /**
     * find and remove multiple custom delimiter definition pattern and replace with base delimiter
     *
     * @param line expression with custom delimiters
     * @return numbers concatenated by base delimiters
     */
    public String replaceDelimiter(String line) {
        String baseDelimiter = this.getFirstBaseDelimiter();
        while (true) {
            var patternFound = findCustomDelimiterDefinitionPhase(line);
            if (patternFound.isEmpty()) {
                break;
            }

            String definitionPhase = patternFound.get();
            int code = findCustomDelimiterCode(definitionPhase);
            String customDelimiter = Character.toString(code);
            assertCustomDelimiterDefineFirstBeforeUsing(line, customDelimiter);

            line = line.replace(definitionPhase, "");
            line = line.replace(customDelimiter, baseDelimiter);
        }
        return line;
    }

    private String getFirstBaseDelimiter() {
        var codePoint = baseDelimiters.iterator().next();
        return Character.toString(codePoint);
    }

    Optional<String> findCustomDelimiterDefinitionPhase(String line) {
        try {
            int indexFrom = line.indexOf(this.prefix);
            int indexTo = line.indexOf(this.suffix) + this.suffix.length();
            return Optional.of(line.substring(indexFrom, indexTo));
        } catch (IndexOutOfBoundsException e) {
            return Optional.empty();
        }
    }

    int findCustomDelimiterCode(String definitionPhase) {
        int indexPrefixEnd = this.prefix.length();
        int indexSuffixStart = definitionPhase.length() - this.suffix.length();
        try {
            var stringBetweenRule = definitionPhase.substring(indexPrefixEnd, indexSuffixStart);
            return getSingleCharacterOrThrow(stringBetweenRule);
        } catch (IndexOutOfBoundsException e) {
            throw InternalCauseMessage.NO_MATCHED_RULE_FOUND.exception();
        }
    }

    private int getSingleCharacterOrThrow(String stringBetweenRule) throws IllegalArgumentException {
        var candidates = stringBetweenRule.codePoints().toArray();
        if (candidates.length != 1 || this.baseDelimiters.contains(candidates[0])) {
            throw InternalCauseMessage.CUSTOM_DELIMITER_SINGLE_CHARACTER_ASSERTION.exception();
        }
        return candidates[0];
    }

    private void assertCustomDelimiterDefineFirstBeforeUsing(String line, String delimiter) {
        int indexPrefixStart = line.indexOf(this.prefix);
        var stringBeforePrefixStart = line.substring(0, indexPrefixStart);
        if (stringBeforePrefixStart.contains(delimiter)) {
            throw InternalCauseMessage.CUSTOM_DELIMITER_RULE_BROKEN.exception();
        }
    }

}
