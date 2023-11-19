/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Collections;


public abstract class GraphInstanceTest {

    @Test(expected = AssertionError.class)
    public void testAssertionsEnabled() {
        assert false;
    }

    @Test
    public void testInitialVerticesEmpty() {
        assertEquals("Expected new graph to have no vertices",
        Collections.emptySet(), emptyInstance().vertices());
    }

    @Test
    public void testAddVertex() {
        Graph<String> graph = emptyInstance();
        assertTrue("Adding a vertex should return true", graph.add("A"));
        assertEquals("Graph should contain the added vertex", Set.of("A"), graph.vertices());
    }

    @Test
    public void testAddDuplicateVertex() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        assertFalse("Adding a duplicate vertex should return false", graph.add("A"));
        assertEquals("Graph should still contain only one vertex", Set.of("A"), graph.vertices());
    }

    @Test
    public void testRemoveVertex() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        assertTrue("Removing an existing vertex should return true", graph.remove("A"));
        assertEquals("Graph should be empty after removing the only vertex", Collections.emptySet(), graph.vertices());
    }

    @Test
    public void testRemoveNonexistentVertex() {
        Graph<String> graph = emptyInstance();
        assertFalse("Removing a nonexistent vertex should return false", graph.remove("A"));
        assertEquals("Graph should remain empty", Collections.emptySet(), graph.vertices());
    }
}
