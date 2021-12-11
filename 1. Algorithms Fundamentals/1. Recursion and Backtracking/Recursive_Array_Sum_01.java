import java.util.Arrays;
import java.util.Scanner;

public class Recursive_Array_Sum_01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] arr = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int sum = sumNumbers(arr, arr.length - 1);

        System.out.println(sum);
    }

    private static int sumNumbers(int[] numbers, int index) {
        if (index < 0) {
            return 0;
        }
        return numbers[index] + sumNumbers(numbers, index - 1);
    }
}
