package pl.edu.amu.wmi.daut.re;

import pl.edu.amu.wmi.daut.base.AutomatonSpecification;
import pl.edu.amu.wmi.daut.base.NaiveAutomatonSpecification;
import pl.edu.amu.wmi.daut.base.EpsilonTransitionLabel;
import pl.edu.amu.wmi.daut.base.State;
import java.util.List;

/**
* Klasa reprezentującą operator '{n}' z wyrażeń regularnych.
*/
public class FixedNumberOfOccurrencesOperator extends UnaryRegexpOperator {

    private int numberOfOccurrences;

    /**
     * Konstruktor klasy.
     */
    public FixedNumberOfOccurrencesOperator(int a) {
        numberOfOccurrences = a;
    }

    /**
     * Główna metoda klasy.
     */
    public AutomatonSpecification createAutomatonFromOneAutomaton(
            AutomatonSpecification subautomaton) {

        AutomatonSpecification automatbudowany = new NaiveAutomatonSpecification();

        if (numberOfOccurrences == 0) {
            State state = automatbudowany.addState();
            automatbudowany.markAsInitial(state);
            automatbudowany.markAsFinal(state);
        }

        if (numberOfOccurrences > 0) {
            automatbudowany = subautomaton.clone();
            for (int i = 1; i < numberOfOccurrences; i++) {
                State newState = automatbudowany.addState();

                for (State state : automatbudowany.allStates()) {
                    if (automatbudowany.isFinal(state)) {
                        automatbudowany.addTransition(state,
                                    newState,
                                    new EpsilonTransitionLabel());
                        automatbudowany.unmarkAsFinalState(state);
                    }
                }
                automatbudowany.insert(newState, subautomaton);
            }
        }
        return automatbudowany;
    }

     /**
     * Fabryka operatora.
     */
    public static class Factory extends UnaryRegexpOperatorFactory {

        @Override
        public int numberOfParams() {
            return 1;
        }

        protected RegexpOperator doCreateOperator(List<String> params) {
            return new FixedNumberOfOccurrencesOperator(Integer.parseInt(params.get(0)));
        }
    }

    /**
     * Metoda toString().
     */
    @Override
    public String toString() {
        return "FIXED_" + numberOfOccurrences + "_TIMES";
    }

}
