/**
 * 
 */
package org.lenzi.algorithm.graph.dijkstra;

import java.util.Hashtable;

/**
 * @author sal
 *
 * Implementation of Dijkstra's algorithm.
 */
public class DijkstraImpl {

	Graph graph;
	
	GraphNodePriorityQueue priorityQ = new GraphNodePriorityQueue();
	private Hashtable <GraphNode, Long> distance = new Hashtable<GraphNode, Long>();
	private Hashtable <GraphNode, GraphNode> paths = new Hashtable<GraphNode, GraphNode>();
	
	/**
	 * All paths will be calculated from the graph's root node.
	 */
	public DijkstraImpl(Graph graph) {
		this.graph = graph;
		// 0 effort to get to the start node.
		this.graph.getRootNode().setDistance(new Long(0));
		this.priorityQ.addAllGraphNodes(this.graph.getAccessibleNodes());
	}
	
	/**
	 * Actual algorithm.
	 */
	public void doAlg(){
		Long newPossiblePathCost = 0L;
		GraphNode nextNode, toNode = null;
		while (this.priorityQ.hasMore()){
			nextNode = this.priorityQ.remove();
			//System.out.println("Next node is " + nextNode.getValue());
			for (GraphEdge e: nextNode.getOutgoingEdges()){
				toNode = e.getToNode();
				newPossiblePathCost = e.getCost() + nextNode.getDistance();
				if (newPossiblePathCost < toNode.getDistance()){
					toNode.setDistance(newPossiblePathCost);
					this.priorityQ.reinsertNode(toNode);
					distance.put(toNode, newPossiblePathCost);
					//System.out.println("Found lower cost for node " + toNode.getValue() + 
					//		"(" + newPossiblePathCost + ") from node " + nextNode.getValue());
					paths.put(toNode, nextNode);
				}
			}
		}
	}
	
	/**
	 * Print the distances to all nodes from the root node.
	 */
	public void printDistance(){
		for(GraphNode n : distance.keySet()){
			System.out.println("Shortest distance from " + this.graph.getRootNode().getValue() + 
					" to " + n.getValue() + " is " + distance.get(n));
		}
	}
	
	/**
	 * Print the path from the root node to any other node in the graph.
	 * 
	 * @param toNode
	 */
	public void printPath(GraphNode toNode){
		StringBuffer path = new StringBuffer();
		if(paths.get(toNode) == null){
			System.out.println("No path from " + this.graph.getRootNode().getValue() + 
					" to " + toNode.getValue());
			return;
		}
		path.insert(0, toNode.getValue());
		GraphNode tmpNode = toNode;
		while(!tmpNode.getValue().equals(this.graph.getRootNode().getValue())){
			tmpNode = paths.get(tmpNode);
			path.insert(0, tmpNode.getValue() + " -> ");
		}
		System.out.println("Shortest path from " + this.graph.getRootNode().getValue() + 
				" to " + toNode.getValue() + " is [" + path.toString() + "] at cost " + distance.get(toNode));
	}

}
