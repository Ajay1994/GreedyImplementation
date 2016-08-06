import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class DijkastraAlgorithm {
	Graph graph;
	private HeapMap heapMap = new HeapMap();
	Map<Integer, Integer> result = new HashMap<Integer, Integer>();
	Map<Integer, Edge> mstSet = new HashMap<Integer, Edge>();
	public DijkastraAlgorithm(int V){
		this.graph = new Graph(V, false);
	}
	public void getDijkastraUtils(){
		this.heapMap.insertItem(0, 0);
		this.result.put(0,0);
		for(int i = 1; i < graph.V; i++){
			this.heapMap.insertItem(i, Integer.MAX_VALUE);
		}
		//heapMap.printHeap();
		while(!heapMap.isEmpty()){
			
			HeapMap.Node node = this.heapMap.extractMinimum();
			
			for(Edge edge : graph.adjacencyList[node.key]){
				if(heapMap.contains(edge.dest) && (heapMap.getWeight(edge.dest) == Integer.MAX_VALUE || 
						heapMap.getWeight(edge.dest) > node.weight + edge.weight)){
					result.put(edge.dest, node.weight + edge.weight);
					heapMap.decreaseKey(edge.dest, node.weight + edge.weight);
				}
			}
		}
	}
	
	class Edge{
		int src;
		int dest;
		int weight;
		Edge(int src, int dest, int weight){
			this.src = src;
			this.dest = dest;
			this.weight = weight;
		}
	}
	
	class Graph{
		public int V; //No of vertices
		public boolean isDirected;
		public LinkedList<Edge> adjacencyList[];
		Graph(int v, boolean isDirected){
			this.V = v;
			this.isDirected = isDirected;
			adjacencyList = new LinkedList[v];
			for(int i = 0 ; i < v; i++){
				adjacencyList[i] = new LinkedList<Edge>();
			}
		}
		public void addEdge(int u, int v, int weight){
			if(this.isDirected){
				adjacencyList[u].add(new Edge(u, v, weight));
			}
			else{
				adjacencyList[u].add(new Edge(u, v, weight));
				adjacencyList[v].add(new Edge(v, u, weight));
			}
		}
		public void printGraph(Graph graph){
			for(int i = 0; i < graph.V; i++){
				printLinkedList(i, adjacencyList[i]);
				System.out.println();
			}
		}
		private void printLinkedList(int i, LinkedList<Edge> list) {
			// TODO Auto-generated method stub
			System.out.print("Vertex -> " +i + " : ");
			Iterator<Edge> iter = list.iterator();
			while(iter.hasNext()){
				System.out.print(iter.next().dest+ " | ");
			}
		}
	}
	
	public static void main(String[] args) {
		DijkastraAlgorithm dijkastraAlgorithm = new DijkastraAlgorithm(9);
		dijkastraAlgorithm.graph.addEdge(0, 1, 4);
	    dijkastraAlgorithm.graph.addEdge(0, 7, 8);
	    dijkastraAlgorithm.graph.addEdge(1, 2, 8);
	    dijkastraAlgorithm.graph.addEdge(1, 7, 11);
	    dijkastraAlgorithm.graph.addEdge(2, 3, 7);
	    dijkastraAlgorithm.graph.addEdge(2, 8, 2);
	    dijkastraAlgorithm.graph.addEdge(2, 5, 4);
	    dijkastraAlgorithm.graph.addEdge(3, 4, 9);
	    dijkastraAlgorithm.graph.addEdge(3, 5, 14);
	    dijkastraAlgorithm.graph.addEdge(4, 5, 10);
	    dijkastraAlgorithm.graph.addEdge(5, 6, 2);
	    dijkastraAlgorithm.graph.addEdge(6, 7, 1);
	    dijkastraAlgorithm.graph.addEdge(6, 8, 6);
	    dijkastraAlgorithm.graph.addEdge(7, 8, 7);
	    
	    dijkastraAlgorithm.graph.printGraph(dijkastraAlgorithm.graph);
	    
	    dijkastraAlgorithm.getDijkastraUtils();
	    
	    Set<Integer> keys = dijkastraAlgorithm.result.keySet();
	    for(Integer key : keys){
	    	System.out.println("("+ key + " -> "+ dijkastraAlgorithm.result.get(key) + ")");
	    }
	   
	}
}
