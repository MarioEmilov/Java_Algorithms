import java.util.Scanner;

public class AlphaDecay_01 {

    public static String[] nucleus;
    public static String[] observationSlots;
    public static int k;
    public static boolean[] used;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        nucleus = scanner.nextLine().split("\\s+");

        used = new boolean[nucleus.length];

        k = Integer.parseInt(scanner.nextLine());

        observationSlots = new String[k];

        variations(0);
    }

    private static void variations(int index) {
        if (index == observationSlots.length) {
            System.out.println(String.join(" ", observationSlots));
        } else {
            for (int i = 0; i < nucleus.length; i++) {
                if (!used[i]) {
                    used[i] = true;
                    observationSlots[index] = nucleus[i];
                    variations(index + 1);
                    used[i] = false;
                }
            }
        }
    }
}
