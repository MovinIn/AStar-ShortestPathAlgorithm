package client;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Node {
	int x,y;
	int f;
	static Node t;
	Node p;
	List<Node> path;
	Node(int x,int y,Node p) {
		this.x=x;
		this.y=y;
		this.p=p;
		path=new ArrayList<Node>();
		path.addAll(p.path);
		path.add(this);
	}
	Node(int x,int y) {
		this.x=x;
		this.y=y;
		path=new ArrayList<Node>();
		path.add(this);
	}
	public void setF() {
		int g = Math.abs(p.x-x)+Math.abs(p.y-y);
		int h = Math.abs(t.x-x)+Math.abs(t.y-y);
		f=g+h;
	}
	
	public void setTarget(Node t) {
		this.t=t;
	}
	
	public boolean equals(Object o) {
		if(o==null||!(o instanceof Node)) return false;
		Node other = (Node)o;
		return other.x==x&&other.y==y;
	}
	public String toString() {
		return "("+x+","+y+")";
	}
}
