import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Food_Programme_01 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int nodes = Integer.parseInt(reader.readLine());
        int edges = Integer.parseInt(reader.readLine());
        int[] points = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[][] graph = new int[nodes][nodes];

        for (int i = 0; i < edges; i++) {
            int[] tokens = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            graph[tokens[0]][tokens[1]] = tokens[2];
            graph[tokens[1]][tokens[0]] = tokens[2];
        }

        int[] distances = new int[graph.length];
        int[] prev = new int[graph.length];

        boolean[] visited = new boolean[graph.length];

        Arrays.fill(distances, Integer.MAX_VALUE);
        Arrays.fill(prev, -1);

        distances[points[0]] = 0;

        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(node -> distances[node]));

        queue.offer(points[0]);

        while (!queue.isEmpty()) {
            int parent = queue.poll();
            visited[parent] = true;
            int[] children = graph[parent];

            for (int childNode = 0; childNode < children.length; childNode++) {
                if (children[childNode] != 0 && !visited[childNode]) {
                    queue.offer(childNode);

                    int newDistance = distances[parent] + graph[parent][childNode];

                    if (newDistance < distances[childNode]) {
                        distances[childNode] = newDistance;
                        prev[childNode] = parent;
                    }
                }
            }
        }

        List<Integer> path = new ArrayList<>();

        path.add(points[1]);

        int n = prev[points[1]];

        while (n != -1) {
            path.add(n);
            n = prev[n];
        }

        StringBuilder out = new StringBuilder();

        for (int i = path.size() - 1; i >= 0; i--) {
            out.append(path.get(i)).append(" ");
        }
        out.append(System.lineSeparator());
        out.append(distances[points[1]]);

        System.out.println(out.toString());
    }
}
/*
static int[] distances;

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int zones = Integer.parseInt(reader.readLine());
        int roads = Integer.parseInt(reader.readLine());

        int[] connection = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        int[][] graph = new int[zones][zones];

        for (int i = 0; i < roads; i++) {
            int[] roadInfo = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
            graph[roadInfo[0]][roadInfo[1]] = roadInfo[2];
            graph[roadInfo[1]][roadInfo[0]] = roadInfo[2];
        }

        distances = new int[graph.length];

        List<Integer> shortestPath = dijkstraAlgorithm(graph, connection[0], connection[1]);

        for (Integer num : shortestPath) {
            System.out.print(num + " ");
        }
        System.out.println();
        System.out.println(distances[connection[1]]);
    }

    public static List<Integer> dijkstraAlgorithm(int[][] graph, int sourceNode, int destinationNode) {

        int[] prev = new int[graph.length];

        boolean[] visited = new boolean[graph.length];

        Arrays.fill(distances, Integer.MAX_VALUE);

        distances[sourceNode] = 0;

        Arrays.fill(prev, -1);

        Queue<Integer> queue = new PriorityQueue<>(Comparator.comparing(node -> distances[node]));

        queue.offer(sourceNode);

        while (!queue.isEmpty()) {

            int parent = queue.poll();

            if (distances[parent] == Integer.MAX_VALUE) {
                break;
            }

            visited[parent] = true;

            for (int child = 0; child < graph[parent].length; child++) {

                if (graph[parent][child] != 0 && !visited[child]) {

                    queue.offer(child);

                    int newDistance = distances[parent] + graph[parent][child];

                    if (newDistance < distances[child]) {
                        distances[child] = newDistance;
                        prev[child] = parent;
                    }
                }
            }
        }

        if (distances[destinationNode] == Integer.MAX_VALUE) {
            return null;
        }

        List<Integer> path = new ArrayList<>();

        while (destinationNode != -1) {
            path.add(destinationNode);
            destinationNode = prev[destinationNode];
        }

        Collections.reverse(path);

        return path;
    }
 */
