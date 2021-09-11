import java.util.Scanner;

public class Q4_MajorityElement_1 {

    public static int majority(int[] arr) {
        int val = arr[0];
        int count = 1;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == val) {
                count++;
            } else {
                if (count > 0) {
                    count--;
                } else {
                    val = arr[i];
                    count = 1;
                }
            }
        }

        return val;
    }

    public static void printMajorityElement(int[] arr) {
        int candidate = majority(arr);
        int count = 0;
        for (int val : arr) {
            if (val == candidate) {
                count++;
            }
        }
        if (count > arr.length / 2) {
            System.out.println(candidate);
        } else {
            System.out.println("No Majority Element exist");
        }
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = scn.nextInt();
        }

        printMajorityElement(arr);
        scn.close();
    }
}
