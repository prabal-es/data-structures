package ds.trees_graphs;


import java.util.*;

/**
 * 4.3 List of Depths: Given a binary tree, design an algorithm which creates a linked list of all the nodes
 * at each depth (e.g., if you have a tree with depth 0, you'll have 0 linked lists).
 * Hints: #107, #123, #135
 *
 * #107 4.3 Try modifying a graph search algorithm to track the depth from the root.
 * #123 4.3 A hash table or array that maps from level number to nodes at that level might also be
 *          useful.
 * #135 4.3 You should be able to come up with an algorithm involving both depth-first search and
 *          breadth-first search.
 * */
public class ListOfDepths {

    public List<List<Tree.Node<Integer>>> getListOfDepthsBFS(Tree.Node<Integer> root){
        List<List<Tree.Node<Integer>>> result = new ArrayList<>();
        List<Tree.Node<Integer>> currentLinkedList = new ArrayList<>();

        if (null != root){
            currentLinkedList.add(root);
        }
        while (!currentLinkedList.isEmpty()){
            List<Tree.Node<Integer>> parents = currentLinkedList;
            result.add(currentLinkedList);
            currentLinkedList = new ArrayList<>();
            for(Tree.Node<Integer> parent : parents){
                if(null != parent.getLeft()){
                    currentLinkedList.add(parent.getLeft());
                }
                if(null != parent.getRight()){
                    currentLinkedList.add(parent.getRight());
                }
            }
        }

        return result;
    }

    public List<List<Tree.Node<Integer>>> getListOfDepthsDFS(Tree.Node<Integer> root){
        List<List<Tree.Node<Integer>>> result = new ArrayList<>();
        getListOdDepthDFS(root, result,0);
        return result;
    }
    private void getListOdDepthDFS(Tree.Node<Integer> root, List<List<Tree.Node<Integer>>> result, int index){
        if(null == root){
            return;
        }
        List<Tree.Node<Integer>> currentStageNodes;
        if(result.size() == index){
            currentStageNodes = new ArrayList<>();
            result.add(currentStageNodes);
        } else {
            currentStageNodes = result.get(index);
        }
        currentStageNodes.add(root);
        getListOdDepthDFS(root.getLeft(), result, index +1);
        getListOdDepthDFS(root.getRight(), result, index +1);
    }
    public static void main(String... args){
        ListOfDepths listOfDepths = new ListOfDepths();
        Tree.Node<Integer> root = new Tree.Node<>();
        root.setName(0);
        Tree.Node<Integer> l1 = new Tree.Node<>();
        l1.setName(1);
        Tree.Node<Integer> r1 = new Tree.Node<>();
        r1.setName(2);
        root.setLeft(l1);
        root.setRight(r1);
        Tree.Node<Integer> l2 = new Tree.Node<>();
        l2.setName(3);
        Tree.Node<Integer> r2 = new Tree.Node<>();
        r2.setName(4);
        l1.setLeft(l2);
        l1.setRight(r2);

        Tree.Node<Integer> l3 = new Tree.Node<>();
        l3.setName(5);
        Tree.Node<Integer> r3 = new Tree.Node<>();
        r3.setName(6);
        r1.setLeft(l3);
        r1.setRight(r3);

        //List<List<Tree.Node<Integer>>> result = listOfDepths.getListOfDepthsBFS(root);
        //System.out.println(result);

        List<List<Tree.Node<Integer>>> result = listOfDepths.getListOfDepthsDFS(root);
        System.out.println(result);
    }

}
