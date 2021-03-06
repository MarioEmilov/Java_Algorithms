import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class DividingPresents_03 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        int[] presents = Arrays.stream(reader.readLine().trim().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();

        int sum = Arrays.stream(presents).sum();

        Integer[] values = new Integer[sum + 1];

        values[0] = 0;

        for (int i = 0; i < presents.length; i++) {
            for (int j = sum - presents[i]; j >= 0; j--) {
                if (values[j] != null && values[j + presents[i]] == null) {
                    values[j + presents[i]] = i;
                }
            }
        }

        int index = sum / 2;

        while (values[index] == null && index > 0) {
            index--;
        }

        System.out.printf("Difference: %d%n", sum - index * 2);
        System.out.printf("Alan:%d Bob:%d%n", index, sum - index);
        System.out.print("Alan takes:");

        while (index > 0 && values[index] != null) {
            int present = presents[values[index]];
            System.out.print(" " + present);
            index -= present;
        }

        System.out.println();
        System.out.println("Bob takes the rest.");
    }
}
