package pl.edu.amu.wmi.daut.re;

import java.util.Arrays;

/**
 * Zarzadca operatorow podstawowych
 * wyrazen regularnych POSIX.
 */
public class BasicPosixRegexpOperatorManager extends PosixRegexpOperatorManager {

    /**
     * Konstruktor klasy.
     */
    public BasicPosixRegexpOperatorManager() {
        addOperator("*", new KleeneStarOperator.Factory(),
                    Arrays.<String>asList("", "*"), PRIORITY_MEDIUM);
        addOperator(".", new AnyCharOperator.Factory(),
                    Arrays.<String>asList("."), PRIORITY_HIGH);
        addOperator("\\(\\)", new DoNothingOperator.Factory(),
                    Arrays.<String>asList("\\(", "\\)"), PRIORITY_HIGH);
        addOperator("\\{m,n\\}", new RangeNumberOfOccurrencesOperator.Factory(),
                    Arrays.<String>asList("", "\\{", ",", "\\}"), PRIORITY_MEDIUM);
        addOperator("\\{m\\}", new FixedNumberOfOccurrencesOperator.Factory(),
                    Arrays.<String>asList("", "\\{", "\\}"), PRIORITY_MEDIUM);
    }
}
