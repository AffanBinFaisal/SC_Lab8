/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Map;

public class ConcreteVerticesGraphTest extends GraphInstanceTest {

    // Provide a ConcreteVerticesGraph for tests in GraphInstanceTest.
    @Override
    public Graph<String> emptyInstance() {
        return new ConcreteVerticesGraph();
    }

    // Testing ConcreteVerticesGraph.toString()
    @Test
    public void testToStringEmptyGraph() {
        Graph<String> graph = emptyInstance();
        assertEquals("Graph should be empty", "Vertices: []", graph.toString());
    }

    @Test
    public void testToStringNonEmptyGraph() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        assertEquals("Graph should have vertices", "Vertices: [A, B]", graph.toString());
    }

    // Example test for the Vertex class
    @Test
    public void testVertexToString() {
        Vertex vertex = new Vertex("A");
        assertEquals("Vertex toString should return the vertex label", "A", vertex.toString());
    }

}
