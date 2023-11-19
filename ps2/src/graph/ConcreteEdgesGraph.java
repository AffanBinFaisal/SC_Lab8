/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import java.util.ArrayList;
import java.util.HashMap;
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
    //   - The set of vertices represents the nodes in the directed graph.
    //   - The list of edges represents the directed edges in the graph.
    //   - Each edge in the list is a directed connection from its source vertex to its target vertex with a specified weight.
    //   - The absence of a vertex in the set indicates that it is not part of the graph.
    // Representation invariant:
    //   - Vertices in the graph must be non-null and distinct.
    //   - Edges must have valid non-null source and target vertices.
    //   - Edge weights must be non-negative.
    //   - There should be no duplicate edges (same source, target, and weight).
    //   - The graph should not have self-loops (edges where source and target are the same).
    // Safety from rep exposure:
    //   - All fields (vertices and edges) are private and final, preventing direct access or modification by clients.
    //   - Methods only return copies or immutable views of internal data structures to prevent clients from modifying the internal state.
    //   - The class does not provide direct access to the internal set of vertices or list of edges.
    
    // Constructor
    public ConcreteEdgesGraph() {
        // The graph is initially empty, so no specific initialization is needed here.
    }
    
    // Representation invariant
    private void checkRep() {
        // Vertices must be non-null and distinct
        assert vertices.stream().allMatch(vertex -> vertex != null) : "Vertices must be non-null";
        assert vertices.size() == new HashSet<>(vertices).size() : "Vertices must be distinct";

        // Edges must have valid non-null source and target vertices
        for (Edge edge : edges) {
            assert edge.getSource() != null : "Edge source must be non-null";
            assert edge.getTarget() != null : "Edge target must be non-null";
        }

        // Edge weights must be non-negative
        assert edges.stream().allMatch(edge -> edge.getWeight() >= 0) : "Edge weights must be non-negative";

        // No duplicate edges (same source, target, and weight)
        assert edges.size() == new HashSet<>(edges).size() : "Duplicate edges are not allowed";

        // The graph should not have self-loops
        assert edges.stream().noneMatch(edge -> edge.getSource().equals(edge.getTarget())) : "Graph should not have self-loops";
    }
    
    @Override public boolean add(String vertex) {
        if (vertex == null || vertices.contains(vertex)) {
            // Vertex is null or already exists in the graph
            return false;
        }
    
        vertices.add(vertex);
        checkRep(); // Check representation invariant
        return true;
    }
    
    @Override public int set(String source, String target, int weight) {
        if (source == null || target == null || weight < 0) {
            throw new IllegalArgumentException("Invalid arguments");
        }
    
        // Check if the source and target vertices exist in the graph
        if (!vertices.contains(source) || !vertices.contains(target)) {
            throw new IllegalArgumentException("Source or target vertex not in the graph");
        }
    
        // Remove any existing edge with the same source and target
        edges.removeIf(edge -> edge.getSource().equals(source) && edge.getTarget().equals(target));
    
        // Add the new edge
        edges.add(new Edge(source, target, weight));
    
        checkRep(); // Check representation invariant
    
        return weight;
    }
    
    @Override public boolean remove(String vertex) {
        if (vertex == null || !vertices.contains(vertex)) {
            // Vertex is null or does not exist in the graph
            return false;
        }
    
        // Remove edges with the specified vertex as the source or target
        edges.removeIf(edge -> edge.getSource().equals(vertex) || edge.getTarget().equals(vertex));
    
        // Remove the vertex from the set of vertices
        vertices.remove(vertex);
    
        checkRep(); // Check representation invariant
        return true;
    }
    
    @Override public Set<String> vertices() {
        return new HashSet<>(vertices);
    }
    
    @Override public Map<String, Integer> sources(String target) {
        if (target == null || !vertices.contains(target)) {
        throw new IllegalArgumentException("Invalid target vertex");
        }

        Map<String, Integer> sourceMap = new HashMap<>();

        for (Edge edge : edges) {
            if (edge.getTarget().equals(target)) {
                // Edge is directed towards the target vertex
                sourceMap.put(edge.getSource(), edge.getWeight());
            }
        }

        return sourceMap;
    }
    
    @Override public Map<String, Integer> targets(String source) {
        if (source == null || !vertices.contains(source)) {
            throw new IllegalArgumentException("Invalid source vertex");
        }
    
        Map<String, Integer> targetMap = new HashMap<>();
    
        for (Edge edge : edges) {
            if (edge.getSource().equals(source)) {
                // Edge is directed from the source vertex
                targetMap.put(edge.getTarget(), edge.getWeight());
            }
        }
    
        return targetMap;
    }
    
    @Override public String toString() {
        StringBuilder sb = new StringBuilder();
    
        sb.append("Vertices: ").append(vertices).append("\n");
        sb.append("Edges:\n");
    
        for (Edge edge : edges) {
            sb.append(edge).append("\n");
        }
    
        return sb.toString();
    }
    
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
    
    private final String source;
    private final String target;
    private final int weight;
    
    // Abstraction function:
    //   - Represents a directed edge in the graph from the source vertex to the target vertex.
    //   - The weight represents the weight of the directed edge.
    // Representation invariant:
    //   - Source and target vertices must be non-null and distinct.
    //   - The weight of the edge must be non-negative.
    // Safety from rep exposure:
    //   - Fields (source, target, weight) are private and final, preventing direct access or modification by clients.
    //   - Methods only provide read access or copies of internal data, preventing clients from modifying the internal state.

    
    

    // Constructor
    public Edge(String source, String target, int weight) {
        if (source == null || target == null || weight < 0) {
            throw new IllegalArgumentException("Invalid arguments for Edge");
        }
        this.source = source;
        this.target = target;
        this.weight = weight;
        checkRep(); // Check representation invariant
    }

    // Getter methods
    public String getSource() {
        return source;
    }

    public String getTarget() {
        return target;
    }

    public int getWeight() {
        return weight;
    }

    // Representation invariant
    private void checkRep() {
        assert source != null : "Source must be non-null";
        assert target != null : "Target must be non-null";
        assert weight >= 0 : "Weight must be non-negative";
    }

    // toString method
    @Override
    public String toString() {
        return String.format("Edge: %s -> %s (Weight: %d)", source, target, weight);
    }
    
}
