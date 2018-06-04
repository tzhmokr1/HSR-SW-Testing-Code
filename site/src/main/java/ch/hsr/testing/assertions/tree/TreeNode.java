package ch.hsr.testing.assertions.tree;

public class TreeNode {

    private TreeNode left = null;
    private TreeNode right = null;
    private int value;

    public TreeNode(int value) {
        this.value = value;
    }

    public TreeNode(TreeNode left, TreeNode right, int value) {
        this.left = left;
        this.right = right;
        this.value = value;
    }

    public TreeNode getLeft() {
        return left;
    }

    public TreeNode getRight() {
        return right;
    }

    public int getValue() {
        return value;
    }


    @Override
    public String toString() {
        return "(" + value
                + ", l: " + (left != null ? left.toString() : "null")
                + ", r: " + (right != null ? right.toString() : "null") + ")";
    }
}
