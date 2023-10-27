package client;

import java.util.List;

public class ShortestPath {
	public static void main(String[]args) {
		AStar d = new AStar();
//		Node start=new Node(1,1);
//		Node target=new Node(9,9);
		List<Node> n=d.randomPathfind();
		if(n!=null) {
			System.out.println(n.toString());
		}
	}
}
