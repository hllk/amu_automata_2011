package pl.edu.amu.wmi.daut.re;

import java.util.List;
import pl.edu.amu.wmi.daut.base.AutomatonSpecification;
import pl.edu.amu.wmi.daut.base.NaiveAutomatonSpecification;

/**
*
* Obsługa wyjątków.
*/
class InvalidEscapeOperatorException extends RuntimeException {
    public InvalidEscapeOperatorException(String message) {
        super(message);
    }
}

/**
*
* Klasa EscapeOperator reprezentująca znak poprzedzony znakiem ucieczki
* (w wyrażeniach regularnych POSIX - odwrócony ukośnik).
*/
public class EscapeOperator extends NullaryRegexpOperator {

    private char znak;

    @Override
    public AutomatonSpecification createFixedAutomaton() {
        return new NaiveAutomatonSpecification().makeOneTransitionAutomaton(tlumacz());
    }
    /**
* Konstruktor klasy.
* @param a znak poprzedzony symbolem ucieczki
*/
    public EscapeOperator(char a) {
        this.setChar(a);

    }

    private void setChar(char b) {
        this.znak = b;
    }

    private char tlumacz() {
        switch (znak) {
            case 'n':
                return ('\n');
            case 't':
                return ('\t');
            case 'a':
                return ('\7');
            case 'f':
                return ('\f');
            case 'r':
                return ('\r');
            case 'v':
                return ('\13');
            default:
                return (znak);

        }
    }
    /**
* Fabryka operatora.
*/
    public static class Factory extends NullaryRegexpOperatorFactory {

        @Override
        public int numberOfParams() {
            return 1;
        }

        @Override
        protected RegexpOperator doCreateOperator(List<String> params) {
            if (params.get(0).length() == 1)
                return new EscapeOperator(params.get(0).charAt(0));
            else
                throw new InvalidEscapeOperatorException("Argument was "
                        + "supposed to be a char, not a string");
        }
    }
}

