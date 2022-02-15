import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Time_03 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] first = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] second = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[][] dp = new int[first.length + 1][second.length + 1];

        for (int rowIndex = 1; rowIndex <= first.length; rowIndex++) {
            for (int colIndex = 1; colIndex <= second.length; colIndex++) {
                if (first[rowIndex - 1] == second[colIndex - 1]) {
                    dp[rowIndex][colIndex] =
                            dp[rowIndex - 1][colIndex - 1] + 1;
                } else {
                    dp[rowIndex][colIndex] = Math.max(
                            dp[rowIndex - 1][colIndex], dp[rowIndex][colIndex - 1]
                    );
                }
            }
        }

        Deque<Integer> result = new ArrayDeque<>();

        int i = first.length;
        int j = second.length;
        while (i > 0 && j > 0) {
            if (first[i - 1] == second[j - 1]) {
                result.push(first[i - 1]);
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        StringBuilder out = new StringBuilder();

        while (!result.isEmpty()) {
            out.append(result.pop()).append(" ");
        }
        out.append(System.lineSeparator()).append(dp[first.length][second.length]);
        System.out.println(out.toString());
    }
}
/*
static int[][] dp;
    static int[] first;
    static int[] second;

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        first = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        second = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        dp = new int[first.length + 1][second.length + 1];

        for (int rowIndex = 1; rowIndex <= first.length; rowIndex++) {
            for (int colIndex = 1; colIndex <= second.length; colIndex++) {
                if (first[rowIndex - 1] == second[colIndex - 1]) {
                    dp[rowIndex][colIndex] = dp[rowIndex - 1][colIndex - 1] + 1;
                } else {
                    dp[rowIndex][colIndex] = Math.max(dp[rowIndex - 1][colIndex], dp[rowIndex][colIndex - 1]);
                }
            }
        }

        StringBuilder out = retrieveLCS();
        out.append(System.lineSeparator()).append(dp[first.length][second.length]);
        System.out.println(out.toString());
    }

    private static StringBuilder retrieveLCS() {

        StringBuilder out = new StringBuilder();

        int row = dp.length - 1;
        int col = dp[row].length - 1;

        while (row > 0 && col > 0) {
            if (first[row - 1] == second[col - 1]) {
                out.insert(0, " ").insert(0, first[row - 1]);
                row--;
                col--;
            } else if (dp[row - 1][col] > dp[row][col - 1]) {
                row--;
            } else {
                col--;
            }
        }

        return out;
    }
 */