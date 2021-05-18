public class L8_RecursionTrees_QueenSet {

    // 1D_Queen_Set ========================================================

    // tboxes = total boxes, tnq = total no of queens, qpsf = queen placed so far
    // bn = box number
    public static int queenCombination(int tboxes, int tnq, int qpsf, int bn, String ans) {
        if (qpsf == tnq) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int i = bn; i < tboxes; i++) {
            count += queenCombination(tboxes, tnq, qpsf + 1, i + 1, ans + "b" + i + "q" + qpsf + " ");
        }
        return count;
    }

    public static int queenPermutation(boolean[] tboxes, int tnq, int qpsf, int bn, String ans) {
        if (qpsf == tnq) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int i = bn; i < tboxes.length; i++) {
            if (!tboxes[i]) {
                tboxes[i] = true;
                count += queenPermutation(tboxes, tnq, qpsf + 1, 0, ans + "b" + i + "q" + qpsf + " ");
                tboxes[i] = false;
            }
        }
        return count;
    }

    // 2D_Queen_Set ===============================================================

    public static int queenCombination2D(boolean[][] tboxes, int tnq, int bn, String ans) {
        if (tnq == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        int n = tboxes.length, m = tboxes[0].length;

        for (int i = bn; i < n * m; i++) {
            int r = i / m;
            int c = i % m;

            count += queenCombination2D(tboxes, tnq - 1, i + 1, ans + "(" + r + ", " + c + ") ");
        }
        return count;
    }

    public static int queenPermutation2D(boolean[][] tboxes, int tnq, int bn, String ans) {
        if (tnq == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        int n = tboxes.length, m = tboxes[0].length;

        for (int i = bn; i < n * m; i++) {
            int r = i / m;
            int c = i % m;

            if (!tboxes[r][c]) {
                tboxes[r][c] = true;
                count += queenPermutation2D(tboxes, tnq - 1, 0, ans + "(" + r + ", " + c + ") ");
                tboxes[r][c] = false;
            }
        }
        return count;
    }

    public static void queenSet() {
        // boolean[] boxes = new boolean[6];
        // System.out.println(queenCombination(6, 4, 0, 0, ""));
        // System.out.println(queenPermutation(boxes, 4, 0, 0, ""));

        boolean[][] boxes = new boolean[4][4];
        // System.out.println(queenPermutation2D(boxes, 4, 0, ""));
        System.out.println(queenCombination2D(boxes, 4, 0, ""));
    }

    public static void main(String[] args) {
        queenSet();
    }
}
