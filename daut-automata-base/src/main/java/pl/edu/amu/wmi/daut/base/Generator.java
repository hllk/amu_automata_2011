package pl.edu.amu.wmi.daut.base;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa zwracająca akceptowane słowa.
 */
public class Generator {

    private List<String> acceptedWords = new ArrayList<String>();
    private TransitionLabel currentLabel;
    /**
     * Metoda zwracająca listę wszystkich słów akceptowanych przez automat bez cykli.
     * Pobiera wszystkie przejscia z bieżącego stanu,
     * jeżeli takie istnieją to każdą po kolei etykietę porównuje z zadanym alfabetem
     * Jeżeli etykieta akceptuje znak z alfabetu,  obecny stan nie jest stanem akceptującym
     * dodaje do słowa symbol a poprzednie słowo usuwa, w przeciwnym wypadku tylko
     * dodaje symbol. Jeżeli z danego stanu nie wychodzą żadne etykiety a stan ten nie jest
     * akceptującu to napis jest usuwany
     */
    void acceptWords(AutomatonSpecification automaton, String alphabet, String word, State state) {
        int i = 0;
        StringBuffer buf = new StringBuffer(word);
        List<OutgoingTransition> allOutTransitions;
        allOutTransitions = automaton.allOutgoingTransitions(state);
            if (!allOutTransitions.isEmpty()) {
                for (OutgoingTransition transition : allOutTransitions) {
                    currentLabel = transition.getTransitionLabel();
                    for (i = 0; i < alphabet.length(); i++) {
                        if (currentLabel.canAcceptCharacter(alphabet.charAt(i))) {
                            if (!automaton.isFinal(state) && automaton.getInitialState() != state) {
                                 acceptedWords.remove(word);
                            }
                            buf.append(alphabet.charAt(i));
                            word = buf.toString();
                            acceptedWords.add(word);
                            acceptWords(automaton, alphabet, word, transition.getTargetState());
                        } else {
                                if (!automaton.isFinal(state)) {
                                    acceptedWords.remove(word);
                                }
                        }
                    }
                }
            } else {
                    if (!automaton.isFinal(state)) {
                        acceptedWords.remove(word);
                    }
            }
    }
    /**
     * Metoda zwracająca listę słów akceptowanych przez automat bez cykli.
     *
     */
    public List wordsFromAutomatonWithoutCycles(AutomatonSpecification automaton, String alphabet) {
        String word = "";
        acceptWords(automaton, alphabet, word, automaton.getInitialState());
        return acceptedWords;
    }
}
