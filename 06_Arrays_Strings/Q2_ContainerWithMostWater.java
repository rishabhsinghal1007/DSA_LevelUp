import java.util.Scanner;

public class Q2_ContainerWithMostWater {
    public static Scanner scn = new Scanner(System.in);

    public static int mostWater(int[] arr) {
        int i = 0, j = arr.length - 1;
        int maxWater = 0;

        while (i < j) {
            int length = j - i;
            int height = Math.min(arr[i], arr[j]);
            int water = length * height;

            maxWater = Math.max(water, maxWater);

            if (arr[i] < arr[j]) {
                i++;
            } else {
                j--;
            }
        }

        return maxWater;
    }

    public static void main(String[] args) {
        int n = scn.nextInt();
        int[] heights = new int[n];
        for (int i = 0; i < n; i++) {
            heights[i] = scn.nextInt();
        }

        int res = mostWater(heights);
        System.out.println(res);
    }
}
