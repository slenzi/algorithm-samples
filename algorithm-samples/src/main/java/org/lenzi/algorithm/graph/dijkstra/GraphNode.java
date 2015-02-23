/**
 * 
 */
package org.lenzi.algorithm.graph.dijkstra;

import java.util.ArrayList;

/**
 * @author sal
 *
 */
public class GraphNode implements Comparable<GraphNode> {
	
	// A sequence used to create unique IDs for each node in the graph.
	private static long nodeCount = 0;
	
	// The children nodes of this node are all the "toNodes" for all the outgoing edges.
	private ArrayList<GraphEdge> outgoingEdges = new ArrayList<GraphEdge>();
	
	private Long ID;
	private String value;
	private boolean visited;
	
	// Initial distance to this node from the root node is infinity. We don't know
	// the real distance to this node until we use dijkstra's algorithm.
	private Long distance = GraphConstants.INFINITY;
	
	public GraphNode(){
		init("");
	}
	
	public GraphNode(String value) {
		init(value);
	}
	
	private void init(String value){
		this.visited = false;
		this.value = value;
		this.ID = GraphNode.nodeCount++;
	}
	
	public String getValue(){
		return this.value;
	}
	
	public void setVisited(boolean visited){
		this.visited = visited;
	}
	
	public boolean isVisited(){
		return this.visited;
	}
	
	public void setDistance(Long distance){
		this.distance = distance;
	}
	
	public Long getDistance(){
		return this.distance;
	}
	
	public boolean hasOutgoingEdges(){
		return this.outgoingEdges.size() > 0;
	}
	
	/**
	 * Add a child node to this node and specify the edge cost to
	 * get to the child node.
	 * 
	 * @param toNode - A child node of this node.
	 * @param cost - The edge cost to get to the child node.
	 */
	public void addOutgoingEdge(GraphNode toNode, Long cost){
		this.outgoingEdges.add(new GraphEdge(toNode,cost));
	}
	
	/**
	 * Get all out going edges from this node. Each outgoing edge
	 * has a destination node. The destination nodes are the child
	 * nodes of this node.
	 * 
	 * @return
	 */
	public ArrayList<GraphEdge> getOutgoingEdges(){
		return this.outgoingEdges;
	}
	
	@Override
	public int compareTo(GraphNode o){
		return this.distance.compareTo(o.getDistance());
	}

}