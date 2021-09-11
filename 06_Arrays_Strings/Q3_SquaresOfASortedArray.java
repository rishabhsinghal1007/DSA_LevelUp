import java.util.Scanner;

public class Q3_SquaresOfASortedArray {

    public static Scanner scn = new Scanner(System.in);

    public static int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int i = 0, j = n - 1;
        int[] ans = new int[n];

        for (int k = ans.length - 1; k >= 0; k--) {
            int val1 = nums[i] * nums[i];
            int val2 = nums[j] * nums[j];

            if (val1 > val2) {
                ans[k] = val1;
                i++;
            } else {
                ans[k] = val2;
                j--;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int n = scn.nextInt();
        int[] nums = new int[n];

        for (int i = 0; i < n; i++)
            nums[i] = scn.nextInt();

        int[] res = sortedSquares(nums);

        for (int i = 0; i < n; i++) {
            System.out.print(res[i] + " ");
        }
    }
}
