public class L6_RecursionTrees{
    public static int permutataionsWithInfiCoins(int[] arr, int tar, String ans) {
        if (tar == 0) {
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        for (int ele: arr) {
            if (tar - ele >= 0)
                count += permutataionsWithInfiCoins(arr, tar - ele, ans + ele);
        }
        return count;
    }

    public static int combinationWithInfiCoins(int[] arr, int tar, int idx, String ans) {
        if (tar == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int i = idx; i < arr.length; i++) {
            if (tar - arr[i] >= 0)
                count += combinationWithInfiCoins(arr, tar - arr[i], i, ans + arr[i]);
        }
        return count;
    }

    public static int combinationWithSingleCoin(int[] arr, int tar, int idx, String ans) {
        if (tar == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int i = idx; i < arr.length; i++) {
            if (tar - arr[i] >= 0)
                count += combinationWithSingleCoin(arr, tar - arr[i], i + 1, ans + arr[i]);
        }
        return count;
    }
    
    public static int permutationWithSingleCoin(int[] arr, int tar, String ans) {
        if (tar == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0 && tar - arr[i] >= 0) {
                int val = arr[i];

                arr[i] = -arr[i];
                count += permutationWithSingleCoin(arr, tar - val, ans + val);
                arr[i] = -arr[i];
            }
        }
        return count;
    }

    public static int permutationWithSingleCoin(int[] arr, boolean[] vis, int tar, String ans) {
        if (tar == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (!vis[i] && tar - arr[i] >= 0) {
                vis[i] = true;
                count += permutationWithSingleCoin(arr, vis, tar - arr[i], ans + arr[i]);
                vis[i] = false;
            }
        }

        return count;
    }
    public static void main(String[] args) {
        int[] arr = { 2, 3, 5, 7 };
        int tar = 10;
        boolean[] vis = new boolean[10];
        // System.out.println(permutataionsWithInfiCoins(arr, tar, ""));
        // System.out.println(combinationWithInfiCoins(arr, tar, 0, ""));
        // System.out.println(combinationWithSingleCoin(arr, tar, 0, ""));
        System.out.println(permutationWithSingleCoin(arr, vis, tar, ""));
        
    }
}