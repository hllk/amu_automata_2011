package pl.edu.amu.wmi.daut.re;

import java.util.Arrays;

/**
 * Zarzadca tych operatorow, ktore sa wspolne
 * dla podstawowych i rozszerzonych wyrazen
 * regularnych POSIX.
 */
class PosixRegexpOperatorManager extends RegexpOperatorManager {

    public static final int PRIORITY_HIGH = 4;
    public static final int PRIORITY_MEDIUM = 3;
    public static final int PRIORITY_LOW = 1;

    PosixRegexpOperatorManager() {
        addOperator("*", new KleeneStarOperator.Factory(),
                    Arrays.<String>asList("", "*"), PRIORITY_MEDIUM);
        addOperator(".", new AnyCharOperator.Factory(),
                    Arrays.<String>asList("."), PRIORITY_HIGH);
    }
}
