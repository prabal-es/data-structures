package ds.trees_graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 4.2 Minimal Tree: Given a sorted (increasing order) array with unique integer elements, write an algorithm
 * to create a binary search tree with minimal height.
 * Hints: #19, #73, #176
 *
 * #19  4.2 TA minimal binary tree has about the same number of nodes on the left of each node as
 *           on the right. Let's focus on just the root for now. How would you ensure that about the
 *           same number of nodes are on the left of the root as on the right?
 * #73  4.2 You could implement this by finding the "ideal" next element to add and repeatedly
 *          calling insertValue. This will be a bit inefficient, as you would have to repeatedly
 *          traverse the tree. Try recursion instead. Can you divide this problem into subproblems?
 * #176 4.2 Imagine we had a createMinimal Tree method that returns a minimal tree for a
 *          given array (but for some strange reason doesn't operate on the root of the tree). Could
 *          you use this to operate on the root of the tree? Could you write the base case for the
 *          function? Great! Then that's basically the entire function.
 */
public class MinimalTree {

    public Tree.Node<Integer> createBinaryTree(int[] sortedNums, int start, int end){
        if(start > end){
            return null;
        }
        Tree.Node<Integer> node = new Tree.Node<>();
        int mid = (start+end)/2;
        System.out.println(mid);
        node.setName(sortedNums[mid]);
        node.setLeft(createBinaryTree(sortedNums, start, mid-1) );
        node.setRight(createBinaryTree(sortedNums, mid+1, end) );
        return node;
    }

    public static void main(String... args){
        MinimalTree minimalTree = new MinimalTree();
        Tree.Node<Integer> node = minimalTree.createBinaryTree(new int[]{0,1,2,3,4,5,6}, 0 ,6);
        System.out.println(node);
    }

}
