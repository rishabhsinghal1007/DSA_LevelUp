import java.util.Scanner;

public class Q7_NextGreaterElement {

    public static int dipIndex(char[] arr) {
        int idx = -1;
        for (int i = arr.length - 1; i > 0; i--) {
            if (arr[i - 1] < arr[i]) {
                idx = i - 1;
                break;
            }
        }

        return idx;
    }

    public static int ceilIndex(char[] arr, int dipIdx) {
        int idx = dipIdx;
        for (int i = arr.length - 1; i > dipIdx; i--) {
            if (arr[dipIdx] < arr[i]) {
                idx = i;
                break;
            }
        }
        return idx;
    }

    public static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void reverse(char[] arr, int left, int right) {
        while (left < right) {
            swap(arr, left, right);
            left++;
            right--;
        }
    }

    public static String nextGreaterElement(String str) {
        char[] arr = str.toCharArray();
        int dipIdx = dipIndex(arr);
        if (dipIdx == -1) {
            return "-1";
        }

        int ceilIndx = ceilIndex(arr, dipIdx);
        swap(arr, dipIdx, ceilIndx);
        reverse(arr, dipIdx + 1, arr.length - 1);
        return String.valueOf(arr);
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String num = scn.next();
        String res = nextGreaterElement(num);

        System.out.println(res);
        scn.close();
    }
}
