import java.util.*;

/**
 * This class is a wrapper around a Graph object.  It can be used
 * to perform Dijsktra's algorithm, or a depth first search on the
 * graph.
 */
public class GraphSolver<T extends Comparable<T>> {

	private Graph<T> graph;
	private Map<T, Integer> shortPath = new HashMap<T, Integer>();
	private Map<T, T> predecessor = new HashMap<T, T>();


	/* STUDENTS:  Add further instance variables, as needed for implementing
	 * Dijkstra's algorithm.
	 */



	/**
	 * @param graph the graph that this GraphSolver wraps
	 */
	public GraphSolver(Graph<T> graph) {
		this.graph = graph;
	}

	/**
	 * Performs a Breadth First Search on the internal graph, using the
	 * parameter as the starting vertex.
	 *
	 * Note:  Whenever there is a choice, elements will be visited according
	 * to the natural ordering of the type T.
	 * 
	 * @param start the data element of the starting vertex
	 * @return a correctly ordered list of all nodes visited during 
	 * Breadth First Search
	 */
	public List<T> BFS(T start) {
		List<T> visited =  new ArrayList<T>();
		List<T> elem = new ArrayList<T>();
		List<T> discovered =  new ArrayList<T>();
		Set<T> allElems = graph.getAllElements();
		Iterator<T> neighbors = graph.getNeighbors(start);
		//Iterator<T> neighbors2 = graph.getNeighbors(start);
		T nex = null;
		T obj = null;


		discovered.add(start);

		while(discovered.size() != 0){
			T a = discovered.get(0);
			discovered.remove(a);
			Iterator<T> neighbors2 = graph.getNeighbors(a);

			if(!visited.contains(a)){
				visited.add(a);
				while(neighbors2.hasNext()){
					nex = neighbors2.next();
					if(!visited.contains(nex)){
						discovered.add(nex);
					}
				}
			}
		}
		return visited;
	}


	/**
	 * Performs Dijsktra's algorithm on the graph.  The results are stored
	 * internally and can be accessed afterward by calls to the 
	 * getShortestDistance and getShortestPath methods.
	 * 
	 * @param start data element of starting vertex
	 */
	public void doDijkstra(T start) {
		Map<T, Integer> costElems = new HashMap<T, Integer>();
		Set<T> allElems = graph.getAllElements();
		Object[] elem = allElems.toArray();
		int min = Integer.MAX_VALUE;
		T minObj = null;
		T nex = null;
		predecessor.put(start, start);
		// Iterator<T> neighbors;
		Arrays.sort(elem);

		for(Object a : elem){
			if(a.equals(start)){
				costElems.put(start, 0);
			}else{
				costElems.put((T) a, Integer.MAX_VALUE);
			}
		}

		while(!shortPath.keySet().containsAll((costElems.keySet()))){
			Iterator<T> neighbors;
			min = Integer.MAX_VALUE;
			for(T obj : costElems.keySet()){
				if(costElems.get(obj)< min){
					min = costElems.get(obj);
					
					minObj = obj;
				}
			}
			neighbors = graph.getNeighbors(minObj);
			while(neighbors.hasNext()){
				nex = neighbors.next();
				if(!shortPath.containsKey(nex)){
					if((costElems.get(minObj)+graph.getWeight(minObj, nex)) < costElems.get(nex)){
						costElems.put(nex ,costElems.get(minObj)+graph.getWeight(minObj, nex));
						predecessor.put(nex ,minObj);
					}
				}
			}
			shortPath.put(minObj, min);
			costElems.remove(minObj);
			
		}
		System.out.println(shortPath);
		System.out.println(predecessor);

	}

	/**
	 * IMPORTANT:  This method may only be called after the doDijkstra method 
	 * has already been invoked.
	 * 
	 * This method will return the shortest distance from the starting node 
	 * (specified in the call to the doDijkstra method) to the parameter.
	 * 
	 * @param destination the destination for computing shortest distance
	 * @return The shortest distance from the starting vertex
	 * (specified in the earlier call to doDijsktra) to the parameter.
	 */
	public int getShortestDistance(T destination) {
		int shortest = 0;
		for(T obj : shortPath.keySet()){
			if(obj.equals(destination)){
				shortest = shortPath.get(obj);
			}
		}
		return shortest;

	}

	/**
	 * IMPORTANT:  This method may only be called after the doDijkstra method 
	 * has already been invoked.
	 * 
	 * This method will compute the shortest Path from the starting node 
	 * (specified in the call to the doDijkstra method) to the parameter.
	 * 
	 * @param destination the destination of the path to be found
	 * @return A list of data elements representing a path from the starting 
	 * vertex (specified in the earlier call to doDijsktra) to the parameter.  
	 * The first element of the list will be the starting element; the last 
	 * element of the list will be the parameter.
	 */
	public List<T> getShortestPath(T destination) {
		List<T> path = new ArrayList<T>();
		List<T> pathFinal = new ArrayList<T>();
		T current = destination;
		path.add(destination);
		while(!current.equals(predecessor.get(current))){
			path.add(0,predecessor.get(current));
			current = predecessor.get(current);
		}
		//System.out.println(path);
		return path;
	}
}
