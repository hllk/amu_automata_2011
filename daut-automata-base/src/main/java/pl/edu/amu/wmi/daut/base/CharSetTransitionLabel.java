package pl.edu.amu.wmi.daut.base;

import java.util.HashSet;

/**
 * Implementacja TransitionLabel reprezentujaca przejscie po dowolnym znaku danego zbioru .
 */
class CharSetTransitionLabel extends TransitionLabel {
    /**
    * Konstruuje etykietę oznaczoną zbiorem znakow 'charSet'.
    */
    public CharSetTransitionLabel(HashSet<Character> charSet) {
        this.charSet = charSet;
    }

    public boolean canBeEpsilon() {
        return false;
    }

    public boolean canAcceptCharacter(char c) {
        return charSet.contains(c);
    }

    public boolean isEmpty() {
        return false;
    }

    public HashSet<Character> getCharSet() {
        return charSet;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (Character c : charSet) {
            sb.append(c + ",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("}");
        return sb.toString();
    }

    protected TransitionLabel intersectWith(TransitionLabel label) {
        HashSet<Character> newCharSet = new HashSet<Character>();
        for (Character c : charSet) {
            if (label.canAcceptCharacter(c))
                newCharSet.add(c);
        }
        return newCharSet.isEmpty()
                ? new EmptyTransitionLabel() : new CharSetTransitionLabel(newCharSet);
    }

    private HashSet<Character> charSet;
}
