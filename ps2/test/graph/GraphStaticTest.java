/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;
import java.util.Collections;
import org.junit.Test;

public class GraphStaticTest {

    @Test(expected = AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }

    @Test
    public void testEmptyVerticesEmpty() {
        assertEquals("Expected empty() graph to have no vertices",
                Collections.emptySet(), Graph.empty().vertices());
    }

    // Additional test cases for different vertex label types
    @Test
    public void testEmptyVerticesEmptyWithTypeInteger() {
        assertEquals("Expected empty() graph with Integer labels to have no vertices",
                Collections.emptySet(), Graph.<Integer>empty().vertices());
    }

    @Test
    public void testEmptyVerticesEmptyWithTypeCharacter() {
        assertEquals("Expected empty() graph with Character labels to have no vertices",
                Collections.emptySet(), Graph.<Character>empty().vertices());
    }

    // Add more test cases for different vertex label types as needed

}
