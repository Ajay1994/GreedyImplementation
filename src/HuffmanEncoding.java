import java.util.*;

class Node implements Comparable<Node>{
	char data;
	int freq;
	Node left;
	Node right;
	public Node(char data ,int freq){
		this.data = data;
		this.freq = freq;
		this.left = null;
		this.right = null;
	}
	public int compareTo(Node node){
		return this.data - node.data;
	}
	
}
public class HuffmanEncoding {

	public static void printHeap(Queue<Node> heap){
		for(Node node : heap){
			System.out.print(node.data+"("+ node.freq + ")" + " -> ");
		}
		System.out.println();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char array[] = {'a', 'b', 'c', 'd', 'e', 'f'};
		int freq[] = {5, 9, 12, 13, 16, 45};
		Queue<Node> heap = new PriorityQueue<Node>(array.length, new Comparator<Node>() {
			@Override
			public int compare(Node node1, Node node2) {
				// TODO Auto-generated method stub
				if(node1.freq > node2.freq) return 1;
				if(node1.freq < node2.freq) return -1;
				return 0;
			}
		});
		
		for(int i = 0 ; i < array.length; i++){
			heap.add(new Node(array[i], freq[i]));
		}
		
		while(heap.size() != 1){
			//printHeap(heap);
			Node first = heap.remove();
			Node second = heap.remove();
			//System.out.println("Removed : "+ first.data + " & " + second.data);
			Node merge = new Node(first.data, first.freq + second.freq);
			merge.left = (first.freq <= second.freq) ? first : second;
			merge.right = (first.freq > second.freq) ? first : second;
			
			heap.add(merge);
			
		}
		
		Node root = heap.remove();
		System.out.println(root.freq);
		
		printHuffmanCode(root, "");
	}

	private static void printHuffmanCode(Node root, String string) {
		// TODO Auto-generated method stub
		//System.out.println("Root : "+ root.data + "freq : "+ root.freq + " and "+ string);
		if(root.left == null && root.right == null){
			System.out.println(root.data + " --> " +string);
			return;
		}
		if(root.left != null) printHuffmanCode(root.left, string+"0");
		if(root.right != null) printHuffmanCode(root.right , string +"1");
		
	}

}
