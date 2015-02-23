/**
 * 
 */
package org.lenzi.algorithm.graph.dijkstra;

/**
 * @author sal
 *
 */
public class GraphTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		(new GraphTest()).doTest();
	}

	/**
	 * 
	 */
	public GraphTest() {}	
	
	public void doTest(){
		
		System.out.println("Dijsktra graph test.");
		
		// create nodes
		GraphNode aNode = new GraphNode("A");
		GraphNode bNode = new GraphNode("B");
		GraphNode cNode = new GraphNode("C");
		GraphNode dNode = new GraphNode("D");
		GraphNode eNode = new GraphNode("E");
		GraphNode fNode = new GraphNode("F");
		GraphNode gNode = new GraphNode("G");
		GraphNode hNode = new GraphNode("H");
		
		GraphNode iNode = new GraphNode("I");
		GraphNode jNode = new GraphNode("J");
		
		
		// define edges
		aNode.addOutgoingEdge(bNode, new Long(20));
		aNode.addOutgoingEdge(dNode, new Long(80));
		aNode.addOutgoingEdge(gNode, new Long(90));
		bNode.addOutgoingEdge(fNode, new Long(10));
		cNode.addOutgoingEdge(fNode, new Long(50));
		cNode.addOutgoingEdge(hNode, new Long(20));
		cNode.addOutgoingEdge(dNode, new Long(10));
		dNode.addOutgoingEdge(gNode, new Long(20));
		eNode.addOutgoingEdge(bNode, new Long(50));
		eNode.addOutgoingEdge(gNode, new Long(30));
		fNode.addOutgoingEdge(cNode, new Long(10));
		fNode.addOutgoingEdge(dNode, new Long(40));
		gNode.addOutgoingEdge(aNode, new Long(20));
		
		hNode.addOutgoingEdge(eNode, new Long(100));
		aNode.addOutgoingEdge(fNode, new Long(5));
		fNode.addOutgoingEdge(bNode, new Long(5));
		hNode.addOutgoingEdge(iNode, new Long(1));
		iNode.addOutgoingEdge(jNode, new Long(1));
		jNode.addOutgoingEdge(gNode, new Long(5));
		
		// Wrap in Graph object and traverse
		Graph g = new Graph(aNode);
		
		//System.out.println("Number of accessible nodes in graph = " + g.getAccessibleNodes().size());
		//for(GraphNode n : g.getAccessibleNodes()){
		//	System.out.println("Accessible Node: " + n.getValue() + ", Visited: " + n.isVisited());
		//}
		
		DijkstraImpl dimpl = new DijkstraImpl(g);
		dimpl.doAlg();
		
		dimpl.printDistance();
		
		dimpl.printPath(gNode);
		dimpl.printPath(hNode);
		dimpl.printPath(dNode);
		dimpl.printPath(eNode);
		
		System.out.println("Test complete.");
		
	}
}
