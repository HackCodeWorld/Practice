import java.util.*;

/**
 * 3
 * AB
 * BA
 * C
 */
public class DFS {
    // key is the node, value is the adjacent list of its nodes for the hashmap representing graph
    static HashMap<String, ArrayList<String>> graph = new HashMap();

    public static void exploreAllNodes() {
        HashSet<String> visited = new HashSet<>();
        ArrayList<String> componentsOutput = new ArrayList<>();

        for (String node : graph.keySet()) {
            if (!visited.contains(node)) {
                HashSet<String> component = dfs(node, visited);
                ArrayList<String> sortedComponent = new ArrayList<>(component);
                Collections.sort(sortedComponent);
                componentsOutput.add(String.join(" ", sortedComponent));
            }
        }

        Collections.sort(componentsOutput);
        for (String componentStr : componentsOutput) {
            System.out.print(componentStr + " ");
        }
    }

    private static HashSet<String> dfs(String startNode, HashSet<String> visited) {
        HashSet<String> currentComponent = new HashSet<>();
        Stack<String> stack = new Stack<>();
        stack.push(startNode); // push the first node to stack

        while (!stack.isEmpty()) {
            String currNode = stack.pop();
            if (!visited.contains(currNode)) {
                currentComponent.add(currNode);
                visited.add(currNode);
                for (String neighbour : graph.get(currNode)) {
                    if (!visited.contains(neighbour)) stack.push(neighbour);
                }
            }
        }
        return currentComponent;
    }

    /**
     * initialize a graph with adjacent list
     *
     * @param nodesLt  each node's adjacent list
     * @param numNodes number of nodes in the graph
     */
    private static void makeGraph(char[] nodesLt, int numNodes) {
        ArrayList<String> adjLt = new ArrayList<>();
        // fulfil the tie-breaker priority while making the graph
        for (int i = numNodes - 1; i > 0; i--) {
            adjLt.add(nodesLt[i] + "");
        }
        graph.put(nodesLt[0] + "", adjLt);
    }

    /**
     * entry of the algorithm
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // how many instances the input got
        int instances = scanner.nextInt();
        // consume the rest of newlines
        scanner.nextLine();

        for (int i = 0; i < instances; i++) {
            // how many nodes the instance got (it can include the number of multiple sub-graphs)
            int numNodes = scanner.nextInt(); // number of nodes
            scanner.nextLine(); // consume the rest of newline

            for (int j = 0; j < numNodes; j++) {
                String nodes = scanner.nextLine().replace(" ", "");
                char[] nodesLt = nodes.toCharArray(); // adjLt for each line
                makeGraph(nodesLt, nodesLt.length);
            }
            exploreAllNodes();
            System.out.println(); // separate different sub-graphs with a new line
            graph.clear();
        }
    }
}
