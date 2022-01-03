import java.util.*;
import java.util.stream.Collectors;

public class TheStoryTelling_03 {

    static Map<String, List<String>> graph = new LinkedHashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String line = scanner.nextLine();

        while (!line.equals("End")) {

            String[] tokens = line.split("->");

            String source = tokens[0].trim();

            graph.putIfAbsent(source, new ArrayList<>());

            if (tokens.length > 1) {
                graph.get(source).addAll(Arrays.stream(tokens[1].trim().split("\\s+")).collect(Collectors.toList()));
            }

            line = scanner.nextLine();
        }

        ArrayDeque<String> ordered = new ArrayDeque<>();

        Set<String> visited = new HashSet<>();

        for (String key : graph.keySet()) {
            dfs(key, visited, ordered);
        }

        StringBuilder out = new StringBuilder();

        while (!ordered.isEmpty()) {
            out.append(ordered.pop()).append(" ");
        }

        System.out.println(out.toString().trim());
    }

    private static void dfs(String key, Set<String> visited, ArrayDeque<String> ordered) {
        if (!visited.contains(key)) {
            visited.add(key);
            if (graph.containsKey(key)) {
                for (String node : graph.get(key)) {
                    dfs(node, visited, ordered);
                }

                ordered.push(key);
            }
        }
    }
}