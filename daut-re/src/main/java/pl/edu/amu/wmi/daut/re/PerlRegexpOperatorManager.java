package pl.edu.amu.wmi.daut.re;


import java.util.Arrays;


class PerlRegexpOperatorManager extends ExtendedPosixRegexpOperatorManager {
    public PerlRegexpOperatorManager() {
        addOperator("\\o", new OctSingleCharacterOperator.Factory(),
                    Arrays.<String>asList("\\o{", "}"), PRIORITY_4);
        addOperator("\\n", new NewLineOperator.Factory(),
                    Arrays.<String>asList("\\n"), PRIORITY_4);
        addOperator("\\a", new BellCharacterOperator.Factory(),
                    Arrays.<String>asList("\\n"), PRIORITY_4);
        addOperator("\\w", new WhitespaceOperator.Factory(),
                    Arrays.<String>asList("\\w"), PRIORITY_4);
        addOperator("\\d", new DigitOperator.Factory(),
                    Arrays.<String>asList("\\d"), PRIORITY_4);
        addOperator("\\D", new NoDigitOperator.Factory(),
                    Arrays.<String>asList("\\D"), PRIORITY_4);
        addOperator("\\x{}", new HexSingleCharacterOperator.Factory(),
                    Arrays.<String>asList("\\x{", "}"), PRIORITY_4);
    }
}

