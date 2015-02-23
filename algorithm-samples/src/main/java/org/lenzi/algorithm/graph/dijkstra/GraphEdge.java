/**
 * 
 */
package org.lenzi.algorithm.graph.dijkstra;

/**
 * @author sal
 *
 */
public class GraphEdge {

	private Long cost; // the effort to traverse this edge.
	private GraphNode toNode; // the node that this edge leads to.
	
	/**
	 * 
	 */
	public GraphEdge(GraphNode toNode, Long cost) {
		this.cost = cost;
		this.toNode = toNode;
	}
	
	public Long getCost(){
		return this.cost;
	}
	
	public GraphNode getToNode(){
		return this.toNode;
	}

}
