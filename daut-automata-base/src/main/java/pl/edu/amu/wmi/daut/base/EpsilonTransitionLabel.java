package pl.edu.amu.wmi.daut.base;

/**
 * Klasa epsilon przejscia.
 */
public class EpsilonTransitionLabel extends TransitionLabel {

    /**
     * Konstruktor domyslny.
     */
    public EpsilonTransitionLabel() { };

    @Override
    public boolean canBeEpsilon() {
        return true;
    };

    @Override
    public boolean canAcceptCharacter(char c) {
        return false;
    };

    @Override
    public boolean isEmpty() {
        return false;
    };

    @Override
    public String toString() {
        return "epsilon";
    }

    @Override
    protected TransitionLabel intersectWith(TransitionLabel label) {
        if (label.canBeEpsilon()) {
            return this;
        } else {
            return new EmptyTransitionLabel();
        }
    };
}
