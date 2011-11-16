package pl.edu.amu.wmi.daut.base;
import java.util.Set;

/**
* Różne pomocnicze funkcje
* związane z automatami deterministycznymi.
*/
public final class DeterministicUtilities {
     /**
     * Tworzy automat deterministyczny, który akceptuje napisy ze zbioru `language`
     * i nie akceptuje żadnych innych napisów. Automat powstaje przez
     * rozbudowanie pustego automatu deterministycznego przekazanego jako argument `automaton`.
     */
    public static void createAutomatonForFiniteLanguage(DeterministicAutomatonSpecification
automaton, Set<String> language) {
    int symbolsCounter = 0;
    for (String s : language) {
        symbolsCounter += s.length();
    }
    State[] q;
    q = new State[symbolsCounter];
    automaton.markAsInitial(q[0]);
    int statesCounter = 0;
    for (String s : language) {
        int activeState = 0;
        for ( ; activeState < s.length(); activeState++) {
            boolean leave = false;
            for (int search = 0; search <= statesCounter; search++) {
                if (automaton.targetState(q[activeState], s.charAt(activeState)) != null) {
                    activeState = search;
                    leave = true;
                    break;
                }
            }
            if (!leave) {
                statesCounter++;
                q[statesCounter] = automaton.addState();
                automaton.addTransition(q[activeState], q[statesCounter],
new CharTransitionLabel(s.charAt(activeState)));
            }
        }
        automaton.markAsFinal(q[statesCounter]);
    }
    }

/**
* Pusty konstruktor klasy DeterministicUtilities
* niebędący konstruktorem domyślnym.
*/
    private DeterministicUtilities() {
    }
}
