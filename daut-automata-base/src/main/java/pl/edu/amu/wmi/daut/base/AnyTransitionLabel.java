package pl.edu.amu.wmi.daut.base;

/*
 * @author cole1911
 */

/*
* Implementacja Transition Label reprezentujaca
* przejscie po dowolnym znaku.
*/
class AnyTransitionLabel extends TransitionLabel {

    @Override
    public boolean canBeEpsilon() {
        return false;
    }

    @Override
    public boolean canAcceptCharacter(char c) {
        return true;
    }

    @Override
    public boolean isEmpty() {
         return false;
    }

    public String toString() {
         return "ANY";
    }

    @Override
    protected TransitionLabel intersectWith(TransitionLabel label) {
         return label.isEmpty() ? new EmptyTransitionLabel() : this;
    }
}
