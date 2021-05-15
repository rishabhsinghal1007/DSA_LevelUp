// https://practice.geeksforgeeks.org/problems/special-matrix4201/1#F

import java.io.*;
import java.util.*;
public class L4_SpecialMatrix_GFG {

    public static int dfs(int sr, int sc, int er, int ec, boolean[][] block, int[][] dp) {
        int mod = (int) 1e9 + 7;
        if (sr == er && sc == ec) {
            return dp[sr][sc] = 1;
        }

        if (dp[sr][sc] != -1) {
            return dp[sr][sc];
        }

        int count = 0;
        if (sc + 1 <= ec && !block[sr][sc + 1])
            count = (count % mod + dfs(sr, sc + 1, er, ec, block, dp) % mod) % mod;

        if (sr + 1 <= er && !block[sr + 1][sc])
            count = (count % mod + dfs(sr + 1, sc, er, ec, block, dp) % mod) % mod;

        return dp[sr][sc] = count;
    }

    public static int FindWays(int n, int m, int[][] blocked_Cells) {
        n++;
        m++;
        boolean[][] block = new boolean[n][m];

        for (int[] b: blocked_Cells)
            block[b[0]][b[1]] = true;

        if (block[1][1] || block[n - 1][m - 1])
            return 0;

        int[][] dp = new int[n][m];
        for (int[] d: dp)
            Arrays.fill(d, -1);

        return dfs(1, 1, n - 1, m - 1, block, dp);
    }

    public static void main(String[] args){
        int n = 3, m = 3;
        int[][] blocked_cells = {{1,2},{3,2}};
        
        System.out.println(FindWays(n, m, blocked_cells));
    }
}
