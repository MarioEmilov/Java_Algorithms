import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Battle_Points_03 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] energy = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] points = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int maxEnergy = Integer.parseInt(reader.readLine());

        int[][] dp = new int[points.length + 1][maxEnergy + 1];

        for (int i = 1; i <= points.length; i++) {
            for (int j = 1; j <= maxEnergy; j++) {
                if (energy[i - 1] <= j) {
                    dp[i][j] = Math.max(points[i - 1] + dp[i - 1][j - energy[i - 1]],
                            dp[i - 1][j]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        System.out.println(dp[points.length][maxEnergy]);
    }
}
