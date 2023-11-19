/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Map;

public class ConcreteEdgesGraphTest extends GraphInstanceTest {

    // Provide a ConcreteEdgesGraph for tests in GraphInstanceTest.
    @Override
    public Graph<String> emptyInstance() {
        return new ConcreteEdgesGraph();
    }

    // Testing ConcreteEdgesGraph.toString()
    @Test
    public void testToStringEmptyGraph() {
        Graph<String> graph = emptyInstance();
        assertEquals("Graph should be empty", "Vertices: [], Edges: []", graph.toString());
    }

    @Test
    public void testToStringNonEmptyGraph() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        graph.set("A", "B", 5);
        assertEquals("Graph should have vertices and edges", "Vertices: [A, B], Edges: [(A -> B, 5)]", graph.toString());
    }

}
