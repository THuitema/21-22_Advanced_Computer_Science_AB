package com.thuitema.Labs;
/*****************************************************************************************************************
 NAME: Thomas Huitema
 PERIOD: 6
 DUE DATE: 1/17/2022

 PURPOSE: Build a Binary Search Tree and display it as a sideways tree

 WHAT I LEARNED:
    In a BST, smaller/equal values are on the left while greater values are on the right
    You use Binary Search to search within a BST (which should be obvious since it's in the name Binary Search Tree)

 HOW I FEEL ABOUT THIS LAB:
    I feel very competent in understanding binary search trees and writing methods to traverse, insert, and search.
    My favorite method to write was display() because it required a great deal of thought and planning before I could understand and write it.

 CREDITS (BE SPECIFIC: FRIENDS, PEERS, ONLINE WEBSITE):
    Evan Ho suggested that I use reverse inorder traversal in the display method
 ****************************************************************************************************************/

import java.util.Scanner;

/****************************************************************
 Practice with a Binary Search Tree. Uses TreeNode.
 Prompt the user for an input string.  Build a Binary Search Tree
 using Comparables.  Display it as a sideways tree (take the code
 from the Tree Lab).  Prompt the user for a target and search the tree
 for it.  Display the tree's minimum and maximum values.  Print
 the data in order from smallest to largest.
 *****************************************************************/

public class Pd6ThomasHuitemaBinarySearchTree_SHELL {
    public static void main(String[] args) {
        // your code goes here

        BinarySearchTree<String> t = new BinarySearchTree<>();

        // build the tree
        Scanner sc = new Scanner(System.in);
        System.out.print("Input string: ");
        String s = sc.next();               // "MAENIRAC";  "AMERICAN";   "AACEIMNR"
        for (int k = 0; k < s.length(); k++)
            t.insert("" + s.charAt(k));

        t.delete("M");

//        // get the root of the newly created BinarySearchTree
//        TreeNode<String> root = t.getRoot();
//
//        // call the display sideway method
//        t.display();
//
//        // test the find method
//        sc = new Scanner(System.in);
//        System.out.print("Input target: ");
//        String target = sc.next(); // "I"
//
//        boolean itemFound = t.find(target);
//        if (itemFound)
//            System.out.println("found: " + target);
//        else
//            System.out.println(target + " not found.");
//
//        // test the min and max methods
//        System.out.println("Min = " + t.min());
//        System.out.println("Max = " + t.max());
//
//        // inorder traversal display the values from smallest to largest
//        System.out.println("\nIn Order: ");
//        t.smallToLarge();
    }
}


class BinarySearchTree<E extends Comparable<E>> { // E extends Comparable to give E compareTo()
    private TreeNode<E> root;

    public TreeNode<E> getRoot() {
        return root;
    }

    /****************************************************************
     Recursive algorithm to build a BST:  if the node is null, insert the
     new node.  Else, if the item is less, set the left node and recur to
     the left.  Else, if the item is greater, set the right node and recur
     to the right.
     *****************************************************************/

    public TreeNode<E> insert(E s) {
        root = insertHelper(root, s);
        return root;
    }

    public void delete(E target) {
        deleteHelper(root, target);
    }
    private TreeNode<E> deleteHelper(TreeNode<E> root, E target) {
        if(root == null) {
            return null;
        }
        else if (target.compareTo(root.getValue()) < 0) {
            root.setLeft(deleteHelper(root.getLeft(), target));
        }
        else if (target.compareTo(root.getValue()) > 0) {
            root.setRight(deleteHelper(root.getRight(), target));
        }
        else {
            if(root.getLeft() == null) {
                return root.getRight();
            }
            else if(root.getRight() == null) {
                return root.getLeft();
            }
            else {
                root.setValue(maxHelper(root.getLeft()));
                root.setLeft(deleteHelper(root.getLeft(), target));
                return root;
            }
        }
        return root;

    }
    // Recursive helper method that works similar to binary search to find the location to insert the given node
    private TreeNode<E> insertHelper(TreeNode<E> t, E s) {
        if (t == null) {
            return new TreeNode<>(s);
        }
        if (s.compareTo(t.getValue()) <= 0) { // target is less than or equal to current node --> go left
            t.setLeft(insertHelper(t.getLeft(), s));
        } else { // target is greater than current node --> go right
            t.setRight(insertHelper(t.getRight(), s));
        }
        return t; // returns tree with inserted node
    } // insert

    // display root sideways (root on left)
    public void display() {
        displayHelper(root, 0);
    }

    // display helper method
    private void displayHelper(TreeNode<E> t, int level) {
        // traverses through tree reverse inorder (RCL)
        if (t != null) {
            displayHelper(t.getRight(), level + 1); // right child

            // print current node based on its level (higher level --> more tabs --> farther right)
            // for loop prints a tab for the node's level number
            for (int i = 0; i < level; i++) {
                System.out.print("\t\t");
            }
            System.out.println(t.getValue()); // prints current node

            displayHelper(t.getLeft(), level + 1); // left child
        }
    }

    /***************************************************************
     Iterative algorithm:  create a temporary pointer p at the root.
     While p is not null, if the p's value equals the target, return true.
     If the target is less than the p's value, go left, otherwise go right.
     If the target is not found, return false.

     Find the target. Recursive algorithm:  If the tree is empty,
     return false.  If the target is less than the current node
     value, return the left subtree.  If the target is greater, return
     the right subtree.  Otherwise, return true.
     . ****************************************************************/
    public boolean find(E x) {
        return find(root, x);
    }

    // Iterative helper method to perform binary search on the tree
    private boolean find(TreeNode<E> t, E x) {
        TreeNode<E> p = root;
        while (p != null) {
            if (x.equals(p.getValue())) { // node equals target
                return true;
            }
            if (x.compareTo(p.getValue()) < 0) { // target is less than node --> go left
                p = p.getLeft();
            } else { // target is greater than node --> go right
                p = p.getRight();
            }
        }
        return false; // target doesn't exist in tree
    }

    /***************************************************************
     starting at the root, return the min value in the BST.
     Use iteration.   Hint:  look at several BSTs. Where are
     the min values always located?
     ***************************************************************/
    // Recursively traverses to the tree's left-most node and returns its value
    public E min() {
        TreeNode<E> p = root;
        while (p.getLeft() != null) { // no left child node = left-most node
            p = p.getLeft();
        }
        return p.getValue();
    }

    /*****************************************************************
     starting at the root, return the max value in the BST.
     Use recursion!
     *****************************************************************/
    public E max() {
        return maxHelper(root);
    }

    // Recursive helper method to get right-most node value
    private E maxHelper(TreeNode<E> t) {
        if (t.getRight() == null) { // no right child node = right-most node
            return t.getValue();
        } else {
            return maxHelper(t.getRight());
        }
    }

    public void smallToLarge() { // need a helper method as well?
        inorder(root);
    }

    // Recursive helper method to perform inorder traversal
    private void inorder(TreeNode<E> t) {
        if (t != null) {
            inorder(t.getLeft()); // left
            System.out.print(t.getValue() + " "); // center
            inorder(t.getRight()); // right
        }
    }
}  // BinarySearchTree


/* TreeNode class for the AP Exams */
class TreeNode<E> {
    private E value;
    private TreeNode left, right;

    public TreeNode(E initValue) {
        value = initValue;
        left = null;
        right = null;
    }

    public TreeNode(E initValue, TreeNode<E> initLeft, TreeNode<E> initRight) {
        value = initValue;
        left = initLeft;
        right = initRight;
    }

    public E getValue() {
        return value;
    }

    public TreeNode<E> getLeft() {
        return left;
    }

    public TreeNode<E> getRight() {
        return right;
    }

    public void setValue(E theNewValue) {
        value = theNewValue;
    }

    public void setLeft(TreeNode<E> theNewLeft) {
        left = theNewLeft;
    }

    public void setRight(TreeNode<E> theNewRight) {
        right = theNewRight;
    }
}

/*
Input string: AMERICAN
				R
						N
		M
						I
				E
						C
A
		A
Input target: I
found: I
Min = A
Max = R

In Order:
A A C E I M N R

--------------

Input string: AACEIMNR
												R
										N
								M
						I
				E
		C
A
		A
Input target: Z
Z not found.
Min = A
Max = R

In Order:
A A C E I M N R

--------------

Input string: MAENIRAC
				R
		N
M
						I
				E
						C
		A
				A
Input target: C
found: C
Min = A
Max = R

In Order:
A A C E I M N R
 */
