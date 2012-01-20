package pl.edu.amu.wmi.daut.base;

import junit.framework.TestCase;

/**
 * Test klasy AlwaysAcceptingContextChecker.
 */
public class TestAlwaysAcceptingContextChecker extends TestCase {

    /**
     * Prosty test klasy AlwaysAcceptingContextChecker.
     */
    public final void testAlwaysAcceptingContextChecker() {
        AlwaysAcceptingContextChecker contextChecker = new AlwaysAcceptingContextChecker();

        assertTrue(contextChecker.check(new AnyTransitionLabel()));
    }

}

