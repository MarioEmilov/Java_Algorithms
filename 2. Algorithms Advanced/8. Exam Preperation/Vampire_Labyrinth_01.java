import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Vampire_Labyrinth_01 {
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
