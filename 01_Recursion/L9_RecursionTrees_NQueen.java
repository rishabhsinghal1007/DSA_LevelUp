public class L9_RecursionTrees_NQueen {

    // Variation-1 =================================================================
    public static boolean isSafeToPlaceQueen(boolean[][] boxes, int r, int c) {
        // for combination
        // int[][] dir = { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 } };

        // for permutation
        int[][] dir = { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 } };

        int n = boxes.length, m = boxes[0].length;

        for (int d = 0; d < dir.length; d++) {
            for (int rad = 1; rad < n; rad++) {
                int x = r + rad * dir[d][0];
                int y = c + rad * dir[d][1];

                if (x >= 0 && y >= 0 && x < n && y < m) {
                    if (boxes[x][y])
                        return false;
                } else
                    break;
            }
        }

        return true;
    }

    public static int nQueen_Combination01(boolean[][] boxes, int tnq, int idx, String ans) {
        if (tnq == 0) {
            System.out.println(ans);
            return 1;
        }
        int n = boxes.length, m = boxes[0].length;
        int count = 0;
        for (int i = idx; i < n * m; i++) {
            int r = i / m;
            int c = i % m;

            if (isSafeToPlaceQueen(boxes, r, c)) {
                boxes[r][c] = true;
                count += nQueen_Combination01(boxes, tnq - 1, i + 1, ans + "(" + r + ", " + c + ") ");
                boxes[r][c] = false;
            }
        }
        return count;
    }

    public static int nQueen_Permutation01(boolean[][] boxes, int tnq, int idx, String ans) {
        if (tnq == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        int n = boxes.length, m = boxes[0].length;
        for (int i = idx; i < n * m; i++) {
            int r = i / m;
            int c = i % m;

            if (!boxes[r][c] && isSafeToPlaceQueen(boxes, r, c)) {
                boxes[r][c] = true;
                count += nQueen_Permutation01(boxes, tnq - 1, 0, ans + "(" + r + ", " + c + ") ");
                boxes[r][c] = false;
            }
        }
        return count;
    }

    public static int nQueen_Combination_SubSeq(boolean[][] boxes, int tnq, int idx, String ans) {
        int n = boxes.length, m = boxes[0].length;

        if (tnq == 0 || idx >= n * m) {
            if (tnq == 0) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }
        int count = 0;
        int r = idx / m;
        int c = idx % m;
        if (isSafeToPlaceQueen(boxes, r, c)) {
            boxes[r][c] = true;
            count += nQueen_Combination_SubSeq(boxes, tnq - 1, idx + 1, ans + "(" + r + ", " + c + ") ");
            boxes[r][c] = false;
        }

        count += nQueen_Combination_SubSeq(boxes, tnq, idx + 1, ans);
        return count;
    }

    // Variation-2 =================================================================

    static boolean[] rows;
    static boolean[] cols;
    static boolean[] diag;
    static boolean[] adiag;

    public static int nQueen_Combination02(int n, int m, int tnq, int idx, String ans) {
        if (tnq == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int i = idx; i < n * m; i++) {
            int r = i / m;
            int c = i % m;
            if (!rows[r] && !cols[c] && !diag[r + c] && !adiag[r - c + m - 1]) {
                rows[r] = cols[c] = diag[r + c] = adiag[r - c + m - 1] = true;
                count += nQueen_Combination02(n, m, tnq - 1, i + 1, ans + "(" + r + ", " + c + ") ");
                rows[r] = cols[c] = diag[r + c] = adiag[r - c + m - 1] = false;
            }
        }
        return count;
    }

    public static int nQueen_Permutation02(int n, int m, int tnq, int idx, String ans) {
        if (tnq == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int i = idx; i < n * m; i++) {
            int r = i / m;
            int c = i % m;
            if (!rows[r] && !cols[c] && !diag[r + c] && !adiag[r - c + m - 1]) {
                rows[r] = cols[c] = diag[r + c] = adiag[r - c + m - 1] = true;
                count += nQueen_Permutation02(n, m, tnq - 1, 0, ans + "(" + r + ", " + c + ") ");
                rows[r] = cols[c] = diag[r + c] = adiag[r - c + m - 1] = false;
            }
        }

        return count;
    }

    public static void NQueen() {
        int n = 4, m = 4, q = 4;
        boolean[][] boxes = new boolean[n][m];
        // System.out.println(nQueen_Combination01(boxes, q, 0, ""));
        // System.out.println(nQueen_Permutation01(boxes, q, 0, ""));
        // System.out.println(nQueen_Combination_SubSeq(boxes, q, 0, ""));

        rows = new boolean[n];
        cols = new boolean[m];
        diag = new boolean[n + m - 1];
        adiag = new boolean[n + m - 1];

        // System.out.println(nQueen_Combination02(n, m, q, 0, ""));
        System.out.println(nQueen_Permutation02(n, m, q, 0, ""));
    }

    public static void main(String[] args) {
        NQueen();
    }
}
