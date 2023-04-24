package com.thuitema.Trees;

import com.sun.source.tree.Tree;

/**
 * Common Binary Tree Methods to review for quiz: Traverse (preorder, inorder, postorder), Search, Min/Max, Insert, Delete
 * Assume tree is a BST
 */

public class Operations <E extends Comparable<E>> {
    public static void main(String[] args) {
        Operations<Integer> ops = new Operations<>();
        TreeNode<Integer> root = new TreeNode<Integer>(10);
        root = ops.insert(root, 6);
        root = ops.insert(root, 11);
        root = ops.insert(root, 7);
        root = ops.insert(root, 4);
        root = ops.insert(root, 5);
        root = ops.insert(root, 6);
        root = ops.insert(root, 13);

//        System.out.println("Min: " + ops.min(root));
//        System.out.println("Max: " + ops.max(root));
        System.out.println(ops.search(root, 14));
    }

    // preorder traverse (CLR)
    public void preorderTraverse(TreeNode<E> root) {
        if(root != null) {
            System.out.println(root.getValue());
            preorderTraverse(root.getLeft());
            preorderTraverse(root.getRight());
        }
    }

    // inorder traverse (LCR)
    public void inorderTraverse(TreeNode<E> root) {
        if(root != null) {
            inorderTraverse(root.getLeft());
            System.out.println(root.getValue());
            inorderTraverse(root.getRight());
        }
    }

    // postorder traverse (LRC)
    public void postorderTraverse(TreeNode<E> root) {
        if(root != null) {
            postorderTraverse(root.getLeft());
            postorderTraverse(root.getRight());
            System.out.println(root.getValue());
        }
    }

    // search for value in BST
    public boolean search(TreeNode<E> root, E target) {
        TreeNode<E> p = root;
        while(p != null) {
            if(target.equals(p.getValue())) {
                return true;
            }
            else if(target.compareTo(p.getValue()) < 0) { // target is less than value --> go left
                p = p.getLeft();
            }
            else { // target is greater than value --> go right
                p = p.getRight();
            }
        }
        return false;
    }

    // find min - node farthest to the left
    public E min(TreeNode<E> root) {
        if(root.getLeft() == null) {
            return root.getValue();
        }
        else {
            return min(root.getLeft());
        }
    }

    // find max - node farthest to the right
    public E max(TreeNode<E> root) {
        if(root.getRight() == null) {
            return root.getValue();
        }
        else {
            return max(root.getRight());
        }
    }

    public TreeNode<E> insert(TreeNode<E> root, E value) {
        TreeNode<E> p = root;
        return insertHelper(p, value);
    }

    private TreeNode<E> insertHelper(TreeNode<E> p, E value) {
        if(p == null) {
            return new TreeNode<E>(value);
        }
        if(value.compareTo(p.getValue()) <= 0) {
            p.setLeft(insertHelper(p.getLeft(), value));
        }
        else {
            p.setRight(insertHelper(p.getRight(), value));
        }
        return p;
    }

//    public TreeNode<E> delete(TreeNode<E> root, E value) {
//
//    }
}
