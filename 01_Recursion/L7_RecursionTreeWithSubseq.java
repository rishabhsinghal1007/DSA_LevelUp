public class L7_RecursionTreeWithSubseq {

    public static int combinationWithInfi_subSeq(int[] arr, int tar, int idx, String ans) {

        if (tar == 0 || idx == arr.length) {
            if (tar == 0) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int count = 0;
        if (tar - arr[idx] >= 0)
            count += combinationWithInfi_subSeq(arr, tar - arr[idx], idx, ans + arr[idx]);

        count += combinationWithInfi_subSeq(arr, tar, idx + 1, ans);

        return count;
    }

    public static int combinationWithSingle_subSeq(int[] arr, int tar, int idx, String ans) {
        if (tar == 0 || idx == arr.length) {
            if (tar == 0) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int count = 0;
        if (tar - arr[idx] >= 0) {
            count += combinationWithSingle_subSeq(arr, tar - arr[idx], idx + 1, ans + arr[idx]);
        }
        count += combinationWithSingle_subSeq(arr, tar, idx + 1, ans);

        return count;
    }

    public static void coinChange() {
        int[] arr = { 2, 3, 5, 7 };
        int tar = 10;
        System.out.println(combinationWithInfi_subSeq(arr, tar, 0, ""));
        // System.out.println(combinationWithSingle_subSeq(arr, tar, 0, ""));
    }

    public static void main(String[] args) {
        coinChange();
    }
}
