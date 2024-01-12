import java.util.List;
import java.util.NoSuchElementException;

/**
 * This class extends the GraphADT implemented by an undirected graph class and added the leastTransferPath and its cost
 * methods shortestPath will return the least weighted lines but leastTransferPath will return the least number of lines
 * no matter the weight because some users don’t like transferring stations a lot which is very nice especially for
 * a lazy guy like me
 **/
public interface IStationGraph<T> extends GraphADT<T>{
    /**
     * Returns the leastTransfer path between starting data and destination data.
     * @param start data item within first node in path
     * @param end data item within last node in path
     * @return the least transfer path from start to end, as computed by Dijkstra's algorithm
     * @throws NoSuchElementException when no path from start to end can be found,
     *     including when no vertex containing start or end can be found
     */
    public List<T> leastTransferPath(T start, T end);
    /**
     * this class returns the leastTransferPath's cost by computing based on the leastTransferPath()'s weights based on
     * the algorithm mentioned below
     * @param start data item within first node in path
     * @param end data item within last node in path
     * @return the least transfer path cost from start to end, as computed by leastTransferPath's Dijkstra's algorithm
     */
    public int leastTransferPathCost(T start, T end);

}
