/**
 * 
 */
package org.lenzi.algorithm.graph.dijkstra;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author sal
 *
 */
public class Graph {

	private GraphNode rootNode;
	
	// all accessible nodes from the root node
	private ArrayList<GraphNode> accessibleNode = new ArrayList<GraphNode>();
	
	/**
	 * 
	 */
	public Graph(GraphNode rootNode) {
		this.rootNode = rootNode;
		this.traverseGraph();
	}
	
	public GraphNode getRootNode(){
		return this.rootNode;
	}
	
	/**
	 * Retrieve a collection of all accessible nodes from the root 
	 * node in the graph. Nodes that cannot be reached from the root
	 * node will not be in the collection.
	 * 
	 * @return - A collection of all accessible nodes from the root
	 * node in the graph.
	 */
	public ArrayList<GraphNode> getAccessibleNodes(){
		return accessibleNode;
	}
	
	/**
	 * Breadth-first search traverse.
	 * 
	 */
	private void traverseGraph(){
		Queue<GraphNode> q = new LinkedList<GraphNode>();
		visitNode(rootNode);
		q.add(rootNode);
		GraphNode nextNode, childNode = null;
		while(!q.isEmpty()){
			nextNode = q.remove();
			for(GraphEdge e : nextNode.getOutgoingEdges()){
				childNode = e.getToNode();
				if(!childNode.isVisited()){
					visitNode(childNode);
					q.add(childNode);
				}
			}
		}
		clearVisited();
	}
	
	private void clearVisited(){
		clearVisited(this.rootNode);
	}
	private void clearVisited(GraphNode g){
		g.setVisited(false);
		if(g.hasOutgoingEdges()){
			for(GraphEdge e : g.getOutgoingEdges()){
				if(e.getToNode().isVisited()){
					clearVisited(e.getToNode());
				}
			}
		}
	}
	
	private void visitNode(GraphNode g){
		g.setVisited(true);
		accessibleNode.add(g);
		//System.out.println(g.getValue());
	}

}
