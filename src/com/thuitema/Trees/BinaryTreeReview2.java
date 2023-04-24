package com.thuitema.Trees;


public class BinaryTreeReview2 {
    public static void main(String[] args) {
        TreeNode<Integer> left2 = new TreeNode<Integer>(5, null, null);

        TreeNode<Integer> left1 = new TreeNode<Integer>(1, left2, null);
        TreeNode<Integer> right1 = new TreeNode<Integer>(3, null, null);

        TreeNode<Integer> root = new TreeNode<Integer>(2, left1, right1);

        System.out.println(isLeaf(left2)); // good
        System.out.println(sumTree(root)); // good
        TreeNode<Integer> clone = copy(root); // good
        TreeNode<Integer> mirror = mirrorImage(root); // good
    }

    public static boolean isLeaf(TreeNode node) {
        return node.getLeft() == null && node.getRight() == null;
    }

    public static int sumTree(TreeNode<Integer> root) {
        if(root == null) {
            return 0;
        }

        else {
            return root.getValue() + sumTree(root.getLeft()) + sumTree(root.getRight());
        }
    }

    public static TreeNode copy(TreeNode root) {
        if(root == null) {
            return null;
        }

        TreeNode nodeCopy = new TreeNode(root.getValue(), copy(root.getLeft()), copy(root.getRight()));
        return nodeCopy;
    }

    public static TreeNode mirrorImage(TreeNode root) {
        if(root == null) {
            return null;
        }

        TreeNode node = new TreeNode(root.getValue(), mirrorImage(root.getRight()), mirrorImage(root.getLeft()));
        return node;
    }
}
