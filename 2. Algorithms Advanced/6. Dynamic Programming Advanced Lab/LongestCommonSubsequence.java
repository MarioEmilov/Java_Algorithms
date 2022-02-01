import java.util.*;

public class LongestCommonSubsequence {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String A = sc.nextLine();
        String B = sc.nextLine();

        System.out.println(LCS(A, B));
    }

    public static int LCS(String A, String B) {
        int[][] res = new int[A.length() + 1][B.length() + 1];
        for (int i = 1; i <= A.length(); i++) {
            for (int j = 1; j <= B.length(); j++) {
                if (i == 0 || j == 0) {
                    return 0;
                } else if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    res[i][j] = 1 + res[i - 1][j - 1];

                } else {
                    res[i][j] = Math.max(res[i - 1][j], res[i][j - 1]);
                }
            }
        }
        return res[A.length()][B.length()];
    }
}