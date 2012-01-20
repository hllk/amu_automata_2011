package pl.edu.amu.wmi.daut.re;


import java.util.Arrays;


class PerlRegexpOperatorManager extends ExtendedPosixRegexpOperatorManager {
    public PerlRegexpOperatorManager() {
        addOperator("\\o", new OctSingleCharacterOperator.Factory(),
                    Arrays.<String>asList("\\o{", "}"), PRIORITY_HIGH);
        addOperator("\\n", new NewLineOperator.Factory(),
                    Arrays.<String>asList("\\n"), PRIORITY_HIGH);
        addOperator("\\a", new BellCharacterOperator.Factory(),
                    Arrays.<String>asList("\\n"), PRIORITY_HIGH);
        addOperator("\\w", new WhitespaceOperator.Factory(),
                    Arrays.<String>asList("\\w"), PRIORITY_HIGH);
        addOperator("\\d", new DigitOperator.Factory(),
                    Arrays.<String>asList("\\d"), PRIORITY_HIGH);
        addOperator("\\D", new NoDigitOperator.Factory(),
                    Arrays.<String>asList("\\D"), PRIORITY_HIGH);
        addOperator("\\x{}", new HexSingleCharacterOperator.Factory(),
                    Arrays.<String>asList("\\x{", "}"), PRIORITY_HIGH);
    }
}

