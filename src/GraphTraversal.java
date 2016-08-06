import java.util.*;
import java.io.*;
class Graph{
	public int V; //No of vertices
	public boolean isDirected;
	public LinkedList<Integer> adjacencyList[];
	Graph(int v, boolean isDirected){
		this.V = v;
		this.isDirected = isDirected;
		adjacencyList = new LinkedList[v];
		for(int i = 0 ; i < v; i++){
			adjacencyList[i] = new LinkedList<Integer>();
		}
	}
	public void addEdge(int u, int v){
		if(this.isDirected) adjacencyList[u].add(v);
		else{
			adjacencyList[u].add(v);
			adjacencyList[v].add(u);
		}
	}
	public void printGraph(Graph graph){
		for(int i = 0; i < graph.V; i++){
			printLinkedList(i, adjacencyList[i]);
			System.out.println();
		}
	}
	private void printLinkedList(int i, LinkedList<Integer> list) {
		// TODO Auto-generated method stub
		System.out.print("Vertex -> " +i + " : ");
		Iterator<Integer> iter = list.iterator();
		while(iter.hasNext()){
			System.out.print(iter.next()+ " | ");
		}
	}
}
public class GraphTraversal {
	public static void main(String[] args) {
		Graph graph = new Graph(5, true);
		graph.addEdge(0, 1);
		graph.addEdge(0, 4);
		graph.addEdge(1, 2);
		graph.addEdge(1, 3);
		graph.addEdge(1, 4);
		graph.addEdge(2, 3);
		graph.addEdge(3, 4);
		graph.printGraph(graph);
	}
}
