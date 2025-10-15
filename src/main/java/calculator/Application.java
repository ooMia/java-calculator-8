package calculator;

import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {
        Delimeter[] delims = new Delimeter[] {new Delimeter(','), new Delimeter(':'),};

        var line = Console.readLine();

        Delimeter customDelim = Delimeter.extractCustomDelim(line);

        String splitRule = customDelim.toRegexFormat(delims);

        String[] stringTokens = line.split(splitRule);

        for (var s : stringTokens)
            System.out.println(s);
    }
}
