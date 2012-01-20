package pl.edu.amu.wmi.daut.re;

import java.util.List;
import pl.edu.amu.wmi.daut.base.AutomatonSpecification;
import pl.edu.amu.wmi.daut.base.ComplementCharClassTransitionLabel;
import pl.edu.amu.wmi.daut.base.NaiveAutomatonSpecification;
import pl.edu.amu.wmi.daut.base.State;

/**
 *
 * @author jakub
 */
public class NegatedAsciiCharacterClass extends NullaryRegexpOperator {
    private String str;

    /**
     * Konstruktor ASCII character classes.
     */
    public NegatedAsciiCharacterClass(String classString) {
        transformToClassString(classString);
    }


    private void transformToClassString(String a) {
        str = AsciiCharacterClasses.CLASS_MAP.get(a);
        if (str == null)
            throw new UnknownAsciiCharacterClassException();
    }

    /**
     * Generuje automat.
     */
    @Override
    public AutomatonSpecification createFixedAutomaton() {
        AutomatonSpecification automaton = new NaiveAutomatonSpecification();
        State q0 = automaton.addState();
        State q1 = automaton.addState();
        automaton.markAsInitial(q0);
        automaton.markAsFinal(q1);

        automaton.addTransition(q0, q1, new ComplementCharClassTransitionLabel(str));

        return automaton;
    }

    /**
     * Fabryka operatora.
     */
    public static class Factory extends NullaryRegexpOperatorFactory {

        @Override
        protected RegexpOperator doCreateOperator(List<String> params) {
            return new NegatedAsciiCharacterClass(params.get(0));
        }

        @Override
        public int numberOfParams() {
            return 1;
        }
    }

    /**
     * Metoda toString().
     */
    @Override
    public String toString() {
        return "^ASCII";
    }
}
