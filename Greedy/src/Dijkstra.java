import java.util.*;

/**
 * This class extends the Graph class implemented by an undirected graph class and added the leastTransferPath()
 * leastTransferPath will return the least number of nodes ignoring the least cost. Also, its corresponding cost value
 * will be returned as well in the leastTransferPathCost method
 **/
@SuppressWarnings("all")
public class Dijkstra<T> extends MyGraph<T> implements IStationGraph<T>{
   
    private Path leastTransferPath; //store the leastTransfer path object
    
    /**
     * Uses Dijkstra's shortest path algorithm to find and return the shortest path 
     * between two vertices in this graph: start and end. This path contains an ordered list
     * of the data within each node on this path, and also the distance or cost of all edges
     * that are a part of this path.
     *
     * Should create an instance of this pq class within the dijkstrasShortestPath()
     * to hold the discovered paths (including total cost distance, and previous vertex data) that have not yet
     * been determined to be the shortest possible paths to their given end vertex.
     * Just like in the lecture demonstrations, use a priority queue as the frontier for the dijkstra algorithm.
     * Within a loop: call remove() which would return the most optimal path in the priority queue.
     * If not reached the final destination node end yet, then use the edges of the final node in the path
     * to branch out (create new paths and insert them back into the priority queue). Remember to keep track of
     * which nodes you have already visited. And all comparisons would be made by the priority queue since there is a
     * compareTo method for the Path class, the priority queue stores in sorted order (best -> worst) path
     *
     * @param start data item within first node in path
     * @param end data item within last node in path
     * @return the shortest path from start to end, as computed by Dijkstra's algorithm
     * @throws NoSuchElementException when no path from start to end can be found,
     *     including when no vertex containing start or end can be found
     */
    protected Path dijkstrasShortestPath(T start, T end) {
        Path djShortestPath = null;
        Hashtable<T, Boolean> visitedMap = new Hashtable<>();
        PriorityQueue<Path> unsurePaths = new PriorityQueue<>();

        // case1: start / end data is null
        if (start == null || end == null){
            throw new NoSuchElementException("no path from start to end can be found" +
                    "or no vertex containing start or end can be found");
        }
        // case2: vertices doesn't contain start or end key
        if (!vertices.containsKey(start) || !vertices.containsKey(end)) {
            throw new NoSuchElementException("no path from start to end can be found" +
                    "or no vertex containing start or end can be found");
        }
        // case3: base when start is the end and distance should be 0
        if (start.equals(end)) {
            return new Path(vertices.get(start));
        }

        unsurePaths.add(new Path(vertices.get(start))); //add default path at first to the frontier

        //while not sure frontier still exit, keep looping until all unsure remove/all vertices become sure status
        while(!unsurePaths.isEmpty()) {
            // adding the path's end node in visitedMap map
            Path currentOptimalPath = unsurePaths.remove(); // return the most optimal path in the priority queue
            visitedMap.put(currentOptimalPath.end.data, true); //keep tracked the visited to make sure not in cycle
            List<Edge> edges = currentOptimalPath.end.edgesLeaving; //get the current node's all edges
            for (Edge e : edges) {
                Path newOptimalPath = new Path(currentOptimalPath, e); //get the updated path based on each new edge
                T previousNodeData = e.target.data;
                // check edge's target node already in visitedMap
                if (visitedMap.containsKey(previousNodeData)) {
                    if (!visitedMap.get(previousNodeData)) {//into frontier for visited but not explored
                        unsurePaths.add(newOptimalPath);
                    }//otherwise, skip it
                    else continue;
                } else { //unexplored and not in the visitedMap, expand frontier to include all new things
                    unsurePaths.add(newOptimalPath);
                }
                // found the shortest path pointing to target end vertex
                if (previousNodeData.equals(end)) {
                    if (djShortestPath == null){
                        djShortestPath = newOptimalPath;//assign unsureShortestPath at first
                    }
                    //min(unsureShortestPath, newPotentialSmallerPath/newOptimalPath)
                    else if (newOptimalPath.compareTo(djShortestPath) < 0){
                        djShortestPath = newOptimalPath;//update the shorter path then
                    }
                }
            }
        }
        if (djShortestPath == null) throw new NoSuchElementException("no path from start to end can be found" +
                "or no vertex containing start or end can be found");
        return djShortestPath;
    }

    /**
     * Uses Dijkstra's shortest path algorithm to find and return the least transfer path list
     * between two vertices in this graph: start and end. Main logic here is to get the least nodes path and also kept
     * the weight
     * Should create an instance of this pq class within the dijkstrasShortestPath()
     * to hold the discovered paths (including total cost distance, and previous vertex data) that have not yet
     * been determined to be the shortest possible paths to their given end vertex.
     * Just like in the lecture demonstrations, use a priority queue as the frontier for the dijkstra algorithm.
     * Within a loop: call remove() which would return the most optimal path in the priority queue.
     * If not reached the final destination node end yet, then use the edges of the final node in the path
     * to branch out (create new paths and insert them back into the priority queue). Remember to keep track of
     * which nodes you have already visited. And all comparisons would be made by the priority queue since there is a
     * compareTo method for the Path class, the priority queue stores in sorted order (best -> worst) path
     *
     * @param start data item within first node in path
     * @param end data item within last node in path
     * @return the least transfer path from start to end, as computed by Dijkstra's algorithm
     * @throws NoSuchElementException when no path from start to end can be found,
     *     including when no vertex containing start or end can be found
     */
   @Override
    public List<T> leastTransferPath(T start, T end) {
       Path djShortestPath = null;
       Hashtable<T, Boolean> visitedMap = new Hashtable<>();
       PriorityQueue<Path> unsurePaths = new PriorityQueue<>();

       // case1: start / end data is null
       if (start == null || end == null){
           throw new NoSuchElementException("no path from start to end can be found" +
                   "or no vertex containing start or end can be found");
       }
       // case2: vertices doesn't contain start or end key
       if (!vertices.containsKey(start) || !vertices.containsKey(end)) {
           throw new NoSuchElementException("no path from start to end can be found" +
                   "or no vertex containing start or end can be found");
       }
       // case3: base when start is the end and distance should be 0
       if (start == end || start.equals(end)) {
           Path p = new Path(vertices.get(start));
           leastTransferPath = p;
           return p.dataSequence;
       }

       unsurePaths.add(new Path(vertices.get(start))); //add default path at first to the frontier

       //while not sure frontier still exit, keep looping until all unsure remove/all vertices become sure status
       while(!unsurePaths.isEmpty()) {
           // adding the path's end node in visitedMap map
           Path currentOptimalPath = unsurePaths.remove(); // return the most optimal path in the priority queue
           visitedMap.put(currentOptimalPath.end.data, true); //keep tracked the visited to make sure not in cycle
           List<Edge> edges = currentOptimalPath.end.edgesLeaving; //get the current node's all edges
           for (Edge e : edges) {
               int temp = e.weight; //store the original edge weight as a temp
//               e.weight = e.weight + 1000000000; //add each weight a constant
               int tempDis = currentOptimalPath.distance;
               e.weight = 1; // another way is to set them to 1 or a same constant
               //let each edge of the shorter cost path with more edges to add a constant which will have larger cost
               //than letting each edge of the least node path with fewer edges to add a constant
               Path newOptimalPath = new Path(currentOptimalPath, e); //get the updated path based on each new edge
               e.weight = temp; // recover the edge weight to make sure each weight stays the same
               newOptimalPath.distance = tempDis; //another way is to recover the distance and edge was 1
               T previousNodeData = e.target.data;
               // check edge's target node already in visitedMap
               if (visitedMap.containsKey(previousNodeData)) {
                   if (!visitedMap.get(previousNodeData)) {//into frontier for visited but not explored
                       unsurePaths.add(newOptimalPath);
                   }//otherwise, skip it
               } else { //unexplored and not in the visitedMap, expand frontier to include all new things
                   unsurePaths.add(newOptimalPath);
               }
               // found the shortest path pointing to target end vertex
               if (previousNodeData.equals(end)) {
                   if (djShortestPath == null){
                       djShortestPath = newOptimalPath;//assign unsureShortestPath at first
                   }
                   //min(unsureShortestPath, newPotentialSmallerPath/newOptimalPath)
                   else if (newOptimalPath.compareTo(djShortestPath) < 0){
                       djShortestPath = newOptimalPath;//update the shorter path then
                   }
               }
           }
       }
       if (djShortestPath == null) throw new NoSuchElementException("no path from start" +
               " to end can be found" +
               "or no vertex containing start or end can be found");

       leastTransferPath = djShortestPath; //added this to store the path object result since return a list
       return djShortestPath.dataSequence;
    }

    /**
     * this class returns the leastTransferPath's cost by computing based on the leastTransferPath()'s weights based on
     * the algorithm mentioned below
     * @param start data item within first node in path
     * @param end data item within last node in path
     * @return the least transfer path cost from start to end, as computed by leastTransferPath's Dijkstra's algorithm
     */
    public int leastTransferPathCost(T start, T end) {
//        int edgeNumber = leastTransferPath(start, end).size() - 1; //number of edges is V (vertices' numbers) - 1
//        if(start == end || start.equals(end)) return 0; //distance 0 for base case
//       //make sure the leastTransferPath object is loaded and all weights loaded
//        return leastTransferPath.distance - edgeNumber * 1000000000; //total cost can be obtained by adding each edge
        // with a constant and minus number of edges times the constant (it can replace to the max edge's weight but
        // constant's time complexity is better) -> formula: cost + C (V-1) =>  cost + C (V-1) - C (V-1) -> cost = cost
        int cost = 0;
        List<T> ds = new ArrayList<T>();
        ds = leastTransferPath(start, end);
        for (int i = 0; i < ds.size() - 1; i++) {
            for (Edge e : vertices.get(ds.get(i)).edgesLeaving) {
                if (e.target.data == vertices.get(ds.get(i + 1)).data) {
                    cost += e.weight;
                }
            }
        }
        return cost;
    }
}

