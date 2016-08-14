package model;

/**
 * @author Bo Tian
 * Present a graph in adjacent matrix.
 */
public class Graph {
	public Vertex[] vertices; // in this program, vertices is fixed
	public int[][] edgeCapacities;
	
	public Graph(Vertex[] vertices, int[][] edgeCapacities) {
		this.vertices = vertices;
		this.edgeCapacities = edgeCapacities;
	}

}