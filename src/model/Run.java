package model;

public class Run {

	public static void main(String[] args) {
		Vertex s = new Vertex("s");
		Vertex t = new Vertex("t");
		Vertex v1 = new Vertex("v1");
		Vertex v2 = new Vertex("v2");
		Vertex v3 = new Vertex("v3");
		Vertex v4 = new Vertex("v4");
		Vertex[] vertices = {s, v1, v2, v3, v4, t};
		int[][] edgeCapacities = {
				{0, 16, 13, 0, 0, 0}, 
				{0, 0, 0, 12, 0, 0}, 
				{0, 4, 0, 0, 14, 0}, 
				{0, 0, 9, 0, 0, 20}, 
				{0, 0, 0, 7, 0, 4}, 
				{0, 0, 0, 0, 0, 0}};
		Graph g = new Graph(vertices, edgeCapacities);
		
		/*for (int i = 0; i < g.edgeCapacities.length; i++) {
			for (int j = 0; j < g.edgeCapacities[0].length; j++) {
				System.out.print(g.edgeCapacities[i][j] + " ");
			}
			System.out.println();
		}*/
		
//		System.out.println(g.vertices.length);
//		System.out.println(FordFulkerson.findPath(g, s, t).toString());
//		LinkedList<Vertex> path1 = FordFulkerson.findPath(g, s, t);
//		System.out.println(FordFulkerson.findMinCapacity(g, s, t, path1));
		Graph result = FordFulkerson.finalResidue(g, s, t);
		
//		System.out.println();
		for (int i = 0; i < result.edgeCapacities.length; i++) {
			for (int j = 0; j < result.edgeCapacities[0].length; j++) {
				System.out.print(result.edgeCapacities[i][j] + " ");
			}
			System.out.println();
		}
	}

}
