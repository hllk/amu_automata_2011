package pl.edu.amu.wmi.daut.re;

import pl.edu.amu.wmi.daut.base.AutomatonSpecification;
import pl.edu.amu.wmi.daut.base.NaiveAutomatonSpecification;
import pl.edu.amu.wmi.daut.base.EpsilonTransitionLabel;
import pl.edu.amu.wmi.daut.base.AutomataOperations;
import pl.edu.amu.wmi.daut.base.State;
import java.util.List;

/**
 * Klasa reprezentujaca operator oznaczajacy, ze dwa elementy moga wystapic jeden po drugim
 * w dowolnej kolejnosci.
 */
public class AnyOrderOperator extends BinaryRegexpOperator {

    @Override
    public AutomatonSpecification createAutomatonFromTwoAutomata(
            AutomatonSpecification leftSubautomaton, AutomatonSpecification rightSubautomaton) {

        AutomatonSpecification automatonLR = new NaiveAutomatonSpecification();
        AutomatonSpecification automatonRL = new NaiveAutomatonSpecification();

        concatenate(leftSubautomaton, rightSubautomaton, automatonLR);
        concatenate(rightSubautomaton, leftSubautomaton, automatonRL);

        return AutomataOperations.sum(automatonLR, automatonRL);
    }

    /**
     * @param first
     * Automat poczatkowy, ktory rozpoczyna wyjsciowy automat.
     * @param second
     * Automat ktory jest 'doklejany' do stanow koncowych poczatkowego automatu.
     * @param automaton
     * Pusty automat ktory ma byc konkatenacja pierwszych 2 automatow.
     */
    private void concatenate(AutomatonSpecification first,
            AutomatonSpecification second, AutomatonSpecification automaton) {

        State initialStateL = automaton.addState();
        State finalStateL = automaton.addState();
        automaton.markAsInitial(initialStateL);
        automaton.insert(initialStateL, first);
        for (State q : automaton.allStates()) {
            if (automaton.isFinal(q)) {
               automaton.addTransition(q, finalStateL, new EpsilonTransitionLabel());
               automaton.unmarkAsFinalState(q);
            }
        }
        automaton.insert(finalStateL, second);
    }
     /**
      * Fabryka operatora.
      */
    public static class Factory extends BinaryRegexpOperatorFactory {

        @Override
        public int numberOfParams() {
            return 0;
        }

        protected RegexpOperator doCreateOperator(List<String> params) {
            return new AnyOrderOperator();
        }
    }

    /**
     * Metoda toString().
     */
    @Override
    public String toString() {
        return "ANY_ORDER";
    }

}
