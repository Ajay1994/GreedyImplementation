import java.util.*;
public class DetectCycleUndirected {
	private Set<Integer> whiteSet = new HashSet<Integer>();
	private Set<Integer> greySet = new HashSet<Integer>();
	private Set<Integer> blackSet = new HashSet<Integer>();
	
	
	private boolean hasCycle(Graph graph){
		for(int i = 0 ; i < graph.V; i++){
			whiteSet.add(i);
		}
		while(whiteSet.size() > 0){
			int node =  whiteSet.iterator().next();
			boolean isCycle = getDFS(node,  graph);
			if(isCycle == true) return true;
		}
		return false;
	}

	private boolean getDFS(int node, Graph graph) {
		// TODO Auto-generated method stub
		greySet.add(node);
		whiteSet.remove(node);
		for(Integer adj : graph.adjacencyList[greySet.iterator().next()]){
			if(blackSet.contains(adj)) continue;
			if(greySet.contains(adj)) return true;
			boolean isCycle = getDFS(adj, graph);
		}
		greySet.remove(node);
		blackSet.add(node);
		return false;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DetectCycleUndirected detectCycleUndirected = new DetectCycleUndirected();
		
		Graph graph = new Graph(4, true);
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(1, 2);
		graph.addEdge(2, 0);
		graph.addEdge(2, 3);
		graph.addEdge(3, 3);
		
		boolean hasCycle = detectCycleUndirected.hasCycle(graph);
		
		System.out.println(hasCycle ? "Contains Cycle !!" : "Not contains cycle.");
	}

}
