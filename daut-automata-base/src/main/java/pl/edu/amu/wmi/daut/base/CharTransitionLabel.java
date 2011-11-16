package pl.edu.amu.wmi.daut.base;
/**
 * Implementacja TransitionLabel reprezentujaca przejscie po podanym znaku .
 */
class CharTransitionLabel extends TransitionLabel {
    /**
    * Konstruuje etykietę oznaczoną znakiem 'c'.
    */
    public CharTransitionLabel(char c) {
        ch = c;
    }

    public boolean canBeEpsilon() {
        return false;
    }

    public boolean canAcceptCharacter(char c) {
        return c == ch;
    }

    public boolean isEmpty() {
        return false;
    }

    public char getChar() {
        return ch;
    }

    @Override
    public String toString() {
        return Character.toString(ch);
    }

    protected TransitionLabel intersectWith(TransitionLabel label) {
        return label.canAcceptCharacter(ch) ? this : new EmptyTransitionLabel();
    }

    private char ch;
}
