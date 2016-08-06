import java.util.*;

public class PrimsAlgorithm {
	Graph graph;
	private HeapMap heapMap = new HeapMap();
	List<Edge> result = new ArrayList<PrimsAlgorithm.Edge>();
	Map<Integer, Edge> mstSet = new HashMap<Integer, Edge>();
	public PrimsAlgorithm(int V){
		this.graph = new Graph(V, false);
	}
	public void getPrimUtils(){
		this.heapMap.insertItem(0, 0);
		for(int i = 1; i < graph.V; i++){
			this.heapMap.insertItem(i, Integer.MAX_VALUE);
		}
		while(!heapMap.isEmpty()){
			HeapMap.Node node = this.heapMap.extractMinimum();
			if(mstSet.containsKey(node.key)) result.add(mstSet.get(node.key));
			for(Edge edge : graph.adjacencyList[node.key]){
				if(heapMap.contains(edge.dest) && heapMap.getWeight(edge.dest) > edge.weight){
					mstSet.put(edge.dest, edge);
					heapMap.decreaseKey(edge.dest, edge.weight);
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
		PrimsAlgorithm primsAlgorithm = new PrimsAlgorithm(9);
		primsAlgorithm.graph.addEdge(0, 1, 4);
	    primsAlgorithm.graph.addEdge(0, 7, 8);
	    primsAlgorithm.graph.addEdge(1, 2, 8);
	    primsAlgorithm.graph.addEdge(1, 7, 11);
	    primsAlgorithm.graph.addEdge(2, 3, 7);
	    primsAlgorithm.graph.addEdge(2, 8, 2);
	    primsAlgorithm.graph.addEdge(2, 5, 4);
	    primsAlgorithm.graph.addEdge(3, 4, 9);
	    primsAlgorithm.graph.addEdge(3, 5, 14);
	    primsAlgorithm.graph.addEdge(4, 5, 10);
	    primsAlgorithm.graph.addEdge(5, 6, 2);
	    primsAlgorithm.graph.addEdge(6, 7, 1);
	    primsAlgorithm.graph.addEdge(6, 8, 6);
	    primsAlgorithm.graph.addEdge(7, 8, 7);
	    
	    primsAlgorithm.graph.printGraph(primsAlgorithm.graph);
	    
	    primsAlgorithm.getPrimUtils();
	    
	    for(Edge edge : primsAlgorithm.result){
	    	System.out.println("("+edge.src + " , "+edge.dest + ")" + " -> "+ edge.weight);
	    }
	}
}

