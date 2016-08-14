package model;

public class Vertex {
	
	public String name;
	public boolean discovered;
	public Vertex pre;
	public int distance; // used in BFS
	
	public Vertex(String name) {
		this.name = name;
		discovered = false;
		pre = null;
		distance = Integer.MAX_VALUE;
	}
	
	@Override
	public boolean equals(Object O) {
		Vertex v = (Vertex) O;
		return v.name.equals(this.name);
	}
	
	@Override
	public String toString() {
		return name;
	}
}