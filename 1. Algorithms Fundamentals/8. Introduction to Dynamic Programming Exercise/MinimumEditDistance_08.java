import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MinimumEditDistance_08 {
    static int replaceCost;
    static int insertCost;
    static int deleteCost;
    static int[][] editCost;

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        replaceCost = Integer.parseInt(reader.readLine());
        insertCost = Integer.parseInt(reader.readLine());
        deleteCost = Integer.parseInt(reader.readLine());

        char[] first = reader.readLine().toCharArray();
        char[] second = reader.readLine().toCharArray();

        compute(first, second);
    }

    private static void compute(char[] first, char[] second) {

        int firstLen = first.length;
        int secondLen = second.length;

        editCost = new int[firstLen + 1][secondLen + 1];

        for (int row = 0; row <= firstLen; row++) {
            editCost[row][0] = row * deleteCost;
        }

        for (int col = 0; col <= secondLen; col++) {
            editCost[0][col] = col * insertCost;
        }

        for (int row = 1; row <= firstLen; row++) {
            for (int col = 1; col <= secondLen; col++) {

                int cost = second[col - 1] == first[row - 1] ? 0 : replaceCost;

                int delete = editCost[row - 1][col] + deleteCost;
                int replace = editCost[row - 1][col - 1] + cost;
                int insert = editCost[row][col - 1] + insertCost;

                editCost[row][col] = Math.min(Math.min(delete, insert), replace);
            }
        }

        System.out.println("Minimum edit distance: " + editCost[firstLen][secondLen]);
    }
}
