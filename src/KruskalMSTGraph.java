import java.util.*;

class Edge implements Comparable<Edge>{
		int src ,  dest,  weight;
		public Edge(int src, int dest, int weight){
			this.src = src;
			this.dest = dest;
			this.weight = weight;
		}
		public int compareTo(Edge edge){
			return this.weight - edge.weight;
		}
}

public class KruskalMSTGraph {
	class Node{
		int data;
		int rank;
		Node parent;
	}
	private Map<Integer , Node> map = new HashMap<Integer, Node>();
	Edge[] edges;
	int E = 0;
	int V = 0;
	
	public KruskalMSTGraph(int E, int V){
		this.E = E;
		this.V = V;
		this.edges = new Edge[E];
	}
	
	public void makeSet(){
		for(int i = 0 ; i < V; i++){
			Node node = new Node();
			node.data = i;
			node.rank = 0;
			node.parent = node;
			map.put(i, node);
		}
	}
	//Combining two sets together - UNION
	public void union(int data1, int data2){
		Node node1 = map.get(data1);
		Node node2 = map.get(data2);
		
		Node parent1 = findSet(node1);
		Node parent2 = findSet(node2);
		
		if(parent1.data == parent2.data) return;
		
		if(parent1.rank <= parent2.rank){
			parent2.rank = (parent1.rank == parent2.rank) ? parent2.rank + 1 : parent2.rank;
			parent1.parent = parent2;
		}else{
			parent2.parent = parent1;
		}
	}
	
	public Node getRepresentative(int data){
		Node node = map.get(data);
		if(node.parent == node) return node;
		return findSet(node);
	}
	
	private Node findSet(Node node) {
		// TODO Auto-generated method stub
		Node parent = node.parent;
		if(parent == node){
			return node;
		}
		node.parent = findSet(node.parent);
		return node.parent;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		KruskalMSTGraph graph = new KruskalMSTGraph(5, 4);
		graph.edges[0] =  new Edge(0, 1, 10);
		graph.edges[1] =  new Edge(0, 2, 6);
		graph.edges[2] =  new Edge(0, 3, 5);
		graph.edges[3] =  new Edge(1, 3, 15);
		graph.edges[4] =  new Edge(2, 3, 4);
		
		Arrays.sort(graph.edges);
		
		List<Edge> mst = new ArrayList<Edge>();
		
		graph.makeSet();
		
		for(int i = 0; i < graph.V; i++){
			Edge edge = graph.edges[i];
			
			Node parent1 = graph.findSet(graph.map.get(edge.src));
			Node parent2 = graph.findSet(graph.map.get(edge.dest));
			
			if(parent1 != parent2){
				mst.add(edge);
				graph.union(edge.src, edge.dest);
			}
		}
		
		for(Edge edge : mst){
			System.out.println("Src : "+ edge.src + " Dest : " + edge.dest + " Weight : "+ edge.weight);
		}
	}

}
