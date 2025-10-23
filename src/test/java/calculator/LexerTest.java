package calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LexerTest {

    private Lexer lexer;

    @BeforeEach
    void setUp() {
        this.lexer = new Lexer(Set.of((int) ',', (int) ':'), "//", "\\n");
    }

    @Test
    void testFindCustomDelimiterDefinitionPhase_1() {
        var expect = "//;\\n";
        var actual = lexer.findCustomDelimiterDefinitionPhase("//;\\n1");
        assertEquals(expect, actual.orElseThrow(), "ASCII 형태의 커스텀 구분자 패턴 식별");
    }

    @Test
    void testFindCustomDelimiterDefinitionPhase_2() {
        var expect = "//가\\n";
        var actual = lexer.findCustomDelimiterDefinitionPhase("//가\\n1");
        assertEquals(expect, actual.orElseThrow(), "유니코드 형태의 커스텀 구분자 패턴 식별");
    }

    @Test
    void testExtractDelimiter_ByRegex_1() {
        var input = "//;\\n";
        var expect = ';';
        assertEquals(expect, lexer.findCustomDelimiterCode(input));
    }

    @Test
    void testExtractDelimiter_ByRegex_2() {
        var input = "//가\\n";
        var expect = '가';
        assertEquals(expect, lexer.findCustomDelimiterCode(input));
    }

    @Test
    void testExtractDelimiter_ByRegex_3() {
        var input = "//12\\n";
        assertThrows(IllegalArgumentException.class, () -> lexer.findCustomDelimiterCode(input), "ASCII 2개 실패");
    }

    @ParameterizedTest
    @ValueSource(strings = {"//;\\n11;22;33", "//\\\\n11\\22\\33", "//n\\n11n22n33", "///\\n11/22/33"})
    void testReplaceDelimiterSuccess(String input) {
        var soloDelimiterLexer = new Lexer(Set.of((int) ','), "//", "\\n");
        var expect = "11,22,33";
        assertEquals(expect, soloDelimiterLexer.replaceDelimiter(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"//,\\n11,22,33", "//:\\n11:22:33", "//\\n", "//가1\\n", "//12\\n"})
    void testReplaceDelimiterFail(String input) {
        assertThrows(IllegalArgumentException.class, () -> lexer.replaceDelimiter(input), "기본 구분자와 겹치거나 한 문자가 아니라면 예외 발생");
    }

}
