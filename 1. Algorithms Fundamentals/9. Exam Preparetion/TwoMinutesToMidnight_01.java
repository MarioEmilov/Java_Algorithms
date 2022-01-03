import java.util.Scanner;

public class TwoMinutesToMidnight_01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        int k = Integer.parseInt(scanner.nextLine());

        long[][] binom = new long[n + 1][k + 1];

        calcBinom(n, k, binom);

        System.out.println(binom[n][k]);
    }

    private static long calcBinom(int n, int k, long[][] binom) {
        if (k == 0 || k == n) {
            return binom[n][k] = 1;
        }

        if (binom[n][k] != 0) {
            return binom[n][k];
        }

        return binom[n][k] = calcBinom(n - 1, k - 1, binom) + calcBinom(n - 1, k, binom);
    }
}