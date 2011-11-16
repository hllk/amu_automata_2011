package pl.edu.amu.wmi.daut.base;

import java.util.Iterator;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

/**
* Klasa implementująca interfejs: java.util.Iterator
* Iterator umożliwia iterowanie po kolekcjach.
* Iteratory tworzone są metodą iterator() odpowiedniej klasy kolekcji.
*/

public class AllAcceptedWords implements Iterator<String> {
  private AutomatonSpecification automat;

  private static final class StateWord {

    private State state;
    private String word;
    private StateWord(State s, String w) {

      state = s;
      word = w;
    }
  };

  private Set<StateWord> statesVisited;
  private Stack<StateWord> stack;
  private String nextWord;

  AllAcceptedWords(AutomatonSpecification a) {

    automat = a;
    nextWord = null;
    stack = new Stack<StateWord>();
    statesVisited = new HashSet<StateWord>();
    stack.push(new StateWord(automat.getInitialState(), ""));
    prepareNextWord(true);
  }

  private void prepareNextWord(boolean forced) {

    if (nextWord == null && !forced)
      return;

    while (!stack.empty()) {

      boolean found = false;
      StateWord sw = stack.peek();
      if (automat.isFinal(sw.state)) {

        found = true;
        nextWord = sw.word;
      }

      List<StateWord> statesToAdd = new ArrayList<StateWord>();
      for (OutgoingTransition ot : automat.allOutgoingTransitions(sw.state)) {

        String newWord = sw.word;
        TransitionLabel tl = ot.getTransitionLabel();

        if (tl instanceof EpsilonTransitionLabel)
          newWord += "";
        else if (tl instanceof ComplementCharClassTransitionLabel)
            newWord += "{cokolwiek poza " + ((ComplementCharClassTransitionLabel) tl)
                    .getSet().toString() + "}";
        else if (tl instanceof CharTransitionLabel)
            newWord += ((CharTransitionLabel) tl).toString();
        else if (tl instanceof EmptyTransitionLabel)
              throw new UnsupportedOperationException("Not supported yet.");

        StateWord newSW = new StateWord(ot.getTargetState(), newWord);
        if (statesVisited.contains(newSW)) //to do - pod lupę: sprawdzaj po
                                // zawartości atrybutów, a nie po zgodności referencji
          continue;

        statesVisited.add(newSW);
        statesToAdd.add(newSW);
      }

      stack.pop();
      stack.addAll(statesToAdd);

      if (found)
        return;
    }
    nextWord = null;
  }

  @Override
  /**
  * Do sprawdzenia, czy odwiedzono wszystkie elementy kolekcji stosuje się metodę hasNext()
  */
  public boolean hasNext() {
    return nextWord != null;
  }

  @Override
  /**
  * Metoda next() przesuwa iterator i zwraca wartość, na którą wskazuje iterator.
  * Zaraz po utworzeniu iterator wskazuje na specjalną wartość przed pierwszym elementem,
  * tak by pierwszy element był pobrany przy pierwszym wywołaniu next().
  * 
  * Każde wywołanie metody `next` zwraca/podaje kolejne napisy akceptowane przez automat.
  */
  public String next() {
    String wordToReturn = nextWord;
    prepareNextWord(false);
    return wordToReturn;
  }

  @Override
  /**
  * Dla kolekcji, które obsługują tę funkcjonalność,
  * ostatnio odwiedzony element można usunąć z kolekcji metodą remove() iteratora.
  */
  public void remove() {
     throw new UnsupportedOperationException("Not supported yet.");
  }

}
