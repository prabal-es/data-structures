package ds.linked_lists.book;

import java.util.HashMap;
import java.util.Map;

/*
* 2.3 Delete Middle Node: Implement an algorithm to delete a node in the middle (i.e., any node but
* the first and last node, not necessarily the exact middle) of a singly linked list, given only access to
* that node.
* EXAMPLE
* Input: the node c from the linked list a - >b- >c - >d - >e- >f
* Result: nothing is returned, but the new linked list looks like a - >b- >d - >e- >f
* Hints: #72

* #73  2.3 Picture the list 1- > 5 - >9 - > 12. Removing 9 would make it look like 1- > 5 - > 12. You only
*          have access to the 9 node. Can you make it look like the correct answer?
* */
public class DeleteMidNode {


    boolean deleteNode(LinkedList<Integer> n) {
        if (n == null || n.next == null) {
            return false; // Failure
        }
        LinkedList<Integer> next = n.next;
        n.data = next.data;
        n.next = next.next;
        return true;
    }
    public static void main(String... args) {
        DeleteMidNode deleteMidNode = new DeleteMidNode();
        LinkedList<Integer> linkedList = new LinkedList<>(3);
        linkedList.appendToTail(3);
        linkedList.appendToTail(2);
        linkedList.appendToTail(5);
        linkedList.appendToTail(4);
        linkedList.appendToTail(6);
        linkedList.appendToTail(3);
        System.out.println(linkedList);
        deleteMidNode.deleteNode(linkedList.next.next);//2
        System.out.println(linkedList);
    }

    /*
    * 2.3 Delete Middle Node: Implement an algorithm to delete a node in the middle (Le., any node but
    * the first and last node, not necessarily the exact middle) of a singly linked list, given only access to
    * that node.
    * EXAMPLE
    * Input: the node c from the linked list a - >b- >c - >d - >e- >f
    * Result: nothing is returned, but the new linked list looks like a->b->d->e->f
    *
    * SOLUTION
    * In this problem, you are not given access to the head of the linked list. You only have access to that node.
    * The solution is simply to copy the data from the next node over to the current node, and then to delete the
    * next node.
    * The code below implements this algorithm.
    * 1 boolean deleteNode(LinkedListNode n) {
    * 2 if (n == null I I n.next == nUll) {
    * 3 ret urn false; II Failure
    * 4 }
    * 5 LinkedListNode next = n.next;
    * 6 n.data = next.data;
    * 7 n.next = next.next;
    * 8 return true;
    * 9 }
    * Note that this problem cannot be solved if the node to be deleted is the last node in the linked list. That's
    * okay- your interviewer wants you to point that out, and to discuss how to handle this case. You could, for
    * example, consider marking the node as dummy.
    */
}
