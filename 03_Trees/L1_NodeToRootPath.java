import java.util.*;

public class L1_NodeToRootPath {
    public static Scanner scn = new Scanner(System.in);

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // 1st Method ==============================================
    // public static boolean NodeToRootPath(TreeNode root, int data, ArrayList<TreeNode> ans) {
    //     if (root == null)
    //         return false;

    //     if (root.val == data) {
    //         ans.add(root);
    //         return true;
    //     }

    //     boolean res = NodeToRootPath(root.left, data, ans) || NodeToRootPath(root.right, data, ans);

    //     if (res)
    //         ans.add(root);

    //     return res;

    // }

    // public static ArrayList<TreeNode> nodeToRootPath(TreeNode root, int data) {
    //     ArrayList<TreeNode> ans = new ArrayList<>();
    //     NodeToRootPath(root, data, ans);
    //     return ans;
    // }

    // 2nd Method ==============================================================

    public static ArrayList<TreeNode> nodeToRootPath(TreeNode root, int data) {
    if (root == null) {
    return new ArrayList<>();
    }

    if (root.val == data) {
    ArrayList<TreeNode> base = new ArrayList<>();
    base.add(root);
    return base;
    }

    ArrayList<TreeNode> left = nodeToRootPath(root.left, data);
    if (left.size() != 0) {
    left.add(root);
    return left;
    }

    ArrayList<TreeNode> right = nodeToRootPath(root.right, data);
    if (right.size() != 0) {
    right.add(root);
    return right;
    }

    return new ArrayList<>();

    }

    // input_section=================================================

    public static TreeNode createTree(int[] arr, int[] IDX) {
        if (IDX[0] > arr.length || arr[IDX[0]] == -1) {
            IDX[0]++;
            return null;
        }
        TreeNode Treenode = new TreeNode(arr[IDX[0]++]);
        Treenode.left = createTree(arr, IDX);
        Treenode.right = createTree(arr, IDX);

        return Treenode;
    }

    public static void solve() {
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = scn.nextInt();

        int[] IDX = new int[1];
        TreeNode root = createTree(arr, IDX);

        int data = scn.nextInt();
        ArrayList<TreeNode> ans = nodeToRootPath(root, data);
        if (ans.size() == 0)
            System.out.println();
        for (TreeNode node : ans)
            System.out.print(node.val + " ");

    }

    public static void main(String[] args) {
        solve();
    }
}