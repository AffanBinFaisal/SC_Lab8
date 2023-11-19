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
public class ConcreteVerticesGraph implements Graph<String> {
    
    private final List<Vertex> vertices = new ArrayList<>();
    
    // Abstraction function:
    //   Represents a graph with vertices and directed edges between them.
    //   Each vertex in the graph is represented by an instance of the Vertex class.
    // Representation invariant:
    //   All vertices in the graph are distinct.
    // Safety from rep exposure:
    //   The 'vertices' field is private and final, and it is not directly exposed outside the class.

    
    public ConcreteVerticesGraph() {
        // Nothing specific to initialize for the graph at the moment
    }
    
    // Check representation invariant
    private void checkRep() {
        Set<String> uniqueVertices = new HashSet<>();
        for (Vertex vertex : vertices) {
            if (uniqueVertices.contains(vertex.getLabel())) {
                throw new RuntimeException("Representation invariant violation: Duplicate vertices found.");
            }
            uniqueVertices.add(vertex.getLabel());
        }
    }
    
    @Override public boolean add(String vertexLabel) {
        // Check if the vertex with the given label already exists
        for (Vertex existingVertex : vertices) {
            if (existingVertex.getLabel().equals(vertexLabel)) {
                return false; // Vertex with the same label already exists
            }
        }

        // If the vertex doesn't exist, add it to the graph
        Vertex newVertex = new Vertex(vertexLabel);
        vertices.add(newVertex);

        // Check and ensure the representation invariant
        checkRep();

        return true;
    }

    @Override public int set(String sourceLabel, String targetLabel, int weight) {
        // Find the source and target vertices
        Vertex sourceVertex = null;
        Vertex targetVertex = null;

        for (Vertex vertex : vertices) {
            if (vertex.getLabel().equals(sourceLabel)) {
                sourceVertex = vertex;
            } else if (vertex.getLabel().equals(targetLabel)) {
                targetVertex = vertex;
            }

            // If both source and target vertices are found, exit the loop
            if (sourceVertex != null && targetVertex != null) {
                break;
            }
        }

        // If either source or target vertex is not found, return -1 to indicate failure
        if (sourceVertex == null || targetVertex == null) {
            return -1;
        }

        // Update the weight of the edge between source and target vertices
        int previousWeight = sourceVertex.getOutboundWeight(targetVertex);
        sourceVertex.setOutboundWeight(targetVertex, weight);

        // Check and ensure the representation invariant
        checkRep();

        return previousWeight;
    }
    
    @Override public boolean remove(String vertexLabel) {
        // Find the vertex to be removed
        Vertex vertexToRemove = null;

        for (Vertex vertex : vertices) {
            if (vertex.getLabel().equals(vertexLabel)) {
                vertexToRemove = vertex;
                break;
            }
        }

        // If the vertex is not found, return false to indicate failure
        if (vertexToRemove == null) {
            return false;
        }

        // Remove the vertex and its associated edges
        vertices.remove(vertexToRemove);

        // Remove incoming edges to the vertex
        for (Vertex otherVertex : vertices) {
            otherVertex.removeOutboundEdge(vertexToRemove);
        }

        // Check and ensure the representation invariant
        checkRep();

        return true;
    }
    
    @Override public Set<String> vertices() {
        Set<String> vertexLabels = new HashSet<>();

        for (Vertex vertex : vertices) {
            vertexLabels.add(vertex.getLabel());
        }

        return vertexLabels;
    }
    
    @Override public Map<String, Integer> sources(String targetLabel) {
        Map<String, Integer> sourceVertices = new HashMap<>();
    
        for (Vertex sourceVertex : vertices) {
            int weight = sourceVertex.getInboundWeight(targetLabel);
            if (weight >= 0) {
                sourceVertices.put(sourceVertex.getLabel(), weight);
            }
        }
    
        return sourceVertices;
    }
    
    @Override public Map<String, Integer> targets(String sourceLabel) {
        Map<String, Integer> targetVertices = new HashMap<>();

        // Find the source vertex
        Vertex sourceVertex = null;
        for (Vertex vertex : vertices) {
            if (vertex.getLabel().equals(sourceLabel)) {
                sourceVertex = vertex;
                break;
            }
        }

        // If the source vertex is not found, return an empty map
        if (sourceVertex == null) {
            return targetVertices;
        }

        // Get outgoing edges from the source vertex
        for (Map.Entry<String, Integer> entry : sourceVertex.getOutboundEdges().entrySet()) {
            targetVertices.put(entry.getKey(), entry.getValue());
        }

        return targetVertices;
    }
    
    @Override public String toString() {
        StringBuilder result = new StringBuilder("Graph:\n");

        for (Vertex vertex : vertices) {
            result.append("Vertex: ").append(vertex.getLabel()).append("\n");

            Map<String, Integer> outboundEdges = vertex.getOutboundEdges();
            if (!outboundEdges.isEmpty()) {
                result.append("  Outbound Edges: ").append(outboundEdges).append("\n");
            }
        }

        return result.toString();
    }
    
}

/**
 * TODO specification
 * Mutable.
 * This class is internal to the rep of ConcreteVerticesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Vertex {
    
    private final String label;
    private final Map<String, Integer> outboundEdges = new HashMap<>();
    private final Map<String, Integer> inboundEdges = new HashMap<>();
    
    // Abstraction function:
    //   Represents a vertex in a graph with a label and directed edges.
    //   'outboundEdges' represents outgoing edges from this vertex.
    //   'inboundEdges' represents incoming edges to this vertex.
    // Representation invariant:
    //   'label' is not null and is unique among all vertices in the graph.
    //   'outboundEdges' and 'inboundEdges' do not contain null keys.
    // Safety from rep exposure:
    //   The 'label', 'outboundEdges', and 'inboundEdges' fields are private and final.
    //   Accessors are provided to get information safely.
    
    // Constructor
    public Vertex(String label) {
        if (label == null) {
            throw new IllegalArgumentException("Vertex label cannot be null");
        }
        this.label = label;
    }

    public String getLabel(){
        return this.label;
 
    }

    // Check representation invariant
    private void checkRep() {
        if (label == null) {
            throw new RuntimeException("Representation invariant violation: Vertex label is null");
        }
        if (containsNullKey(outboundEdges)) {
            throw new RuntimeException("Representation invariant violation: Outbound edges contain null key");
        }
        if (containsNullKey(inboundEdges)) {
            throw new RuntimeException("Representation invariant violation: Inbound edges contain null key");
        }
    }

    private boolean containsNullKey(Map<String, Integer> edges) {
        return edges.containsKey(null);
    }
    
    // Add an outgoing edge to the vertex
    public void addOutboundEdge(String targetLabel, int weight) {
        outboundEdges.put(targetLabel, weight);
        // Check and ensure the representation invariant
        checkRep();
    }

    // Remove an outgoing edge from the vertex
    public void removeOutboundEdge(Vertex targetVertex) {
        outboundEdges.remove(targetVertex.getLabel());
        // Check and ensure the representation invariant
        checkRep();
    }

    // Get the weight of an outgoing edge to a target vertex
    public int getOutboundWeight(Vertex targetVertex) {
        return outboundEdges.getOrDefault(targetVertex.getLabel(), -1);
    }

    // Set the weight of an outgoing edge to a target vertex
    public void setOutboundWeight(Vertex targetVertex, int weight) {
        outboundEdges.put(targetVertex.getLabel(), weight);
        // Check and ensure the representation invariant
        checkRep();
    }

    // Get the weight of an incoming edge from a source vertex
    public int getInboundWeight(String sourceLabel) {
        return inboundEdges.getOrDefault(sourceLabel, -1);
    }

    // Set the weight of an incoming edge from a source vertex
    public void setInboundWeight(String sourceLabel, int weight) {
        inboundEdges.put(sourceLabel, weight);
        // Check and ensure the representation invariant
        checkRep();
    }

    // Get a copy of the outbound edges
    public Map<String, Integer> getOutboundEdges() {
        return new HashMap<>(outboundEdges);
    }
    
    
    @Override public String toString() {
        StringBuilder result = new StringBuilder("Vertex: " + label + "\n");

        if (!outboundEdges.isEmpty()) {
            result.append("  Outbound Edges: ").append(outboundEdges).append("\n");
        }

        return result.toString();
    }
    
}
