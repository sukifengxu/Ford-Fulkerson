package model;

import java.util.LinkedList;
import java.util.Queue;

public class FordFulkerson {
	
	/**
	 * Find a path from s to t in directed graph g.
	 * @param g the directed graph.
	 * @param s source
	 * @return the path as a linked list, in the reversed order. Thus, t to s. Return null if no path found.
	 * @param t sink
	 */
	public static LinkedList<Vertex> findPath(Graph g, Vertex s, Vertex t) { // This method will finally be made private
		for (int i =0; i < g.vertices.length; i++) {
			if (!g.vertices[i].equals(s)) {
				g.vertices[i].discovered = false;
				g.vertices[i].distance = Integer.MAX_VALUE;
				g.vertices[i].pre = null;
			}
		}
		s.discovered = true;
		s.distance = 0;
		s.pre = null;
		Queue<Vertex> q = new LinkedList<Vertex>();
		q.add(s);
		while (q.size() != 0) {
			Vertex u = q.remove();
			int index = getIndex(g, u);
//			System.out.println(index);
//			System.out.println(g.edgeCapacities[index][2]);
			for (int i = 0; i < g.edgeCapacities[index].length; i++) {
				int n = g.edgeCapacities[index][i]; // g.edgeCapacities[index] is a row
				if (n != 0) {
//					System.out.println(i);
					Vertex v = g.vertices[i];
//					System.out.println(v.name);
					if (v.equals(t)) {
						v.discovered = true;
						v.distance = u.distance + 1;
						v.pre = u;
//						System.out.println("v is t");
						LinkedList<Vertex> ans = new LinkedList<Vertex>();
//						System.out.println(s.name);
						while (!v.equals(s)) {
//							System.out.println("in while loop: " + v.name);
							ans.add(v);
//							System.out.println(v.pre);
							v = v.pre;
						}
						ans.add(s);
						return ans;
					}
					if (!v.discovered) {
						v.discovered = true;
						v.distance = u.distance + 1;
						v.pre = u;
						q.add(v);
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * Find the index of a specific vertex of a graph.
	 * @param g the graph
	 * @param v the target vertex
	 * @return the index of target vertex in the graph's vertices array.
	 */
	public static int getIndex(Graph g, Vertex v) {
		int index = 0;
		while (!g.vertices[index].equals(v)) {
			index++;
		}
		return index;
	}
	
	/**
	 * Find the bottleneck capacity of an augmenting path.
	 * @param g residue graph
	 * @param path augmenting path
	 * @return the bottleneck capacity of this augmenting path.
	 */
	public static int findMinCapacity(Graph g, Vertex s, Vertex t, LinkedList<Vertex> path) { // path is in the reversed order
		int minCapacity = Integer.MAX_VALUE;
		Vertex[] pathArray = getOrderedArrayPath(path);
//		System.out.println(Arrays.toString(pathArray));
		for (int i = 0; i < pathArray.length - 1; i++) {
			if (minCapacity > g.edgeCapacities[getIndex(g, pathArray[i])][getIndex(g, pathArray[i + 1])]) {
				minCapacity = g.edgeCapacities[getIndex(g, pathArray[i])][getIndex(g, pathArray[i + 1])];
			}
		}
		return minCapacity;
	}
	
	/**
	 * Transform the reversed linked list representation of a path into an ordered array representation.
	 * @param path path in reversed linked list representation
	 * @return path in ordered array representation
	 */
	public static Vertex[] getOrderedArrayPath(LinkedList<Vertex> path) {
//		Vertex[] pathArrayReversed = (Vertex[]) path.toArray();
//		int n = pathArrayReversed.length;
		int n = path.size();
		Vertex[] pathArray = new Vertex[n];
		for (int i = 0; i < n; i++) {
			pathArray[i] = (Vertex) path.toArray()[n - 1 - i];
//			pathArray[i] = pathArrayReversed[n - i - 1];
		}
		return pathArray;
	}
	
	/**
	 * Set all vertices undiscovered, set all predecessors to be null, set distances to be MAX.
	 * @param g the graph to be refreshed.
	 */
	public static void refreshGraph(Graph g) {
		for (Vertex v : g.vertices) {
			v.discovered = false;
			v.pre = null;
			v.distance = Integer.MAX_VALUE;
		}
	}
	
	/**
	 * Computer the final residue graph from a given graph.
	 * @param g the input graph.
	 * @return the final residue graph on which no augmenting path exists.
	 */
	public static Graph finalResidue(Graph g, Vertex s, Vertex t) {
		Object o = findPath(g, s, t);
		while (o != null) {
			LinkedList<Vertex> path = (LinkedList<Vertex>) o;
			int flowValue = findMinCapacity(g, s, t, path);
//			System.out.println(flowValue);
			Vertex[] pathArray = getOrderedArrayPath(path);
//			System.out.println(Arrays.toString(pathArray));
			// flow values and paths are all correct.
			for (int i = 0; i < pathArray.length - 1; i++) {
				g.edgeCapacities[getIndex(g, pathArray[i])][getIndex(g, pathArray[i + 1])] = 
						g.edgeCapacities[getIndex(g, pathArray[i])][getIndex(g, pathArray[i + 1])] - flowValue;
				g.edgeCapacities[getIndex(g, pathArray[i + 1])][getIndex(g, pathArray[i])] = 
						g.edgeCapacities[getIndex(g, pathArray[i + 1])][getIndex(g, pathArray[i])] + flowValue;
			}
			/*for (int i = 0; i < g.edgeCapacities.length; i++) {
				for (int j = 0; j < g.edgeCapacities[0].length; j++) {
					System.out.print(g.edgeCapacities[i][j] + " ");
				}
				System.out.println();
			}*/
//			refreshGraph(g);
			o = findPath(g, s, t);
		}
		return g;
	}
	
	/**
	 * Find the edges in the minimum cut that corresponds to maximum flow in the original graph.
	 * @param g the original graph
	 * @param s source
	 * @param t sink
	 *//*
	public void findCut(Graph g, Vertex s, Vertex t) {
		Graph residue = finalResidue(g, s, t);
	}*/
}
