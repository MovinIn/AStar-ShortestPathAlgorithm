package client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AStar implements IsPathfinding {
	
	public AStar(){
		
	}

	public List<Node> randomPathfind() {
		// 0 = empty space
		// 1 = mountain
		// 2 = source
		// 3 = target
		System.out.println("asd");
		int w = (int)(Math.random()*20)+2; 
		int h= (int)(Math.random()*20)+2;
		//+2 makes sure map is not a 0x0 or 1x1
		int[][]map=new int[h][w];
		for(int v=0; v<w*h; v++) {
			if(Math.random()<(1.0/5)) {
				map[v/w][v%w] = 1;
			}
		}
		int i = (int)(Math.random()*w*h);
		int j=i;
		while(j==i)
			j=(int)(Math.random()*w*h);
		
		Node s = new Node(i%w,i/w);
		Node t = new Node(j%w,j/w);
		System.out.println(s.x+","+s.y);
		System.out.println(t.x+","+t.y);
		System.out.println(w+","+h);
		return getPath(s,t,map);
	}
	
	
	
	private boolean boundCheck(Node n,int w,int h) {
		if(n==null||n.x>(w-1)||n.y>(h-1)||n.x<0||n.y<0) {
			return false;
		}
		return true;
	}
	
	private boolean isEmpty(int x,int y,int[][]map) {
		try {
			return map[y][x]==0||map[y][x]==3;
		}
		catch(ArrayIndexOutOfBoundsException e) {
			return false;
		}
	}
	
	@Override
	public List<Node> getPath(Node s,Node t,int[][]m) {
		int w,h;
		int[][]map;
		ArrayList<String> test=new ArrayList<String>();
		if(m==null) { //default map
			map=new int[10][10];
		}
		else {
			map=m;
		}
		w=map[0].length;
		h=map.length;
		System.out.println(w+","+h);
		if(s==null||t==null)
			throw new IllegalArgumentException("Nodes are not initialized!");
		if(!boundCheck(s,w,h)||!boundCheck(t,w,h))
			throw new IllegalArgumentException("Nodes are out of bounds!");
		
		map[s.y][s.x]=2;
		map[t.y][t.x]=3;
		printMap(map);
		System.out.println("");
		
//		int[][]map=initRandomMap(w,w);
		ArrayList<Node> openList=new ArrayList<Node>();
		ArrayList<Node> closedList=new ArrayList<Node>();
		Node.t=t;
		s.f=0;
		openList.add(s);
		while(!openList.isEmpty()) {
			Node q=openList.get(0);
			//Find node with lowest f from open list
			for(int i=1; i<openList.size(); i++) { 
				if(openList.get(i).f<q.f) {
					q=openList.get(i);
				}
			}
			openList.remove(q);
			//Find successors
			ArrayList<Node> successors=new ArrayList<Node>();
			if(isEmpty(q.x+1,q.y,map)) {
				successors.add(new Node(q.x+1,q.y,q));
			}
			if(isEmpty(q.x,q.y+1,map)) {
				successors.add(new Node(q.x,q.y+1,q));
			}
			if(isEmpty(q.x-1,q.y,map)) {
				successors.add(new Node(q.x-1,q.y,q));
			}
			if(isEmpty(q.x,q.y-1,map)) {
				successors.add(new Node(q.x,q.y-1,q));
			}
			for(Node n:successors) {
				test.add(n.toString());
				if(test.size()>=2000) {
					for(String str:test) {
						System.out.println(str);
					}
					printMap(map);
					return null;
				}
				if(n.equals(t)) {
					System.out.println("Path found!");
					return n.path;
				}
				n.setF();
				int i = openList.indexOf(n);
				if(i!=-1&&openList.get(i).f<n.f) {
					continue;
				}
				i=closedList.indexOf(n);
				if(i!=-1) {
					continue;
				}
				openList.add(n);
			}
			closedList.add(q);
		}
		System.out.println("No path found.");
		return null;
	}
	
	
	
	public void printMap(int[][]map) {
		String print = Arrays.deepToString(map)
				.replace("], ", "]\n").replace(", ", "");
		System.out.println(print.substring(1,print.length()-1));
	}
	
}
