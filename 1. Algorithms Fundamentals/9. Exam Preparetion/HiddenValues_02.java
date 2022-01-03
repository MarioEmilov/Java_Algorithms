import java.util.Arrays;
import java.util.Scanner;

public class HiddenValues_02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] numbers = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int maxReached = Integer.MIN_VALUE;
        int maxEnding = 0;
        int bestStart = 0;
        int bestEnd = 0;
        int size = 0;

        for (int i = 0; i < numbers.length; i++) {
            maxEnding += numbers[i];

            if (maxReached < maxEnding) {
                maxReached = maxEnding;
                bestStart = size;
                bestEnd = i;
            }

            if (maxEnding < 0) {
                maxEnding = 0;
                size = i + 1;
            }
        }

        StringBuilder out = new StringBuilder(String.valueOf(maxReached));

        out.append(System.lineSeparator());

        for (int i = bestStart; i <= bestEnd; i++) {
            out.append(numbers[i]).append(" ");
        }

        System.out.println(out.toString());
    }
}
