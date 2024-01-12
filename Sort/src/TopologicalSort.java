import java.util.*;

class Graph {
    private final int vertices;
    private final LinkedList<Integer>[] adjacencyList;

    public Graph(int vertices) {
        this.vertices = vertices;
        adjacencyList = new LinkedList[vertices];
        for (int i = 0; i < vertices; i++) {
            adjacencyList[i] = new LinkedList<>();
        }
    }

    public void addEdge(int source, int destination) {
        adjacencyList[source].add(destination);
    }

    public int getVertices() {
        return vertices;
    }

    public LinkedList<Integer> getAdjacencyList(int vertex) {
        return adjacencyList[vertex];
    }
}

public class TopologicalSort {
    private final Graph graph;
    private final Stack<Integer> stack;
    private final boolean[] visited;

    public TopologicalSort(Graph graph) {
        this.graph = graph;
        stack = new Stack<>();
        visited = new boolean[graph.getVertices()];
    }

    public void dfs(int vertex) {
        visited[vertex] = true;
        for (int neighbor : graph.getAdjacencyList(vertex)) {
            if (!visited[neighbor]) {
                dfs(neighbor);
            }
        }
        stack.push(vertex);
    }

    public List<Integer> sort() {
        for (int i = 0; i < graph.getVertices(); i++) {
            if (!visited[i]) {
                dfs(i);
            }
        }
        List<Integer> order = new ArrayList<>();
        while (!stack.isEmpty()) {
            order.add(stack.pop());
        }
        return order;
    }
}
class GraphToDot {

    public static String convert(Graph graph) {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph G {\n");

        for (int i = 0; i < graph.getVertices(); i++) {
            for (int neighbor : graph.getAdjacencyList(i)) {
                sb.append(String.format("    %d -> %d;\n", i, neighbor));
            }
        }

        sb.append("}\n");
        return sb.toString();
    }

}

class Main {
    public static void main(String[] args) {
        Graph graph = new Graph(6);
        graph.addEdge(5, 2);
        graph.addEdge(5, 0);
        graph.addEdge(4, 0);
        graph.addEdge(4, 1);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);

        String dotRepresentation = GraphToDot.convert(graph);
        System.out.println(dotRepresentation);

        TopologicalSort topologicalSort = new TopologicalSort(graph);
        List<Integer> order = topologicalSort.sort();
        System.out.println(order);  // Output: [5, 4, 2, 3, 1, 0]
    }
}

