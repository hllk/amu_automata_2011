package pl.edu.amu.wmi.daut.re;

import java.util.Arrays;

/**
 * Zarzadca operatorow rozszerzonych
 * wyrazen regularnych POSIX.
 */
class ExtendedPosixRegexpOperatorManager extends PosixRegexpOperatorManager {
    public ExtendedPosixRegexpOperatorManager() {
        addOperator("()", new DoNothingOperator.Factory(),
                    Arrays.<String>asList("(", ")"), PRIORITY_HIGH);
        addOperator("[::]", new AsciiCharacterClassOperator.Factory(),
                    Arrays.<String>asList("[:", ":]"), PRIORITY_HIGH);
        addOperator("+", new AtLeastOneOperator.Factory(),
                    Arrays.<String>asList("", "+"), PRIORITY_MEDIUM);
        addOperator("?", new OptionalityOperator.Factory(),
                    Arrays.<String>asList("", "?"), PRIORITY_MEDIUM);
        addOperator("{m}", new FixedNumberOfOccurrencesOperator.Factory(),
                    Arrays.<String>asList("", "{", "}"), PRIORITY_MEDIUM);
        addOperator("{m,n}", new RangeNumberOfOccurrencesOperator.Factory(),
                    Arrays.<String>asList("", "{", ",", "}"), PRIORITY_MEDIUM);
        addOperator("|", new AlternativeOperator.Factory(),
                    Arrays.<String>asList("", "|", ""), PRIORITY_LOW);
    }
}
