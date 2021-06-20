public class l001 {

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static int size(TreeNode root) {
        return root == null ? 0 : size(root.left) + size(root.right) + 1;
    }

    public static int height(TreeNode root) {
        return root == null ? -1 : Math.max(height(root.left), height(root.right)) + 1;
    }

    public static int maximum(TreeNode root) {
        return root == null ? -(int) 1e9 : Math.max(Math.max(maximum(root.left), maximum(root.right)), root.val);
    }

    public static boolean find(TreeNode root, int data) {
        if (root == null) {
            return false;
        }

        if (root.val == data) {
            return true;
        }

        return find(root.left, data) || find(root.right, data);
    }
}