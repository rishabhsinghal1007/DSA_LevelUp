import java.util.Random;

public class L15_QuickSort_Array {

    public static Random rand = new Random();

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int segregate(int[] arr, int pivotIdx, int sp, int ep) {
        swap(arr, pivotIdx, ep);

        int itr = sp;
        int p = sp - 1;

        while (itr <= ep) {
            if (arr[itr] <= arr[ep]) {
                swap(arr, ++p, itr);
            }
            itr++;
        }
        return p;
    }

    public static void quickSort(int[] arr, int si, int ei) {
        if (si > ei)
            return;

        // pivotIdx = (si + ei) / 2 || si || ei || rand.nextInt(ei - si + 1) + si ;
        // int pivotIdx = (si + ei) / 2;
        int pivotIdx = rand.nextInt(ei - si + 1) + si;
        pivotIdx = segregate(arr, pivotIdx, si, ei);

        boolean flag = true;
        for (int i = si + 1; i <= ei; i++)
            if (arr[i - 1] > arr[i]) {
                flag = false;
                break;
            }

        if (flag)
            return;

        quickSort(arr, si, pivotIdx - 1);
        quickSort(arr, pivotIdx + 1, ei);

    }

    public static void main(String[] args) {

        int[] arr = { -12, 2, 7, 3, 34, 0, -50, 23, 4, 4 };
        quickSort(arr, 0, arr.length - 1);

        for (int ele : arr) {
            System.out.print(ele + " ");
        }
    }
}
