/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * An implementation of Graph.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteEdgesGraph implements Graph<String> {
    
    private final Set<String> vertices = new HashSet<>();
    private final List<Edge> edges = new ArrayList<>();
    
    // Abstraction function:
    //   TODO
    // Representation invariant:
    //   TODO
    // Safety from rep exposure:
    //   TODO
    
    // TODO constructor
    
    // TODO checkRep
    
    /**
     * @param <vertex> must not be null and must be immutable
     * @return true if the vertex is added, false if the vertex already exists
     */
    @Override public boolean add(String vertex) {
        throw new RuntimeException("not implemented");
    }
    
    /**
     * @param <String> source and target, must not be null and must be immutable
     * weight must be a non negative integar
     * @return previous weight of the edge, 0 if there was noprevious edge
     */
    @Override public int set(String source, String target, int weight) {
        throw new RuntimeException("not implemented");
    }
    
    /**
     * @param <vertex> must not be null and must be immutable
     * @return true if the vertex is removed, false if there was no such vertex
     */
    @Override public boolean remove(String vertex) {
        throw new RuntimeException("not implemented");
    }
    
    /**
     * @param nothing
     * @return all the vertices of the graph in the form of a set
     */
    @Override public Set<String> vertices() {
        throw new RuntimeException("not implemented");
    }
    
    /**
     * @param <target> must not be null and must be immutable
     * @return a map containing all the source vertices and their corresponding weights having edges directed towards the target 
     */
    @Override public Map<String, Integer> sources(String target) {
        throw new RuntimeException("not implemented");
    }
    
    /**
     * @param <source> must not be null and must be immutable
     * @return a map containing all the target vertices and their corresponding weights having edges directed from the source
     */
    @Override public Map<String, Integer> targets(String source) {
        throw new RuntimeException("not implemented");
    }
    
    // TODO toString()
    /**
     * @param nothing
     * @return a string representation of the graph showing vertices and edges
     */
    
}

/**
 * TODO specification
 * Immutable.
 * This class is internal to the rep of ConcreteEdgesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Edge {
    
    // TODO fields
    
    // Abstraction function:
    //   TODO
    // Representation invariant:
    //   TODO
    // Safety from rep exposure:
    //   TODO
    
    // TODO constructor
    /**
     * @param <String> source and target must not be null, and must be immutable
     * @return creates a new edge between the given vertices having the given weight
     */

    // TODO checkRep
    
    // TODO methods
    
    // TODO toString()
     /**
     * @param nothing
     * @return a string representation of the edge showing source and target vertex and the wetght
     */
}
