/**
 * 
 */
package org.lenzi.algorithm.graph.dijkstra;

import java.util.Collection;
import java.util.PriorityQueue;

/**
 * @author sal
 *
 */
public class GraphNodePriorityQueue {

	// nodes will be ordered by their distance from the root node.
	private PriorityQueue<GraphNode> pQueue = new PriorityQueue<GraphNode>();
	
	/**
	 * 
	 */
	public GraphNodePriorityQueue() {}

	public void addGraphNode(GraphNode n){
		pQueue.add(n);
	}
	
	public void addAllGraphNodes(Collection<GraphNode> nodes){
		pQueue.addAll(nodes);
	}
	
	public Boolean hasMore(){
		return !this.pQueue.isEmpty();
	}
	
	public GraphNode remove(){
		return this.pQueue.remove();
	}
	
	public int size(){
		return pQueue.size();
	}
	
	/**
	 * Removes desired graph node, then inserts into appropriate slot.
	 * 
	 * @param n
	 */
	public void reinsertNode(GraphNode n){
		this.pQueue.remove(n);
		this.pQueue.add(n);
	}
	
	public void printQueue(){
		System.out.println("Queue size = " + pQueue.size());
		while (!pQueue.isEmpty()){
			GraphNode n = pQueue.remove();
			System.out.println(n.getValue() + " distance=" + n.getDistance());
		}		
	}
}
